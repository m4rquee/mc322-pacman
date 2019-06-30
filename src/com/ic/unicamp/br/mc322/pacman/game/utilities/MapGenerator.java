package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator {

    private Point spawn;

    private static int scaleUp(int k) { // Coverts tile map index to final map index
        return 2 * k + 1;
    }

    private static int safeGet(int[][] m, int i, int j) { // Returns -1 if the index is out of bound
        return i < 0 || i >= m.length || j < 0 || j >= m[0].length ? -1 : m[i][j];
    }

    // Recursively covers the map with L tiles (each has an unique number), prof and example of such tiling:
    // https://www.geeksforgeeks.org/tiling-problem-using-divide-and-conquer-algorithm/
    private static void generateTileMapRec(int[][] map, int size, int x_0, int y_0, int p_x, int p_y, int count) {
        count++;
        if (size == 2) {// In this case the remaining cells will be an L
            for (int i = x_0; i < x_0 + size; i++)
                for (int j = y_0; j < y_0 + size; j++)
                    if (i != p_x || j != p_y)
                        map[i][j] = count;
        } else {
            int half_s = size / 2; // Size of each sub-map
            int L = (size * size - 1) / 12; // Number of L needed to cover each sub-map
            for (int i = 0; i < 2; i++) // Iterates over each sub-map
                for (int j = 0; j < 2; j++) {
                    int n_x = p_x, n_y = p_y;
                    int count_pad = (i * 2 + j) * L; // Used to divide the ids over the sub-maps

                    // If the generated point isn't inside the sub-map the corner is used instead:
                    if (!(p_x >= x_0 + i * half_s && p_x < x_0 + (i + 1) * half_s &&
                            p_y >= y_0 + j * half_s && p_y < y_0 + (j + 1) * half_s)) {
                        // Corner of the current sub-map
                        n_x = x_0 + half_s + i - 1;
                        n_y = y_0 + half_s + j - 1;

                        map[n_x][n_y] = count;
                    }

                    // Tile the current sub-map:
                    generateTileMapRec(map, size / 2, x_0 + i * half_s, y_0 + j * half_s, n_x, n_y,
                            count + count_pad);
                }
        }
    }

    // Spawns a random point inside the matrix, then covers with L shaped tiles:
    private static int[][] generateTileMap(int size) {
        int[][] ret = new int[size][size];
        // Generates a point to start tiling around:
        int p_x = ThreadLocalRandom.current().nextInt(1, size - 1);
        int p_y = ThreadLocalRandom.current().nextInt(1, size - 1);
        ret[p_x][p_y] = 0; // Starting point
        generateTileMapRec(ret, size, 0, 0, p_x, p_y, 0);
        return ret;
    }

    // Generates a map with size 2^(N + 1) + 1, uses the tiles as the map walls:
    public static int[][] generateMap(int N, Point spawn) {
        int tile_size = 2 << N;
        int[][] tile_map = generateTileMap(tile_size);

        // Use the generated tiles as the walls of the new map:

        int map_size = 2 * tile_size + 1;
        int[][] ret = new int[map_size][map_size];

        for (int i = 1; i < map_size; i += 2) // Fill every odd coordinate pair with ones
            for (int j = 1; j < map_size; j += 2)
                ret[i][j] = 1;

        for (int i = 0; i < tile_size; i++)
            for (int j = 0; j < tile_size; j++) {
                int curr = tile_map[i][j];
                int scaled_i = scaleUp(i), scaled_j = scaleUp(j);

                if (curr == 0) {
                    spawn.setX(scaled_i);
                    spawn.setY(scaled_j);

                    // Make ghosts spawn area:
                    for (int a = scaled_i - 1; a <= scaled_i + 1; a++)
                        for (int b = scaled_j - 1; b <= scaled_j + 1; b++)
                            ret[a][b] = -1;
                } else {
                    // If a neighbor cell is part of this tile the wall is extended towards it:
                    if (safeGet(tile_map, i - 1, j) == curr)
                        ret[scaled_i - 1][scaled_j] = 1;
                    if (safeGet(tile_map, i, j - 1) == curr)
                        ret[scaled_i][scaled_j - 1] = 1;
                    if (safeGet(tile_map, i, j + 1) == curr)
                        ret[scaled_i][scaled_j + 1] = 1;
                    if (safeGet(tile_map, i + 1, j) == curr)
                        ret[scaled_i + 1][scaled_j] = 1;
                }
            }

        return ret;
    }

    public static void printMap(int[][] map) { // For debugging propose
        for (int[] line : map) {
            for (int j = 0; j < map[0].length; j++)
                System.out.print(String.format("%02d ", line[j]));
            System.out.println();
        }
    }
}
