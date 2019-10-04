package com.company.commands;

import com.company.Theory;

import java.awt.*;

/**
 * Created by acer on 19.09.2016.
 */
public class ControlTasks extends Command {
    public ControlTasks(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void drawTheoryOrTask(Graphics g, Theory theory) {
        if (theory.isWork()) {
            theory.getTask().draw(g);
            theory.getBack().addOnPanel();
        }
        else {
            theory.makeTitle(g, "Контрольные задания", "В этом разделе приведены контрольные задания по всему курсу алгоритмизации",
                    Toolkit.getDefaultToolkit().getImage("src/com/company/control_table.png"), null, null, "");
        }
    }

    @Override
    public Image getImageForHelpWindow() {
        return Toolkit.getDefaultToolkit().getImage("src/com/company/help_window1.png");
    }
}
