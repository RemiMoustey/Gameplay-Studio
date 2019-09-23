package com.cursan.gameplay_studio;

import java.util.Scanner;

/**
 * Brings fields which are used in the three modes
 */
abstract public class Game {
    protected String userCombination;
    protected String userProposition;
    protected String userResponse;
    protected String computerCombination;
    protected String computerProposition;
    protected String computerResponse;
    protected boolean endedGame;

    /**
     * Pick a random number between 0 and 10
     *
     * @return
     *          The random double
     */
    public int pickRandomNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    /**
     * Build the random combination
     */
    public void makeCombination() {
        computerCombination = "";
        int number;
        for (int i = 0; i < 4; i++) {
            number = pickRandomNumber(0, 10);
            computerCombination += number;
        }
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
            answerProposition();
        } while(!userCombination.equals(computerProposition));
        System.out.println("L'ordinateur a trouvé la bonne combinaison !");
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
                newProposition += pickRandomNumber(Character.getNumericValue(computerProposition.charAt(i) + 1), 9);
            }
            else if (userResponse.charAt(i) == '-') {
                newProposition += pickRandomNumber(0, Character.getNumericValue(computerProposition.charAt(i) - 1));
            }
            else if (userResponse.charAt(i) == '=') {
                newProposition += computerProposition.charAt(i);
            }
            if (userResponse.charAt(i) != '+' && userResponse.charAt(i) != '-' && userResponse.charAt(i) != '=' || userResponse.length() != 4) {
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
            System.out.println("Saisie invalide : vous devez saisir un nombre entier à quatre chiffres.\nRecommencez : ");
            drawInputUser(field);
            return; // The first call has to finish
        }
        if (input.length() != 4) {
            System.out.println("Saisie invalide : vous devez saisir un nombre entier à quatre chiffres.\nRecommencez : ");
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
