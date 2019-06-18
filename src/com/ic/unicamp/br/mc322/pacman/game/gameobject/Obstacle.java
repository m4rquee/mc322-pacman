package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public abstract class Obstacle extends GameObject {

    public boolean shouldPontuate;

    public abstract void drawMe(Graphics g);

    public abstract boolean collision(Character character);
}
