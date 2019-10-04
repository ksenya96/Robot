package com.company.commands;

import com.company.*;
import com.company.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 18.09.2016.
 */
public class Procedure extends Command {
    public Procedure(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public String getTextForProgramField() {
        return "Proc;";
    }



    @Override
    public JButton createButton(ImageIcon icon) {
        if (icon != null)
            return new JButton(icon);
        else
            return new JButton(getCommandEnum().getName());
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
        if (task.getNumberOfCommands() < task.getLineLimit()) {
            task.getProgramField().addText(getCommandEnum());
            task.getBlocks().addCommand(getCommandEnum());
            task.getCommands().add(getCommandEnum());
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            task.getFrame().repaint();
        }
    }
}
