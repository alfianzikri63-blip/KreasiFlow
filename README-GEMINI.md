# KreasiFlow Studio v5.0.12 — Gemini API Debug

## Fitur

- Judul/naskah/storyboard: Gemini 2.5 Flash (alternatif OpenRouter)
- Gambar: Gemini 3.1 Flash Lite Image (alternatif Cloudflare FLUX.1 Schnell)
- Voice-over: Gemini 3.8 Flash Lite TTS (alternatif ElevenLabs). Hanya dialog narator dikirim, arahan panggung dibersihkan.
- Image-to-video: Gemini Omni 1.2 Flash, 360p, format portrait/landscape, melalui Interactions API (alternatif Replicate Wan 2.2). Mulai dari satu scene saja.
- Render final: native Android dari klip AI dan voice-over, serta slideshow cadangan.
- API key disimpan terenkripsi di Android Keystore. APK debug berbeda package ID dengan APK release.
- GitHub Releases auto-update tetap aktif untuk RELEASE signed, bukan debug.

## Langkah pertama untuk Google AI Pro

1. Buka https://me.developers.google.com/benefits dan klaim manfaat developer US$10/bulan bila tersedia di akun.
2. Buka https://aistudio.google.com/api-keys dan buat/copy Gemini API key pada proyek yang terhubung billing.
3. Periksa https://aistudio.google.com/billing dan https://aistudio.google.com/usage. Langganan AI Pro tidak otomatis meningkatkan kuota Gemini API; API gambar/video dapat berbayar.
4. Di APK Debug, buka ⚙ → masukkan Gemini API Key → Simpan → Uji koneksi Gemini (model tidak dipanggil, tidak menghabiskan token generasi).
5. Pastikan penyedia teks/gambar/suara/video = gemini. Uji judul dulu, lalu satu gambar. Jangan langsung 20 gambar.
6. Untuk video bergerak, pilih satu gambar scene, klik 🎞 Buat klip AI bergerak, setujui biaya hanya jika billing sudah aktif. Tunggu status selesai dan klik ▶ Putar klip AI.
7. Lanjut tab Render → Gabungkan klip AI + narasi MP4.

## GitHub Debug dari ZIP

- Ganti `KreasiFlow-Android-Update-V5-Source.zip` di root repo dengan ZIP sumber yang disertakan (isinya folder `KreasiFlow-Studio-Android-Updater-V5/`).
- Jalankan Actions → `Test KreasiFlow APK Build from ZIP`.
- Download artifact `Test-Debug-APK-Not-Updateable`, ekstrak, pasang APK Debug di HP. Tidak perlu uninstall aplikasi utama.
- JANGAN jalankan `Build KreasiFlow Debug APK` lama dari folder root karena folder root repo masih belum lengkap.
- JANGAN Publish Signed Release sampai API Gemini asli dan render native diuji.

## Batasan

Konektor sudah dibuat mengikuti dokumen API Google, tetapi **belum diuji dengan kredensial sungguhan** dan belum dikompilasi oleh GitHub pada tahap penyerahan ini. Keberhasilan login Google AI Pro tidak membuktikan akses Gemini API. Mode Omni mungkin menghasilkan file MP4 dengan codec yang perlu ditranscode di perangkat tertentu. Hanya gunakan API key milik sendiri; jangan unggah ke GitHub atau chat.

Dokumentasi:
- https://aistudio.google.com/docs/video
- https://aistudio.google.com/docs/omni
- https://aistudio.google.com/docs/image-generation
- https://aistudio.google.com/docs/speech-generation
- https://developers.google.com/profile/help/benefits
