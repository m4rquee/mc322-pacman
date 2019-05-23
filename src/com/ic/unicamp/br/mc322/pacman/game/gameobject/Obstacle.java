package com.ic.unicamp.br.mc322.pacman.game.gameobject;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Character;

public abstract class Obstacle extends GameObject {

    public abstract void place(Obstacle[][] board);

    public abstract boolean collision(Character character);
}
