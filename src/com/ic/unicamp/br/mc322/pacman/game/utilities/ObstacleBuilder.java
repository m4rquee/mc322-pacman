package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

    public static Point spawn;
    public static int[][] intMap;

    // Getting obstacles with random generated map
    public static List<Obstacle> buildObstacles(AbstractMap.SimpleEntry<Point, int[][]> mapAndSpawn) {
        intMap = mapAndSpawn.getValue();
        boolean foundSpawn = false;
        for (int i = 0; i < intMap.length && !foundSpawn; i++) {
            for (int j = 0; j < intMap[i].length; j++) {
                if (intMap[i][j] == -1) {
                    foundSpawn = true;
                    spawn = new Point((i+1) * Rectangle.DEFAULT_SIZE + 20, (j+1) * Rectangle.DEFAULT_SIZE + 20);
                    break;
                }
            }
            if (foundSpawn)
                break;
        }
        List<Obstacle> ret = new LinkedList<>();
        fillObstacleList(intMap, ret);
        return ret;
    }

    // Reading map from file
    public static List<Obstacle> buildObstacles() {
        spawn = new Point(4 * Rectangle.DEFAULT_SIZE + 20, 11 * Rectangle.DEFAULT_SIZE + 20);
        int[][] map = new int[17][17];
        List<Obstacle> ret = new LinkedList<>();
        String linha;
        try {
            File mapa = new File("resources/map1");
            FileReader fileReader = new FileReader(mapa);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;
            boolean spawnLine = false;
            int spawnLineCount = 0;
            while ((linha = bufferedReader.readLine()) != null) {
                int in;
                int qtosSub = 0;
                for (int j = 0; j < linha.length() - 1; j++) {
                    if (linha.substring(j, j + 1).equals("-")) {
                        in = Integer.parseInt(linha.substring(j, j + 2));
                        j++;
                        qtosSub++;
                        spawnLine = true;

                    } else {
                        in = Integer.parseInt(linha.substring(j, j + 1));
                    }
                    if (i < 17 && j < 17)
                        map[i][j - qtosSub] = in;
                }
                if (qtosSub != 0)
                    spawnLineCount++;
                if (spawnLine) {
                    if (spawnLineCount == 3)
                        map[i][linha.length() - qtosSub - 2] = 0;
                    else
                        map[i][linha.length() - qtosSub - 2] = 1;
                }
                i++;
                spawnLine = false;
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        fillObstacleList(map, ret);
        intMap = map;
        return ret;
    }

    // Get the obstacle list of the map of integers
    private static void fillObstacleList(int[][] map, List<Obstacle> list) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) {
                Point offset = new Point(i, j).times(Rectangle.DEFAULT_SIZE);
                if (map[i][j] == 1) {
                    list.add(new Rectangle(offset.plus(20)));
                } else if (i != 0 || j != 0) {
                    if (map[i][j] == 2)
                        list.add(new Circle(offset.plus(28), Color.RED, 18, 100));
                    else if (map[i][j] == 0)
                        list.add(new Circle(offset.plus(28), 10));
                }
            }
    }
}
