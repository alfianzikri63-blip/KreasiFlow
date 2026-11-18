# KreasiFlow Studio v5.0.9 — Cloudflare Gambar AI

**Pengganti fal.ai:** Cloudflare Workers AI, model `@cf/black-forest-labs/flux-1-schnell`.

## 1. Dapatkan akses API Cloudflare

1. Buka https://dash.cloudflare.com/ dan buat akun jika belum ada.
2. Pilih akun → AI → Workers AI → **Use REST API**.
3. Buat **Workers AI API Token** lewat panduan Cloudflare dan salin **Account ID**. Ini dua nilai berbeda.
4. Jangan bagikan token lewat chat atau unggah ke GitHub.
5. Workers Free menyediakan **10.000 neurons/hari**; kuota gambar aktual bergantung model dan penggunaan. FLUX.1 Schnell tidak menjamin gambar berasio 9:16; saat dirender, KreasiFlow bisa memotong gambar.

## 2. Upload source ke GitHub

1. Unduh file `KreasiFlow-Android-Update-V5-Source.zip` dari paket ini.
2. Masuk ke repo https://github.com/alfianzikri63-blip/KreasiFlow.
3. Ganti file root `KreasiFlow-Android-Update-V5-Source.zip` dengan file baru **bernama persis sama**; commit.
4. Buka https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/test-build-from-zip.yml.
5. Pilih **Run workflow** pada branch `main` dan buka hasil run. Jika sukses, scroll ke **Artifacts → Test-Debug-APK-Not-Updateable**, unduh dan ekstrak ZIP artifact untuk mendapatkan `app-debug.apk`.
6. Instal debug APK di HP. **KreasiFlow Debug v5.0.9** memakai ID `id.kreasiflow.studio.debug`, jadi dapat berdampingan dengan APK rilis `id.kreasiflow.studio`. Data/kunci pada rilis tidak otomatis pindah ke debug.

## 3. Uji satu gambar di APK Debug

1. Masuk ⚙ → Pengaturan AI → Cloudflare API Token & Cloudflare Account ID.
2. Simpan keduanya; status “tersimpan” hanya berarti ada di perangkat, **bukan verifikasi jaringan**.
3. Buka tab **Scene & Gambar** dan pilih scene. Tekan **Generate gambar Cloudflare Workers AI** pada satu scene saja.
4. Jika gambar tampil, lanjut uji 2–3 scene sebelum membuat gambar massal.
5. Error 401/403: cek token, izin Workers AI dan Account ID. Error 429 atau quota exceeded: jatah harian kemungkinan habis; lihat penggunaan Workers AI di dashboard, lalu coba setelah reset.
6. Jika tidak ingin memakai layanan AI gambar, tombol **Upload gambar** tetap ada.

## 4. Rilis setelah debug berhasil

- Saat semua tes HP lulus, buka https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/release-apk-from-zip.yml.
- Isi versi `5.0.9`, gunakan **empat secrets signing lama** tanpa membuat kunci signing baru.
- APK release akan tetap memakai application ID `id.kreasiflow.studio` dan sistem auto-update GitHub Releases.
- Jangan instal APK debug sebagai update release, karena package ID dan tanda tangannya berbeda.

**Catatan:** Paket ini sudah diperiksa sintaks JavaScript dan diuji alur gambar dengan respons simulasi. Build Android dan API Cloudflare sungguhan belum diuji dari sesi ini. Tidak ada token/keystore signing dalam ZIP ini.
