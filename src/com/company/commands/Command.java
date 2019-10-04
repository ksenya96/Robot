package com.company.commands;

import com.company.Field;
import com.company.Point;
import com.company.Task;
import com.company.Theory;

import javax.swing.*;
import java.awt.*;


/**
 * Created by acer on 18.09.2016.
 */
public abstract class Command {
    private CommandEnum commandEnum;

    public Command(CommandEnum commandEnum) {
        this.commandEnum = commandEnum;
    }

    public String getTextForProgramField() {
        return null;
    }
    public void drawWay(Field field) {}
    public Point move(Point p) {
        return null;
    }
    public JButton createButton(ImageIcon icon) {
        return null;
    }
    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {}
    public void drawTheoryOrTask(Graphics g, Theory theory) {}
    public void setParametersInButtonActionListener(Theory theory) {
        if (theory.getTask() != null)
            theory.getTask().TIMER.stop();
        theory.setTheory(false);
        theory.setCommand(commandEnum);
        theory.setWork(false);
        theory.getFrame().repaint();
    }

    public CommandEnum getCommandEnum() {
        return commandEnum;
    }

    public void setParametersInControlButtonActionListener (Task task, Task.ControlButtonActionListener actionListener) {}

    public Image getImageForHelpWindow() {return null;}
}
