package com.company.commands;

import com.company.ControlButton;
import com.company.Task;


import javax.swing.*;
import java.awt.*;


/**
 * Created by acer on 18.09.2016.
 */
public class Help extends Command{
    public Help(CommandEnum commandEnum) {
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
        final JFrame fr = new JFrame("Помощь");
        fr.setSize(1000, 700);
        fr.setResizable(false);

        JPanel pan = new JPanel() {
            public void paintComponent (Graphics g) {
                super.paintComponent(g);
                CommandFactory factory = new CommandFactory(task.getSignal());
                Image background = factory.getImageForHelpWindow();
                g.drawImage(background, 0, 0, this);

            }
        };
        fr.add(pan);
        fr.setVisible(true);
    }
}
