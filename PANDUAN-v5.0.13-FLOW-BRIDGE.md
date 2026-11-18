# KreasiFlow Studio v5.0.13 — Google Flow Bridge (basis v5.0.12)

## Tetap dipertahankan
- Seluruh tampilan v5.0.12, Gemini judul/naskah/storyboard, provider alternatif, AI gambar, voice-over, serta render MP4.
- Penyimpanan API key lokal dan opsi video API berbayar dengan konfirmasi.

## Baru
1. Pada scene dengan gambar, tap **Siapkan di Google Flow**.
2. KreasiFlow menyimpan gambar otomatis di `Download/KreasiFlow/KreasiFlow-Flow-Scene-XX.jpg|png|webp`, menyalin motion prompt dengan Android clipboard, lalu membuka Flow di Chrome.
3. Pengguna di Flow memilih model Video → Frames, unggah gambar dari Download/KreasiFlow, tempel prompt dan tekan Generate. Tidak ada API key video diperlukan, tetapi tetap memerlukan kredit Flow milik akun.
4. Unduh MP4 dari Flow secara normal. Kembali ke KreasiFlow, pilih **Impor MP4 hasil Flow** untuk scene itu.
5. Di tab Render gunakan **Gabungkan klip AI + narasi MP4**. Hanya klip H.264 yang kompatibel (dimensi dan codec cocok) dapat digabung tanpa re-encode pada renderer native yang ada. Bila gagal karena format Flow, file MP4 sumber tidak dihapus.

## Batasan penting
Google Flow tidak memiliki API publik yang terdokumentasi untuk aplikasi pihak ketiga. APK ini **TIDAK** mengotomatisasi upload, klik Generate, pengunduhan, atau tindakan akun Chrome. Itu tidak dapat dilakukan secara aman melalui Android WebView/Chrome Custom Tabs tanpa dukungan API atau otomasi antarmuka berizin. Tidak ada scraping cookie, bypass login, injection browser, atau akses Accessibility.

## Play Protect
Google Play Protect adalah mekanisme sistem Android, bukan prompt yang bisa ditonaktifkan lewat kode aplikasi. APK debug menghapus izin instalasi aplikasi dan notifikasi yang hanya dibutuhkan oleh updater release untuk mengurangi izin yang tidak perlu; **ini bukan bypass Play Protect dan tidak menjamin pemindaian hilang**. Gunakan APK signed release konsisten untuk update produksi, dan distribusi resmi Google Play (internal testing / production) untuk kemudahan instalasi. Jangan mengganti signing key yang sudah dipakai aplikasi utama.

## Build di repo lama
1. Ganti ZIP di root `KreasiFlow-Android-Update-V5-Source.zip` dengan ZIP source ini, **bukan ZIP kit luar**.
2. Actions → `Test KreasiFlow APK Build from ZIP` → Run workflow; artifact debug terpisah dari aplikasi utama.
3. APK debug bukan update produksi. Setelah diuji, workflow `Publish Signed KreasiFlow APK from ZIP` versi `5.0.13` dengan **keystore dan 4 GitHub Secrets yang sudah terpasang**. Jangan menaruh kunci signing/API dalam repo.
4. Google Play Protect masih dapat memindai sideload APK. Gunakan signing dan distribusi yang tepercaya.

## QA lokal
- Struktur ZIP sama dengan workflow test existing.
- `node --check` JavaScript dan parser XML manifest; pemeriksaan lengkap Gradle/Android SDK & Google Flow aktual belum dilakukan.
