package src.com.ic.unicamp.br.mc322.pacman;

public class Game {

	private boolean exitSelected;

	public void start() {
		exitSelected = false;

		System.out.println("src.com.ic.unicamp.br.mc322.pacman.Game  started!");

		while(!exitSelected) {
			drawBoard();
			readInput();
			updateBoard();
		}

		System.out.println("src.com.ic.unicamp.br.mc322.pacman.Game  terminated. Bye!");
	}
}
