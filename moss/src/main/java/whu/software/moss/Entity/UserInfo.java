package whu.software.moss.Entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "userInfo")
public class UserInfo {

        @Id
        private String username;

        @Column(name = "sex", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private Sex sex;

        @Column(name = "nickname", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String nickname;
        @Column(name = "phone", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String phone;

        @Column(name = "email", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String email;

        @Column(name = "date", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String date;
        @Column(name = "device", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String device;
        @Column(name = "lastlogindate", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
        private String lastlogindate;


        @OneToMany
        private List<Memorandum> memorandumList;
        @OneToMany
        private List<ClockIn> clockInList;

         @OneToMany
         private List<Reminder> reminderList;

         @ManyToMany
         private List<CorpusUser> corpusUserList;
        public UserInfo() {

        }



        public UserInfo(String username,Sex sex,String nickname,
                        String phone,String email,String lastlogindate,String device) {

            this.username=username;
            this.sex=sex;
            this.nickname=nickname;
            this.phone=phone;
            this.email=email;
            Date temp = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.date = sdf.format(temp);
            this.device=device;
            this.lastlogindate=lastlogindate;

        }

    public List<CorpusUser> getCorpusUserList() {
        return corpusUserList;
    }

    public List<Reminder> getReminderList() {
        return reminderList;
    }

    public void setCorpusUserList(List<CorpusUser> corpusUserList) {
        this.corpusUserList = corpusUserList;
    }

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }


    public List<ClockIn> getClockInList() {
        return clockInList;
    }

    public List<Memorandum> getMemorandumList() {
        return memorandumList;
    }

    public void setClockInList(List<ClockIn> clockInList) {
        this.clockInList = clockInList;
    }

    public void setMemorandumList(List<Memorandum> memorandumList) {
        this.memorandumList = memorandumList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Sex getSex() {
        return sex;
    }

    public String getDate() {
        return date;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getDevice() {
        return device;
    }

    public String getPhone() {
        return phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(String lastlogindate) {
        this.lastlogindate = lastlogindate;
    }
}


