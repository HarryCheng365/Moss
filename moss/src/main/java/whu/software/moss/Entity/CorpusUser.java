package whu.software.moss.Entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="corpusUser")
public class CorpusUser {
    @Id
    @Column(name = "corpusId",columnDefinition = "varchar(255) CHARACTER SET utf8 ")
    private String corpusId;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
    private List<Corpus> corpusList;

    @JoinColumn(name ="username")
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<UserInfo> userInfoList;

    @Column(name = "name", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String name;
    @Column(name = "iconurl", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String iconurl;



    public CorpusUser(){

    }
    public CorpusUser(String corpusid,String name,String iconurl ){

        this.corpusId=corpusid;
        this.name=name;
        this.iconurl=iconurl;



    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public String getCorpusId() {
        return corpusId;
    }

    public void setCorpusId(String corpusId) {
        this.corpusId = corpusId;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }


    public List<Corpus> getCorpusList() {
        return corpusList;
    }

    public void setCorpusList(List<Corpus> corpusList) {
        this.corpusList = corpusList;
    }



    public String getIconurl() {
        return iconurl;
    }

    public String getName() {
        return name;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public void setName(String name) {
        this.name = name;
    }

}
