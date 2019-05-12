package com.ic.unicamp.br.mc322.pacman.game.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    static Boolean leftDirection = false;
    static Boolean rightDirection = true;
    static Boolean upDirection = false;
    static Boolean downDirection = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
            rightDirection = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
            leftDirection = false;
        }

        if (key == KeyEvent.VK_UP) {
            upDirection = true;
            rightDirection = false;
            leftDirection = false;
            downDirection = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            downDirection = true;
            rightDirection = false;
            leftDirection = false;
            upDirection = false;
        }
    }
}
