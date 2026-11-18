# Perbaikan "Gagal menyimpan: openrouter, fal, elevenlabs" — v5.0.6

## Diagnosa

Di source APK v5.0.5, `ProviderSecrets.save()` memakai `Cipher.init(ENCRYPT_MODE, aesKey(), new GCMParameterSpec(128, nonce))`. Android Keystore melarang IV yang disediakan aplikasi saat mengenkripsi jika `setRandomizedEncryptionRequired` memakai pengaturan default (`true`). Akibatnya ketiga key bisa gagal disimpan meskipun key sah.

## Apa yang diperbaiki

1. Android yang menentukan IV GCM secara acak (`c.init(Cipher.ENCRYPT_MODE, aesKey()); byte[] iv=c.getIV();`) dan ciphertext tetap dienkripsi memakai Android Keystore.
2. Jika ada key gagal disimpan, formulir pengaturan tidak hilang; tampil pesan aman tanpa memperlihatkan credential.
3. Perbaikan mempertahankan `applicationId=id.kreasiflow.studio`, updater GitHub Releases, folder proyek, dan mekanisme signing lama.
4. Paket ini didasarkan pada source **v5.0.6 dengan Render MP4** yang sebelumnya disiapkan. Fitur tersebut masih perlu pengujian native pada HP.

## Langkah GitHub lewat HP

1. Unduh `KreasiFlow-Android-Update-V5-Source.zip` dari paket ini.
2. Buka `https://github.com/alfianzikri63-blip/KreasiFlow` dan ganti file di root repository yang namanya sama. Jika GitHub tidak mengizinkan upload langsung, hapus file lama lalu upload file baru.
3. Jalankan workflow `Test KreasiFlow APK Build from ZIP` di tab Actions. Jangan lanjut apabila gagal.
4. Jalankan `Publish Signed KreasiFlow APK from ZIP` dengan `version=5.0.6` dan **empat GitHub Secrets signing lama**. Jangan mengubah kunci signing atau menaruhnya di repo.
5. Jika sukses, download `KreasiFlow-Studio.apk` dari tab Releases versi 5.0.6, kemudian instal menimpa v5.0.5. **Jangan uninstall aplikasi lama** karena proyek yang ada di storage aplikasi bisa hilang.
6. Buka KreasiFlow > API Keys > masukkan OpenRouter API key lalu Simpan. Buka kembali pengaturan, pastikan `✓ tersimpan`. Ulangi untuk fal.ai dan ElevenLabs. Jangan kirim API key atau screenshot yang memperlihatkannya.

## Bila masih gagal

Lihat pesan kesalahan baru setelah `Gagal menyimpan ...`. Kirim teks pesan error tersebut **tanpa API key**. Jika semua error tetap, gunakan logcat filter `KreasiFlow` di perangkat untuk mendapatkan penyebab yang pasti.

## Status pengujian

- Validasi struktur ZIP dan pemeriksaan sintaks JavaScript: lulus.
- Simulasi browser saat penyimpanan gagal/berhasil dan status 3 key: lulus.
- Uji AES-GCM di JDK dengan `Cipher` pembuat IV sendiri + dekripsi: lulus.
- Build Android GitHub serta AndroidKeyStore/MP4 pada HP nyata: **belum diuji**.
