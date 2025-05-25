 import java.util.Scanner;
/**
 * Write a description of class Mahasiswa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mahasiswa extends Users
{
    private String NPM;
    private String nama;
    private String email;
    private String noHp;
    private String programStudi;
    private String fakultas;
    private int angkatan;
    private BerkasPendaftaran berkas;
    public Mahasiswa(String username,
    String password,
    Role role,
    String NPM,
    String nama,
    String email,
    String noHp,
    String programStudi, 
    String fakultas,
    int angkatan){
    super(username, password, role);
    this.NPM = NPM;
    this.nama = nama;
    this.email = email;
    this.noHp = noHp;
    this.programStudi = programStudi;
    this.fakultas = fakultas;
    this.angkatan = angkatan;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setBerkasMahasiswa(BerkasPendaftaran berkas){
        this.berkas = berkas;
    }
    
    public void displayBerkas(){
    berkas.displayBerkasPendaftaran(this.nama);
    }
    
    public void displayDataMahasiswa(Scanner scanner){
    System.out.println("Data Mahasiswa");
    System.out.println("Nama Lengkap : " + nama);
    System.out.println("NPM Mahasiswa : " + NPM);
    System.out.println("Email Mahasiswa : " + email);
    System.out.println(noHp);
    System.out.println(programStudi);
    System.out.println(fakultas);
    System.out.println(angkatan + "\n");
    System.out.print("Tampilkan Berkas Pendaftaran (Y/N) : ");
    String nilaiInput = scanner.nextLine();
    if(nilaiInput == "Y" || nilaiInput == "y"){
    displayBerkas();
    }
    }
    
    public void displayStatusPendaftaran(Admin adm, Mahasiswa mhs, StatusPendaftaran status){
    System.out.println("Status Pendaftaran : Mahasiswa" + mhs + " " + status + " oleh Admin " + adm);
    }
    
}
