package com.cursan.gameplay_studio;

import java.util.Scanner;

public class Defender extends Game {
    /**
     * Implements the Defender mode : the user answers to the propositions of the computer
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
    }
}
