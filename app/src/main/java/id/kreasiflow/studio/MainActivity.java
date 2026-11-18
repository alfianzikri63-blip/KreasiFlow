package id.kreasiflow.studio;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.webkit.WebViewAssetLoader;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * KreasiFlow Split — 2 native WebView panes.
 * - Panel atas memuat aplikasi offline dari asset tepercaya, tidak pernah memuat situs asing.
 * - Panel bawah adalah peramban terbatas tanpa JavaScript bridge.
 * - Google OAuth TIDAK dipaksakan ke WebView; pengguna diarahkan ke browser sistem.
 */
public final class MainActivity extends Activity {
    private static final String LOCAL = "https://appassets.androidplatform.net/assets/index.html";
    private static final String HOME = "https://appassets.androidplatform.net/assets/browser-home.html";
    private static final String STUDIO = "https://aistudio.google.com/generate-speech";
    private static final String FLOW = "https://flow.google.com/";
    private static final String GEMINI = "https://gemini.google.com/app";
    private static final int PICK_FILE = 731;
    private static final int BG = Color.rgb(6, 20, 14);
    private static final int PANEL = Color.rgb(11, 37, 25);
    private static final int BORDER = Color.rgb(40, 87, 60);
    private static final int GREEN = Color.rgb(121, 235, 176);
    private static final int WHITE = Color.rgb(228, 249, 235);

