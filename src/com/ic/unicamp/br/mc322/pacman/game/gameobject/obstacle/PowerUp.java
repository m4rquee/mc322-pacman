package com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.awt.*;

public class PowerUp extends Circle {

    public PowerUp(Point pos, int pontuation) {
        super(pos, pontuation);
    }

    public PowerUp(Point pos, Color color, int size, int pontuation) {
        super(pos, color, size, pontuation);
    }
}
