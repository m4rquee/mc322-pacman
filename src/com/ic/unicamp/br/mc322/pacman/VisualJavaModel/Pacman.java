package src.com.ic.unicamp.br.mc322.pacman.VisualJavaModel;

import javax.swing.*;
import java.awt.*;

public class Pacman extends JFrame {

    public Pacman() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Pacman");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Pacman();
            ex.setVisible(true);
        });
    }
}
