package com.company;

import java.awt.*;
import javax.swing.*;

public class Main extends JComponent {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Робот");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/com/company/robot.png"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dim.width, dim.height);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(dim.width, dim.height));

        frame.pack();

        final Theory theory = new Theory(frame);
        final JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image background = Toolkit.getDefaultToolkit().getImage("src/com/company/background1.jpg");
                g.drawImage(background, 0, 0, frame.getWidth(), frame.getHeight(), this);
                theory.setPanel(this);
                theory.draw(g);

            }
        };
        panel.setLayout(null);

        frame.add(panel);
        frame.setVisible(true);
        frame.repaint();
        frame.repaint();
    }
}

