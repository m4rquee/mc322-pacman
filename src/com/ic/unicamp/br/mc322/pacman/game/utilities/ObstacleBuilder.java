package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Circle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class ObstacleBuilder {

    public static Point spawn;

    // Getting obstacles with random generated map
    public static List<Obstacle> buildObstacles(int[][] map) {
        List<Obstacle> ret = new LinkedList<>();
        boolean foundSpawn = false;
        for (int i = 1; i < map.length - 1 && !foundSpawn; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                spawn = new Point(i * Rectangle.DEFAULT_SIZE + 20, j * Rectangle.DEFAULT_SIZE + 20);
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
        ret = fillObstacleList(map, ret);
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
        ret = fillObstacleList(map, ret);
        return ret;
    }

    // Get the obstacle list of the map of integers
    private static List<Obstacle> fillObstacleList(int[][] map, List<Obstacle> ret) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1) {
                    ret.add(new Rectangle(new Point(20 + i * Rectangle.DEFAULT_SIZE, 20 + j * Rectangle.DEFAULT_SIZE)));
                } else if ((i != 0 || j != 0) && map[i][j] == 0) {
                    ret.add(new Circle(new Point(28 + i * Rectangle.DEFAULT_SIZE, 28 + j * Rectangle.DEFAULT_SIZE)));
                }
        return ret;
    }

}
