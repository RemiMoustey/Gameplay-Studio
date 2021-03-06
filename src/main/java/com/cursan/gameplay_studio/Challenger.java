package com.cursan.gameplay_studio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements the Challenger mode : the user tries to guess the random combination
 */
public class Challenger extends Game {

    private static final Logger logger = LogManager.getLogger(Challenger.class);

    /**
     * The constructor of the Challenger Mode
     */
    public Challenger() {
        logger.debug("Beginning of Challenger mod");
        readConfigFile();
        developerMode = convertBooleanToString(properties.getProperty("developerMode"));
        numberTries = getNumberTriesInFile();
        makeCombination();
        do {
            if (numberTries > 1) {
                System.out.println("Il vous reste " + numberTries + " essais.");
            } else if (numberTries == 1) {
                System.out.println("Il vous reste " + numberTries + " essai.");
            }
            printSolution(computerCombination);
            gameChallenger();
            numberTries--;
        } while (!computerCombination.equals(userProposition) && numberTries > 0);
        if (computerCombination.equals(userProposition))
            System.out.println("Félicitations ! Vous avez trouvé la bonne combinaison !");
        else if (numberTries == 0)
            System.out.println("Dommage ! Vous n'avez plus d'essai possible. La solution était : " + computerCombination);
        logger.debug("End of game");
        choiceReplay("challenger");
    }
}
