package com.clashofcards;

public class Card {
    private Integer index;
    private String name;
    private Integer strength;
    private Integer toughness;


    //  constructors
    public Card(){
    }

    public Card(Integer index, String name, Integer strength, Integer toughness){
        setIndex(index);
        setName(name);
        setStrength(strength);
        setToughness(toughness);
    }



    //  accessor method
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getToughness() {
        return toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    @Override
    public String toString(){
        return String.format(
                "---------------------\n" +
                "| #: %s       %s |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "|                   |\n" +
                "| S: %s    |    T: %s |\n" +
                "---------------------"
        ,getIndex(),getName(),getStrength(),getToughness());
    }

/*    private String goblin = "" +
            "            /(.----.)\          " +
            "        |\  \/      \/  /|      "
            "        | \ / =.  .= \ / |
            "        \( \   o\/o   / )/
            "         \_, '-/  \-' ,_/
            "           /   \__/   \
            "           \ \__/\__/ /
            "         ___\ \|--|/ /___
            "       /`    \      /    `\
            "      /       '----'       \



    public String showGoblin(){
        return
    }*/
public String gobo() {
    return " " +
            "             ,      ,\n" +
            "            /(.-\"\"-.)\\\n" +
            "        |\\  \\/      \\/  /|\n" +
            "        | \\ / =.  .= \\ / |\n" +
            "        \\( \\   o\\/o   / )/\n" +
            "         \\_, '-/  \\-' ,_/\n" +
            "           /   \\__/   \\\n" +
            "           \\ \\__/\\__/ /\n" +
            "         ___\\ \\|--|/ /___\n" +
            "       /`    \\      /    `\\\n" +
            "      /       '----'       \\";
}
}