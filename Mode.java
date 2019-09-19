package com.cursan.gameplay_studio;
import java.util.Scanner;

public class Mode {
    private int choicedMode;

    public Mode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez votre mode de jeu (1 : challenger, 2 : défenseur, 3 : duel)");
        choicedMode = sc.nextInt();
        while (choicedMode != 1 && choicedMode != 2 && choicedMode != 3) {
            System.out.println("Veuillez entrer un numéro valide");
            choicedMode = sc.nextInt();
        }
    }

    public int getChoicedMode() {
        return choicedMode;
    }
}
