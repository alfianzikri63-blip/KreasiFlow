# KreasiFlow Studio — Android Split Screen (Source APK)

Ini adalah **source Android Studio** untuk mengubah webpage **KreasiFlow Studio V3** menjadi aplikasi Android dua panel native, bukan APK yang telah dikompilasi. Sumber webpage V3 terpasang di `app/src/main/assets/index.html`.

## Tata letak

- **Atas**: aplikasi KreasiFlow lokal (judul → naskah → narasi → scene → render), dapat di-scroll meskipun panelnya pendek.
- **Tengah**: bilah pembatas yang bisa **ditarik**, plus preset 30:70, 50:50, 70:30 di toolbar.
- **Bawah**: WebView browser AI dengan pilihan **AI Studio**, **Google Flow**, **Gemini**, URL HTTPS lain, tombol **↗ Chrome**, serta bantuan split-screen.
- Panel atas memiliki JavaScript bridge Android khusus untuk mengirim link AI ke bawah dan menyimpan file teks/video ke **Downloads/KreasiFlow**, dengan penyimpanan potongan data agar file besar tidak harus diubah ke string raksasa. Panel bawah **tidak** mendapatkan bridge Android.
- Tombol upload file memakai pemilih berkas Android; proyek teks tetap di local storage aplikasi.

## Batasan Google yang penting

Google **melarang OAuth di WebView tertanam**. Situs AI Studio, Google Flow dan Gemini dapat terbuka sebagian pada panel bawah, tetapi **login Google dapat ditolak**; tidak mungkin dijamin berjalan penuh di sana. Bila login tidak berfungsi, gunakan tombol **↗ Chrome** kemudian fitur **Split screen** dari menu *Recent apps* Android untuk membuka KreasiFlow berdampingan dengan Chrome. Tombol mengirim permintaan *launch adjacent*, tetapi Android/HP-lah yang memutuskan apakah split-screen aktif otomatis. Jangan memodifikasi user-agent untuk menyiasati kebijakan login.

Juga, render MP4/WebM di WebView bergantung dukungan MediaRecorder/perangkat. Render belum diuji pada perangkat nyata. Google Flow tidak bisa dikendalikan otomatis dari aplikasi ini; pengguna menyalin prompt dan mengunggah hasil secara manual.

## Membuat APK dengan Android Studio

1. Ekstrak ZIP dan buka folder **KreasiFlow-Split-Android** dengan Android Studio versi modern.
2. Pastikan **JDK 17**, **Android SDK Platform 35**, dan Gradle tersedia. Proyek menggunakan **Android Gradle Plugin 8.7.3**, **Gradle 8.9**, dan AndroidX WebKit **1.12.1**.
3. Lakukan **Sync Project with Gradle Files**. Koneksi internet diperlukan saat mengunduh Gradle/plugin/dependensi pertama kali.
4. Klik **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
5. APK debug akan tersedia di `app/build/outputs/apk/debug/app-debug.apk` setelah build berhasil. Untuk rilis publik, gunakan **Build → Generate Signed Bundle / APK**.

### Penting tentang Gradle Wrapper

Jika `gradle-wrapper.jar` belum tersedia, jalankan dari instalasi Gradle lokal: `gradle wrapper --gradle-version 8.9`, atau gunakan Android Studio untuk membuat Gradle Wrapper. Script `gradlew`/`gradlew.bat` dan JAR wrapper dapat ditambahkan kemudian. File `gradle/wrapper/gradle-wrapper.properties` sudah disediakan.

## Fitur yang belum siap produksi

- Belum ada pengujian on-device dan build binary pada lingkungan ini. Perlu uji file chooser, izin media, pilihan codec untuk render di sejumlah HP Android.
- Google AI Studio/Flow belum dapat ditanam penuh secara resmi; gunakan Chrome.
- Tidak ada sinkronisasi otomatis antara situs Google dan KreasiFlow; salin/tempel teks atau ekspor-impor file secara manual.
- Gemini API key jangan disisipkan permanen di source APK. Buat backend/token sementara jika aplikasinya didistribusikan ke banyak pengguna.

Aplikasi ini bukan produk resmi Google.

## Alternatif build APK lewat GitHub Actions

Bila GitHub Actions di akun/repository kamu **aktif**, unggah **seluruh isi folder proyek** ke root repo baru pada branch `main`, lalu buka tab **Actions → Build KreasiFlow Debug APK → Run workflow**. Setelah berhasil, unduh artifact `KreasiFlow-Studio-APK-debug`. Workflow memakai Gradle langsung sehingga tidak perlu `gradle-wrapper.jar`.

Jika repo GitHub sedang dibatasi sehingga Actions tidak dapat berjalan, cara ini **tidak** bisa digunakan sampai masalah akun/repo selesai; gunakan Android Studio lokal.
