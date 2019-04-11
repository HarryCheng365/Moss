package whu.software.moss.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

//@javax.persistence.Entity
public class Memorandum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String date;

    @Column(name = "feedback", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String feedback;
    @Column(name = "name", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT NULL")
    private String name;


    public Memorandum() {

    }

    public Memorandum(String feedback, String name) {

        if (feedback == null || feedback.equals(""))
            this.feedback = "null";
        else
            this.feedback = feedback;
        Date temp = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(temp);
        this.name = name;


    }
    public void setId(int id){
        this.id=id;

    }
    public int getId(){
        return this.id;
    }
    public void setDate(String date){
        this.date=date;

    }
    public String getDate(){
        return this.date;
    }

    public void setFeedback(String feedback){
        this.feedback=feedback;

    }
    public String getFeedback(){
        return this.feedback;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}
