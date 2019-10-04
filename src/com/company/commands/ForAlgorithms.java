package com.company.commands;


import com.company.Theory;

import java.awt.*;

/**
 * Created by acer on 19.09.2016.
 */
public class ForAlgorithms extends Command {
    public ForAlgorithms(CommandEnum commandEnum) {
        super(commandEnum);
    }



    @Override
    public void drawTheoryOrTask(Graphics g, Theory theory) {
        if (theory.isWork()) {
            theory.getTask().draw(g);
            theory.getBack().addOnPanel();
        }
        else {
            theory.makeTitle(g, "����������� ���������", "����������� �������� - ��� ��������, � ������� �������� �����������&������������ ���������� ���", Toolkit.getDefaultToolkit().getImage("src/com/company/for_block.png"),
                    Toolkit.getDefaultToolkit().getImage("src/com/company/for_prog.png"), Toolkit.getDefaultToolkit().getImage("src/com/company/for.jpg"),
                    "�� ������� �����, ��� �������� ����� ����������� n ���, �.�. ���� ������� i <= n �������.");
        }
    }

    @Override
    public Image getImageForHelpWindow() {
        return Toolkit.getDefaultToolkit().getImage("src/com/company/help_window3.png");
    }
}
