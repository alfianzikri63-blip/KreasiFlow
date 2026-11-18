# KreasiFlow Studio v5.0.12 — Perbaikan Gemini 404

**Masalah:** tombol Generate judul gagal dengan `Gemini HTTP 404: models/gemini-2.5-flash is no longer available to new users`.

**Perbaikan:** model Gemini teks diperbarui menjadi `gemini-3.8-flash`. Jika Google menolak model itu dengan HTTP 404, aplikasi mencoba `gemini-3.5-flash-lite` satu kali. Error 401/403/429 tidak mengaktifkan fallback agar tidak menutupi masalah API key/kuota. Label error di pembuat judul/naskah/prompt kini menampilkan provider yang sebenarnya. Fitur Gemini gambar, TTS, video AI, OpenRouter, Cloudflare, ElevenLabs, Replicate, dan render tidak diubah.

## Memasang dari HP dengan GitHub Actions

1. Buka repository `alfianzikri63-blip/KreasiFlow`.
2. Upload file **KreasiFlow-Android-Update-V5-Source.zip** dari paket ini ke root repository dan **ganti** file lama dengan nama yang sama. Jangan upload ZIP kit luarnya.
3. Buka **Actions > Test KreasiFlow APK Build from ZIP > Run workflow** pada branch `main`.
4. Jika build hijau, buka run tersebut dan unduh artifact **Test-Debug-APK-Not-Updateable**. Ekstrak APK lalu instal.
5. Buka KreasiFlow > ⚙ > Uji koneksi Gemini, lalu uji satu judul dahulu.

**Penting:** APK debug hasil build berbeda bisa mempunyai sertifikat debug yang berubah antar-run. Jika Android menolak pembaruan, simpan/ekspor proyek JSON terlebih dahulu; setelah itu mungkin perlu uninstall APK debug lama dan memasang yang baru. Ini bisa menghapus API key lokal (perlu input ulang). APK release resmi harus ditandatangani dengan signing key release yang sama; jangan publish sebelum pengujian selesai.

**Belum diuji langsung:** Kompilasi Gradle/GitHub Actions pada repo dan panggilan live Gemini dengan API key. Tidak ada API key di arsip. Langganan Google AI Pro tidak otomatis memberi kuota API Gemini.

Sumber model Google (18 November 2026): https://firebase.google.com/docs/ai-logic/models
