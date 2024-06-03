
public class Courier {
    private Integer id;
    private String login;
    private String password;
    private String firstName;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}