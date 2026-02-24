# POS UMKM Android (Blueprint + Starter Code)

Starter project arsitektur POS Android berbasis Kotlin untuk UMKM Indonesia dengan prinsip **offline-first**, **Clean Architecture**, dan **MVVM**.

## Tech Stack
- Kotlin
- MVVM + Jetpack Components
- Room Database (local source of truth)
- Firebase/REST API (sinkronisasi)
- XML + Jetpack Compose
- Bluetooth thermal printer
- Barcode scanner
- PDF generator

## Struktur Folder

```text
android-pos/
  app/src/main/java/com/umkm/pos/
    data/
      local/
        dao/
        entity/
      repository/
    domain/
      model/
      repository/
      usecase/
    ui/
      base/
      auth/
      dashboard/
      product/
      transaction/
      theme/
    utils/
    di/
  app/src/main/res/layout/
docs/
  ERD.md
  API_ENDPOINTS.md
  USAGE.md
```

## Modul Fitur Utama
1. Autentikasi + Role Admin/Kasir
2. Dashboard (sales hari ini, transaksi, produk terlaris)
3. Manajemen produk + barcode
4. Transaksi POS + pembayaran tunai/QRIS
5. Stok masuk/keluar/adjustment
6. Cetak struk Bluetooth + PDF + share
7. Laporan harian/mingguan/bulanan
8. Sinkronisasi online + conflict resolution

## Build APK Debug
> Jalankan di Android Studio karena environment CLI ini tidak menyertakan Android SDK lengkap.

```bash
./gradlew assembleDebug
```

APK debug akan berada di:
`app/build/outputs/apk/debug/app-debug.apk`

## Catatan Implementasi
- Gunakan enkripsi lokal untuk data sensitif (PIN/token).
- Session login disimpan aman via DataStore + token expiry.
- Untuk scanner barcode direkomendasikan ML Kit / ZXing.
- Untuk printer Bluetooth direkomendasikan ESC/POS compatible library.
