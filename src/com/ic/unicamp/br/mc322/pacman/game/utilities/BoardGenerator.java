package com.ic.unicamp.br.mc322.pacman.game.utilities;

import com.ic.unicamp.br.mc322.pacman.game.gameobject.Board;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Obstacle;
import com.ic.unicamp.br.mc322.pacman.game.gameobject.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class BoardGenerator {

	private static final int PREFIXED_BOARDS = 3;
	private static final Random randomGenerator = new Random();

	private int boardHeight;
	private int boardWith;
	private boolean rnd;

	private final ArrayList<ArrayList<Obstacle>> obstacles = new ArrayList<>(PREFIXED_BOARDS);

	private BoardGenerator(int boardHeight, int boardWith, boolean rnd) {
		this.boardHeight = boardHeight;
		this.boardWith = boardWith;
		this.rnd = rnd;
		for (int i = 0; i < PREFIXED_BOARDS; i++)
			this.obstacles.add(new ArrayList<>());

		init();
	}

	private void init() { // Prefixed boards
		for (int y = 20; y < this.boardHeight; y += 35)
			for (int x = 20; x < this.boardWith - 20; x += 70)
				this.obstacles.get(0).add(new Rectangle(new Point(x, y), 50, 10));
	}

	public Board generateBoard() {
		return this.rnd ? generatedBoard() : prefixedBoard();
	}

	private Board generatedBoard() {
		return null;
	}

	private Board prefixedBoard() {
		Board ret = new Board(this.boardHeight, this.boardWith, 5);
		int option = 0; // randomGenerator.nextInt(PREFIXED_BOARDS);
		this.obstacles.get(option).forEach(ret::addObstacle);
		return ret;
	}
}
