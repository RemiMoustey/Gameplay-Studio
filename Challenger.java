package com.cursan.gameplay_studio;

/**
 * Implements the Challenger mode : the user tries to guess the random combination
 */
public class Challenger extends Game {
    /**
     * The constructor of the Challenger Mode
     */
    public Challenger() {
        readConfigFile();
        developerMode = convertBooleanToString(properties.getProperty("developerMode"));
        numberTries = getNumberTriesInFile();
        makeCombination();
        do {
            System.out.println("Il vous reste " + numberTries + " essais.");
            printSolution(computerCombination);
            gameChallenger();
            numberTries--;
        } while (!computerCombination.equals(userProposition) && numberTries > 0);
        if (computerCombination.equals(userProposition))
            System.out.println("Félicitations ! Vous avez trouvé la bonne combinaison !");
        else if (numberTries == 0)
            System.out.println("Dommage ! Vous n'avez plus d'essai possible. La solution était : " + computerCombination);
        choiceReplay("challenger");
    }
}
