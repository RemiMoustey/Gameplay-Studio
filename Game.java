package com.cursan.gameplay_studio;

import java.util.Scanner;

/**
 * Brings fields which are used in the three modes
 */
abstract public class Game {
    protected String combination;
    protected String proposition;
    protected String response;

    /**
     * Pick a random number between 0 and 10
     *
     * @return
     *          The random double
     */
    public int pickRandomNumber(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    public void makeProposition(String combination) {

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
                proposition = input;
            } else if (field == "combination") {
                combination = input;
            }
        }
        catch (Exception e) {
            System.err.println("Le champ saisi est invalide.");
            System.exit(1);
        }
    }
}
