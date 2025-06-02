import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PendaftaranWisuda {
    Scanner scanner = new Scanner(System.in);
    List<Users> daftarUsers = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    List<Admin> daftarAdmin = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswaWisuda = new ArrayList<>();
    private int pilihan;
    private int opsi;
    public void main(String[] args) {
        mainFunction();
    }

    public void mainFunction() {
        login();
    }

    public void login() {
    System.out.println("=======================================");
    System.out.println("||Sistem Pendaftaran Wisuda Mahasiswa||");
    System.out.println("||         Universitas Lampung       ||");
    System.out.println("=======================================\n");

    boolean running = true;

    while (running) {
        System.out.print("Username : ");
        String username = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();

        Users loggedInUser = null;
        // Cari user di daftarUsers
        for (Users user : daftarUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {
            System.out.println("Selamat Datang " + username);

            if (loggedInUser instanceof Admin) {
                System.out.println("Login sebagai Admin.");
                menuAdmin((Admin) loggedInUser);
            } else if (loggedInUser instanceof Mahasiswa) {
                System.out.println("Login sebagai Mahasiswa.");
                menuMahasiswa((Mahasiswa) loggedInUser);
            } else {
                System.out.println("Role tidak dikenali.");
            }

            // Setelah user selesai dari menuAdmin/menuMahasiswa, tanya apakah ingin logout
            System.out.print("Apakah Anda ingin Logout? (Y/N): ");
            String keluar = scanner.nextLine();
            if (keluar.equalsIgnoreCase("Y")) {
                running = false;
            }

        } else {
            System.out.println("Maaf, Username atau Password Anda salah.");
            System.out.print("Coba lagi? (Y/N): ");
            String cobaLagi = scanner.nextLine();
            if (cobaLagi.equalsIgnoreCase("N")) {
                running = false;
            }
        }
    }

    System.out.println("Terima kasih telah menggunakan sistem.");
}


    public boolean loginUsers(String username, String password, Role role) {
        for (Users user : daftarUsers) {
            if (user.getUsername().equals(username) &&
                user.getPassword().equals(password) &&
                user.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public Users getUser(String username, String password, Role role) {
        for (Users user : daftarUsers) {
            if (user.getUsername().equals(username) &&
                user.getPassword().equals(password) &&
                user.getRole().equals(role)) {
                return user;
            }
        }
        return null;
    }

    public void menuMahasiswa(Mahasiswa mahasiswa) {
        do {
            System.out.println("Silahkan Pilih Opsi : ");
            System.out.println("1. Tambahkan Berkas Pendaftaran Wisuda.");
            System.out.println("2. Lihat Status Pendaftaran Wisuda");
            System.out.println("3. Keluar");
            opsi = scanner.nextInt();
            scanner.nextLine();
            switch(opsi){
                case 1 : 
                    tambahBerkasPendaftaran(mahasiswa);
                    break;
                case 2 :
                    lihatStatusPendaftaran(mahasiswa);
                    break;
                case 3 :
                    System.out.println("... Keluar Dari Sistem");
                    break;
                default :
                    System.out.println("Pilihan tidak tersedia");
            }
        } while(opsi != 3);
    }
    
    public void tambahBerkasPendaftaran(Mahasiswa mahasiswa){
        System.out.println("Masukan Formulir Pendaftaran Wisuda : ");
        String formulirPendaftaranWisuda = scanner.nextLine();
        System.out.println("Masukan Formulir Biodata Mahasiswa : ");
        String formulirBiodataMahasiswa = scanner.nextLine();
        System.out.println("Masukan Formulir Permohonan Bebas Pustaka : ");
        String formulirPermohonanBebasPustaka = scanner.nextLine();
        System.out.println("Masukan Formulir Permohonan Bebas Laboratorium : ");
        String formulirBebasLaboratorium = scanner.nextLine();
        System.out.println("Masukan Formulir Surat Ikut Serta Wisuda : ");
        String formulirSuratIkutSertaWisuda = scanner.nextLine();
        System.out.println("Masukan Formulir Dokumen Wisuda : ");
        String formulirDokumenWisuda = scanner.nextLine();
        System.out.println("Masukan Formulir Penyerahan Karya Ilmiah : ");
        String formulirPenyerahanKaryaIlmiah = scanner.nextLine();
        BerkasPendaftaran berkas = new BerkasPendaftaran(
            formulirPendaftaranWisuda,
            formulirBiodataMahasiswa,
            formulirPermohonanBebasPustaka,
            formulirBebasLaboratorium,
            formulirSuratIkutSertaWisuda,
            formulirDokumenWisuda,
            formulirPenyerahanKaryaIlmiah);
        mahasiswa.setBerkasMahasiswa(berkas);
    }

    public void lihatStatusPendaftaran(Mahasiswa mahasiswa){
        mahasiswa.displayStatusPendaftaran(mahasiswa);
    }
    
    public void menuAdmin(Admin adminLogin) {
        do {
            System.out.println("\nSilahkan Pilih Opsi : ");
            System.out.println("1. Lihat Daftar Mahasiswa.");
            System.out.println("2. Lihat Daftar Mahasiswa Wisuda.");
            System.out.println("3. Lihat Data Mahasiswa.");
            System.out.println("4. Verifikasi Berkas Pendaftaran.");
            System.out.println("5. Keluar.");
            System.out.print("Pilihan Anda: ");

            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (pilihan) {
                    case 1:
                        displayDaftarMahasiswa();
                        break;

                    case 2:
                        displayDaftarMahasiswaWisuda();
                        break;

                    case 3:
                        System.out.print("Masukan Nama Mahasiswa: ");
                        String nama = scanner.nextLine();
                        Mahasiswa mhs = cariMahasiswaByNama(nama);
                        if (mhs != null) {
                            mhs.displayDataMahasiswa(scanner);
                        } else {
                            System.out.println("Mahasiswa tidak ditemukan.");
                        }
                        break;

                    case 4:
                        System.out.print("Masukan Nama Mahasiswa: ");
                        String namaMahasiswa = scanner.nextLine();
                        Mahasiswa mhs1 = cariMahasiswaByNama(namaMahasiswa);
                        if (mhs1 == null) {
                            System.out.println("Mahasiswa tidak ditemukan.");
                            break;
                        }

                        System.out.print("Masukan Status Pendaftaran (DITERIMA / DITOLAK): ");
                        String statusInput = scanner.nextLine();
                        try {
                            StatusPendaftaran status = StatusPendaftaran.valueOf(statusInput.toUpperCase());
                            mhs1.setStatusPendaftaran(adminLogin, mhs1, status);
                            if(status == StatusPendaftaran.DITERIMA){
                                daftarMahasiswaWisuda.add(mhs1);
                                System.out.println("Mahasiswa berhasil ditambahkan ke daftar wisuda.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Status tidak valid.");
                        }
                        break;

                    case 5:
                        System.out.println("Keluar dari menu admin...");
                        break;

                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }
            } else {
                System.out.println("Input harus berupa angka!");
                scanner.next(); // consume invalid input
            }
        } while (pilihan != 5);
    }

    public void displayDaftarMahasiswa() {
        System.out.println("Daftar Nama Mahasiswa:");
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            System.out.println(mahasiswa.getNama());
        }
    }

    public void displayDaftarMahasiswaWisuda() {
    if(daftarMahasiswaWisuda != null){
        System.out.println("Daftar Nama Mahasiswa Wisuda:");
        for (Mahasiswa mahasiswa : daftarMahasiswaWisuda) {
        System.out.println(mahasiswa.getNama());
        } 
    } else {
        System.out.println("Data Mahasiswa Wisuda Tidak Tersedia");
    }
    }

    public Mahasiswa cariMahasiswaByNama(String nama) {
        for (Mahasiswa mhs : daftarMahasiswa) {
            if (mhs.getNama().equalsIgnoreCase(nama)) {
                return mhs;
            } else {
            System.out.println("Mahasiswa tidak ditemukan!");
            }
        }
        
        return null;
    }

    public Admin cariAdminByNama(String nama) {
        for (Admin adm : daftarAdmin) {
            if (adm.getNama().equalsIgnoreCase(nama)) {
                return adm;
            }else {
            System.out.println("Admin tidak ditemukan!");
            }
        }
        return null;
    }

    public void setUsers(Users user) {
        daftarUsers.add(user);
    }

public void setMahasiswa(Users user) {
    if (user instanceof Mahasiswa) {
        Mahasiswa mahasiswa = (Mahasiswa) user;
        if (!daftarMahasiswa.contains(mahasiswa)) {
            daftarMahasiswa.add(mahasiswa);
        }
    }
}

public void setAdmin(Users user) {
    if (user instanceof Admin) {
        Admin admin = (Admin) user;
        if (!daftarAdmin.contains(admin)) {
            daftarAdmin.add(admin);
        }
    }
}

    public void displayBerkasMahasiswa(Mahasiswa mahasiswa) {
        mahasiswa.displayBerkas();
    }
    
    public void initializeUsers(){
    // Contoh data dummy
    Mahasiswa mhs1 = new Mahasiswa("mahasiswa1", "pass123", Role.MAHASISWA, 
                                  "1234567", "John Doe", "john@email.com", 
                                  "08123456789", "Teknik Informatika", 
                                  "Teknik", 2020);
    registerUser(mhs1);
    
    Admin admin1 = new Admin("admin1", "admin123", Role.ADMIN, "Admin Satu", "123", "email", "0897");
    registerUser(admin1);
    }

    public void registerUser(Users user) {
    if (!daftarUsers.contains(user)) {
        daftarUsers.add(user);
    }
    if (user instanceof Mahasiswa mahasiswa) {
        if (!daftarMahasiswa.contains(mahasiswa)) {
            daftarMahasiswa.add(mahasiswa);
        }
    } else if (user instanceof Admin admin) {
        if (!daftarAdmin.contains(admin)) {
            daftarAdmin.add(admin);
        }
    }
}
}