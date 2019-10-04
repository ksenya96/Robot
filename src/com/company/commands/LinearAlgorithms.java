package com.company.commands;


import com.company.Theory;

import java.awt.*;

/**
 * Created by acer on 19.09.2016.
 */
public class LinearAlgorithms extends Command {
    public LinearAlgorithms(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void drawTheoryOrTask(Graphics g, Theory theory) {
        if (theory.isWork()) {
            theory.getTask().draw(g);
            theory.getBack().addOnPanel();
        }
        else {
            theory.makeTitle(g, "Ћинейные алгоритмы", "Ћинейный алгоритм - это алгоритм, в котором действи€ выполн€ютс€ строго последовательно", Toolkit.getDefaultToolkit().getImage("src/com/company/linear_block.png"),
                    Toolkit.getDefaultToolkit().getImage("src/com/company/linear_prog.png"), Toolkit.getDefaultToolkit().getImage("src/com/company/linear.jpg"), "");
        }
    }

    @Override
    public Image getImageForHelpWindow() {
        return Toolkit.getDefaultToolkit().getImage("src/com/company/help_window1.png");
    }
}
