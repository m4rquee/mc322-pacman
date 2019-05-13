package com.ic.unicamp.br.mc322.pacman.game.model;

import com.ic.unicamp.br.mc322.pacman.game.controller.KeyController;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Point;

import java.awt.*;

public class GameModel {
	void initBoard() {
		buildObstacles();
	}

	private void buildObstacles() {
		for (int y = 20; y < B_HEIGHT; y += 35) {
			for (int x = 20; x < B_WIDTH - 20; x += 70) {
				obstacleController.add(new com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle(new Point(x, y), 50, 10));
			}
		}
	}
}
