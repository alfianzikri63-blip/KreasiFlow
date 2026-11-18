# KreasiFlow Studio v5.0.4 — Kuota Gambar

- Mengatasi tampilan error `429 RESOURCE_EXHAUSTED / limit: 0` yang memenuhi layar.
- Memberikan pesan jelas saat API gambar tidak tersedia di Free Tier.
- Default 0 gambar untuk project baru (mode hemat), tanpa mengurangi fungsi judul/naskah/narasi/storyboard.
- Menghentikan loop generate gambar ketika kuota 0 terdeteksi, mempertahankan data yang sudah berhasil dibuat.
- Menambahkan tombol akses cepat Cek Kuota & Billing API di Chrome.
- Tidak melewati billing atau menciptakan kuota API baru.
