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
public class CommandFactory {
    private Command command;

    public CommandFactory(CommandEnum commandEnum) {
        switch (commandEnum) {
            case FOR:
                command = new For(commandEnum);
                break;
            case GO_DOWN:
                command = new GoDown(commandEnum);
                break;
            case GO_LEFT:
                command = new GoLeft(commandEnum);
                break;
            case GO_RIGHT:
                command = new GoRight(commandEnum);
                break;
            case GO_UP:
                command = new GoUp(commandEnum);
                break;
            case IF:
                command = new If(commandEnum);
                break;
            case JUMP_DOWN:
                command = new JumpDown(commandEnum);
                break;
            case JUMP_LEFT:
                command = new JumpLeft(commandEnum);
                break;
            case JUMP_RIGHT:
                command = new JumpRight(commandEnum);
                break;
            case JUMP_UP:
                command = new JumpUp(commandEnum);
                break;
            case CANCEL:
                command = new Cancel(commandEnum);
                break;
            case CANCEL_IN_PROCEDURE:
                command = new CancelInProcedure(commandEnum);
                break;
            case HELP:
                command = new Help(commandEnum);
                break;
            case PROCEDURE:
                command = new Procedure(commandEnum);
                break;
            case RETURN:
                command = new Return(commandEnum);
                break;
            case START:
                command = new Start(commandEnum);
                break;
            case LINEAR_ALGORITHMS:
                command = new LinearAlgorithms(commandEnum);
                break;
            case IF_ALGORITHMS:
                command = new IfAlgorithms(commandEnum);
                break;
            case FOR_ALGORITHMS:
                command = new ForAlgorithms(commandEnum);
                break;
            case PROCEDURE_ALGORITHMS:
                command = new ProcedureAlgorithms(commandEnum);
                break;
            case CONTROL_TASKS:
                command = new ControlTasks(commandEnum);
                break;
            case BACK:
                command = new Back(commandEnum);
                break;
            case FIRST:
                command = new First(commandEnum);
                break;
            case SECOND:
                command = new Second(commandEnum);
                break;
            case THIRD:
                command = new Third(commandEnum);
                break;
            case FORTH:
                command = new Forth(commandEnum);
                break;
        }
    }

    public String getTextForProgramField() {
        return command.getTextForProgramField();
    }

    public void drawWay(Field field) {
        command.drawWay(field);
    }

    public Point move(Point p) {
        return command.move(p);
    }

    public JButton createButton(ImageIcon icon){
        return command.createButton(icon);
    }

    public void drawBlock(Graphics2D g2, Point p, int rectangleWidth) {
        command.drawBlock(g2, p, rectangleWidth);
    }

    public Command getCommand() {
        return command;
    }

    public void setParametersInButtonActionListener(Theory theory) {
        command.setParametersInButtonActionListener(theory);
    }

    public void drawTheoryOrTask(Graphics g, Theory theory) {
        command.drawTheoryOrTask(g, theory);
    }

    public void setParametersInControlButtonActionListener(Task task, Task.ControlButtonActionListener actionListener) {
        command.setParametersInControlButtonActionListener(task, actionListener);
    }

    public Image getImageForHelpWindow() {
        return command.getImageForHelpWindow();
    }
}
