package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BattleCardGame extends JFrame {

    private List<Card> playerDeck;
    private List<Card> aiDeck;

    private JLabel playerLabel;
    private JLabel aiLabel;
    private JButton attackButton;

    private int playerHealth;
    private int aiHealth;

    public BattleCardGame() {
        // Initialize player and AI decks
        initializeDecks();

        // Set up the JFrame
        setTitle("Battle example.Card Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        // Create components
        playerLabel = new JLabel();
        aiLabel = new JLabel();
        attackButton = new JButton("Attack");

        // Add components to the frame
        add(playerLabel);
        add(aiLabel);
        add(attackButton);

        // Register event handler for the button
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performPlayerTurn();
            }
        });

        // Initialize game state
        playerHealth = 30;
        aiHealth = 30;

        updateLabels();

        // Start the game loop
        startGameLoop();
    }

    private void initializeDecks() {
        playerDeck = new ArrayList<>();
        aiDeck = new ArrayList<>();

        // Populate decks with cards
        for (int i = 1; i <= 5; i++) {
            playerDeck.add(new Card("PlayerCard" + i, i, i + 1));
            aiDeck.add(new Card("AICard" + i, i, i + 1));
        }

        // Shuffle decks
        Collections.shuffle(playerDeck);
        Collections.shuffle(aiDeck);
    }

    private void updateLabels() {
        playerLabel.setText("Player Health: " + playerHealth + " | Deck Size: " + playerDeck.size());
        aiLabel.setText("AI Health: " + aiHealth + " | Deck Size: " + aiDeck.size());
    }

    private void performPlayerTurn() {
        if (!playerDeck.isEmpty() && !aiDeck.isEmpty()) {
            Card playerCard = playerDeck.remove(0);
            Card aiCard = aiDeck.remove(0);

            displayTurnResult("Player's Turn", "Player drew: " + playerCard.getName() +
                    "\nAI's Defense: " + aiCard.getDefense());

            // Compare attack stats
            if (playerCard.getAttack() > aiCard.getDefense()) {
                aiHealth -= playerCard.getAttack() - aiCard.getDefense();
            }

            updateLabels();

            // Check for game over
            if (playerHealth > 0 && aiHealth > 0) {
                // Introduce a delay (e.g., 2 seconds) to simulate player's turn completion
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                performAITurn();
            } else {
                endGame();
            }
        }
    }

    private void performAITurn() {
        if (!playerDeck.isEmpty() && !aiDeck.isEmpty()) {
            // Simulate AI thinking time (e.g., 1 second)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Randomly select an AI card and attack
            Random random = new Random();
            Card aiCard = aiDeck.remove(random.nextInt(aiDeck.size()));

            displayTurnResult("AI's Turn", "AI drew: " + aiCard.getName() +
                    "\nPlayer's Defense: " + playerDeck.get(0).getDefense());

            // Compare attack stats
            if (aiCard.getAttack() > playerDeck.get(0).getDefense()) {
                playerHealth -= aiCard.getAttack() - playerDeck.get(0).getDefense();
            }

            updateLabels();

            // Check for game over
            if (playerHealth > 0 && aiHealth > 0) {
                // Continue to the player's turn
            } else {
                endGame();
            }
        }
    }

    private void displayTurnResult(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void endGame() {
        if (playerHealth <= 0) {
            JOptionPane.showMessageDialog(this, "AI Wins!");
        } else if (aiHealth <= 0) {
            JOptionPane.showMessageDialog(this, "Player Wins!");
        }

        System.exit(0);
    }

    private void startGameLoop() {
        // No need for a Timer since the turns are controlled manually
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BattleCardGame().setVisible(true);
            }
        });
    }
}

class Card {
    private String name;
    private int attack;
    private int defense;

    public Card(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}