package edu.uga.cs.project4;

import java.util.Random;

public class Question {

    private long id;
    private Country country;
    private String correctAnswer;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;

    public Question() {
        this.id = -1;
        this.country = null;
    }

    public Question(Country country) {
        this.country = country;
        this.correctAnswer = country.getContinent();

        // need to be changed later, just for testing purposes
        this.wrongAnswerOne = "Asia";
        this.wrongAnswerTwo = "Africa";
    }

    // don't think we need these but just in case
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    // forming the actual question
    public String toString() {
        return "Name the continent on which " + this.country + " is located.";
    }

}
