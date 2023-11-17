package com.clashofcards.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    //  Card vars
    private String index;
    private String name;
    private Integer strength;
    private Integer toughness;
    private List<String> imageLines;
    private int imageRowsNum = 10;


    //  constructors for Card
    public Card() {
    }

    public Card(String index, String name, Integer strength, Integer toughness) {
        setIndex(index);
        setName(name);
        setStrength(strength);
        setToughness(toughness);
        try {
            this.imageLines = Files.readAllLines(Path.of("Data/" + getName() + ".txt"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void print(){
        for(String line : getImageLines()){
            System.out.println(line);
        }
        System.out.printf("#%s %s   ✊ %s ❤ %s\n", getIndex(), getName(), getStrength(), getToughness());
    }
    // ❤ ✊
    public void printHand(List <Card> list){
        for(Card card : list){
            for(String line : card.getImageLines()){
                System.out.println(line);
            }
        }
    }

    public void workingPrintHand(List<Card> list){

        //  loop repeats i times, where "i" is rows in each image
        for (int i = 0; i < imageRowsNum; i++){

            //  loop repeats number of card times
            for(Card card : list){
                System.out.print(card.getImageLines().get(i)+"\t\t");
            }
            System.out.println();
        }
        for (Card card : list){
            System.out.printf("id:%s       ✊ %s ❤ %s\t\t", card.getIndex(), card.getStrength(), card.getToughness());
            //System.out.printf("id:%s.......✊ %s ❤ %s\t\t", card.getIndex(), card.getStrength(), card.getToughness());
        }
        System.out.println();
    }

    //  accessor method
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
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
        return this.toughness;
    }

    public void setToughness(Integer toughness) {
        this.toughness = toughness;
    }

    public List<String> getImageLines() {
        return imageLines;
    }

    public void setImageLines(List<String> imageLines) {
        this.imageLines = imageLines;
    }

    //    @Override
//    public String toString(){
//        return String.format("\n#: %s - %s   %s/%s",getIndex(),getName(),getStrength(),getToughness());
//    }


    //    @Override
//    public String toString() {
//        return String.format("| #: %s %s S: %s T: %s |", getIndex(), getName(), getStrength(), getToughness());
//    }
    @Override
    public String toString() {
        return String.format(
                         "---------------------" +
                        "| #: %s     %s   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "|                   |" +
                        "| S: %s    |    T: %s |" +
                        "---------------------\n",
                getIndex(), getName(), getStrength(), getToughness());
    }
}