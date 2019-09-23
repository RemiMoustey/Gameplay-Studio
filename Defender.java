package com.cursan.gameplay_studio;

import java.util.Scanner;

public class Defender extends Game {

    /**
     * Implements the Defender mode : the user answers to the propositions of the computer
     */
    public Defender() {
        System.out.println("Saisissez votre combinaison : ");
        do {
            drawInputUser("combination");
        } while(combination.length() != 4);
        game();
    }

    /**
     * Implements the working of the game in the challenger mode
     */
    public void game() {
        Scanner sc = new Scanner(System.in);
        proposition = "";
        for(int i = 0; i < combination.length(); i++) {
            proposition += Integer.toString(pickRandomNumber(0, 9));
        }
        do {
            System.out.println("La proposition de l'ordinateur est : " + proposition);
            System.out.println("Votre réponse :");
            response = sc.next();
            loopResponse();
        } while(!combination.equals(proposition));
        System.out.println("L'ordinateur a trouvé la bonne combinaison !");
    }

    /**
     * Controls the response of the user
     */
    public void loopResponse() {
        String newProposition = "";
        for (int i = 0; i < response.length(); i++) {
            if (response.charAt(i) == '+') {
                newProposition += pickRandomNumber(Character.getNumericValue(proposition.charAt(i) + 1), 9);
            }
            else if (response.charAt(i) == '-') {
                newProposition += pickRandomNumber(0, Character.getNumericValue(proposition.charAt(i) - 1));
            }
            else if (response.charAt(i) == '=') {
                newProposition += proposition.charAt(i);
            }
            if (response.charAt(i) != '+' && response.charAt(i) != '-' && response.charAt(i) != '=' || response.length() != 4) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir une réponse correcte. Recommencez : ");
                response = sc.next();
                loopResponse();
                return;
            }
        }
        this.proposition = newProposition;
    }

}
