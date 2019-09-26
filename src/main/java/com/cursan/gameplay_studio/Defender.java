package com.cursan.gameplay_studio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements the Defender mode : the user answers to the propositions of the computer
 */
public class Defender extends Game {

    private static final Logger logger = LogManager.getLogger(Defender.class);

    /**
     * The constructor of the Defender Mode
     */
    public Defender() {
        logger.debug("Beginning of Defender mod");
        readConfigFile();
        developerMode = convertBooleanToString(properties.getProperty("developerMode", "defaultDeveloperMode"));
        numberTries = getNumberTriesInFile();
        numberDigits = getNumberDigitsInFile();
        System.out.println("Saisissez votre combinaison : ");
        do {
            drawInputUser("combination");
        } while(userCombination.length() != numberDigits);
        gameDefender();
        logger.debug("End of game");
        choiceReplay("defender");
    }
}
