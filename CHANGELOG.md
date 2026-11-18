# KreasiFlow Studio v5.0.8

**Bug:** Voice-over ElevenLabs membaca metadata dan arahan produksi karena `extractNarration()` hanya menghapus sebagian tanda kurung siku.

**Perbaikan:**
- Parser hanya mengambil bagian `Voice Over: "dialog"` untuk naskah bertag VO.
- Menghapus label bab, arahan musik/SFX, serta catatan verifikasi sebelum teks dikirim ke ElevenLabs.
- Menyediakan tombol `Bersihkan teks VO` untuk preview hasil di kolom narasi.
- Menggunakan parser yang sama untuk suara browser dan produksi sampai video.
- Tetap mempertahankan enkripsi API key, pembaruan dari GitHub Releases, dan render MP4 native.

**Uji:** sintaks JavaScript dan uji parser lokal berhasil. APK Android serta ElevenLabs riil belum diuji.
