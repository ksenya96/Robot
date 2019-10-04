package com.company.commands;

import com.company.ControlButton;
import com.company.Task;


import javax.swing.*;


/**
 * Created by acer on 18.09.2016.
 */
public class Start extends Command {
    public Start(CommandEnum commandEnum) {
        super(commandEnum);
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
        task.TIMER.start();
    }
}
