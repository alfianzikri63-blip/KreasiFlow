# KreasiFlow Studio v5.0.8 — Perbaikan suara narator

**Tujuan:** ElevenLabs hanya membacakan dialog narator, tidak membacakan [HOOK], musik, efek suara, dan catatan editor.

## Cara update

1. Di repo https://github.com/alfianzikri63-blip/KreasiFlow, ganti file `KreasiFlow-Android-Update-V5-Source.zip` di root dengan ZIP yang disediakan dalam paket ini.
2. Jalankan Actions → **Test KreasiFlow APK Build from ZIP** sampai hijau.
3. Jalankan Actions → **Publish Signed KreasiFlow APK from ZIP** dengan versi `5.0.8`.
4. Gunakan keempat GitHub Secrets signing yang **sudah ada**. Jangan mengunggah keystore atau password ke GitHub. Jangan ganti keystore signing atau applicationId.
5. Instal APK v5.0.8 dari halaman Releases di atas versi lama, jangan uninstall.

## Cara uji di aplikasi

1. Buka tab **Upload → Naskah & narasi**.
2. Pastikan naskah lengkap ada di kotak **Naskah** (termasuk Voice Over dan instruksi produksi).
3. Tekan **↻ Ambil dialog dari naskah**, atau **🧹 Bersihkan teks VO** jika kotak narasi berisi teks lama.
4. Periksa kotak **Teks KHUSUS yang akan dibaca ElevenLabs**: isinya hanya ucapan narator.
5. Tekan **🎙 Generate hanya teks narasi**. Pembuatan MP3 baru memakai kuota ElevenLabs.

## Batasan

- Source ini belum dikompilasi menjadi APK di lingkungan pembuat. Uji JS hanya memastikan pemisahan teks dan sintaksnya, bukan kompatibilitas perangkat.
- Catatan `[PERLU VERIFIKASI]` dibuang dari audio, tetapi **fakta yang belum dipastikan dalam naskah tetap perlu diperiksa sebelum video dipublikasikan**.
- Pembersihan teks tidak dapat memperbaiki MP3 lama secara otomatis. Jangan kirim API key atau signing key ke chat publik.
