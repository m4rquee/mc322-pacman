package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

    public static List<Obstacle> buildObstacles(int[][] map, int squareSize) {
        List<Obstacle> ret = new LinkedList<>();
        boolean foundSpawn = false;
        for (int i = 1; i < map.length - 1 && !foundSpawn; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = -1;
                    map[i - 1][j - 1] = -1;
                    map[i - 1][j] = -1;
                    map[i - 1][j + 1] = -1;
                    map[i][j + 1] = -1;
                    map[i][j - 1] = -1;
                    map[i + 1][j - 1] = -1;
                    map[i + 1][j] = -1;
                    map[i + 1][j + 1] = -1;
                    foundSpawn = true;
                    break;
                }
            }
        }
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1) {
                    ret.add(new Rectangle(new Point(20 + i * squareSize, 20 + j * squareSize)));
                } else if ((i != 0 || j != 0) && map[i][j] == 0) {
                    ret.add(new Circle(new Point(28 + i * squareSize, 28 + j * squareSize)));
                }
        return ret;
    }
}
