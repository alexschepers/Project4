package edu.uga.cs.project4;

import android.util.Log;

import java.util.Random;

public class Question {

    private long id;
    private Country country;
    private String correctAnswer;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;

    String continentArray[] = {"Asia", "Antarctica", "Africa", "Australia", "Europe", "North America", "South America"};

    public Question() {
        this.id = -1;
        this.country = null;
    }

    public Question(Country country) {
        this.country = country;
        this.correctAnswer = country.getContinent();

        // need to be changed later, just for testing purposes
        this.wrongAnswerOne = getWrongAnswer();
        this.wrongAnswerTwo = getWrongAnswer();
    }

    // don't think we need these but just in case
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCorrectAnswer() {return correctAnswer; }
    public String getWrongAnswerOne() {return wrongAnswerOne; }
    public String getWrongAnswerTwo() {return wrongAnswerTwo; }


    // forming the actual question
    public String toString() {
        return "Name the continent on which " + this.country.getName() + " is located.";
    }

    public String getWrongAnswer() {
        String returnString = "";

        Random randomNum = new Random();
        int upper = continentArray.length;
        int rand = randomNum.nextInt(upper);

        if (continentArray[rand] != correctAnswer && continentArray[rand]!= wrongAnswerOne) {
            returnString = continentArray[rand];
            wrongAnswerOne = returnString; //this might have fixed the problem of having 2 of the same wrong answer choices
        } else {
            rand = randomNum.nextInt(upper);
            returnString = continentArray[rand];
        }

        // bug here because getWrongAnswer() could result in the same wrong answer option.
        // need to think of a way to ensure two different wrong answer options.
        return returnString;
    }

}
