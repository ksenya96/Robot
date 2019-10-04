package com.company;

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 20.09.2015.
 */
public class Robot {
    private int x0, y0;
    private int x, y;
    private int width, height;
    private JFrame frame;

    public Robot(int x0, int y0, int width, int height, JFrame frame) {
        this.x0 = x0;
        this.y0 = y0;
        x = x0;
        y = y0;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    public void draw(Graphics g) {
        Image background = Toolkit.getDefaultToolkit().getImage("src/com/company/robot.png");
        g.drawImage(background, x, y, width, height, null);
    }

    public void setInitialCoords() {
        this.x = x0;
        this.y = y0;
        frame.repaint();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(CommandEnum command) {
        CommandFactory factory = new CommandFactory(command);
        Point p = factory.move(new Point(x, y));
        if (p != null) {
            x = p.getX();
            y = p.getY();
        }
        frame.repaint();
    }
}

