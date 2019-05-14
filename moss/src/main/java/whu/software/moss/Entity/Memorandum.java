/**
 * Demo class
 *
 * @author keriezhang
 * @date 2016/10/31
 */


package whu.software.moss.Entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@javax.persistence.Entity
@Table(name="memorandum")
public class Memorandum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "headline", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String headline;

    @Column(name = "content", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String content;

    @Column(name ="date",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL" )
    private String date;

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    @JoinColumn(name = "username")
    private UserInfo userInfo;
    public Memorandum() {

    }

    public Memorandum(String headline,String content,UserInfo userInfo) {

        this.headline = headline;
        this.content = content;
        Date temp = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(temp);
        this.userInfo=userInfo;

    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getHeadline() {
        return headline;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setId(int id) {
        this.id = id;
    }

}
