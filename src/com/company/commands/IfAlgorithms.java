package com.company.commands;


import com.company.Theory;

import java.awt.*;

/**
 * Created by acer on 19.09.2016.
 */
public class IfAlgorithms extends Command {
    public IfAlgorithms(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void drawTheoryOrTask(Graphics g, Theory theory) {
        if (theory.isWork()) {
            theory.getTask().draw(g);
            theory.getBack().addOnPanel();
        }
        else {
            theory.makeTitle(g, "Алгоритмы с ветвлением", "Алгоритм с ветвлением - это алгоритм, в котором в зависимости&от условия выполняется то, либо иное действие", Toolkit.getDefaultToolkit().getImage("src/com/company/if_block.png"),
                    Toolkit.getDefaultToolkit().getImage("src/com/company/if_prog.png"), Toolkit.getDefaultToolkit().getImage("src/com/company/if.jpg"),
                    "Если условие ветвления истинно, то выполняется Действие 1, в противном случае выполняется Действие 2");
        }
    }

    @Override
    public Image getImageForHelpWindow() {
        return Toolkit.getDefaultToolkit().getImage("src/com/company/help_window2.png");
    }

}
