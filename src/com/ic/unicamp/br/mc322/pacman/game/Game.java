package com.ic.unicamp.br.mc322.pacman.game;

public class Game {

	private boolean exitSelected;

	public void start() {
		exitSelected = false;

		System.out.println("src.com.ic.unicamp.br.mc322.pacman.game.Game  started!");

		while(!exitSelected) {
			drawBoard();
			readInput();
			updateBoard();
		}

		System.out.println("src.com.ic.unicamp.br.mc322.pacman.game.Game  terminated. Bye!");
	}
}
