package com.ic.unicamp.br.mc322.pacman.game;

import com.ic.unicamp.br.mc322.pacman.game.element.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    static final int B_WIDTH = 300;
    static final int B_HEIGHT = 300;
    static final int B_LENGTH = 900;

    static final ArrayList<Point> points = new ArrayList<>();

    static int dots;
    static int apple_x;
    static int apple_y;

    public Board() {
        initBoard();
    }
    
    void initBoard() {

        addKeyListener(new KeyController());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        for(int i = 0; i < B_LENGTH; i++) {
            points.add(new Point(0, 0));
        }
    }

    public void doDrawing(Graphics g, Image apple, Image head, Image ball) {
        g.drawImage(apple, apple_x, apple_y, this);

        for (int z = 0; z < B_LENGTH; z++) {
            int x = points.get(z).getX();
            int y = points.get(z).getY();
            if (z == 0) {
                g.drawImage(head, x, y, this);
            } else {
                g.drawImage(ball, x, y, this);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
}
