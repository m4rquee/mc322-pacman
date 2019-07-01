package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.character.Pacman;

import java.awt.*;

public class PowerUp extends Circle {

    public PowerUp(Point pos, Color color, int size, int pontuation) {
        super(pos, color, size, pontuation);
        this.givesPowerUp = true;
    }

    @Override
    public void givePoints(Pacman pacman) {
        super.givePoints(pacman);
        pacman.setPowerUp(true);
    }
}
