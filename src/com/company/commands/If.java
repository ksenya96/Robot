package com.company.commands;

import com.company.*;
import com.company.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 18.09.2016.
 */
public class If extends Command {
    public If(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public String getTextForProgramField() {
        return "if is_wall then";
    }


    @Override
    public JButton createButton(ImageIcon icon) {
        return new JButton("<html>is<br>wall</html>");
    }

    @Override
    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {
        int x = p.getX();
        int y = p.getY();
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());

        g2.drawLine(x, y + Blocks.FIGURE_HEIGHT / 2, x + rectangleWidth / 2, y);
        g2.drawLine(x + rectangleWidth / 2, y, x + rectangleWidth, y + Blocks.FIGURE_HEIGHT / 2);
        g2.drawLine(x + rectangleWidth, y + Blocks.FIGURE_HEIGHT / 2, x + rectangleWidth / 2, y + Blocks.FIGURE_HEIGHT);
        g2.drawLine(x + rectangleWidth / 2, y + Blocks.FIGURE_HEIGHT, x, y + Blocks.FIGURE_HEIGHT / 2);

        //стрелочка
        g2.drawLine(x, y + Blocks.FIGURE_HEIGHT / 2, x - 20, y + Blocks.FIGURE_HEIGHT / 2);
        g2.drawLine(x - 20, y + Blocks.FIGURE_HEIGHT / 2, x - 20, y + Blocks.FIGURE_HEIGHT / 2 + 25);
        g2.drawLine(x - 20,
                y + Blocks.FIGURE_HEIGHT / 2 + 25,
                (int)(x - 20 - Blocks.SMALL_ARROW_LENGTH / Math.sin(Blocks.ALPHA)),
                (int)(y + Blocks.FIGURE_HEIGHT / 2 + 25 - Blocks.SMALL_ARROW_LENGTH / Math.cos(Blocks.ALPHA)));
        g2.drawLine(x - 20,
                y + Blocks.FIGURE_HEIGHT / 2 + 25,
                (int)(x - 20 + Blocks.SMALL_ARROW_LENGTH / Math.sin(Blocks.ALPHA)),
                (int)(y + Blocks.FIGURE_HEIGHT / 2 + 25 - Blocks.SMALL_ARROW_LENGTH / Math.cos(Blocks.ALPHA)));

        g2.drawLine(x + rectangleWidth, y + Blocks.FIGURE_HEIGHT / 2, x + rectangleWidth + 20, y + Blocks.FIGURE_HEIGHT / 2);
        g2.drawLine(x + rectangleWidth + 20, y + Blocks.FIGURE_HEIGHT / 2, x + rectangleWidth + 20, y + Blocks.FIGURE_HEIGHT / 2 + 25);
        g2.drawString(getCommandEnum().getName(),
                x + rectangleWidth / 2 - metrics.stringWidth(getCommandEnum().getName()) / 2,
                y + Blocks.FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);
    }

    @Override
    public void setParametersInControlButtonActionListener(Task task, Task.ControlButtonActionListener actionListener) {
        if (task.getNumberOfCommands() < task.getLineLimit()) {
            task.getProgramField().addText(getCommandEnum());
            task.getCommands().add(getCommandEnum());
            task.getBlocks().addCommand(getCommandEnum());
            task.getJumpDown().getButton().setEnabled(true);
            task.getJumpRight().getButton().setEnabled(true);
            task.getJumpUp().getButton().setEnabled(true);
            task.getJumpLeft().getButton().setEnabled(true);
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            task.getFrame().repaint();
        }
    }

}
