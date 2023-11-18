# JavaProject_Team4 (Clash Of Cards)

# What is this?
- This is a card battle card game built using Java and ASCII Art.
- The game offers a singular game loop allowing you to choose cards to play and battle our smart AI. If you like the world of Magic The Gathering then this is the game for you!
- We collectively learned a lot on this project. Class creation and business method delegation can get very tricky as methods and logic continues to grow. Organization and clean code was imperative to getting the game logic to flow smoothly. It was cool to see a super class be used in a way that made sense while using overrides for an alternate logic.

## Table of Contents (Optional)

- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [Features](#features)
- [Testing](#testing)

## Installation
- I would recommend cloning this repo to your local machine in a directory that is easy for you to find.
- Located at the base of the repository is the run.cmd file. This is how you run the game. The console clearing features will not work in IntelliJ or whichever IDE you are using.
- That's it, the repo already include Junit and all other need JAR's. Documentation for the 

## Usage
- How to play
  - Once you have cloned the game onto your local machine, open the file and click the run.cmd if you are on windows or the run.sh if you are on mac.
  - Upon launching the game you will be prompted and asked if you would like to see the game instructions. If you type yes, you will be shown a very basic outline of how the game works.
  - The game works in an infinite loop until the game winning conditions have been met.
  - When the game fires up, each player will be given 7 random cards from our 40 card deck.
  - Playing cards in this game goes based on the cards ID. The ID of the card is located at the bottom left of the card, if you'd like to play that card, type that cards ID when prompted.
  - IT IS HIGHLY RECOMMENDED YOU PLAY IN FULL SCREEN
  - The game will not look very nice if you play it windowed and not full screen. Our game makes full use of the console with the cards and battling.
- Attack Phase
  - Upon the start of the attack phase, if you have cards on your battlefield, you will be asked if you want to attack. Be careful, this might not be the smart move here. You must ensure that you are playing a card from your battlefield, not your hand. 
  - After you have chosen to attack or not, you will be prompted to play a card from your hand. Pick a card from your hand, not your battlefield.
  - Once you have played a card from your battlefield that is the end of the attack phase
- Defense Phase
  - It is now the AI's turn. The AI will not choose to attack you if they have a card on the battlefield. You will be prompted to block with a card from your battlefield. Be careful, this might not be the smart move here.  
  - After you have blocked with your card (or not) it is now the AI's turn to play a card. The AI will play a card from their hand if they have one to play.
  - The game will now repeat to the attack phase until one of the players has 0 health
- Ending the game
  - The goal is to beat the AI and take their health down to zero.
  - Your health and the enemy health is displayed in the middle of the screen in between the battlefields. Make sure to keep track of your health
- How damage works
  - If you have a card that has 4 strength and 4 toughness and the enemy has a card that has 2 strength and 2 toughness and the enemy blocks with their card, you will in turn destroy the enemies card AND deal 2 damage directly to the enemy as the damage effects are rolled over from the remainder of your card's strength. Strength can be indicated by the fist at the bottom right of the card and toughness can be indicated by the heart at the bottom right of the card.
  - Good Luck And We Hope You Enjoy Our Game!

## Credits
The Brogrammersy
- Vitalii Chibizov 
- Udyr Barnes
- Tyler Poepping

## Features
- Full cyclical battle cycle until game completion
- Complete card randomization
- Fully functional responsive AI player to battle

## Testing
- Junit is included with this repository, if your test directory is not green, right click on it, and move down to module settings and open it. Click dependencies and add Junit to it if it is not present. You should also right-click on the test directory move down to "Mark Directory As" and then select "Test Root".
- If you'd like to run the test, you can either run all of them at the top file by right-clicking the test directory and click "Run All Test's". You can also run them file by file or singular test's. 
