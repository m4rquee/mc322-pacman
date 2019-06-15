package com.ic.unicamp.br.mc322.pacman.game.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator {

	private static int scaleUp(int k) {
		return 2 * k + 1;
	}

	private static int safeGet(int[][] m, int i, int j, int n) {
		return i < 0 || i >= n || j < 0 || j >= n ? -1 : m[i][j];
	}

	private static void genTileMapRec(int[][] map, int size, int x_0, int y_0, int p_x, int p_y, int count) {
		count++;
		if (size == 2)
			for (int i = x_0; i < x_0 + size; i++)
				for (int j = y_0; j < y_0 + size; j++) {
					if (i != p_x || j != p_y)
						map[i][j] = count;
				}
		else {
			int L = (size * size - 1) / 12;
			int half_s = size / 2;
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++) {
					int n_x = p_x, n_y = p_y;
					int count_pad = (i * 2 + j) * L;
					if (!(p_x >= x_0 + i * half_s && p_x < x_0 + (i + 1) * half_s &&
							p_y >= y_0 + j * half_s && p_y < y_0 + (j + 1) * half_s)) {
						n_x = x_0 + half_s + i - 1;
						n_y = y_0 + half_s + j - 1;

						map[n_x][n_y] = count;
					}

					genTileMapRec(map, size / 2, x_0 + i * half_s, y_0 + j * half_s, n_x, n_y,
							count + count_pad);
				}
		}
	}

	private static int[][] genTileMap(int size) {
		int[][] ret = new int[size][size];
		int x = ThreadLocalRandom.current().nextInt(0, size);
		int y = ThreadLocalRandom.current().nextInt(0, size);
		genTileMapRec(ret, size, 0, 0, x, y, 0);
		return ret;
	}

	public static int[][] genMap(int[][] tile_map, int tile_size, int size) {
		int[][] ret = new int[size][size];
		for (int i = 1; i < size; i += 2)
			for (int j = 1; j < size; j += 2)
				ret[i][j] = '+';

		for (int i = 0; i < tile_size; i++)
			for (int j = i % 2; j < tile_size; j += 2) {
				int curr = tile_map[i][j];

				if (safeGet(tile_map, i - 1, j, tile_size) == curr)
					ret[scaleUp(i) - 1][scaleUp(j)] = '+';

				if (safeGet(tile_map, i, j - 1, tile_size) == curr)
					ret[scaleUp(i)][scaleUp(j) - 1] = '+';

				if (safeGet(tile_map, i, j + 1, tile_size) == curr)
					ret[scaleUp(i)][scaleUp(j) + 1] = '+';

				if (safeGet(tile_map, i + 1, j, tile_size) == curr)
					ret[scaleUp(i) + 1][scaleUp(j)] = '+';
			}

		return ret;
	}
}
