# KreasiFlow v5.0.14 — Prompt Video Bergerak AI per Scene

Versi ini memperbarui KreasiFlow v5.0.13 yang lama, tidak mengganti tampilan atau membuang fitur sebelumnya.

## Perubahan
- Kolom Prompt Video Bergerak terpisah dari Prompt Gambar pada setiap scene.
- Ketika membuat storyboard lewat tombol "Generate storyboard", aplikasi langsung menyiapkan draft gerakan untuk setiap scene, lalu meminta prompt yang lebih spesifik dari Gemini/OpenRouter bila API key teks tersedia.
- Tombol "Prompt gambar + video AI" memperbarui prompt gambar dan gerak; tombol "AI prompt video semua scene" hanya memperbarui prompt video yang belum diedit manual.
- Tombol "AI gerakan scene" memproses scene yang dipilih. Tombol "Salin prompt gerak" menyalin instruksi gerak.
- Tombol "Siapkan di Google Flow" memakai prompt video gerak per scene, menyiapkan gambar lokal, dan membuka Google Flow di Chrome.
- Prompt video gerak disimpan di localStorage dan termasuk saat ekspor/impor proyek JSON. Proyek v5.0.13 tetap bisa dibuka.
- Tidak mengubah fungsi VO, gambar, atau video API berbayar dan tidak menambah API key.

## Batasan
Google Flow tidak memiliki antarmuka API publik yang didukung untuk otomatis mengklik Generate: pengguna masih memilih gambar, menempel prompt yang sudah disalin, dan menekan Generate di Flow. Video yang selesai diunduh dan diimpor ke KreasiFlow. Prompt AI mungkin mengonsumsi kuota provider teks.

## Cara update pada repository lama
1. Ganti ZIP root `KreasiFlow-Android-Update-V5-Source.zip` di repo `alfianzikri63-blip/KreasiFlow` dengan ZIP dari paket ini.
2. Jalankan workflow `Test KreasiFlow APK Build from ZIP` pada branch `main`.
3. Unduh artifact APK debug setelah workflow berhasil, lalu uji satu scene.
4. Untuk APK rilis gunakan signing key lama dan nomor versi 5.0.14 setelah seluruh fungsi lolos pengujian.

## Cek fungsi
1. Buka scene lama, pastikan ada dua kolom: Prompt Gambar dan Prompt Video Bergerak.
2. Tekan "AI prompt video semua scene" dan pastikan prompt tiap scene berbeda sesuai narasinya.
3. Edit prompt scene 1 secara manual, lalu tekan "AI prompt video semua scene"; edit tersebut harus tetap tersimpan.
4. Tekan "Siapkan di Google Flow" pada scene 1: prompt video, bukan prompt gambar, tersalin; gambar disimpan ke Downloads.
5. Unduh MP4 dari Flow lalu gunakan "Impor MP4 hasil Flow". Buat render MP4 di tab Render.

## Status
Paket disusun dari source v5.0.13; build Android lengkap dan uji langsung Google Flow perlu dilakukan di perangkat/GitHub Actions.
