package com.cursan.gameplay_studio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Brings fields which are used in the three modes
 */
abstract public class Game {
    protected int numberDigits;
    protected int numberTries;
    protected String userCombination;
    protected String userProposition;
    protected String userResponse;
    protected String computerCombination;
    protected String computerProposition;
    protected int[] min;
    protected int[] max;
    protected String computerResponse;
    protected boolean endedGame;
    Properties properties = new Properties();
    protected boolean developerMode;

    private static final Logger logger = LogManager.getLogger(Game.class);

    /**
     * Reads the file config.properties
     */
    public void readConfigFile() {
        logger.debug("Reading of the file configuration");
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            properties.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open the file");
        } catch (IOException e) {
            System.err.println("Unable to load config file");
        }
    }

    /**
     * Converts a string to a boolean
     *
     * @param s
     *          The string to convert
     * @return
     *          The boolean
     */
    public boolean convertBooleanToString (String s) {
        return s.equals("true");
    }

    /**
     * Prints the solution for the developer mode
     *
     * @param combination
     *           The solution
     */
    public void printSolution(String combination) {
        if (developerMode)
            if (combination == computerCombination)
                System.out.println("La solution de l'ordinateur est : " + combination);
            else if (combination == userCombination)
                System.out.println("Votre solution est : " + combination);
    }

    /**
     * Pick a random number between 0 and 10
     *
     * @param min
     *          The lower limit
     * @param max
     *          The higher limit
     * @return
     *          The random integer
     */
    public int pickRandomNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    /**
     * Build the random combination
     */
    public void makeCombination() {
        computerCombination = "";
        numberDigits = getNumberDigitsInFile();
        logger.debug("Random draw of the combination (" + numberDigits + " numbers)");
        int number;
        for (int i = 0; i < numberDigits; i++) {
            number = pickRandomNumber(0, 10);
            computerCombination += number;
        }
    }

    /**
     * Reads the number of digits in a file and return it
     *
     * @return
     *          The mentioned number of digits
     */
    public int getNumberDigitsInFile() {
        readConfigFile();
        return Integer.parseInt(properties.getProperty("digits"));
    }

    /**
     * Reads the number of tries in a file and return it
     *
     * @return
     *          The mentioned number of tries
     */
    public int getNumberTriesInFile() {
        readConfigFile();
        return Integer.parseInt(properties.getProperty("tries"));
    }

    /**
     * Implements the working of the game in the challenger mode
     */
    public void gameChallenger() {
        computerResponse = "";
        System.out.println("Votre proposition : ");
        drawInputUser("proposition");
        for (int i = 0; i < computerCombination.length(); i++) {
            if ((int) computerCombination.charAt(i) > (int) userProposition.charAt(i)) {
                computerResponse += "+";
            } else if ((int) computerCombination.charAt(i) < (int) userProposition.charAt(i)) {
                computerResponse += "-";
            } else {
                computerResponse += "=";
            }
        }
        System.out.println("Proposition : " + userProposition + " -> Réponse : " + computerResponse);
    }

    /**
     * Implements the working of the game in the challenger mode
     */
    public void gameDefender() {
        proposeCombination();
        min = new int[getNumberDigitsInFile()];
        for (int i = 0; i < min.length; i++) {
            min[i] = -1;
        }
        max = new int[getNumberDigitsInFile()];
        for (int i = 0; i < max.length; i++) {
            max[i] = 10;
        }
        do {
            if (numberTries > 1) {
                System.out.println("Il reste à l'ordinateur " + numberTries + " essais");
            } else if (numberTries == 1) {
                System.out.println("Il reste à l'ordinateur " + numberTries + " essai");
            }
            printSolution(userCombination);
            answerProposition();
            numberTries--;
        } while(!userCombination.equals(computerProposition) && numberTries > 0);
        if (userCombination.equals(computerProposition))
            System.out.println("L'ordinateur a trouvé la bonne combinaison !");
        else if (numberTries == 0)
            System.out.println("Dommage ! L'ordinateur n'a plus d'essai possible. La solution était : " + userCombination);
    }

    /**
     * The user answers to the proposition of the computer
     */
    public void answerProposition() {
        Scanner sc = new Scanner(System.in);
        System.out.println("La proposition de l'ordinateur est : " + computerProposition);
        System.out.println("Votre réponse :");
        userResponse = sc.next();
        loopResponse();
    }

    /**
     * The computer proposes a combination
     */
    public void proposeCombination() {
        computerProposition = "";
        for(int i = 0; i < userCombination.length(); i++) {
            computerProposition += Integer.toString(pickRandomNumber(0, 10));
        }
    }

    /**
     * Controls the response of the user
     */
    public void loopResponse() {
        String currentProposition = computerProposition;
        computerProposition = "";
        for (int i = 0; i < userResponse.length(); i++) {
            if (userResponse.charAt(i) == '+') {
                if (Character.getNumericValue(currentProposition.charAt(i)) > min[i]) {
                    min[i] = Character.getNumericValue(currentProposition.charAt(i));
                }
                computerProposition += pickRandomNumber(min[i] + 1, max[i]);
            }
            else if (userResponse.charAt(i) == '-') {
                if (Character.getNumericValue(currentProposition.charAt(i)) < max[i]) {
                    max[i] = Character.getNumericValue(currentProposition.charAt(i));
                }
                computerProposition += pickRandomNumber(min[i] + 1, max[i]);
            }
            else if (userResponse.charAt(i) == '=') {
                computerProposition += currentProposition.charAt(i);
            }
            if (userResponse.charAt(i) != '+' && userResponse.charAt(i) != '-' && userResponse.charAt(i) != '=' || userResponse.length() != numberDigits) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir une réponse correcte. Recommencez : ");
                computerProposition = currentProposition;
                userResponse = sc.next();
                loopResponse();
                return;
            }
        }
    }

    /**
     * Modify a field with the user's input
     *
     * @param field
     *              The identifier of the field
     */
    public void drawInputUser(String field) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        try {
            Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            System.out.println("Saisie invalide : vous devez saisir un nombre entier à " +  numberDigits + " chiffres.\nRecommencez : ");
            drawInputUser(field);
            return; // The first call has to finish
        }
        if (input.length() != getNumberDigitsInFile()) {
            System.out.println("Saisie invalide : vous devez saisir un nombre entier à " + numberDigits + " chiffres.\nRecommencez : ");
            drawInputUser(field);
            return; // The first call has to finish
        }
        try {
            if (field == "proposition") {
                userProposition = input;
            } else if (field == "combination") {
                userCombination = input;
            }
        }
        catch (Exception e) {
            System.err.println("Le champ saisi est invalide.");
            System.exit(1);
        }
    }

    /**
     *  Manages the different possible replays
     *
     * @param currentMode
     *              The mode on which the user played
     */
    public void choiceReplay(String currentMode) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous rejouer ? (1 : rejouer au même mode, 2 : jouer à un autre mode, 3 : quitter le jeu)");
        String input = sc.next();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Saisie invalide. Recommencez. (1 : rejouer au même mode, 2 : jouer à un autre mode, 3 : quitter le jeu)");
            input = sc.next();
        }
        if (input.equals("1")) {
            if (currentMode == "challenger")
                new Challenger();
            else if (currentMode == "defender")
                new Defender();
            else if (currentMode == "duel")
                new Duel();
        }
        else if (input.equals("2")) {
            chooseMode();
        }
    }

    /**
     * Opens the select mode
     */
    public static void chooseMode() {
        logger.debug("Choice of a mod");
        Mode mode = new Mode();
        if (mode.getSelectMode().equals("1")) {
            new Challenger();
        } else if (mode.getSelectMode().equals("2")) {
            new Defender();
        } else if (mode.getSelectMode().equals("3")) {
            new Duel();
        }
    }
}