    private LinearLayout root;
    private FrameLayout topFrame;
    private FrameLayout bottomFrame;
    private WebView topWeb;
    private WebView bottomWeb;
    private EditText address;
    private TextView ratioLabel;
    private float ratio = .5f;
    private float startDragY;
    private float dragInitialRatio;
    private float dragAvailableHeight;
    private String lastAiUrl = STUDIO;
    private ValueCallback<Uri[]> uploadCallback;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final Map<String, SaveSession> transfers = new HashMap<>();
    private final AtomicLong nextTransfer = new AtomicLong(1);

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        buildLayout();
        configureWebViews();
        if (savedInstanceState != null) {
            ratio = savedInstanceState.getFloat("ratio", .5f);
            lastAiUrl = savedInstanceState.getString("lastAiUrl", STUDIO);
            if (topWeb.restoreState(savedInstanceState.getBundle("top")) == null) topWeb.loadUrl(LOCAL);
            if (bottomWeb.restoreState(savedInstanceState.getBundle("bottom")) == null) bottomWeb.loadUrl(HOME);
        } else {
            topWeb.loadUrl(LOCAL);
            bottomWeb.loadUrl(HOME);
        }
        setRatio(ratio);
    }

    private void buildLayout() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BG);
        setContentView(root);

        LinearLayout topBar = bar(44);
        TextView title = label("✦  KREASIFLOW", 15, GREEN, true);
        topBar.addView(title, new LinearLayout.LayoutParams(0, -1, 1));
        topBar.addView(button("30:70", () -> setRatio(.3f)));
        topBar.addView(button("50:50", () -> setRatio(.5f)));
        topBar.addView(button("70:30", () -> setRatio(.7f)));
        root.addView(topBar);

        topFrame = new FrameLayout(this);
        topFrame.setBackgroundColor(BG);
        root.addView(topFrame, new LinearLayout.LayoutParams(-1, 0, .5f));
        topWeb = new WebView(this);
        topFrame.addView(topWeb, new FrameLayout.LayoutParams(-1,-1));

        LinearLayout splitter = bar(23);
        splitter.setGravity(Gravity.CENTER);
        splitter.setBackgroundColor(BORDER);
        ratioLabel = label("━━━━  ↕  GESER PEMBATAS  •  50:50  ━━━━", 10, WHITE, true);
        ratioLabel.setGravity(Gravity.CENTER);
        splitter.addView(ratioLabel, new LinearLayout.LayoutParams(-1,-1));
        splitter.setOnTouchListener((v, event) -> {
            switch(event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    startDragY = event.getRawY();
                    dragInitialRatio = ratio;
                    dragAvailableHeight = topFrame.getHeight() + bottomFrame.getHeight();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (dragAvailableHeight > 0) {
                        setRatio(dragInitialRatio + (event.getRawY() - startDragY) / dragAvailableHeight);
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    v.performClick();return true;
                default:return true;
            }
        });
        root.addView(splitter);

        LinearLayout aiBar = new LinearLayout(this);
        aiBar.setOrientation(LinearLayout.VERTICAL);
        aiBar.setBackgroundColor(PANEL);
        LinearLayout aiChoices = new LinearLayout(this);
        aiChoices.setGravity(Gravity.CENTER_VERTICAL);
        HorizontalScrollView scroll = new HorizontalScrollView(this);
        scroll.setHorizontalScrollBarEnabled(false);
        scroll.setFillViewport(true);
        aiChoices.addView(button("🎙 AI Studio", () -> openAi(STUDIO)));
        aiChoices.addView(button("🎬 Flow", () -> openAi(FLOW)));
        aiChoices.addView(button("✦ Gemini", () -> openAi(GEMINI)));
        aiChoices.addView(button("⌂", () -> { bottomWeb.loadUrl(HOME); address.setText(""); }));
        aiChoices.addView(button("ⓘ Split", this::showSplitHelp));
        scroll.addView(aiChoices);
        aiBar.addView(scroll, new LinearLayout.LayoutParams(-1,dp(38)));
        LinearLayout urlRow = new LinearLayout(this);
        urlRow.setGravity(Gravity.CENTER_VERTICAL);
        urlRow.setPadding(dp(7), dp(3), dp(5), dp(5));
        address = new EditText(this);
        address.setSingleLine(true);
        address.setTextColor(WHITE);
        address.setHintTextColor(Color.rgb(143, 179, 153));
        address.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        address.setHint("https://alamat-ai.com");
        address.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        address.setBackgroundTintList(android.content.res.ColorStateList.valueOf(GREEN));
        urlRow.addView(address, new LinearLayout.LayoutParams(0,dp(39),1));
        urlRow.addView(button("GO", () -> {
            String u = normalize(address.getText().toString());
            openAi(u);
            ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(address.getWindowToken(),0);
        }));
        urlRow.addView(button("↗ Chrome", () -> openExternal(currentAiUrl())));
        aiBar.addView(urlRow, new LinearLayout.LayoutParams(-1, dp(47)));
        root.addView(aiBar);

        bottomFrame = new FrameLayout(this);
        bottomFrame.setBackgroundColor(BG);
        root.addView(bottomFrame, new LinearLayout.LayoutParams(-1,0,.5f));
        bottomWeb = new WebView(this);
        bottomFrame.addView(bottomWeb,new FrameLayout.LayoutParams(-1,-1));
    }

    private LinearLayout bar(int heightDp) {
        LinearLayout bar=new LinearLayout(this);
        bar.setGravity(Gravity.CENTER_VERTICAL);
        bar.setPadding(dp(7),0,dp(6),0);
        bar.setBackgroundColor(PANEL);
        bar.setMinimumHeight(dp(heightDp));
        return bar;
    }
    private TextView label(String s,int sp,int color,boolean bold) {
        TextView t = new TextView(this);
        t.setText(s); t.setTextColor(color);t.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if(bold)t.setTypeface(null,android.graphics.Typeface.BOLD);
        return t;
    }
    private TextView button(String s,Runnable onClick) {
        TextView t=label(s,11,WHITE,true);
        t.setPadding(dp(8),0,dp(8),0);
        t.setGravity(Gravity.CENTER);
        t.setBackgroundColor(PANEL);
        t.setMinHeight(dp(37));
        t.setOnClickListener(v -> onClick.run());
        t.setContentDescription(s);
        return t;
    }
    private int dp(float n){return (int)(n*getResources().getDisplayMetrics().density+.5f);}
    private void setRatio(float next) {
        ratio=Math.min(.75f,Math.max(.25f,next));
        topFrame.setLayoutParams(new LinearLayout.LayoutParams(-1,0,ratio));
        bottomFrame.setLayoutParams(new LinearLayout.LayoutParams(-1,0,1-ratio));
        int upper=Math.round(ratio*100);
        ratioLabel.setText("━━━━  ↕  GESER PEMBATAS  •  "+upper+":"+(100-upper)+"  ━━━━");
    }
    private String normalize(String value) {
        String s=value==null?"":value.trim();
        if(s.length()>0 && !s.contains("://"))s="https://"+s;
        return s;
    }
    private boolean isHttps(String s){
        try{return "https".equalsIgnoreCase(Uri.parse(s).getScheme()) && Uri.parse(s).getHost()!=null;}
        catch(Exception ex){return false;}
    }
    private String currentAiUrl(){
        String u = bottomWeb.getUrl();
        if(u!=null && isHttps(u) && !u.contains("appassets.androidplatform.net"))return u;
        return lastAiUrl;
    }
    private void openAi(String url) {
        if(!isHttps(url)){Toast.makeText(this,"Gunakan alamat HTTPS yang valid",Toast.LENGTH_SHORT).show();return;}
        lastAiUrl=url;
        address.setText(url);
        bottomWeb.loadUrl(url);
    }
    private void openExternal(String url) {
        if(!isHttps(url))return;
        try {
            Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            // Meminta berdampingan bila Android mengizinkan. Tidak memaksa split-screen OS.
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
            startActivity(i);
        }catch(Exception ex){Toast.makeText(this,"Browser tidak tersedia: "+ex.getMessage(),Toast.LENGTH_LONG).show();}
    }
    private void showSplitHelp(){
        new AlertDialog.Builder(this)
            .setTitle("Mode AI: dua pilihan")
            .setMessage("1. Situs yang mengizinkan WebView bisa dibuka langsung di panel bawah. Geser pembatas untuk menyesuaikan ukuran.\n\n2. Login Google sering diblokir pada WebView. Klik ↗ Chrome, buka Recent Apps Android, lalu pilih 'Layar terpisah / Split Screen' untuk menempatkan KreasiFlow dan Chrome berdampingan. Dukungan dan letak menu bergantung HP.")
            .setPositiveButton("Mengerti",(d,w)->{})
            .show();
    }
    private void askChromeForGoogle(){
        new AlertDialog.Builder(this)
            .setTitle("Login Google membutuhkan browser")
            .setMessage("Google tidak mengizinkan proses OAuth di WebView aplikasi. Buka AI Studio / Flow di Chrome agar login akun bekerja. Lalu aktifkan split-screen dari menu aplikasi terbaru Android jika tersedia.")
            .setPositiveButton("Buka Chrome", (d,w)->openExternal(lastAiUrl))
            .setNegativeButton("Tetap di panel",(d,w)->{})
            .show();
    }

    private void configureWebViews() {
        CookieManager.getInstance().setAcceptCookie(true);
        for(WebView web:new WebView[]{topWeb,bottomWeb}) {
            WebSettings s=web.getSettings();
            s.setJavaScriptEnabled(true);
            s.setDomStorageEnabled(true);
            s.setAllowFileAccess(false);
            s.setAllowContentAccess(true);
            s.setAllowFileAccessFromFileURLs(false);
            s.setAllowUniversalAccessFromFileURLs(false);
            s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
            s.setSafeBrowsingEnabled(true);
            s.setSupportZoom(false);
            s.setJavaScriptCanOpenWindowsAutomatically(false);
            s.setSupportMultipleWindows(false);
            s.setMediaPlaybackRequiresUserGesture(true);
            web.setWebChromeClient(new WebChromeClient(){
                @Override public boolean onShowFileChooser(WebView view,ValueCallback<Uri[]> cb,FileChooserParams params){
                    if(uploadCallback!=null)uploadCallback.onReceiveValue(null);
                    uploadCallback=cb;
                    try{startActivityForResult(params.createIntent(),PICK_FILE);return true;}
                    catch(Exception ex){cb.onReceiveValue(null);uploadCallback=null;return true;}
                }
            });
        }
        CookieManager.getInstance().setAcceptThirdPartyCookies(bottomWeb,false);
        // JavaScript bridge hanya pada aset aplikasi. Jangan tambahkan ke WebView situs asing.
        topWeb.addJavascriptInterface(new NativeBridge(),"KreasiFlowNative");
        WebViewAssetLoader assets = new WebViewAssetLoader.Builder()
            .addPathHandler("/assets/",new WebViewAssetLoader.AssetsPathHandler(this))
            .build();
        topWeb.setWebViewClient(new WebViewClient(){
            @Override public WebResourceResponse shouldInterceptRequest(WebView v, WebResourceRequest r){
                return assets.shouldInterceptRequest(r.getUrl());
            }
            @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest r){
                String u=r.getUrl().toString();
                if(u.startsWith("https://appassets.androidplatform.net/assets/"))return false;
                if(isHttps(u)){runOnUiThread(()->openAi(u));return true;}
                return true;
            }
        });
        bottomWeb.setWebViewClient(new WebViewClient(){
            @Override public WebResourceResponse shouldInterceptRequest(WebView v, WebResourceRequest r){
                return assets.shouldInterceptRequest(r.getUrl());
            }
            @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest r){
                Uri uri=r.getUrl();
                String scheme=uri.getScheme();
                if("https".equalsIgnoreCase(scheme)){
                    if("accounts.google.com".equalsIgnoreCase(uri.getHost())){
                        runOnUiThread(MainActivity.this::askChromeForGoogle);
                        return true;
                    }
                    return false;
                }
                return !"about".equalsIgnoreCase(scheme);
            }
            @Override public void onPageFinished(WebView v,String url){
                if(isHttps(url) && !url.contains("appassets.androidplatform.net"))address.setText(url);
            }
        });
        bottomWeb.setDownloadListener((url,userAgent,disposition,mimeType,contentLength)->{
            if(url==null)return;
            if(url.startsWith("blob:")){
                Toast.makeText(this,"Unduh di situs AI mungkin perlu Chrome",Toast.LENGTH_LONG).show();
                return;
            }
            if(!isHttps(url))return;
            try{
                DownloadManager manager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request req=new DownloadManager.Request(Uri.parse(url));
                String guess=URLUtil.guessFileName(url,disposition,mimeType);
                req.setTitle(guess);
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                String cookie=CookieManager.getInstance().getCookie(url);
                if(cookie!=null)req.addRequestHeader("Cookie",cookie);
                if(userAgent!=null)req.addRequestHeader("User-Agent",userAgent);
                req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,guess);
                manager.enqueue(req);
                Toast.makeText(this,"Download dimulai",Toast.LENGTH_SHORT).show();
            }catch(Exception ex){openExternal(url);}
        });
    }

    @Override protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==PICK_FILE && uploadCallback!=null){
            uploadCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode,data));
            uploadCallback=null;
        }
    }
    @Override protected void onSaveInstanceState(Bundle out){
        out.putFloat("ratio",ratio);
        out.putString("lastAiUrl",lastAiUrl);
        Bundle top=new Bundle();topWeb.saveState(top);out.putBundle("top",top);
        Bundle bottom=new Bundle();bottomWeb.saveState(bottom);out.putBundle("bottom",bottom);
        super.onSaveInstanceState(out);
    }
    @Override public void onBackPressed(){
        if(bottomWeb.canGoBack())bottomWeb.goBack();
        else new AlertDialog.Builder(this).setMessage("Tutup KreasiFlow Studio?")
                .setNegativeButton("Batal",(d,w)->{})
                .setPositiveButton("Keluar",(d,w)->finish()).show();
    }
    @Override protected void onDestroy(){
        if(uploadCallback!=null){uploadCallback.onReceiveValue(null);uploadCallback=null;}
        synchronized(transfers){
            for(SaveSession session:transfers.values()){
                try{session.out.close();}catch(Exception ignored){}
                getContentResolver().delete(session.uri,null,null);
            }
            transfers.clear();
        }
        topWeb.destroy(); bottomWeb.destroy();
        super.onDestroy();
    }
    private static final class SaveSession {
        final Uri uri;
        final OutputStream out;
        long size;
        SaveSession(Uri u,OutputStream o){uri=u;out=o;}
    }
    private final class NativeBridge {
        @JavascriptInterface public void openAi(String url){
            if(isHttps(url))main.post(()->MainActivity.this.openAi(url));
        }
        @JavascriptInterface public String beginSave(String name,String mime){
            String safe=name==null?"file":name.replaceAll("[^a-zA-Z0-9._-]","_");
            if(safe.length()>100)safe=safe.substring(safe.length()-100);
            if(safe.isEmpty())safe="kreasiflow-file";
            String type=mime==null||mime.length()>120?"application/octet-stream":mime;
            Uri uri=null;
            try{
                ContentValues v=new ContentValues();
                v.put(MediaStore.MediaColumns.DISPLAY_NAME,safe);
                v.put(MediaStore.MediaColumns.MIME_TYPE,type);
                v.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_DOWNLOADS+"/KreasiFlow");
                v.put(MediaStore.MediaColumns.IS_PENDING,1);
                uri=getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI,v);
                if(uri==null)return "error";
                OutputStream out=getContentResolver().openOutputStream(uri,"w");
                if(out==null){getContentResolver().delete(uri,null,null);return "error";}
                String id=String.valueOf(nextTransfer.getAndIncrement());
                synchronized(transfers){transfers.put(id,new SaveSession(uri,out));}
                return id;
            }catch(Exception ex){
                if(uri!=null)getContentResolver().delete(uri,null,null);
                return "error";
            }
        }
        @JavascriptInterface public boolean appendSave(String id,String base64){
            if(base64==null||base64.length()>180000)return false;
            synchronized(transfers){
                SaveSession s=transfers.get(id);
                if(s==null)return false;
                try{
                    byte[] chunk=Base64.decode(base64,Base64.DEFAULT);
                    if(s.size+chunk.length>512L*1024*1024)return false;
                    s.out.write(chunk);
                    s.size+=chunk.length;
                    return true;
                }catch(Exception ex){return false;}
            }
        }
        @JavascriptInterface public void finishSave(String id,boolean ok){
            SaveSession s;
            synchronized(transfers){s=transfers.remove(id);}
            if(s==null)return;
            try{s.out.flush();s.out.close();}catch(IOException ex){ok=false;}
            try{
                if(ok){
                    ContentValues v=new ContentValues();v.put(MediaStore.MediaColumns.IS_PENDING,0);
                    getContentResolver().update(s.uri,v,null,null);
                    main.post(()->Toast.makeText(MainActivity.this,"Tersimpan ke Download/KreasiFlow",Toast.LENGTH_LONG).show());
                }else{
                    getContentResolver().delete(s.uri,null,null);
                    main.post(()->Toast.makeText(MainActivity.this,"Gagal menyimpan file",Toast.LENGTH_LONG).show());
                }
            }catch(Exception ignored){}
        }
    }
}
