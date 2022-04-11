package edu.uga.cs.project4;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
        this.wrongAnswerOne = getFirstWrongAnswer();
        this.wrongAnswerTwo = getSecondWrongAnswer();
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

    public String getFirstWrongAnswer() {
        List<String> continentList = Collections.synchronizedList(new ArrayList<>());
        continentList.add("Asia");
        continentList.add("Antarctica");
        continentList.add("Africa");
        continentList.add("Australia");
        continentList.add("Europe");
        continentList.add("North America");
        continentList.add("South America");
        //String continentArray[] = {"Asia", "Antarctica", "Africa", "Australia", "Europe", "North America", "South America"};

        String returnString = "";

        Random randomNum = new Random();

        continentList.remove(correctAnswer);

        int upper = continentList.size();
        int rand = randomNum.nextInt(upper);

        returnString = continentList.get(rand);

        return returnString;
    }

    public String getSecondWrongAnswer() {

        List<String> continentList = Collections.synchronizedList(new ArrayList<>());
        continentList.add("Asia");
        continentList.add("Antarctica");
        continentList.add("Africa");
        continentList.add("Australia");
        continentList.add("Europe");
        continentList.add("North America");
        continentList.add("South America");

        String returnString = "";

        Random randomNum = new Random();

        continentList.remove(correctAnswer);
        continentList.remove(wrongAnswerOne);

        int upper = continentList.size();
        int rand = randomNum.nextInt(upper);

        returnString = continentList.get(rand);

        return returnString;
    }

}
