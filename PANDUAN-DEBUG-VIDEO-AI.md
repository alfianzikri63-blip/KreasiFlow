# KreasiFlow Studio v5.0.11 — Video AI Bergerak (Debug)

Versi ini melanjutkan source KreasiFlow v5.0.10. OpenRouter (teks), Cloudflare Workers AI (gambar), ElevenLabs (voice-over), slideshow native, dan update GitHub Releases tetap ada.

## Baru
- **Replicate Wan 2.2 Fast image-to-video**: gambar scene menjadi klip MP4 yang bergerak menggunakan model video generatif, bukan sekadar efek zoom.
- Menu pengaturan **Replicate API Token** (disimpan terenkripsi melalui Android KeyStore, token tidak diekspor ke JSON proyek ataupun GitHub).
- Tombol **Buat klip bergerak** per scene dan batch kecil 1 / 2 / 3 / 5 scene. Perlu konfirmasi biaya sebelum memulai setiap batch.
- Hasil klip bisa dipratinjau di tab Scene & Gambar; pratinjau URL CDN akan kadaluarsa. File klip disalin ke cache Android untuk render dalam sesi tersebut.
- Di tab Render: **Gabungkan klip AI + narasi MP4** menyatukan video dan audio; jika narasi lebih panjang daripada total klip, klip diputar berulang hingga audio selesai. MP4 disimpan ke **Download/KreasiFlow**.
- Tombol lama **Slideshow MP4 (hemat)** tetap tersedia.
- Preview gambar scene memakai contain agar seluruh gambar terlihat.

## Cara build debug (aman untuk aplikasi utama)
1. Upload **KreasiFlow-Android-Update-V5-Source.zip** ke root repository `alfianzikri63-blip/KreasiFlow` untuk mengganti file lama.
2. Jalankan workflow yang sudah ada: **Test KreasiFlow APK Build from ZIP** (`.github/workflows/test-build-from-zip.yml`). **Jangan gunakan `Build KreasiFlow Debug APK` lama** karena source root-nya tidak lengkap.
3. Unduh artifact `Test-Debug-APK-Not-Updateable`, ekstrak dan instal APK debug. `applicationIdSuffix=.debug` memungkinkan instal berdampingan dengan aplikasi utama. Debug tidak memakai sistem auto-update rilis.
4. **Belum perlu publish signed release v5.0.11** sampai lulus uji API dan render di HP.

## Uji satu scene dan hasil video bergerak
1. Di APK debug, buka **⚙ API Keys**, masukkan **Replicate API Token** dari `https://replicate.com/account/api-tokens`. Jangan berbagi token atau menaruhnya di GitHub. Akun Replicate mungkin memerlukan saldo untuk memakai model.
2. Buat naskah/scene dan gambar Cloudflare, atau upload satu gambar JPG/PNG.
3. Pada Scene 1, pilih **🎞 Buat klip bergerak (Replicate)** dan setujui perkiraan biaya sebelum generasi.
4. Setelah klip dibuat, lihat pratinjau videonya di scene. Ini berbeda dari gambar statis.
5. Jika ingin video bersuara, pastikan MP3 narasi sudah terpasang, lalu buka Render → **🎬 Gabungkan klip AI + narasi MP4**.
6. Buka hasilnya dari Download/KreasiFlow.

## Biaya dan keterbatasan
- Model Replicate `wan-video/wan-2.2-5b-fast` **bukan free tier**. Harga model dapat berubah. Periksa halaman resmi `https://replicate.com/wan-video/wan-2.2-5b-fast` sebelum menjalankan batch. Setelan awal 480p, 81 frame @ 16 fps, sekitar 5 detik tiap klip.
- Setiap permintaan generasi video dapat dikenakan biaya, termasuk jika hasilnya tidak sesuai. Jangan membuat banyak klip sebelum lulus uji satu scene.
- Penggabungan tanpa re-encode mengharuskan klip berformat H.264 dengan dimensi sama. Jika model mengembalikan format berbeda, renderer akan memberi error dan tidak mengeluarkan MP4 rusak.
- Di mode AI bergerak, subtitle/watermark/musik latar belum di-overlay. Voice-over akan masuk sebagai AAC jika tersedia. Jika narasi lebih panjang, urutan klip akan diulang sehingga gerakan tidak berhenti, tetapi bisa terlihat repetitif.
- Input menggunakan data URI JPEG di bawah 790 KB (dibuat lokal dari gambar scene) sesuai pedoman Replicate untuk data URI kecil.
- Klip video sementara tidak masuk ke JSON proyek, dan dapat hilang jika APK ditutup/dihentikan. Simpan MP4 final ke Downloads sebelum keluar.
- Google Flow tetap dibuka lewat Chrome. Tidak ada otomatisasi antarmuka Chrome atau akses tanpa izin akun.
- API keys untuk pemakaian bersama/public sebaiknya diproksikan melalui backend yang aman. Penyimpanan terenkripsi lokal bukan pengganti backend untuk banyak pengguna.

## Status QA
- JavaScript: **`node --check` lulus**.
- Dua class Java baru (`ReplicateVideoApi` dan `NativeClipRenderer`): **uji kompilasi statis dengan stub API Android lulus**.
- **Belum diuji build Gradle Android lengkap dan API video Replicate langsung**, karena Android SDK dan token pengguna tidak tersedia di lingkungan ini. Perlu cek job GitHub dan uji di HP terlebih dahulu.
- Kunci signing rahasia dan API token tidak termasuk ZIP.
