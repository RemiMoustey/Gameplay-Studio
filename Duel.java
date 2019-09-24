package com.cursan.gameplay_studio;

/**
 * Implements the Duel mode : the user and the computer play against each other
 */
public class Duel extends Game {
    /**
     * The constructor of the Duel Mode
     */
    public Duel() {
        readConfigFile();
        developerMode = convertBooleanToString(properties.getProperty("developerMode", "defaultDeveloperMode"));
        numberDigits = getNumberDigitsInFile();
        endedGame = false;
        boolean computerWinner = false;
        boolean userWinner = false;
        makeCombination();
        System.out.println("Saisissez votre combinaison : ");
        do {
            drawInputUser("combination");
        } while(userCombination.length() != numberDigits);
        printSolution(userCombination);
        proposeCombination();
        answerProposition();
        makeCombination();
        printSolution(computerCombination);
        gameChallenger();
        do {
            if (userProposition.equals(computerCombination)) {
                System.out.println("Vous avez trouvé la bonne combinaison !");
                endedGame = true;
                userWinner = true;
            }
            if (computerProposition.equals(userCombination)) {
                System.out.println("L'ordinateur a trouvé la bonne combinaison !");
                endedGame = true;
                computerWinner = true;
            }
            if (computerWinner && !userWinner) {
                System.out.println("L'ordinateur a gagné !");
            }
            else if (userWinner && !computerWinner) {
                System.out.println("Vous avez gagné !");
            }
            else if (userWinner && computerWinner) {
                System.out.println("Match nul ! Vous avez trouvé la solution en même temps que l'ordinateur !");
            }
            if (!endedGame) {
                printSolution(userCombination);
                answerProposition();
                printSolution(computerCombination);
                gameChallenger();
            }
        } while(!endedGame);
        choiceReplay("duel");
    }
}
