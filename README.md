# Tugas Akhir
## Authors
* **Nur Fajar Sayyidul Ayyam** - *2106751114* - *A*
* **Son Sulung Suryahatta Asnan** - *2106751455* - *A*
* **Muhammad Rayhan Denel** - *2106752161* - *A*
* **Sakinah Richas** - *2106751511* - *A*

---
**Kontrak Tahap 1 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2106751114 | Nur Fajar Sayyidul Ayyam | A1 - A4 |
| 2106751455 | Son Sulung Suryahatta Asnan | A5 - A6, B1 - B2 |
| 2106752161 | Muhammad Rayhan Denel | B3 - B6 |
| 2106751511 | Sakinah Richas | B7 - B10 |
---
**Kontrak Tahap 2 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2106751114 | Nur Fajar Sayyidul Ayyam | A1 - A3 |
| 2106751455 | Son Sulung Suryahatta Asnan | B1 - B3 |
| 2106752161 | Muhammad Rayhan Denel | C1 - C4 |
| 2106751511 | Sakinah Richas | D1 - D4 |
---
**Kontrak Tahap 3 Tugas Akhir**

| NPM | Nama Lengkap | Fitur yang akan diselesaikan  |
| ----------| --- | ---------- | 
| 2106751114 | Nur Fajar Sayyidul Ayyam | C1 - C4 |
| 2106751455 | Son Sulung Suryahatta Asnan | D1 - D4 |
| 2106752161 | Muhammad Rayhan Denel | A1 - A3 |
| 2106751511 | Sakinah Richas | B1 - B3 |

Bobot Tugas
User service: 6 fitur
Catalog service: 10 fitur
Order service: 11 fitur
Frontend: 17 fitur

total: 44 fitur

Iterasi 1: User (6) + Catalog (10) = (16 fitur)
User Service 1-6: 
1. GET User by Id
2. POST user (sign up)
3. POST jwt token (login)
4. PUT Ubah Data User
5. DELETE user
6. PUT Menambah atau Mengurangi Balance

Catalog Service  1-10: 
1. POST Catalog
2. GET All Catalog by Seller Id
3. GET All Catalog (default by Name ASC)
4. GET Catalog by Catalog Id
5. PUT Catalog by Catalog Id
6. DELETE Catalog by Catalog Id
7. GET Catalog List by Catalog Name 
8. GET Catalog List by Catalog Price
9. GET Catalog List Sort by Price or Name and Ascending or Descending Order
10. GET All Category

Iterasi 2: Order (11) + Frontend (3) = (14 fitur)
- Order Service: 
1. POST Cart (add new cart with user id)
2. POST cart_items (add item into cart)
3. PUT cart_items (edit quantity)
- Order Service + Frontend: 
1. Get cart_items by user_id
2. DELETE cart_items
3. [Not logged in] Login & Register page
- Order Service + Frontend: 
1. Get order by customer_id
2. Get order by seller_id
3. [Seller & Not logged in] Catalog Page
4. PUT User (update saldo penjual dan pembeli ketika pembeli tekan selesai)
- Order Service:
1. GET top X products by category (weekly and monthly)
2. POST Order
3. Get order by seller_id
4. [Seller] Graph di Catalog Page

Iterasi 3 : Frontend (14 fitur)
- Frontend WebApp
1. [Seller] Edit Product Page
2. [Seller] Add Product
3. [Seller] Order History Page
- Frontend WebApp
1. [Seller] Profile Page
2. [Seller] Top-up Page
3. [Seller] Edit Profile Page
- Frontend Mobile
1. [Not logged in] Login & Register page
2. [Customer & Not logged in] Catalog Page
3. [Customer] Product Detail Page
4. [Customer] Cart Page
- Frontend Mobile
1. [Customer] Order History Page
2. [Customer] Profile Page
3. [Customer] Top-up Page
4. [Customer] Edit Profile Page
