import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranWisuda {
    Scanner scanner = new Scanner(System.in);
    List<Users> daftarUsers = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    List<Admin> daftarAdmin = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswaWisuda = new ArrayList<>();
    private int pilihan;

    public void main(String[] args) {
        new PendaftaranWisuda().mainFunction();
    }

    public void mainFunction() {
        login();
    }

    public void login() {
        System.out.println("=======================================");
        System.out.println("||Sistem Pendaftaran Wisuda Mahasiswa||");
        System.out.println("||         Universitas Lampung       ||");
        System.out.println("=======================================\n");

        while (true) {
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();

            Role role;
            while (true) {
                System.out.print("🧾 Role (ADMIN / MAHASISWA) : ");
                String roleInput = scanner.nextLine().toUpperCase();
                try {
                    role = Role.valueOf(roleInput);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Role tidak valid. Silakan masukkan ADMIN atau MAHASISWA.");
                }
            }

            if (loginUsers(username, password, role)) {
                System.out.println("Selamat Datang " + username);
                Users loggedInUser = getUser(username, password, role);

                if (role == Role.ADMIN) {
                    setAdmin(loggedInUser);
                    System.out.println("Login sebagai Admin.");
                    menuAdmin();
                } else {
                    setMahasiswa(loggedInUser);
                    System.out.println("Login sebagai Mahasiswa.");
                    menuMahasiswa(scanner);
                }
            } else {
                System.out.println("Maaf, Username atau Password Anda salah.");
            }

            System.out.print("Keluar dari sistem? (Y/N): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("Keluar dari sistem...");
                break;
            }
        }
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

    public void menuMahasiswa(Scanner scanner) {
        System.out.println("Silahkan Pilih Opsi : ");
        System.out.println("1. Tambahkan Berkas Pendaftaran Wisuda.");
        System.out.println("2. Keluar");
        int opsi = scanner.nextInt();
        scanner.nextLine();
        switch(opsi){
            case 1:
                tambahBerkasPendaftaran(scanner);
                break;
            case 2:
                System.out.println("... Keluar Dari Sistem");
                break;
            default:
                System.out.println("Pilihan tidak tersedia");
        }
    }

    public void tambahBerkasPendaftaran(Scanner scanner) {
        System.out.println("Masukan Nama Mahasiswa : ");
        String namaMahasiswa = scanner.nextLine();
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
            formulirPenyerahanKaryaIlmiah
        );
        
        Mahasiswa mhs = cariMahasiswaByNama(namaMahasiswa);
        if (mhs != null) {
            mhs.setBerkasMahasiswa(berkas);
        } else {
            System.out.println("Mahasiswa tidak ditemukan.");
        }
    }

    public void menuAdmin() {
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
                        System.out.print("Masukan Nama Admin: ");
                        String namaAdmin = scanner.nextLine();
                        Admin adm = cariAdminByNama(namaAdmin);
                        if (adm == null) {
                            System.out.println("Admin tidak ditemukan.");
                            break;
                        }

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
                            mhs1.setStatusPendaftaran(adm, mhs1, status);
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
        System.out.println("Daftar Nama Mahasiswa Wisuda:");
        for (Mahasiswa mahasiswa : daftarMahasiswaWisuda) {
            System.out.println(mahasiswa.getNama());
        }
    }

    public Mahasiswa cariMahasiswaByNama(String nama) {
        for (Mahasiswa mhs : daftarMahasiswa) {
            if (mhs.getNama().equalsIgnoreCase(nama)) {
                return mhs;
            }
        }
        return null;
    }

    public Admin cariAdminByNama(String nama) {
        for (Admin adm : daftarAdmin) {
            if (adm.getNama().equalsIgnoreCase(nama)) {
                return adm;
            }
        }
        return null;
    }

    public void setUsers(Users user) {
        daftarUsers.add(user);
    }

    public void setMahasiswa(Users user) {
        if (user instanceof Mahasiswa) {
            daftarMahasiswa.add((Mahasiswa) user);
        }
    }

    public void setAdmin(Users user) {
        if (user instanceof Admin) {
            daftarAdmin.add((Admin) user);
        }
    }

    public void displayBerkasMahasiswa(Mahasiswa mahasiswa) {
        mahasiswa.displayBerkas();
    }
}
