# Perbaikan crash KreasiFlow Studio v5.0.1

## Gejala
APK v5.0.0 telah berhasil dibuild, signed, dan dipublikasi, tetapi langsung menutup ketika dibuka pada HP Android (tampilan "KreasiFlow Studio telah berhenti"). Penyebab pasti memerlukan logcat dari perangkat; patch ini memperbaiki beberapa titik rawan dari audit source.

## Apa yang diperbaiki
- Broadcast receiver unduhan menggunakan flag sesuai Android 14+.
- Error pendaftaran receiver, WorkManager, atau channel notifikasi tidak lagi otomatis menutup aplikasi.
- Saat WebView gagal inisialisasi, tampil layar pemulihan dengan jenis error dan tombol ke Chrome.
- Restore State WebView mengecek null sebelum memanggil restoreState().
- Panggilan onResume updater diamankan.

## Ganti ZIP source di GitHub lewat HP
1. Download file `KreasiFlow-Android-Update-V5-Source.zip` dari folder ini.
2. Login ke https://github.com/alfianzikri63-blip/KreasiFlow .
3. Hapus file lama `KreasiFlow-Android-Update-V5-Source.zip` di root repository (jangan sentuh secrets dan workflow). Commit penghapusan jika tidak ada opsi replace.
4. Upload file baru dengan NAMA PERSIS SAMA ke root repo. Commit.
5. Di tab Actions jalankan `Test KreasiFlow APK Build from ZIP`. Bila sukses, lanjut.
6. Jalankan `Publish Signed KreasiFlow APK from ZIP` dengan input version `5.0.1` dan secrets signing SAMA seperti v5.0.0.
7. Unduh `KreasiFlow-Studio.apk` versi v5.0.1 dari https://github.com/alfianzikri63-blip/KreasiFlow/releases .
8. Buka APK yang baru dari Chrome/Downloads dan pilih Update/Pembarui. JANGAN uninstall v5.0.0, agar data aplikasi tetap ada.

## Jika masih langsung crash
Jangan terus-terusan menerbitkan versi baru tanpa log. Kirim hasil logcat yang memuat `FATAL EXCEPTION` dan `Caused by` dari proses `id.kreasiflow.studio`, atau screenshot layar pemulihan bila muncul. Screenshot pemberitahuan "Aplikasi telah berhenti" saja tidak menunjukkan stack trace.

## Catatan
Source diperiksa secara statis dan ZIP dinyatakan utuh; belum diuji pada HP atau dikompilasi dalam lingkungan ini. Gunakan workflow GitHub Actions untuk memverifikasi kompilasi.
