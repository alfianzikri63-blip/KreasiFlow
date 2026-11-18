# KreasiFlow Studio v5.0.4 — Solusi Kuota Gambar Gemini

## Penyebab error di v5.0.3

Ketika aplikasi memanggil model `gemini-3.1-flash-image`, Google mengembalikan `quota exceeded ... generate_content_free_tier_requests, limit: 0`. Ini berarti project/API key tersebut **tidak memiliki jatah Free Tier untuk membuat gambar melalui API model tersebut**. Ganti ke `gemini-3.1-flash-lite-image` **tidak** mengubah status Free Tier untuk model gambar menjadi gratis.

Rujukan resmi: https://ai.google.dev/gemini-api/docs/pricing dan https://ai.google.dev/gemini-api/docs/rate-limits.

## Cara praktis tanpa membayar (APK v5.0.3)

1. Di KreasiFlow buka tab **Upload** (tab pertama).
2. Di **⚡ Auto Produksi AI**, set **Gambar AI otomatis** ke **0 scene**.
3. Jalankan judul, naskah, narasi, dan storyboard. Generasi suara bergantung kuota model TTS akun.
4. Untuk gambar, pilih **Upload gambar** secara manual atau buka **Google Flow** di Chrome dan ambil hasilnya sesuai kebijakan layanan.

## Apa yang berubah di source v5.0.4

- Jumlah gambar otomatis untuk proyek **baru** default **0** untuk menghindari panggilan model gambar berbayar secara tak sengaja.
- Jika API membalas kuota 0, aplikasi menunjukkan pesan singkat dan link **Cek Kuota** / **Billing API**; tidak menampilkan pop-up teks error panjang.
- Setelah mendeteksi kuota gambar 0, aplikasi berhenti melakukan permintaan gambar berulang selama sesi tersebut.
- Bagian naskah, audio, dan prompt storyboard yang sudah dihasilkan tetap dipertahankan. Pengguna bisa melanjutkan dengan gambar manual.
- Jika ingin mencoba lagi setelah upgrade billing, tutup dan buka kembali aplikasi. Biaya sesuai tarif Google.

## Memasang source baru lewat GitHub

1. Unduh `KreasiFlow-Android-Update-V5-Source.zip` dari paket ini.
2. Di repository https://github.com/alfianzikri63-blip/KreasiFlow ganti ZIP lama di **root** dengan ZIP baru bernama sama. Jangan unggah source ZIP privat signing.
3. Jalankan workflow **Test KreasiFlow APK Build from ZIP**.
4. Jika test berhasil, jalankan **Publish Signed KreasiFlow APK from ZIP** dengan versi `5.0.4`. Pastikan GitHub Secrets signing sama seperti release sebelumnya.
5. Setelah release sukses, unduh APK baru di GitHub Releases dan instal di atas aplikasi lama (jangan uninstall agar data lokal tak terhapus).

**Penting:** Patch telah lulus pengecekan sintaks JavaScript, struktur ZIP, dan uji antarmuka simulasi error Free Tier 0. Belum diuji kompilasi Android, fitur live API, atau perangkat HP. Jika workflow gagal, periksa log build sebelum menerbitkan release.
