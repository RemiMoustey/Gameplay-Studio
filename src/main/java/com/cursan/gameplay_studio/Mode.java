package com.cursan.gameplay_studio;
import java.util.Scanner;

/**
 * Represents the select Mode
 */
public class Mode {
    private String selectMode;

    /**
     * Constructor which modifies the choiced mode and manages wrong types of the user
     */
    public Mode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez votre mode de jeu (1 : challenger, 2 : défenseur, 3 : duel)");
        selectMode = sc.next();
        while (!selectMode.equals("1") && !selectMode.equals("2") && !selectMode.equals("3")) {
            System.out.println("Veuillez entrer un numéro valide (1 : challenger, 2 : défenseur, 3 : duel)");
            selectMode = sc.next();
        }
    }

    public String getSelectMode() {
        return selectMode;
    }
}
