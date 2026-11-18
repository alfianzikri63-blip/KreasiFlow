# KreasiFlow Studio v5.0.7 — Perbaikan OpenRouter 401

1. Di GitHub repo `alfianzikri63-blip/KreasiFlow`, **ganti file** `KreasiFlow-Android-Update-V5-Source.zip` dengan file ini (nama harus sama).
2. Jalankan workflow **Test KreasiFlow APK Build from ZIP** di tab Actions.
3. Jika hijau, jalankan **Publish Signed KreasiFlow APK from ZIP** dengan versi `5.0.7`. Gunakan empat GitHub Secrets signing yang sama dengan v5.0.6.
4. Instal APK release dari **Releases**, tanpa uninstall versi sebelumnya.
5. Buka **⚙ Pengaturan AI**. Pada OpenRouter, masukkan *nilai API key* yang diawali `sk-or-` saja. Jangan menempel nama key atau teks `Bearer` (versi ini juga mendukung normalisasi jika terlanjur menempel prefiks).
6. Klik **Simpan 3 Provider**, kemudian buka ⚙ lagi dan tekan **Uji koneksi OpenRouter**.
7. Jika uji berhasil, coba `✨ Kembangkan judul AI`. Jika gagal 401 lagi, kirim hanya pesan error, jangan API key.

**Mengapa:** HTTP 401 `Missing Authentication header` berarti OpenRouter tidak menerima kredensial yang valid. Patch memvalidasi langsung melalui `GET /api/v1/key` tanpa menghasilkan konten. Hasil uji hanya menampilkan lulus/gagal; API key tetap terenkripsi di HP.

**Catatan**: Source dan sintaks JavaScript dicek secara statis. Build APK dan uji pada HP belum dijalankan dari sini.
