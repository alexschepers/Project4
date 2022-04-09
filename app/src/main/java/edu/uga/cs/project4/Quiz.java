package edu.uga.cs.project4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quiz {

    private long id;
    private long score;
    //private Question[] questions;
    private String time;

    public Quiz() {
        this.id = -1;
        this.score = 0;
        //this.questions = null;
        this.time = setTime();
    }

    public Quiz(Question[] questions) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.score = 0;
        time = setTime();
        //this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    // not sure how to calculate score at this point
    public void setScore(long score) {
        this.score = score;
    }

    public void updateScore(long score) {
        score += 1;
    }

    public String setTime() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());
        this.time = timeStamp;
        return timeStamp;
    }

    public String getTime() {
        return time;
    }

}
