package com.cursan.gameplay_studio;
import java.util.Scanner;

/**
 * Represents the select Mode
 */
public class Mode {
    private int selectMode;

    /**
     * Constructor which modifies the choiced mode and manages wrong types of the user
     */
    public Mode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez votre mode de jeu (1 : challenger, 2 : défenseur, 3 : duel)");
        selectMode = sc.nextInt();
        while (selectMode != 1 && selectMode != 2 && selectMode != 3) {
            System.out.println("Veuillez entrer un numéro valide");
            selectMode = sc.nextInt();
        }
    }

    public int getSelectMode() {
        return selectMode;
    }
}
