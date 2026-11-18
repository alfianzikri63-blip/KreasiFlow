# KreasiFlow Studio v5.0.16 — Tema Kreator Lengkap + OpenRouter Timeout Fix

Update berbasis v5.0.15, meneruskan semua fitur v5.0.14, tanpa mengganti tampilan inti.

## Tema baru
20 kategori baru (total 31 tema spesifik + pilihan acak dan tema sendiri): sejarah Indonesia, sejarah dunia, kerajaan/peradaban kuno, mitologi, horor, sains, teknologi & AI, luar angkasa, dunia hewan, fenomena alam, tubuh manusia, psikologi, bisnis, inspirasi, makanan/budaya, petualangan, fiksi, olahraga, fakta unik Indonesia, dan misteri kriminal fiksi.

Setiap kategori baru memuat 4 ide judul lokal sehingga tombol **Cari Ide Judul** bekerja walaupun API OpenRouter/Gemini timeout. Pilihan **Tema sendiri (ketik bebas)** menyimpan tema yang pengguna tulis dan menghasilkan 10 kerangka judul lokal. AI judul juga menggunakan tema khusus jika dipilih.

## OpenRouter timeout
Perbaikan timeout v5.0.15 tetap disertakan: batas tunggu web/native diselaraskan, prompt dibuat per batch kecil dan disimpan bertahap. Tidak melakukan retry otomatis yang dapat mengonsumsi kuota. Model OpenRouter gratis bisa tetap lambat/tidak tersedia; gunakan Gemini untuk teks jika perlu.

## Upload ke repo lama
1. Cadangkan proyek JSON dan source ZIP sebelumnya.
2. Ganti **KreasiFlow-Android-Update-V5-Source.zip** di root `alfianzikri63-blip/KreasiFlow` dengan ZIP dari paket ini (jangan unggah ZIP kit luar).
3. GitHub Actions → `Test KreasiFlow APK Build from ZIP` → `Run workflow` → unduh APK debug hasil artifact bila build berhasil.
4. Tanda tangan APK debug dapat berbeda dari yang terpasang. Jangan menghapus aplikasi lama sebelum mencadangkan proyek JSON.

## Batasan
Hanya lolos pemeriksaan sintaks JavaScript, integritas arsip, dan uji fungsi tema lokal pada lingkungan pengembangan. Belum ada pengujian build Gradle APK lengkap dan belum diuji OpenRouter sungguhan pada HP. Source tidak diunggah otomatis ke GitHub karena izin tulis integrasi saat ini tidak tersedia.
