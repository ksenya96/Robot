package com.company.commands;

import com.company.*;
import com.company.Point;

import java.awt.*;

/**
 * Created by acer on 18.09.2016.
 */
public class For extends Command {
    private int nFor;

    public For(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public String getTextForProgramField() {
        return "for i:=1 to " + nFor + " do";
    }

    public void setNFor(int nFor) {
        this.nFor = nFor;
    }


    @Override
    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {
        int x = p.getX();
        int y = p.getY();
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());

        g2.drawLine (x, y + Blocks.FIGURE_HEIGHT / 2, x + rectangleWidth / 4, y);
        g2.drawLine(x + rectangleWidth / 4, y, x + (int) (rectangleWidth * 0.75), y);
        g2.drawLine(x + (int) (rectangleWidth * 0.75), y, x + rectangleWidth, y + Blocks.FIGURE_HEIGHT / 2);
        g2.drawLine(x + rectangleWidth, y + Blocks.FIGURE_HEIGHT / 2, x + (int) (rectangleWidth * 0.75), y + Blocks.FIGURE_HEIGHT);
        g2.drawLine(x + (int) (rectangleWidth * 0.75), y + Blocks.FIGURE_HEIGHT, x + rectangleWidth / 4, y + Blocks.FIGURE_HEIGHT);
        g2.drawLine(x + rectangleWidth / 4, y + Blocks.FIGURE_HEIGHT, x, y + Blocks.FIGURE_HEIGHT / 2);
        g2.drawString(getCommandEnum().getName() + nFor + ",1", x + rectangleWidth / 2 - metrics.stringWidth(getCommandEnum().getName() + nFor + ",1") / 2, y + Blocks.FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);
    }


}
