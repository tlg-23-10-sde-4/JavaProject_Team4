package com.clashofcards;

public class Ai extends Player {
    private String name;
    private int health;
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> graveyard;

    public Ai() {}

    public Ai(String name, int health, List<Card> deck, List<Card> hand, List<Card> graveyard) {
      super(name,health,deck,hand, graveyard);
    }
}