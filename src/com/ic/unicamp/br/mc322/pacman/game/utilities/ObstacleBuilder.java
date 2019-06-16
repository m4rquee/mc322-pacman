package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

    public static List<Obstacle> buildObstacles(int[][] map, int squareSize) {
        List<Obstacle> ret = new LinkedList<>();
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1)
                    ret.add(new Rectangle(new Point(20 + i * 30, 30 + j * 30)));
        return ret;
    }
}
