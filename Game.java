package com.cursan.gameplay_studio;

import java.io.File;
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
    protected String computerResponse;
    protected boolean endedGame;
    Properties properties = new Properties();
    protected boolean developerMode;

    /**
     * Reads the file config.properties
     */
    public void readConfigFile() {
        try {
            FileInputStream in = new FileInputStream("src/com/cursan/gameplay_studio/config.properties");
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
     * @return
     *          The random double
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
        int number;
        for (int i = 0; i < numberDigits; i++) {
            number = pickRandomNumber(0, 10);
            computerCombination += number;
        }
    }

    public int getNumberDigitsInFile() {
        readConfigFile();
        return Integer.parseInt(properties.getProperty("digits"));
    }

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
        do {
            System.out.println("Il reste à l'ordinateur " + numberTries + " essais");
            printSolution(userCombination);
            answerProposition();
            loopResponse();
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
            computerProposition += Integer.toString(pickRandomNumber(0, 9));
        }
    }

    /**
     * Controls the response of the user
     */
    public void loopResponse() {
        String newProposition = "";
        for (int i = 0; i < userResponse.length(); i++) {
            if (userResponse.charAt(i) == '+') {
                newProposition += pickRandomNumber(Character.getNumericValue(computerProposition.charAt(i)), 10);
            }
            else if (userResponse.charAt(i) == '-') {
                newProposition += pickRandomNumber(0, Character.getNumericValue(computerProposition.charAt(i)));
            }
            else if (userResponse.charAt(i) == '=') {
                newProposition += computerProposition.charAt(i);
            }
            if (userResponse.charAt(i) != '+' && userResponse.charAt(i) != '-' && userResponse.charAt(i) != '=' || userResponse.length() != numberDigits) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir une réponse correcte. Recommencez : ");
                userResponse = sc.next();
                loopResponse();
                return;
            }
        }
        this.computerProposition = newProposition;
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
}
