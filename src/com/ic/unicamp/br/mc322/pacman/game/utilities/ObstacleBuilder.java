package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.*;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Rectangle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

    public static final String FILE_NAME = "resources/map1";
    public static Point spawnCoordinates;

    // Getting obstacles with random generated map
    public static Tuple<List<Wall>, List<Collectable>> buildObstacles(Tuple<Point, int[][]> mapAndSpawn) {
        int[][] map = mapAndSpawn.getB();
        spawnCoordinates = mapAndSpawn.getA().times(Rectangle.DEFAULT_SIZE).plus(20);
        Tuple<List<Wall>, List<Collectable>> obstacles = new Tuple<>(new LinkedList<>(), new LinkedList<>());
        fillObstacleList(map, obstacles);
        return obstacles;
    }

    // Reading map from file
    public static Tuple<List<Wall>, List<Collectable>> buildObstacles() {
        Tuple<Point, int[][]> mapAndSpawn = MapReader.readMap(FILE_NAME);
        spawnCoordinates = mapAndSpawn.getA().times(Rectangle.DEFAULT_SIZE).plus(20);
        Tuple<List<Wall>, List<Collectable>> obstacles = new Tuple<>(new LinkedList<>(), new LinkedList<>());
        fillObstacleList(mapAndSpawn.getB(), obstacles);
        return obstacles;
    }

    // Get the obstacle list of the map of integers
    private static void fillObstacleList(int[][] map, Tuple<List<Wall>, List<Collectable>> obstacles) {
        List<Wall> walls = obstacles.getA();
        List<Collectable> collectables = obstacles.getB();

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) {
                Point offset = new Point(i, j).times(Rectangle.DEFAULT_SIZE);
                if (map[i][j] == 1) {
                    walls.add(new Rectangle(offset.plus(20)));
                } else if (i != 0 || j != 0) {
                    if (map[i][j] == 2)
                        collectables.add(new PowerUp(offset.plus(28), Color.RED, 18, 100));
                    else if (map[i][j] == 0)
                        collectables.add(new Circle(offset.plus(28), 10));
                }
            }
    }
}
