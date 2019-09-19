package com.cursan.gameplay_studio;

public class Main {

    public static void main(String[] args) {
        Mode mode = new Mode();
        if (mode.getChoicedMode() == 1) {
            Game game = new Challenger();
        }
    }
}
