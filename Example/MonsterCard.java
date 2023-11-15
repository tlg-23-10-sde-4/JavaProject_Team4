package example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


class MonsterCard {
    private final MonsterType type;
    private final int manaCost;
    private final int attack;
    private final int defense;
    private final String specialEffect;

    public MonsterCard(MonsterType type, int manaCost, int attack, int defense, String specialEffect) {
        this.type = type;
        this.manaCost = manaCost;
        this.attack = attack;
        this.defense = defense;
        this.specialEffect = specialEffect;
    }

    public MonsterType getType() {
        return type;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getSpecialEffect() {
        return specialEffect;
    }
}
enum MonsterType {
    DRAGON, GOBLIN, WIZARD, KNIGHT, ELF, ZOMBIE, VAMPIRE, WEREWOLF, ORC, WITCH, FAIRY, ELEMENTAL, DEMON, ANGEL, BEAST
}
class Player {
    private List<MonsterCard> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void drawCard(List<MonsterCard> deck, int numCards) {
        for (int i = 0; i < numCards; i++) {
            if (!deck.isEmpty()) {
                hand.add(deck.remove(0));
            }
        }
    }

    public List<MonsterCard> getHand() {
        return hand;
    }
}

public class CardGame {
    public static void main(String[] args) {
        List<MonsterCard> deck = initializeDeck();
        Collections.shuffle(deck);

        Player player = new Player();
        Player ai = new Player();

        player.drawCard(deck, 7);
        ai.drawCard(deck, 7);

        while (true) {
            playTurn(player, ai);
            playTurn(ai, player);
        }
    }

    private static List<MonsterCard> initializeDeck() {
        List<MonsterCard> deck = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            MonsterType type = MonsterType.values()[new Random().nextInt(MonsterType.values().length)];
            int manaCost = new Random().nextInt(5) + 1;
            int attack = new Random().nextInt(10) + 1;
            int defense = new Random().nextInt(10) + 1;
            String specialEffect = generateRandomEffect();

            MonsterCard card = new MonsterCard(type, manaCost, attack, defense, specialEffect);
            deck.add(card);
        }

        return deck;
    }

    private static String generateRandomEffect() {
        String[] effects = {"Burn", "Freeze", "Heal", "None"};
        return effects[new Random().nextInt(effects.length)];
    }

    private static void playTurn(Player currentPlayer, Player opponent) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player's turn. Your hand: " + currentPlayer.getHand());
        System.out.println("Choose a card to play (enter the index): ");
        int cardIndex = scanner.nextInt();

        MonsterCard playedCard = currentPlayer.getHand().get(cardIndex);
        System.out.println("You played: " + playedCard.getType());

        System.out.println("Do you want to attack? (1. Yes, 2. No)");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Opponent's current hand: " + opponent.getHand());
            System.out.println("Choose a card to attack with (enter the index): ");
            int attackIndex = scanner.nextInt();

            MonsterCard attackedCard = opponent.getHand().get(attackIndex);
            System.out.println("You attacked with: " + attackedCard.getType());

            // Implement combat logic (compare attack and defense, apply effects, etc.)
        }

        currentPlayer.getHand().remove(cardIndex);
        currentPlayer.drawCard(deck, 1);
    }
}