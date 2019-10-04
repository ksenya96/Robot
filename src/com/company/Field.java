package com.company;

/**
 * Created by acer on 20.09.2015.
 */

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;

import java.awt.*;
import java.util.ArrayList;

public class Field {
    private int x0, y0;
    private int width, height;
    public static final int SQUARE_SIZE = 85;
    private ArrayList<Point> robotWay = new ArrayList<>();
    private Point[] necessaryCells;
    private Point[] barrierCells;
    private Point robotCoords;

    public Field(int x0, int y0, int width, int height) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x = x0;
        int y = y0;
        int border = 15;
        g2.setColor(new Color(139, 9, 12));
        g2.fillRect(x0 - border, y0 - border, width * SQUARE_SIZE + border * 2, height * SQUARE_SIZE + border * 2);
        g2.setStroke(new BasicStroke(2));

        //рисуем клетки поля
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g2.setColor(new Color(250, 250, 184));
                g2.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g2.setColor(new Color(139, 9, 12));
                g2.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                x += SQUARE_SIZE;
            }
            y += SQUARE_SIZE;
            x = x0;

        }

        paintCells(g2);
        g2.setColor(new Color(255, 163, 2)); //закрасить начальную клетку робота
        g2.fillRect(robotCoords.getX(), robotCoords.getY(), SQUARE_SIZE, SQUARE_SIZE);
        g2.setColor(new Color(139, 9, 12));
        g2.drawRect(robotCoords.getX(), robotCoords.getY(), SQUARE_SIZE, SQUARE_SIZE);

        g2.setColor(Color.BLACK);
        for (int i = 1; i < robotWay.size(); i += 2) {
            g2.drawLine(robotWay.get(i - 1).getX(), robotWay.get(i - 1).getY(), robotWay.get(i).getX(), robotWay.get(i).getY());
        }
    }

    //нарисовать траекторию движения
    public void drawWay(CommandEnum command) {
        CommandFactory factory = new CommandFactory(command);
        factory.drawWay(this);
    }

    public void clearWay() {
        robotWay.clear();
        robotWay.add(new Point(robotCoords.getX() + SQUARE_SIZE / 2, robotCoords.getY() + SQUARE_SIZE / 2));
    }

    //закрасить клетки для задания
    private void paintCells(Graphics g) {
        int x, y;
        for (int i = 0; i < necessaryCells.length; i++) {
            x = x0 + necessaryCells[i].getX() * SQUARE_SIZE;
            y = y0 + necessaryCells[i].getY() * SQUARE_SIZE;
            g.setColor(new Color(255, 255, 0));
            g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            g.setColor(new Color(139, 9, 12)/*new Color(255, 163, 2)*/);
            g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        }

        if (barrierCells != null)
            for (int i = 0; i < barrierCells.length; i++) {
                x = x0 + barrierCells[i].getX() * SQUARE_SIZE;
                y = y0 + barrierCells[i].getY() * SQUARE_SIZE;
                g.setColor(new Color(0, 0, 0));
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.setColor(new Color(139, 9, 12));
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
    }

    //установить клетки, которые необходимо пройти


    public void setNecessaryCells(Point[] necessaryCells) {
        this.necessaryCells = necessaryCells.clone();
    }

    //установть клетки-барьеры (только для условных алгоритмов)
    public void setBarrierCells(Point[] barrierCells) {
        this.barrierCells = barrierCells.clone();
    }

    //проверить, все ли клетки в задании пройдены
    public boolean isAllCellsCrossed() {
        int k = 0;
        for (int i = 0; i < necessaryCells.length; i++) {
            for (int j = 0; j < robotWay.size(); j++)
                if (robotWay.get(j).getX() == (x0 + necessaryCells[i].getX() * SQUARE_SIZE + SQUARE_SIZE / 2) && robotWay.get(j).getY() == (y0 + necessaryCells[i].getY() * SQUARE_SIZE + SQUARE_SIZE / 2))
                    k++;
        }
        return k >= necessaryCells.length;
    }

    public void setRobotCoords(Point robotCoords) {
        this.robotCoords = robotCoords;
        robotWay.add(new Point(robotCoords.getX() + SQUARE_SIZE / 2, robotCoords.getY() + SQUARE_SIZE / 2));
    }

    public ArrayList<Point> getRobotWay() {
        return robotWay;
    }
}

