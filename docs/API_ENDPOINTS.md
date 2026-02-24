# API Endpoint List (Draft)

Base URL: `https://api.pos-umkm.com/v1`

## Auth
- `POST /auth/register` - registrasi admin toko
- `POST /auth/login` - login admin/kasir
- `POST /auth/logout` - logout + revoke token

## Product
- `GET /products`
- `POST /products`
- `PUT /products/{id}`
- `DELETE /products/{id}`
- `GET /products/barcode/{barcode}`

## Sales
- `POST /sales/sync` - kirim batch transaksi offline
- `GET /sales/reports/daily`
- `GET /sales/reports/weekly`
- `GET /sales/reports/monthly`

## Stock
- `POST /stocks/movement`
- `GET /stocks/movement/history`

## Customers
- `GET /customers`
- `POST /customers`
- `GET /customers/{id}/transactions`
