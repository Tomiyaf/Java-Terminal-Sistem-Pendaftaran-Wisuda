/**
 * Write a description of class Users here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Users
{
    private static int id = 1;
    private int idUser = id++;
    private String username;
    private String password;
    private Role role;
    public Users(String username, String password, Role role){
    this.username = username;
    this.password = password;
    this.role = role;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public Role getRole(){
        return role;
    }
}
