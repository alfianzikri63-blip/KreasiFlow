# KreasiFlow V5 – GitHub Actions + Notifikasi Update APK

Gunakan repository **publik** supaya APK yang terpasang bisa membaca rilis terbaru dari GitHub tanpa login.

## 1. Buat repository di GitHub

1. Buka `https://github.com/new` dan login ke akun GitHub yang ingin dipakai.
2. Repo baru, misalnya **kreasiflow-studio-android**, `Public`, centang README, buat.
3. Upload file **KreasiFlow-Android-Update-V5-Source.zip** ke root repository (jangan upload ZIP kit; jangan upload kunci privat).
4. `Add file` -> `Create new file`: nama path `.github/workflows/release-apk-from-zip.yml`; isinya salin seluruh file `.github/workflows/release-apk-from-zip.yml` dari paket ini. Commit.
5. Opsional: buat `.github/workflows/test-build-from-zip.yml` dari file yang disediakan untuk memeriksa source build debug terlebih dahulu.

## 2. Pasang 4 secret signing di Settings repo

Di `Settings → Secrets and variables → Actions → New repository secret`:

- `KREASIFLOW_KEYSTORE_B64`
- `KREASIFLOW_STORE_PASS`
- `KREASIFLOW_KEY_ALIAS`
- `KREASIFLOW_KEY_PASS`

Nilainya ada dalam file **KreasiFlow-PRIVATE-Signing-Keys-V5.zip** yang disediakan terpisah. Simpan ZIP privat ini aman, **JANGAN UPLOAD ke repo GitHub**, jangan unggah di Issues, dan jangan bagikan linknya.

## 3. Buat rilis APK pertama

1. Buka `Actions` di repo.
2. Pilih `Publish Signed KreasiFlow APK from ZIP`, tekan `Run workflow`.
3. Masukkan `version`: **5.0.0**. Klik Run.
4. Setelah run sukses, buka tab `Releases`, klik **v5.0.0** lalu download **KreasiFlow-Studio.apk**.
5. Instal APK di Android dan aktifkan izin notifikasi saat diminta.

## 4. Pembaruan berikutnya

Saat source diubah, upload ZIP source baru **dengan nama yang sama** (replace file).
Buka `Actions → Publish Signed KreasiFlow APK from ZIP → Run workflow` kemudian versi **5.0.1** atau yang lebih tinggi.
Jika berhasil, GitHub menerbitkan rilis baru dengan APK yang ditandatangani kunci yang sama. Aplikasi memeriksa rilis baru saat dibuka dan sekitar enam jam sekali di background, lalu menampilkan **notifikasi**. Ketuk notifikasi, pilih **Unduh APK**, setelah selesai pilih **Pasang update**. Android akan meminta izin pemasangan, tanpa perlu uninstall, selama packageId + sertifikat signing sama.

## Catatan penting

- Update **tidak instan** setiap detik. Cek background mengikuti pengaturan baterai dan jaringan Android.
- APK yang berasal dari `Test-Debug-APK-Not-Updateable` **bukan** versi yang bisa diperbarui sebagai APK release. Gunakan APK dari **Releases**.
- Jika workflow gagal `Resource not accessible by integration` / 403, periksa izin GitHub Actions repo dan `Workflow permissions` yang mengizinkan penulisan contents, serta pembatasan akun. Workflow sudah meminta `contents: write`.
- Jangan ubah `applicationId = id.kreasiflow.studio` atau ganti kunci signing di rilis berikutnya.
- Chrome lebih andal untuk login Google AI Studio / Flow daripada WebView aplikasi.
- Source diuji secara statis; APK belum dikompilasi di perangkat pembuat ini (SDK Android tidak tersedia).
