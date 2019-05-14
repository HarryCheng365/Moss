package whu.software.moss.Entity;

import javax.persistence.*;

@Entity
@Table(name = "corpus")
public class Corpus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dataId;


    @Column(name = "situation",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String situation;
    @Column(name = "type",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String type;
    @Column(name = "text",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String text;
    @Column(name = "imageurl",columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String imageurl;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="corpusId")
    private CorpusUser corpusUser;

    public Corpus(){

    }

    public Corpus(String situation,String type,String text,String imageurl,CorpusUser corpusUser){

        this.situation=situation;

        this.type=type;
        this.imageurl=imageurl;
        this.text=text;
        this.corpusUser=corpusUser;


    }

    public CorpusUser getCorpusUser() {
        return corpusUser;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public void setCorpusUser(CorpusUser corpusUser) {
        this.corpusUser = corpusUser;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getDataid() {
        return dataId;
    }



    public String getImageurl() {
        return imageurl;
    }

    public String getSituation() {
        return situation;
    }

    public String getType() {
        return type;
    }



    public void setDataid(int dataid) {
        this.dataId = dataid;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
