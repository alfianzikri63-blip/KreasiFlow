# KreasiFlow Studio v5.0.17 — OpenRouter Respons Kosong Fix

Berbasis source v5.0.16. Tampilan, 31 tema, mode tema sendiri, narasi, suara, gambar, Google Flow dan renderer tetap dipertahankan.

## Perubahan
- Respons OpenRouter 200 tanpa teks kini dilaporkan dengan model dan finish_reason (termasuk kasus max_tokens habis untuk reasoning).
- Pembuatan prompt gambar dipecah menjadi 2 scene per permintaan, batas token dinaikkan dari 1550 ke 3500.
- Prompt video diproses per 2 scene, batas token dinaikkan dari 1250 ke 3200.
- Grup yang berhasil langsung disimpan, dan klik berikutnya hanya mencoba scene yang belum selesai.
- Prompt gambar yang diedit pengguna tidak akan ditimpa AI. Ketika kolom prompt dikosongkan, scene dapat diproses ulang.
- Tidak ada retry otomatis atau pergantian model otomatis demi menghindari permintaan tambahan dan pengeluaran kuota tak terduga.
- Tidak mengubah API key, ID aplikasi maupun data proyek yang tersimpan.

## Memasang
1. Upload file KreasiFlow-Android-Update-V5-Source.zip menggantikan ZIP nama sama di root repository.
2. Jalankan workflow Test KreasiFlow APK Build from ZIP dari tab Actions.
3. Setelah build berhasil, unduh APK debug dari artifact.
4. Pada aplikasi, lakukan tes dulu 2 prompt scene dengan model teks OpenRouter yang disetel di pengaturan.
5. Jika error `finish_reason=length` atau respons kosong terus muncul, pilih satu model teks `:free` tertentu dari katalog resmi OpenRouter, atau gunakan Gemini. Tidak perlu mengganti API key ketika muncul respons kosong.

## Batasan pengujian
Pengujian paket: sintaks JavaScript dan integritas ZIP. Belum diuji API OpenRouter langsung atau instal APK pada perangkat nyata.
