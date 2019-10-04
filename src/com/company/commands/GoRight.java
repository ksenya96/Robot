package com.company.commands;

import com.company.*;
import com.company.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by acer on 18.09.2016.
 */
public class GoRight extends Command {
    public GoRight(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public String getTextForProgramField() {
        return getCommandEnum().getName() + ";";
    }

    @Override
    public void drawWay(Field field) {
        ArrayList<Point> robotWay = field.getRobotWay();
        robotWay.add(new Point(robotWay.get(robotWay.size() - 1).getX() + 5, robotWay.get(robotWay.size() - 1).getY()));
    }

    @Override
    public Point move(Point p) {
        p.setX(p.getX() + 5);
        return p;
    }

    @Override
    public JButton createButton(ImageIcon icon) {
        return new JButton(icon);
    }

    @Override
    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {
        int x = p.getX();
        int y = p.getY();
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());

        g2.drawRect(x, y, rectangleWidth, Blocks.FIGURE_HEIGHT);
        g2.drawString(getCommandEnum().getName(),
                x + rectangleWidth / 2 - metrics.stringWidth(getCommandEnum().getName()) / 2,
                y + Blocks.FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);
    }

    @Override
    public void setParametersInControlButtonActionListener(Task task, Task.ControlButtonActionListener actionListener) {
        if (task.getSignal() == CommandEnum.PROCEDURE_ALGORITHMS) {
            if (actionListener.getNumberOfCommandsInProcedure() < task.getLineLimitInProcedure()) {
                task.getProgramField().addTextToProcedure(getCommandEnum());
                for (int i = 0; i < Field.SQUARE_SIZE / 5; i++)
                    task.getCommandsInProcedure().add(getCommandEnum());
                actionListener.setNumberOfCommandsInProcedure(actionListener.getNumberOfCommandsInProcedure() + 1);
            }
        }
        else {
            if (task.getNumberOfCommands() < task.getLineLimit()) {
                ArrayList<CommandEnum> commands = task.getCommands();
                if (commands.size() > 0 && commands.get(commands.size() - 1) == CommandEnum.FOR) {
                    task.getProgramField().setForBlock(true);
                    task.getProgramField().addText(getCommandEnum()); // "  "
                }
                else
                    task.getProgramField().addText(getCommandEnum());
                if (commands.size() > 0 && commands.get(commands.size() - 1)== CommandEnum.FOR)
                    for (int j = 0; j < task.getNFor(); j++)
                        for (int i = 0; i < Field.SQUARE_SIZE / 5; i++)
                            commands.add(getCommandEnum());
                else
                    for (int i = 0; i < Field.SQUARE_SIZE / 5; i++)
                        commands.add(getCommandEnum());
                task.getBlocks().addCommand(getCommandEnum());
                task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            }
        }
        task.getFrame().repaint();
    }
}
