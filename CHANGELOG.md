# KreasiFlow Studio v5.0.9 — Cloudflare Workers AI

- Mengganti provider gambar fal.ai dengan Cloudflare Workers AI FLUX.1 Schnell (REST API resmi).
- Pengaturan API gambar sekarang menggunakan Cloudflare API Token + Account ID dengan enkripsi Android Keystore.
- URL API fixed dan dibatasi ke `api.cloudflare.com/client/v4/accounts/{id}/ai/run/@cf/black-forest-labs/flux-1-schnell`.
- Respons `result.image` base64 diperiksa format dan batas ukuran sebelum ditampilkan di scene.
- Peringatan kuota Cloudflare 10.000 neurons per hari; tidak menjanjikan jumlah gambar tertentu.
- Mempertahankan OpenRouter untuk teks, ElevenLabs untuk suara, render MP4, dan pemeriksaan pembaruan GitHub Releases.
- Debug APK memiliki `applicationIdSuffix '.debug'` supaya dapat dipasang berdampingan tanpa mengganti aplikasi produksi; updater dimatikan hanya pada varian debug.
- Perlu build dan pengujian API asli pada perangkat.
