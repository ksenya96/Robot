package com.company.commands;

import com.company.*;


import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by acer on 18.09.2016.
 */
public class Cancel extends Command {

    public Cancel(CommandEnum commandEnum) {
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
        ArrayList<CommandEnum> commands = task.getCommands();
        ProgramField programField = task.getProgramField();
        Blocks blocks = task.getBlocks();
        if (commands.size() > 0) {
            //если это было ветвление, то удаляем вместе с ветвлением
            if (commands.get(commands.size() - 1) == CommandEnum.JUMP_LEFT || commands.get(commands.size() - 1) == CommandEnum.JUMP_UP ||
                    commands.get(commands.size() - 1) == CommandEnum.JUMP_RIGHT || commands.get(commands.size() - 1) == CommandEnum.JUMP_DOWN
                    ) {
                commands.remove(commands.size() - 1);
                commands.remove(commands.size() - 1);
                programField.removeString(); //удаляем действие и ветвление
                programField.removeString();
                blocks.removeCommand();
                blocks.removeCommand();
                if (task.getNumberOfCommands() - 2 >= 0)
                    task.setNumberOfCommands(task.getNumberOfCommands() - 2);
            }
            //если был оператор
            else if (commands.get(commands.size() - 1) == CommandEnum.IF || commands.get(commands.size() - 1) == CommandEnum.FOR
                    || commands.get(commands.size() - 1) == CommandEnum.PROCEDURE) {
                if (commands.get(commands.size() - 1) == CommandEnum.IF) {
                    task.getJumpDown().getButton().setEnabled(false);
                    task.getJumpRight().getButton().setEnabled(false);
                    task.getJumpUp().getButton().setEnabled(false);
                    task.getJumpLeft().getButton().setEnabled(false);
                }
                commands.remove(commands.size() - 1);
                programField.removeString();
                blocks.removeCommand();
                if (task.getNumberOfCommands() > 0)
                    task.setNumberOfCommands(task.getNumberOfCommands() - 1);
            } else {
                //циклический оператор
                if (blocks.getCommands().size() > 1 && blocks.getCommands().get(blocks.getCommands().size() - 2) == CommandEnum.FOR) {
                    while (commands.get(commands.size() - 1) != CommandEnum.FOR)
                        commands.remove(commands.size() - 1);
                    commands.remove(commands.size() - 1);
                    programField.removeString();
                    blocks.removeCommand();
                    programField.removeString();
                    blocks.removeCommand();
                    if (task.getNumberOfCommands() - 2 >= 0)
                        task.setNumberOfCommands(task.getNumberOfCommands() - 2);
                } else {
                    for (int i = 0; i < Field.SQUARE_SIZE / 5; i++) {
                        commands.remove(commands.size() - 1);

                    }
                    programField.removeString();
                    blocks.removeCommand();
                    if (task.getNumberOfCommands() > 0)
                        task.setNumberOfCommands(task.getNumberOfCommands() - 1);
                }
            }

            task.getFrame().repaint();
        }
    }
}
