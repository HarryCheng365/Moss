package whu.software.moss.Entity;

import javax.persistence.*;

@Entity
@Table(name = "clockIn")
public class ClockIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "content", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String content;

    @Column(name = "location", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String location;

    @Column(name ="date",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL" )
    private String date;

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    @JoinColumn(name = "username")
    private UserInfo userInfo;

    public ClockIn(){

    }
    public ClockIn(String content,String location,String date){
        this.content=content;
        this.location=location;
        this.date=date;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
