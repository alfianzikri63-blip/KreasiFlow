# KreasiFlow Studio v5.0.6 — API Key Storage Hotfix + Render MP4

**Bug fixed:** `Gagal menyimpan: openrouter, fal, elevenlabs` akibat caller-supplied IV pada AndroidKeyStore AES-GCM.

**Keamanan:** Gunakan IV acak dari cipher, tetap Android Keystore + AES-GCM, jangan pernah mengirim API key ke GitHub atau proyek JSON.

**UI:** Saat terjadi kegagalan simpan, modal tetap terbuka, nilai input gagal tidak dibuang, dan kesalahan aman ditampilkan.

**Tetap ada:** OpenRouter, fal.ai, ElevenLabs, editor full screen, pembaruan GitHub Releases, renderer native MP4 yang telah disiapkan sebelumnya.

**Testing:** Pengujian browser mock dan JDK AES-GCM lolos; belum dijalankan build Android di GitHub maupun diuji di HP.
