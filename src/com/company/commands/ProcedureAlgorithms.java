package com.company.commands;


import com.company.Theory;

import java.awt.*;

/**
 * Created by acer on 19.09.2016.
 */
public class ProcedureAlgorithms extends Command {
    public ProcedureAlgorithms(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void drawTheoryOrTask(Graphics g, Theory theory) {
        if (theory.isWork()) {
            theory.getTask().draw(g);
            theory.getBack().addOnPanel();
        }
        else {
            theory.makeTitle(g, CommandEnum.PROCEDURE_ALGORITHMS.getName(), "При решении некоторых задач бывает удобно разбить их на подзадачи,&каждую из которых можно оформить как самостоятельный алгоритм.", Toolkit.getDefaultToolkit().getImage("src/com/company/proc_block.png"),
                    Toolkit.getDefaultToolkit().getImage("src/com/company/proc_prog.png"), Toolkit.getDefaultToolkit().getImage("src/com/company/linear.jpg"),
                    "Каждый такой алгоритм называется подпрограммой (процедурой). Процедуру в основной программе можно вызывать неоднократно");
        }
    }

    @Override
    public Image getImageForHelpWindow() {
        return Toolkit.getDefaultToolkit().getImage("src/com/company/help_window4.png");
    }
}
