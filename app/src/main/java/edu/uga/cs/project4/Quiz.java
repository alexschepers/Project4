package edu.uga.cs.project4;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Quiz {

    private long id;
    private Question[] questionArray;
    private long score;
    private String time;

    public Quiz() {
        this.id = -1;  // the primary key id will be set by a setter method

        for (int i = 0; i < 6; i++) {
            // for loop to set questions, need to get a random country
            // Country country =
            //Question question = new Question();
            // questionArray[i] = question;
        }

        // set score like this until it can be calculated somehow
        this.score = -1;
        setTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question[] getQuestions() {
        return questionArray;
    }


    public long getScore() {
        return score;
    }

    // not sure how to calculate score at this point
    public void setScore(long score) {
        this.score = score;
    }

    public void setTime() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());
        this.time = timeStamp;
    }

}
