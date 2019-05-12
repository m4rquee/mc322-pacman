package com.ic.unicamp.br.mc322.pacman.game;

import com.ic.unicamp.br.mc322.pacman.game.element.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController extends Board implements ActionListener {

	private final int DOT_SIZE = 10;
	private final int RAND_POS = 29;
	private final int DELAY = 140;

	private Image ball;
	private Image apple;
	private Image head;

	private boolean inGame = true;

	private Timer timer;

	public GameController() {
		super.initBoard();
		loadImages();
		initGame();
	}

	private void initGame() {
		dots = 3;

		for (int z = 0; z < dots; z++) {
			Point at = points.get(z);
			at.setX(50 - z * 10);
			at.setY(50);
		}

		locateApple();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void checkApple() {
		if ((points.get(0).getX() == apple_x) && (points.get(0).getY() == apple_y)) {

			dots++;
			locateApple();
		}
	}

	private void loadImages() {
		ImageIcon iid = new ImageIcon("src/resources/dot.png");
		ball = iid.getImage();

		ImageIcon iia = new ImageIcon("src/resources/apple.png");
		apple = iia.getImage();

		ImageIcon iih = new ImageIcon("src/resources/head.png");
		head = iih.getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	public void doDrawing(Graphics g) {
		if (inGame) {
			super.doDrawing(g, apple, head, ball);
		} else {

			super.gameOver(g);
		}
	}

	private void move() {
		for (int z = dots; z > 0; z--) {
			Point point = points.get(z);
			point.setX(points.get(z - 1).getX());
			point.setY(points.get(z - 1).getY());
		}

		if (KeyController.leftDirection) {
			points.get(0).setX(points.get(0).getX() - DOT_SIZE);
		}

		if (KeyController.rightDirection) {
			points.get(0).setX(points.get(0).getX() + DOT_SIZE);
		}

		if (KeyController.upDirection) {
			points.get(0).setY(points.get(0).getY() - DOT_SIZE);
		}

		if (KeyController.downDirection) {
			points.get(0).setY(points.get(0).getY() + DOT_SIZE);
		}
	}

	private void checkCollision() {
		Point ini = points.get(0);
		for (int z = dots; z > 0; z--) {
		Point at = points.get(z);
			if (ini.getX() == at.getX() && ini.getY() == at.getY()) {
				inGame = false;
			}
		}

		if (ini.getY() >= B_HEIGHT) {
			inGame = false;
		}

		if (ini.getY() < 0) {
			inGame = false;
		}

		if (ini.getX() >= B_WIDTH) {
			inGame = false;
		}

		if (ini.getX() < 0) {
			inGame = false;
		}

		if (!inGame) {
			timer.stop();
		}
	}

	private void locateApple() {
		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {

			checkApple();
			checkCollision();
			move();
		}
		repaint();
	}
}
