# KreasiFlow Studio v5.0.3 — Auto Produksi AI

## Apa yang berubah
- APK full screen, tanpa panel split-screen dan WebView browser Google.
- **⚡ Generate semuanya otomatis**: ide judul → naskah → narasi → voice-over WAV → storyboard → gambar Gemini AI; di aplikasi, bukan mengendalikan antarmuka Google Flow.
- **🎙 Generate Suara AI** untuk narasi saja; audio terpasang langsung ke proyek.
- **🖼 Generate gambar AI** di tiap scene atau beberapa scene awal. Batas default 2 scene agar pemakaian kuota lebih terkendali.
- Hasil gambar terpasang langsung ke storyboard dan bisa disimpan; audio WAV juga bisa diunduh.
- Google AI Studio / Google Flow tetap dapat dibuka di Chrome sebagai cadangan. Fitur produksi di Google Flow tidak bisa dijalankan otomatis lewat situsnya.
- Sistem cek update dari GitHub Releases dipertahankan; ID aplikasi sama (`id.kreasiflow.studio`).

## Langkah update dari HP
1. Download **hanya** `KreasiFlow-Android-Update-V5-Source.zip` dari paket ini.
2. Di repository https://github.com/alfianzikri63-blip/KreasiFlow ganti file dengan nama **persis sama** di branch `main` (overwrite file ZIP lama).
3. Buka https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/test-build-from-zip.yml lalu **Run workflow**. Tunggu sampai sukses. Build debug untuk pengujian, jangan instal sebagai update rilis.
4. Buka https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/release-apk-from-zip.yml lalu **Run workflow** dengan versi `5.0.3`. Gunakan **keempat GitHub Secrets signing lama yang sama**, tidak perlu membuat key baru.
5. Setelah berhasil, buka https://github.com/alfianzikri63-blip/KreasiFlow/releases/tag/v5.0.3 dan unduh `KreasiFlow-Studio.apk`.
6. Instal di atas APK lama. Android meminta persetujuan. Jika muncul notifikasi update dalam aplikasi, ikuti. Kalau tidak muncul, gunakan browser Chrome untuk download release secara manual.

## Cara memakai
1. Buka KreasiFlow Studio → ⚙ Pengaturan AI.
2. Masukkan **Gemini API Key milik pengguna** dari https://aistudio.google.com/api-keys. Jangan mengirim API key lewat chat, GitHub, atau screenshots.
3. Isi topik/judul mentah, kategori, genre, gaya, dan durasi.
4. Pilih maksimal gambar AI (0, 1, 2, 3, atau 5). Default 2.
5. Tekan **⚡ Generate semuanya otomatis** → setujui pemakaian API/kuota. Proses berjalan dan menunjukkan progres.
6. Buka tab Proses untuk mendengarkan voice-over; tab Scene & Gambar untuk gambar AI; tab Render untuk menyusun video dari aset yang dihasilkan.

## Batasan & keamanan
- **Google Flow UI tidak bisa diisi/di-generate otomatis** dari APK ini. KreasiFlow memanggil endpoint resmi **Gemini API** untuk judul/naskah/TTS/gambar tanpa copy-paste.
- Pemakaian TTS/gambar memerlukan API key, kuota, ketersediaan model, dan mungkin billing. Periksa biaya sebelum produksi massal.
- API key hanya berada dalam memori sesi aktif, tidak dibenamkan ke APK/ZIP/commit. Harus dimasukkan lagi setelah aplikasi ditutup sepenuhnya.
- Pengujian yang telah dilakukan adalah uji alur browser dengan **respons API simulasi** dan cek sintaks JS. **Belum** dilakukan tes nyata API, build Android GitHub, atau uji langsung pada HP untuk versi ini; tidak boleh dianggap sukses sebelum GitHub Actions selesai dan diuji di perangkat.
- Jika TTS, gambar, atau network error, proses berhenti dengan pesan dan menyimpan teks/scene yang sudah dihasilkan. Jangan menekan Auto Produksi berkali-kali sekaligus.
- Render video lokal memakai WebView MediaRecorder, hanya bekerja jika codec tersedia di HP. Hasil bisa WebM alih-alih MP4.

Dokumentasi resmi API:
- https://ai.google.dev/gemini-api/docs/speech-generation
- https://ai.google.dev/gemini-api/docs/image-generation
- https://ai.google.dev/gemini-api/docs/rate-limits
