# KreasiFlow v5.0.23 — Proyek Supabase Terhubung

Proyek: **Kreasiflow** (ref `ykmatipfszslrdmqqxib`) — Asia Tenggara / Singapura.
URL API: `https://ykmatipfszslrdmqqxib.supabase.co`

## Database terpasang
- Tabel `public.kreasiflow_key_backups` dibuat menggunakan migrasi.
- RLS aktif dan empat kebijakan per akun sudah diverifikasi.
- Akses anon ke tabel ditolak, authenticated dibatasi RLS.
- Pemeriksaan security advisors tidak menemukan temuan saat pengujian awal.

## Login dalam APK
- URL dan publishable key Supabase sudah terisi otomatis pada **instalasi baru**. Ini adalah key publik, bukan secret.
- Dari menu **☁ Akun**: daftar email/password, konfirmasi email bila diwajibkan, lalu login.
- Setelah API key provider disimpan di Keystore, masukkan passphrase khusus backup minimal 12 karakter dan pilih **Cadangkan API key**.
- Pemulihan memerlukan akun yang sama DAN passphrase backup. Key yang sudah ada di HP tidak ditimpa.
- Backup API key bukan backup naskah, gambar, scene atau video. Ekspor proyek JSON terpisah.
- Perbarui API key? Cadangkan lagi secara manual.

## Penting
- Jangan pakai service_role ataupun sb_secret di APK.
- Jangan uninstall APK lama sebelum backup berhasil dan proyek diekspor.
- APK debug memiliki `applicationIdSuffix .videotest`, sehingga data Keystore tidak langsung ikut dari aplikasi utama. Gunakan sertifikat penandatangan dan applicationId sama untuk update rilis tanpa kehilangan data.
- Login dan backup dari APK nyata belum diuji. Belum ada akun yang didaftarkan melalui sistem ini dan belum ada backup API key di Supabase.
- Jika email harus dikonfirmasi, lihat Supabase Dashboard → Authentication → Providers → Email.
