# KreasiFlow v5.0.19 — Perpustakaan Karakter Tetap

Ini update **tambahan** atas v5.0.18, bukan rebuild. Tetap menyertakan Gemini, OpenRouter, ElevenLabs, Cloudflare, Google Flow bridge, mode 3D edukasi, scene biasa, dan render MP4.

## Fitur baru
- Toggle **Mode Karakter Tetap 2D Chibi** pada tab Scene & Gambar.
- Preset ASAQ (adik, tinggi ±70 cm, topi beruang biru tua) dan ZAYN (kakak, tinggi ±89 cm, rambut cokelat pendek tanpa topi), menggunakan potongan dari gambar referensi yang diberikan pengguna.
- Character Bible yang bisa diedit, Style Bible, Setting Bible dan Object/Prop Bible.
- Tambah karakter lain, unggah gambar referensi masing-masing; metadata dan file lokal disimpan lintas episode memakai localStorage + IndexedDB.
- Storyboard 6 scene Episode "Misi Biskuit Terakhir" — setiap scene berisi aksi, dialog dan daftar karakter yang tampil.
- Prompt Google Flow per scene memakai character/style/object bible yang sama. Tombol Flow menyalin prompt dan membuka Chrome; gambar referensi disimpan ke Downloads agar dapat dipilih secara manual dalam Flow.
- Impor MP4 hasil Flow per scene dan penggabungan yang berupaya mempertahankan audio asli klip dengan format yang kompatibel.

## Cara pakai
1. Pada tab Scene & Gambar aktifkan Mode Karakter Tetap 2D Chibi.
2. Pastikan ASAQ dan ZAYN tampil di Perpustakaan. Upload gambar pengganti jika ingin mengubah referensi; jangan menghapus keduanya.
3. Pilih **Muat Episode Misi Biskuit Terakhir** (perhatikan konfirmasi karena akan mengganti scene dan naskah proyek aktif).
4. Edit cast/gerak/dialog per scene. Simpan Character Bible dan dua referensi ke Downloads.
5. Tekan **Siapkan di Google Flow**. Pilih karakter/Ingredients secara manual di Flow, tempel prompt dari clipboard dan Generate. Unduh klip MP4 (audio disertakan jika dihasilkan Flow).
6. Impor MP4 satu per scene pada kartu yang sesuai, kemudian Render > Gabungkan MP4 + suara asli Flow.

**Penting:** Target storyboard adalah 6×5 detik, tetapi Google Flow dapat menyediakan durasi lain seperti 8 detik tergantung model dan akun; potong/susun ulang di editor video untuk tepat 30 detik. Perpustakaan lokal bukan sinkronisasi cloud dan tidak otomatis berpindah perangkat. File referensi dan MP4 tidak ikut JSON proyek. Simpan backup sebelum uninstall APK.

Google Flow tidak memiliki akses automasi browser melalui KreasiFlow, jadi upload, login, Generate dan download selalu manual. Tidak ada jaminan karakter benar-benar identik hanya berdasarkan prompt.

## Status pengujian
HTML/JavaScript: lulus pemeriksaan sintaks. ZIP dapat diekstrak. APK Gradle lengkap, pengunduhan dalam WebView HP, penyimpanan IndexedDB, Google Flow sebenarnya, dan render penggabungan audio belum dinyatakan lulus tanpa uji perangkat.
