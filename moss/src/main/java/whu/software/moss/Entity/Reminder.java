package whu.software.moss.Entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Table(name="reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "headline", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String headline;


    @Column(name = "content", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String content;

    @Column(name=  "timetype",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private TimeType timeType;

    @Column(name = "date",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL" )
    private String date;

    @Column(name=  "time",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL" )
    private String time;

    @Column(name="status",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL" )
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    @JoinColumn(name = "username")
    private UserInfo userInfo;

    public Reminder(){

    }
    public Reminder(String headline,String content,
                    TimeType timeType,String date,String time){
        this.headline=headline;

        this.content=content;
        this.timeType=timeType;
        this.date=date;
        this.time=time;
        this.status=Status.CONTINUE;

    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }


    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public Status getStatus() {
        return status;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }
}
