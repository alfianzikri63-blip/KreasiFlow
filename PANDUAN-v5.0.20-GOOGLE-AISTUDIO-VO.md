# KreasiFlow v5.0.20 — Google AI Studio Voice-over

Pembaruan **langsung dari source KreasiFlow v5.0.19**. Semua mode sebelumnya (Karakter Tetap ASAQ–ZAYN, Animasi 3D Edukasi, storyboard, gambar, prompt Google Flow, render) dipertahankan. Alur VO ElevenLabs dinonaktifkan pada antarmuka, dan permintaan provider ElevenLabs ditolak oleh bridge Android. Kunci lama dalam penyimpanan terenkripsi tidak dihapus otomatis.

## Perubahan

1. **Bersihkan teks VO** hanya mengambil ucapan narator dari naskah dengan penanda `**Voice Over:**`, `Voice Over:`, `Narator:` atau `VO:`. Label, judul bagian, petunjuk produksi (`Musik`, `Visual`, `Efek Suara`, `Catatan untuk Produksi`, `[PERLU VERIFIKASI]`) tidak dikirim sebagai kata yang dibacakan.
2. **Google AI Studio Speech**: gunakan tombol **Buka AI Studio + siapkan teks**. Pada APK Android, hanya bagian narasi yang dipilih akan disalin ke clipboard Android dan Chrome akan dibuka di `https://aistudio.google.com/generate-speech`. Tempel manual di kotak Speech.
3. **Style instruction terpisah**: tombol **Salin Style untuk AI Studio** menyalin arahan performa suara. Masukkan pada pengaturan Style di AI Studio, jangan pada kotak Speech.
4. **Narasi panjang dibagi per bagian** hingga sekitar **1.600 karakter**, sebagai rekomendasi praktis untuk HP, bukan batas resmi Google. Setiap bagian harus diproses dan diunduh tersendiri; gabungkan audio di aplikasi editor jika perlu sebelum mengimpor versi final ke KreasiFlow.
5. **Impor hasil audio**: pada tab Upload narasi, pilih WAV/MP3/M4A/OGG hasil AI Studio. Audio tidak diambil otomatis dari situs Google.
6. **Auto Produksi AI** tetap mengerjakan judul, naskah, storyboard, dan gambar (sesuai penyedia/kuota). Langkah pembuatan VO dalam alur otomatis dihapus sehingga tidak membuat permintaan TTS berbayar dari APK.

## Cara memakai dari HP

1. **Ekspor proyek JSON** sebelum mengganti APK, karena data berada di penyimpanan lokal aplikasi.
2. Buka tab **Upload** → **Naskah & narasi**. Tempel/edit naskah yang memuat `**Voice Over:** ...` dan catatan produksi.
3. Tekan **Ambil dialog dari naskah** atau **Bersihkan teks VO**. Baca **Pratinjau persis kata yang akan dibaca**; jangan lanjut bila ada teks catatan yang lolos.
4. Di bagian **Voice-over melalui Google AI Studio**, pilih **Bagian 1**, lalu tekan **Buka AI Studio + siapkan teks**. Tempel hasil clipboard ke **Speech**, pilih suara dan Style, lalu tekan **Run**.
5. Untuk bagian berikutnya, kembali ke KreasiFlow dan pilih Bagian 2, 3, dst. Jangan menempel semua instruksi ke Speech.
6. Unduh audio dari AI Studio. Jika ada beberapa bagian, gabungkan menjadi satu file di editor audio/video, lalu **impor** ke KreasiFlow lewat kotak **Upload narasi**.
7. Jika video yang dihasilkan Google Flow sudah mengandung suara yang dikehendaki, VO tambahan bisa dilewati.

## Batasan & privasi

- **Tidak ada otomasi Google AI Studio**: aplikasi tidak mengendalikan tombol Run, login, browser, kredit, atau pengunduhan.
- Tidak ada jaminan bahwa semua teks panjang bisa diproses dalam satu permintaan. Pembagian narasi di sini hanya untuk kemudahan pengguna.
- Pembuatan audio via AI Studio mengikuti kuota, akses, dan ketentuan Google. AI Studio dan Gemini API bisa memiliki pengaturan billing yang berbeda.
- Script tidak dikirim ke ElevenLabs. Tidak ada API key baru yang perlu dimasukkan untuk alur Chrome AI Studio.
- **Jangan hapus data aplikasi** saat memperbarui APK bila belum mengekspor proyek. APK debug dengan package berbeda bukan update in-place dari APK rilis.

## Cara build lewat repository lama

Ganti berkas root `KreasiFlow-Android-Update-V5-Source.zip` di repository `alfianzikri63-blip/KreasiFlow`, kemudian jalankan GitHub Actions: **Test KreasiFlow APK Build from ZIP**. ZIP berisi folder `KreasiFlow-Studio-Android-Updater-V5` yang dicari workflow yang sudah ada.

## Pengujian yang telah dilakukan

- `node --check` pada JavaScript HTML: **lulus**.
- Browser Chromium mobile melalui konten HTML yang disisipkan: **lulus** untuk tampilan, pengambilan narasi `**Voice Over:**`, pembuangan catatan, dan jalur tombol menuju bridge clipboard yang disimulasikan.
- Unit test narasi: kelima paragraf contoh tidak terpotong, label dan catatan dibuang, pembagian segmen tidak membuang kata: **lulus**.
- Build Gradle APK dan panggilan Google AI Studio langsung **belum diuji**. Browser dibatasi jaringan untuk membuka situs live, tetapi UI lokal telah diuji.
