package edu.uga.cs.project4;

public class Quiz {

    private long id;
    private Question[] questionArray;
    private long score;

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

}
