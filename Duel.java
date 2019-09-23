package com.cursan.gameplay_studio;

public class Duel extends Game {
    public Duel() {
        endedGame = false;
        boolean computerWinner = false;
        boolean userWinner = false;
        makeCombination();
        System.out.println("Saisissez votre combinaison : ");
        do {
            drawInputUser("combination");
        } while(userCombination.length() != 4);
        do {
            gameChallenger();
            proposeCombination();
            answerProposition();
            if (userProposition.equals(computerCombination)) {
                System.out.println("Vous avez trouvé la bonne combinaison !");
                endedGame = true;
                userWinner = true;
            }
            if (computerProposition.equals(userCombination)) {
                System.out.println("L'ordinateur a trouvé la bonne combinaison !");
                endedGame = true;
                computerWinner = true;
            }
            if (computerWinner && !userWinner) {
                System.out.println("L'ordinateur a gagné !");
            }
            else if (userWinner && !computerWinner) {
                System.out.println("Vous avez gagné !");
            }
            else if (userWinner && computerWinner) {
                System.out.println("Match nul ! Vous avez trouvé la solution en même temps que l'ordinateur !");
            }
        } while(!endedGame);
    }
}
