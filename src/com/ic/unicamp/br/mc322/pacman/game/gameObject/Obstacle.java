package com.ic.unicamp.br.mc322.pacman.game.gameObject;

import com.ic.unicamp.br.mc322.pacman.game.character.Character;

import java.awt.*;

public abstract class Obstacle {
    public abstract void drawMe(Graphics g);

    public abstract boolean collision(Character character);
}
