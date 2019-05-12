package com.ic.unicamp.br.mc322.pacman;

import com.ic.unicamp.br.mc322.pacman.game.GameController;

import javax.swing.*;
import java.awt.*;

public class Runner extends JFrame {

	public Runner() {
		initUI();
	}

	private void initUI() {
		add(new GameController());

		setResizable(false);
		pack();

		setTitle("Pacman");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new Runner();
			ex.setVisible(true);
		});
	}
}
