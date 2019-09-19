package com.cursan.gameplay_studio;

public class Main {

    public static void main(String[] args) {
        Mode mode = new Mode();
        if (mode.getChoicedMode() == 1) {
            Game game = new Challenger();
        }
        else if (mode.getChoicedMode() == 2) {
            Game game = new Defender();
        }
        else if (mode.getChoicedMode() == 3) {
            Game game = new Duel();
        }
    }
}
