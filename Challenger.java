package com.cursan.gameplay_studio;
import java.util.Scanner;

public class Challenger extends Game {
    public Challenger() {
        makeCombination();
        game();
    }

    public void makeCombination() {
        String combination = "";
        int number;
        for (int i = 0; i < 4; i++) {
            number = (int) (Math.random() * 10);
            combination += number;
        }
        setCombination(combination);
    }

    public void game() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            response = "";
            System.out.println("Votre proposition : ");
            do {
                setProposition(sc.next());
                try {
                    Integer.parseInt(getProposition());
                }
                catch (NumberFormatException e) {
                    System.out.println("Erreur : saisie invalide");
                    game();
                    return; // The first call has to finish
                }
                if (getProposition().length() != 4) {
                    System.out.println("Vous devez saisir un nombre entier à quatre chiffres.\nVotre proposition : ");
                }
            } while (getProposition().length() != 4);
            for (int i = 0; i < getCombination().length(); i++) {
                if ((int) getCombination().charAt(i) > (int) getProposition().charAt(i)) {
                    response += "+";
                } else if ((int) getCombination().charAt(i) < (int) getProposition().charAt(i)) {
                    response += "-";
                } else {
                    response += "=";
                }
            }
            setResponse(response);
            System.out.println("Proposition : " + getProposition() + " -> Réponse : " + getResponse());
        } while (!getCombination().equals(getProposition()));
        System.out.println("Félicitations ! Vous avez trouvé la bonne combinaison !");
    }
}
