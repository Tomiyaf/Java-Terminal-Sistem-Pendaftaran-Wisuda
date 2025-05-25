 /**
 * Write a description of class Admin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Admin extends Users
{
    private String nama;
    private String idPegawai;
    private String email;
    private String noTelepon;
    
    public Admin(String username, 
    String password, 
    Role role,
    String nama,
    String idPegawai,
    String email,
    String noTelepon){
    super(username, password, role);
    this.nama = nama;
    this.idPegawai = idPegawai;
    this.email = email;
    this.noTelepon = noTelepon;
    }
    
    public String getNama(){
        return nama;
    }
}
