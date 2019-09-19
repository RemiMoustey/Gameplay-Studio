package com.cursan.gameplay_studio;

/**
 * Implements the Challenger mode : the user tries to guess the random combination
 */
public class Challenger extends Game {
    public Challenger() {
        makeCombination();
        game();
    }

    /**
     * Build the random combination
     */
    public void makeCombination() {
        String programCombination = "";
        int number;
        for (int i = 0; i < 4; i++) {
            number = (int) pickRandomNumber();
            programCombination += number;
        }
        combination = programCombination;
    }

    /**
     * Implements the working of the game in the challenger mode
     */
    public void game() {
        String userResponse;
        do {
            String programResponse = "";
            System.out.println("Votre proposition : ");
            drawInputUser("proposition");
            for (int i = 0; i < combination.length(); i++) {
                if ((int) combination.charAt(i) > (int) proposition.charAt(i)) {
                    programResponse += "+";
                } else if ((int) combination.charAt(i) < (int) proposition.charAt(i)) {
                    programResponse += "-";
                } else {
                    programResponse += "=";
                }
            }
            response = programResponse;
            System.out.println("Proposition : " + proposition + " -> Réponse : " + response);
        } while (!combination.equals(proposition));
        System.out.println("Félicitations ! Vous avez trouvé la bonne combinaison !");
    }
}
