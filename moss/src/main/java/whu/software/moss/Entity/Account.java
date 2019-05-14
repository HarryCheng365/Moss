package whu.software.moss.Entity;


import javax.persistence.*;



@javax.persistence.Entity
@Table(name = "account")
public class Account {

    @Id
    private String username;
    @Column(name = "type", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private AccountType type;
    @Column(name = "password", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String password;



    public Account() {

    }

    public Account(String username, AccountType type,String password) {

        this.username=username;
        this.type=type;
        this.password=password;

    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}