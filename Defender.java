package com.cursan.gameplay_studio;

/**
 * Implements the Defender mode : the user answers to the propositions of the computer
 */
public class Defender extends Game {
    /**
     * The constructor of the Defender Mode
     */
    public Defender() {
        readConfigFile();
        developerMode = convertBooleanToString(properties.getProperty("developerMode", "defaultDeveloperMode"));
        numberTries = getNumberTriesInFile();
        numberDigits = getNumberDigitsInFile();
        System.out.println("Saisissez votre combinaison : ");
        do {
            drawInputUser("combination");
        } while(userCombination.length() != numberDigits);
        gameDefender();
        choiceReplay("defender");
    }
}
