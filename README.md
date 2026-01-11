Albert Louis Lobian - Technical Test Back End Developer - Credit simulator

Aplikasi ini dikerjakan menggunakan bahasa java dan maven.

Fitur aplikasi:
1. Menghitung simulasi bulanan dari kredit Mobil atau Motor.
2. Menyimpan simulasi dalam bentuk sheets(.txt)
3. Memperlihatkan sheets yang sudah di simpan sebelumnya.

Langkah-Langkah pengunaan aplikasi:
1. Menggunakan command ./credit_simulator atau ./credit_simulator file_input.txt (menggunakan file yang di folder sheets).
2. Setelah berhasil di run, aplikasi akan menampilkan 2 menu, show dan exit.
3. Ketika user ketik show, aplikasi akan memanggil API untuk menemukan existing calculation dan menampilkannya.
4. User dapat memilih 4 menu Calculate credit simulation, Save Current Sheet, Switch Sheet dan exit.

# a. Calculate credit simulation
5a. Ketika user ketik "1", user akan dibutuhkan untuk mengisi: 
- Jenis Kendaraan Motor|Mobil
- Kendaraan  Bekas|Baru
- Tahun kendaraan
- Jumlah Pinjaman
- Tenor Pinjaman
- Jumlah DP
6a. Ketika user berhasil mengisi semua data yang sesuai, aplikasi akan menampilkan hasil simulasi kredit.

# b. Save Current Sheet
5b. Ketika user ketik 2, user akan dibutuhkan untuk mengisi nama sheets yang akan disimpan.
6b. aplikasi akan menyimpan sheets dalam bentuk file .txt di folder sheets.

# c. Switch Sheet
5c. Ketikauser ketik 3, aplikasi akan menampilkan sheets yang dapat dipilih melalui folder sheets.
6c. Sesudah user memilih sheets dengan nomor, aplikasi akan menampilkan hasil simulasi kredit sheets yang dipilih.

7. user bisa ketik 4 atau exit untuk keluar dari aplikasi.

# Docker Images
Pull image: docker pull albertlouisl/credit-simulator:latest
Run: docker run -it --rm albertlouisl/credit-simulator:latest

# GitHub link
https://github.com/AlbertLouis-L/credit-simulator
