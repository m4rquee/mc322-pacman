package com.ic.unicamp.br.mc322.pacman.game.runner;

import com.ic.unicamp.br.mc322.pacman.game.controller.BoardController;
import com.ic.unicamp.br.mc322.pacman.game.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Runner extends JFrame {

	private Runner() {
		initUI();
	}

	private void initUI() {
		add(new GameController());

		setResizable(false);
		pack();

		setTitle("Pacman");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escolha uma das opções a seguir:");
		System.out.println("1 - Mapas aleatórios");
		System.out.println("0 - Mapa fixo padrão");
		BoardController.MAP_STYLE = Integer.parseInt(entrada.nextLine());

		EventQueue.invokeLater(() -> new Runner().setVisible(true));
	}
}
