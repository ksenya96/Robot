package com.company.commands;

import com.company.*;
import com.company.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by acer on 18.09.2016.
 */
public class JumpDown extends Command {
    public JumpDown(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public String getTextForProgramField() {
        return "  " + getCommandEnum().getName() + ";";
    }

    @Override
    public void drawWay(Field field) {
        ArrayList<Point> robotWay = field.getRobotWay();
        robotWay.add(new Point(robotWay.get(robotWay.size() - 1).getX(), robotWay.get(robotWay.size() - 1).getY() + Field.SQUARE_SIZE * 2));
    }

    @Override
    public Point move(Point p) {
        p.setY(p.getY() + Field.SQUARE_SIZE * 2);
        return p;
    }

    @Override
    public JButton createButton(ImageIcon icon) {
        return new JButton("<html>Jump<br>Down</html>");
    }

    @Override
    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {
        int x = p.getX();
        int y = p.getY();
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());

        g2.drawRect(x - 20 - rectangleWidth / 3, y, (int)(rectangleWidth / 1.5), Blocks.FIGURE_HEIGHT);
        g2.drawString(getCommandEnum().getName(),
                x - 20 - rectangleWidth / 3 + rectangleWidth / 3 - metrics.stringWidth(getCommandEnum().getName()) / 2,
                y + Blocks.FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);
        g2.drawLine (x - 20, y + Blocks.FIGURE_HEIGHT, x - 20, y + Blocks.FIGURE_HEIGHT + 5);
        g2.drawLine(x - 20, y + Blocks.FIGURE_HEIGHT + 5, x + rectangleWidth + 20, y + Blocks.FIGURE_HEIGHT + 5);
        g2.drawLine(x + rectangleWidth + 20, y + Blocks.FIGURE_HEIGHT + 5, x + rectangleWidth + 20, y - 10);
    }

    @Override
    public void setParametersInControlButtonActionListener(Task task, Task.ControlButtonActionListener actionListener) {
        if (task.getNumberOfCommands() < task.getLineLimit()) {
            task.getProgramField().addText(getCommandEnum());
            task.getBlocks().addCommand(getCommandEnum());
            task.getCommands().add(getCommandEnum());
            task.getJumpDown().getButton().setEnabled(false);
            task.getJumpRight().getButton().setEnabled(false);
            task.getJumpUp().getButton().setEnabled(false);
            task.getJumpLeft().getButton().setEnabled(false);
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            task.getFrame().repaint();
        }
    }
}
