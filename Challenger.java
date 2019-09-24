package com.cursan.gameplay_studio;

/**
 * Implements the Challenger mode : the user tries to guess the random combination
 */
public class Challenger extends Game {
    /**
     * Implements the Challenger mode : the user tries to find the generated combination
     */
    public Challenger() {
        developerMode = activateDeveloperMode();
        makeCombination();
        do {
            printSolution(computerCombination);
            gameChallenger();
        } while (!computerCombination.equals(userProposition));
        System.out.println("Félicitations ! Vous avez trouvé la bonne combinaison !");
    }
}
