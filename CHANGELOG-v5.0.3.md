# Changelog — v5.0.3

- Auto Produksi AI sekali klik untuk judul, naskah, narasi, WAV voice-over, prompt dan beberapa gambar scene.
- Native Android Google API gateway via HTTPS untuk mengatasi CORS WebView; tidak menyisipkan API key permanen.
- Media output API besar dikirim bertahap kembali ke WebView untuk mengurangi risiko transaksi besar.
- Gemini text `gemini-3.8-flash`, TTS `gemini-3.8-flash-tts` dan Nano Banana 2 `gemini-3.1-flash-image` (tersedia jika key mempunyai akses).
- Generator gambar per scene dan batch terbatas; simpan gambar dari storyboard, unduh WAV.
- Tetap memakai fullscreen Android (tanpa split) dan Chrome external fallback dari v5.0.2.
- Tidak mengklaim bisa menekan tombol pada Google Flow via Chrome.
- Persistensi proyek teks, render lokal, dan GitHub Release updater tetap ada.
