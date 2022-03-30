package edu.uga.cs.project4;

public class Question {

    private long id;
    private String country;

    public Question() {
        this.id = -1;
        this.country = null;
    }

    public Question(String country) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.country = country;
    }

    // don't think we need these but just in case
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    // forming the actual question
    public String toString() {
        return "Name the continent on which " + this.country + " is located.";
    }

}
