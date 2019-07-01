package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.GameObject;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

import java.awt.*;

public abstract class Obstacle extends GameObject {

    public abstract void drawMe(Graphics g);

    public abstract boolean collision(Character character);
}
