package com.company.commands;

import com.company.ControlButton;
import com.company.Field;
import com.company.Task;


import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by acer on 18.09.2016.
 */
public class CancelInProcedure extends Command {
    public CancelInProcedure(CommandEnum commandEnum) {
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
        ArrayList<CommandEnum> commandsInProcedure = task.getCommandsInProcedure();
        if (commandsInProcedure.size() > 0) {
            for (int i = 0; i < Field.SQUARE_SIZE / 5; i++) {
                commandsInProcedure.remove(commandsInProcedure.size() - 1);
            }
            task.getProgramField().removeStringFromProcedure();
            if (actionListener.getNumberOfCommandsInProcedure() > 0)
                actionListener.setNumberOfCommandsInProcedure(actionListener.getNumberOfCommandsInProcedure() - 1);
        }
    }
}
