package edu.uga.cs.project4;

/**
 * This class (a POJO) represents a single country object.
 */


public class Country {

    private long id;
    private String name;
    private String continent;

    public Country(String name) {
        this.id = -1;
        this.name = null;
        this.continent = null;
    }

    public Country(String name, String continent) {
        this.id = -1;
        this.name = name;
        this.continent = continent;
    }


    public long getId(){ return id;}

    public void setId(long id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String toString() {
        return name + " " + continent;
    }

}
