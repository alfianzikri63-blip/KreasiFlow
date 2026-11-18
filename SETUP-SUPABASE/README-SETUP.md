# KreasiFlow v5.0.23 — Login Supabase + Backup API Key Terenkripsi

## Penting sebelum instal
- APK yang di-update dengan **applicationId sama + sertifikat signing sama** biasanya mempertahankan penyimpanan Android. Supabase adalah cadangan untuk kasus uninstall, pindah HP, atau debug APK dengan ID/signing berbeda.
- Jangan uninstall versi lama sebelum API key selesai dicadangkan. Ekspor proyek JSON juga; fitur backup ini hanya untuk API key, bukan gambar, video, file audio, atau seluruh proyek.
- **Jangan gunakan proyek Supabase lain** (mis. proyek aplikasi yang tidak terkait) sebagai penyimpanan KreasiFlow tanpa memutuskan pemisahan data terlebih dahulu.

## 1. Siapkan proyek Supabase
1. Buat proyek baru bernama KreasiFlow di https://supabase.com/dashboard.
2. Buka **SQL Editor**, salin dan jalankan `01_kreasiflow_key_backups.sql`.
3. Periksa **Authentication → Providers → Email** diaktifkan; umumnya email perlu dikonfirmasi.
4. Periksa **Project Settings → API Keys**, salin **Project URL** dan **publishable key** `sb_publishable_...`. Keduanya bukan rahasia (RLS harus aktif), **jangan gunakan** secret / service_role.
5. Kalau tabel REST tidak terlihat di API, periksa Integrations → Data API → exposed `public` schema. SQL sudah menetapkan GRANT yang sesuai untuk authenticated.

## 2. Daftar & login di KreasiFlow
1. Build APK dari source ZIP melalui workflow GitHub repo lama.
2. Buka **☁ Akun** pada toolbar (atau ⚙ Pengaturan AI → Login + Backup Supabase).
3. Isi Project URL + publishable key; tekan **Simpan konfigurasi**.
4. Buat akun email/password, konfirmasi email jika diminta, lalu login.
5. API key yang sudah ada di HP tetap tersimpan di Android Keystore. Login tidak langsung mengirim key ke cloud.

## 3. Cadangkan API key sebelum ganti HP / debug APK
1. Pastikan API key Gemini, OpenRouter, Cloudflare, atau Replicate sudah tersimpan lokal.
2. Setelah login, isi **passphrase backup berbeda dari password login (minimal 12 karakter)**.
3. Ketuk **Cadangkan API key**, lalu setujui konfirmasi. Backup baru menggantikan backup cloud lama.
4. **Catat passphrase secara terpisah dan aman.** Jika hilang, isi backup tidak dapat didekripsi oleh Supabase maupun pengembang.

## 4. Pulihkan pada APK baru
1. Pasang APK baru. Buka ☁ Akun, isi konfigurasi proyek yang sama, lalu login dengan akun yang sama.
2. Masukkan passphrase backup dan pilih **Pulihkan key ke HP**.
3. API key yang sudah ada di HP **tidak ditimpa**; hanya key lokal yang belum ada yang dipulihkan.
4. Uji koneksi Gemini/OpenRouter setelah restore. Untuk berpindah akun, Logout dan login lagi.

## Keamanan
- API key tidak dimasukkan ke JSON proyek, GitHub, sumber APK, atau log.
- Backup berisi ciphertext AES-256-GCM dengan PBKDF2-HMAC-SHA256 (250.000 iterasi), salt/nonce acak dan AAD terikat user ID.
- Supabase menyimpan hanya ciphertext dan metadata. RLS `auth.uid() = user_id` membatasi data per pengguna.
- Password login, passphrase backup, dan API key tidak disimpan di database sebagai plaintext. Token sesi Supabase disimpan terenkripsi dengan Android Keystore.
- Jika API key provider pernah terekspos ke publik, segera rotasi key tersebut. Backup terenkripsi tidak memperbaiki key yang sudah bocor.
- **Tidak ada backup otomatis**: tekan Cadangkan setelah menambah/mengganti API key. Ini mencegah overwrite dan pengiriman yang tidak diminta.

## Batasan
- Belum diuji terhadap Supabase proyek nyata dan APK Android penuh karena belum tersedia proyek KreasiFlow yang dikonfigurasi. Semua operasi memerlukan koneksi internet.
- Pengaturan URL/publishable key dimasukkan oleh pemilik proyek pada APK; bukan rahasia dan tidak boleh berupa service_role.
- Signing APK release harus konsisten supaya bisa update tanpa hapus data lokal. APK debug dari runner yang berbeda mungkin memakai sertifikat berbeda.
