package com.company.commands;

import com.company.Theory;


/**
 * Created by acer on 20.09.2016.
 */
public class Back extends Command {
    public Back(CommandEnum commandEnum) {
        super(commandEnum);
    }

    @Override
    public void setParametersInButtonActionListener(Theory theory) {
        if (theory.getTask() != null)
            theory.getTask().TIMER.stop();
        if (theory.isWork())
            theory.setWork(false);
        else {
            theory.setTheory(true);
            theory.setCommand(null);
        }
        theory.getFrame().repaint();
    }
}
