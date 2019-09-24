package com.cursan.gameplay_studio;

public class Main {

    public static void main(String[] args) {
        Mode mode = new Mode();
        if (mode.getSelectMode() == 1) {
            Game game = new Challenger();
        }
        else if (mode.getSelectMode() == 2) {
            Game game = new Defender();
        }
        else if (mode.getSelectMode() == 3) {
            Game game = new Duel();
        }
    }
}