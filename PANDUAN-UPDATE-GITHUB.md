# Upload v5.0.5 ke GitHub dari HP

Repository tujuan: https://github.com/alfianzikri63-blip/KreasiFlow

## Persiapan

- Tidak perlu mengganti GitHub Actions Secrets untuk **signing APK**; gunakan empat Secrets yang sudah dipakai rilis sebelumnya.
- Jangan unggah OpenRouter, fal.ai, atau ElevenLabs API key ke GitHub. Masukkan semuanya dari ⚙ **di dalam APK**, setelah instalasi.
- Aplikasi masih menggunakan `id.kreasiflow.studio` dan updater GitHub Releases lama.

## 1. Ganti ZIP source

1. Unduh file `KreasiFlow-Android-Update-V5-Source.zip` dari paket ini.
2. Buka https://github.com/alfianzikri63-blip/KreasiFlow
3. Di tab Code, **hapus ZIP lama dengan nama sama** jika GitHub Mobile tidak mengizinkan overwrite, lalu unggah ZIP baru dengan nama **persis sama** di root repository. Hindari dua file dengan nama mirip.
4. Commit perubahan. **Jangan unggah ZIP Kit besar, screenshot, atau kunci signing.**

## 2. Test Build

Buka: https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/test-build-from-zip.yml

Pilih `Run workflow` pada branch `main`. Periksa hasil hijau, terutama tahap **Build debug (only for smoke test)**. Artifact debug itu bukan APK update publik.

## 3. Rilis APK update

Buka: https://github.com/alfianzikri63-blip/KreasiFlow/actions/workflows/release-apk-from-zip.yml

1. `Run workflow` pada branch `main`.
2. Input version: `5.0.5`.
3. Periksa build **Build signed release APK** dan **Publish GitHub Release + APK** berhasil.
4. Buka https://github.com/alfianzikri63-blip/KreasiFlow/releases/tag/v5.0.5
5. Unduh `KreasiFlow-Studio.apk` dan pasang di atas APK sebelumnya (jangan uninstall).

Jika repo Actions memberi error izin tulis, periksa Settings → Actions → General → Workflow permissions: read/write contents. GitHub dapat menerapkan pembatasan akun sehingga proses perlu bantuan GitHub Support.

## 4. Atur provider di HP

⚙ Pengaturan Multi-Provider:
- OpenRouter API key (model awal `openrouter/free`) — https://openrouter.ai/settings/keys
- fal.ai API key — https://fal.ai/dashboard/keys
- ElevenLabs API key + Voice ID — https://elevenlabs.io/app/settings/api-keys

Mulai dengan judul dan naskah. Coba 1 suara. Barulah pilih 1 gambar jika saldo fal.ai sudah tersedia.

**Catatan:** Uji lokal dilakukan memakai API palsu untuk memeriksa alur; panggilan API sungguhan dan hasil kompilasi GitHub belum diuji untuk source ini.
