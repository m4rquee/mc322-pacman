package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.*;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.obstacle.Rectangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

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
    public static Tuple<List<Wall>, List<Collectable>> buildObstacles(int N) {
        spawnCoordinates = new Point(11, 3).times(Rectangle.DEFAULT_SIZE).plus(20);
        int[][] map = new int[(2 << (N + 1)) + 1][(2 << (N + 1)) + 1];
        String line;
        try {
            File mapa = new File("resources/map1");
            FileReader fileReader = new FileReader(mapa);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;
            boolean spawnLine = false;
            int spawnLineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                int in;
                int qtosSub = 0;
                for (int j = 0; j < line.length() - 1; j++) {
                    if (line.substring(j, j + 1).equals("-")) {
                        in = Integer.parseInt(line.substring(j, j + 2));
                        j++;
                        qtosSub++;
                        spawnLine = true;

                    } else {
                        in = Integer.parseInt(line.substring(j, j + 1));
                    }
                    if (i < 17 && j < 17)
                        map[i][j - qtosSub] = in;
                }
                if (qtosSub != 0)
                    spawnLineCount++;
                if (spawnLine) {
                    if (spawnLineCount == 3)
                        map[i][line.length() - qtosSub - 2] = 0;
                    else
                        map[i][line.length() - qtosSub - 2] = 1;
                }
                i++;
                spawnLine = false;
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Tuple<List<Wall>, List<Collectable>> obstacles = new Tuple<>(new LinkedList<>(), new LinkedList<>());
        fillObstacleList(map, obstacles);
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
