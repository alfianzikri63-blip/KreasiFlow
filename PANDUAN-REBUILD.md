# KreasiFlow Studio Rebuild — mengikuti alur video 265307.mp4

**Status:** Source Android + web UI versi awal (MVP). Tidak mengklaim APK telah dibuild atau render di HP telah diuji.

## Alur yang ditiru dari video tutorial (tanpa memakai merek/logo aplikasinya)

1. **Upload**: pilih tema, ide judul, format Shorts atau 1–30 menit, gaya narasi, bahasa Indonesia/Inggris. Prompt naskah disalin untuk dipakai di Google AI Studio. Tempel hasil naskah di KreasiFlow.
2. **Narasi**: buka Google AI Studio melalui browser Chrome (bukan login Google dalam WebView). Gunakan Gemini 3.8 Flash TTS dan suara Riko bila tersedia, tempel naskah, unduh WAV/MP3. Unggah narasi ke KreasiFlow.
3. **Proses**: deteksi jeda waveform dan pemotongan jeda panjang menjadi WAV menggunakan Web Audio API (maksimum 10 menit untuk pemotongan di perangkat). Susun scene dari teks naskah dan durasi audio. **Belum** ada speech-to-text otomatis dari file audio; naskah harus tersedia agar teks scene akurat.
4. **Scene & Gambar**: preset Komik/Manhwa, Pixel Art Retro, Kolase Kertas, Cerita Rakyat Nusantara, realistik, horor. Prompt per scene dapat disalin ke Google Flow dengan Nano Banana 2.1 (atau model yang tersedia), gambar kemudian diunduh dan diunggah ke tiap scene di KreasiFlow. Tidak ada automasi tersembunyi terhadap situs Flow.
5. **Render**: musik opsional dibuat lewat FlowMusic (salin prompt, buat lagu, download), lalu impor. Render menggunakan Media3 Transformer pada APK Android (scene image + narasi + musik + teks overlay/watermark + zoom halus) ke MP4 di Downloads/KreasiFlow. Terbatas perangkat dan codec, perlu uji fisik.

## Apa yang sengaja tidak dilakukan

- Tidak menyalin logo, nama, kode, sistem lisensi, atau UI identik dari aplikasi pihak ketiga.
- Tidak menyatakan Google Flow dapat diotomasi lewat Chrome Android, dan tidak membuat login OAuth di WebView.
- Tidak meminta atau membenamkan API key. Pengguna login sendiri di Google AI Studio, Flow, dan FlowMusic di Chrome. Masing-masing layanan dapat memiliki kredit, batas pemakaian, dan biaya.
- Tidak mengklaim naskah ide contoh buatan AI: contoh judul adalah templat lokal. Hasil naskah AI diperoleh pengguna di AI Studio.
- Belum mendukung speech-to-text otomatis, klip video generatif langsung, assets Flow otomatis, time-aligned subtitle, transisi yang kompleks, atau render video lengkap 30 menit terbukti stabil.

## Menggunakan REPO YANG SUDAH ADA

Repository: `alfianzikri63-blip/KreasiFlow`

Repo sudah punya workflow `.github/workflows/test-build-from-zip.yml` yang mengharapkan:
- ZIP tepat bernama **KreasiFlow-Android-Update-V5-Source.zip** pada root repo.
- Folder teratas dalam ZIP: **KreasiFlow-Studio-Android-Updater-V5**.
- Proyek Gradle termasuk `app/build.gradle`, `app/src/main/AndroidManifest.xml`.

Cara update:
1. Buka repository di GitHub (browser HP).
2. Hapus ZIP lama `KreasiFlow-Android-Update-V5-Source.zip`, lalu unggah ZIP baru **dengan nama yang sama**. Jangan mengunggah ZIP kit yang membungkusnya.
3. Tab **Actions** → **Test KreasiFlow APK Build from ZIP** → **Run workflow** (branch `main`).
4. Jika sukses, unduh artifact `Test-Debug-APK-Not-Updateable`, ekstrak, pasang `app-debug.apk`.
5. `applicationId` baru adalah `id.kreasiflow.rebuild`; instalasi terpisah dari aplikasi lama. Proyek lama tidak dihapus.

## Developer

- Java 17 / Android SDK 35 / Gradle 8.9 / Android Gradle Plugin 8.7.3.
- AndroidX Media3 1.9.0 (Transformer/Effect/Common) untuk MP4, sinkronisasi audio visual di perangkat.
- Open `app/src/main/assets/index.html` dengan browser untuk menguji UI, prompt, dan storyboard tanpa APK. Render MP4 tetap memerlukan Android.
- Data teks disimpan `localStorage`; blob image/audio `IndexedDB` lokal. Uninstall/hapus data aplikasi dapat menghapusnya; ekspor JSON hanya **metadata**.
- Build source tidak memerlukan private signing secrets. Workflow menghasilkan APK debug untuk pengujian, bukan APK update signed.

## QA saat dibuat

- Web JS: `node --check` lulus.
- File XHTML/HTML dan struktur paket diperiksa.
- Kode Media3 menggunakan API yang didokumentasikan Google, tetapi build Gradle Android lengkap dan tes render perangkat BELUM dilakukan. Jika Actions gagal, simpan log dari langkah Gradle untuk koreksi.

## Referensi layanan

- Google AI Studio: https://aistudio.google.com/
- Google Flow: https://flow.google.com/
- FlowMusic: https://www.flowmusic.app/
- Android Media3 composition API: https://developer.android.com/media/media3/transformer/composition

Proyek ini independen dan bukan produk resmi Google, FlowMusic, atau aplikasi pada video.
