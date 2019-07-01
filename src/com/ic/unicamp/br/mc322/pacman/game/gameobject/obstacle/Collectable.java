package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;

public abstract class Collectable extends Obstacle {

    private int pontuation;
    protected boolean givesPowerUp;

    public Collectable(int pontuation) {
        this.pontuation = pontuation;
        this.givesPowerUp = false;
    }

    public void givePoints(Pacman pacman) {
        pacman.pontuate(this.pontuation);
    }

    public boolean givesPowerUp() {
        return this.givesPowerUp;
    }
}
