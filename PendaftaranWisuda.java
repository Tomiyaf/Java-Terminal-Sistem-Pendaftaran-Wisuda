import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class PendaftaranWisuda here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PendaftaranWisuda
{
    Scanner scanner = new Scanner(System.in);
    List<Users> daftarUsers = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    List<Admin> daftarAdmin = new ArrayList<>();
    List<Mahasiswa> daftarMahasiswaWisuda = new ArrayList<>();
    private int nilaiInput;
    private int pilihan;
    Role role = null;
    
    public void pendaftaranWisuda(String args[]){
    mainFunction();
    }
    
    public void mainFunction(){
    login();
    }
    
    public void login(){
    
    System.out.println("=======================================");
    System.out.println("||Sistem Pendaftaran Wisuda Mahasiswa||");
    System.out.println("||         Universitas Lampung       ||");
    System.out.println("=======================================" + "\n");
    System.out.print("Username : ");
    String username = scanner.nextLine();
    System.out.print("Password : ");
    String password = scanner.nextLine();
    System.out.print("🧾 Role (ADMIN / MAHASISWA) : ");
    String roleInput = scanner.nextLine().toUpperCase();
    Role role = Role.valueOf(roleInput.toUpperCase());

    for(Users users : daftarUsers){
        if(loginUsers(username, password, role) == true){
        System.out.println("Selamat Datang " + username);
        if(role == role.ADMIN){
        setAdmin(users);
        System.out.println("Login sebagai Admin.");
        menuAdmin(scanner);
        } else if(role == role.MAHASISWA){
        setMahasiswa(users);
        System.out.println("Login sebagai Mahasiswa.");
        menuMahasiswa();
        }
        }
    }
    
    }
 
    public void menuMahasiswa(){
    System.out.println("Silahkan Pilih Opsi : ");
    System.out.println("1. Tambahkan Berkas Pendaftaran Wisuda.");
    System.out.println("2. Keluar");
    }
    
    public void menuAdmin(Scanner scanner){
    System.out.println("Silahkan Pilih Opsi : ");
    System.out.println("1. Lihat Daftar Mahasiswa.");
    System.out.println("2. Lihat Daftar Mahasiswa Wisuda");
    System.out.println("3. Lihat Data Mahasiswa.");
    System.out.println("4. Verifikasi Berkas Pendaftaran");
    System.out.println("5. Keluar");
    
    do{
    pilihan = scanner.nextInt();
    switch (pilihan){
        case 1 : 
        displayDaftarMahasiswa();
        break;
        
        case 2 : 
        displayDaftarMahasiswaWisuda();
        break;
        
        case 3 :
        System.out.println("Masukan Nama Mahasiswa : ");
        String nama = scanner.nextLine();
        Mahasiswa mhs = cariMahasiswaByNama(nama);
        mhs.displayDataMahasiswa(scanner);
        
        case 4 :
        System.out.print("Masukan Nama Admin : ");
        String namaAdmin = scanner.nextLine();
        Admin adm = cariAdminByNama(namaAdmin);
        System.out.print("Masukan Nama Mahasiswa : ");
        String namaMahasiswa = scanner.nextLine();
        Mahasiswa mhs1 = cariMahasiswaByNama(namaMahasiswa);
        System.out.print("Masukan Status Pendaftaran (DITERIMA / DITOLAK): ");
        String statusPendaftaran = scanner.nextLine();
        StatusPendaftaran status = StatusPendaftaran.valueOf(statusPendaftaran.toUpperCase());
        mhs1.displayStatusPendaftaran(adm, mhs1, status);
    }
    }while(pilihan != 4);
    }
    
    public Mahasiswa cariMahasiswaByNama(String nama) {
    for (Mahasiswa mhs : daftarMahasiswa) {
        if (mhs.getNama().equalsIgnoreCase(nama)) {
            return mhs;
        }
    }
    return null;
}
    

    public boolean loginUsers(String username, String password, Role role){
    for (Users user : daftarUsers) {
        if (user.getUsername().equals(username) &&
            user.getPassword().equals(password) &&
            user.getRole().equals(role)) {
            return true;
        }   
    }   
    return false;

}
    
public void displayDaftarMahasiswa(){
    System.out.println("Daftar Nama Mahasiswa");
    for(Mahasiswa mahasiswa : daftarMahasiswa){
    System.out.println(mahasiswa.getNama());
    }
}

public void displayDaftarMahasiswaWisuda(){
    System.out.println("Daftar Nama Mahasiswa Wisuda");
    for(Mahasiswa mahasiswa : daftarMahasiswaWisuda){
    System.out.println(mahasiswa.getNama());
    }
}

    public void setUsers(Users user){
        daftarUsers.add(user);
    }
    
    //Scanner nama mahasiswa sebagai instance object
    public void displayBerkasMahasiswa(Mahasiswa mahasiswa){
    mahasiswa.displayBerkas();
    }

    
public void setMahasiswa(Users user) {
    if (user instanceof Mahasiswa) {
        daftarMahasiswa.add((Mahasiswa) user);
    }
}
    
    
    public Admin cariAdminByNama(String nama) {
    for (Admin adm : daftarAdmin) {
        if (adm.getNama().equalsIgnoreCase(nama)) {
            return adm;
        }
    }
    return null;
}

public void setAdmin(Users user) {
    if (user instanceof Admin) {
        daftarAdmin.add((Admin) user);
    }
}
}
