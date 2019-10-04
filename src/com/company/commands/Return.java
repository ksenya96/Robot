package com.company.commands;

import com.company.ControlButton;
import com.company.Point;
import com.company.Task;

import javax.swing.*;


/**
 * Created by acer on 18.09.2016.
 */
public class Return extends Command {
    public Return(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public Point move(Point p) {
        return null;
    }

    @Override
    public JButton createButton(ImageIcon icon) {
        if (icon == null)
            return new ControlButton.RoundButton(getCommandEnum().getName());
        else
            return new ControlButton.RoundButton(icon);
    }

    @Override
    public void setParametersInControlButtonActionListener(Task task, Task.ControlButtonActionListener actionListener) {
        task.getField().clearWay();
        task.getRobot().setInitialCoords();
        task.setNum(-1);
        task.setNumForProcedure(-1);
    }
}
