package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.io.*;
import java.security.InvalidParameterException;

public class MapReader {

    public static Tuple<Point, int[][]> readMap(String fileName) {
        try {
            int[][] map;
            Point spawn;
            Tuple<Point, int[][]> ret;

            String line;
            File mapa = new File(fileName);
            FileReader fileReader = new FileReader(mapa);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] mapParams = bufferedReader.readLine().split(" ");
            if (mapParams.length == 4) {
                map = new int[Integer.parseInt(mapParams[0])][Integer.parseInt(mapParams[1])];
                spawn = new Point(Integer.parseInt(mapParams[2]), Integer.parseInt(mapParams[3]));
            } else
                throw new InvalidParameterException("Invalid file format");

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

            return new Tuple<>(spawn, map);
        } catch (FileNotFoundException e) {
            throw new InvalidParameterException("File does not exists");
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new InvalidParameterException("Invalid file");
    }
}
