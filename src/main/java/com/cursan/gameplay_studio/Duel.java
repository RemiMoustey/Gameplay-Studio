package com.cursan.gameplay_studio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements the Duel mode : the user and the computer play against each other
 */
public class Duel extends Game {

    private static final Logger logger = LogManager.getLogger(Defender.class);

    /**
     * The constructor of the Duel Mode
     */
    public Duel() {
        logger.debug("Beginning of Duel mod");
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
        logger.debug("End of game");
        choiceReplay("duel");
    }
}
