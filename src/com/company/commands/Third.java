package com.company.commands;

import com.company.Point;
import com.company.Theory;


/**
 * Created by acer on 20.09.2016.
 */
public class Third extends Command {
    public Third(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void setParametersInButtonActionListener(Theory theory) {
        if (theory.getCommand() == CommandEnum.LINEAR_ALGORITHMS) {
            Point[] cells = new Point[]{new Point(2, 1), new Point(3, 1), new Point(3, 2), new Point(3, 3),
                    new Point(2, 3), new Point(1, 3), new Point(1, 2)};
            theory.makeWork(1, 1, cells, CommandEnum.LINEAR_ALGORITHMS, "Квадрат", 8);
        }
        if (theory.getCommand() == CommandEnum.IF_ALGORITHMS) {
            Point[] cells = new Point[]{new Point(0, 3), new Point(2, 3), new Point(2, 1), new Point(4, 1), new Point(4, 3)};
            theory.makeWork(0, 1, cells, CommandEnum.IF_ALGORITHMS, "Змейка", 10);
            Point[] barrier = new Point[]{new Point(0, 2), new Point(1, 3), new Point(2, 2), new Point(3, 1), new Point(4, 2)};
            theory.getTask().getField().setBarrierCells(barrier);
        }
        if (theory.getCommand() == CommandEnum.FOR_ALGORITHMS) {
            Point[] cells = new Point[]{new Point(2, 2), new Point(2, 3), new Point(2, 4), new Point(2, 5), new Point(3, 4),
                    new Point(4, 4), new Point(4, 5)};
            theory.makeWork(2, 1, cells, CommandEnum.FOR_ALGORITHMS, "Буква H", 6);
        }
        if (theory.getCommand() == CommandEnum.PROCEDURE_ALGORITHMS) {
            Point[] cells = new Point[]{new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 1),
                    new Point(2, 2), new Point(2, 3), new Point(3, 2), new Point(3, 3), new Point(3, 4), new Point(4, 3), new Point(4, 4),
                    new Point(4, 5)};
            theory.makeWork(0, 1, cells, CommandEnum.PROCEDURE_ALGORITHMS, "По кочкам с возвратом", 4);
            theory.getTask().setLineLimitInProcedure(4);
        }
        if (theory.getCommand() == CommandEnum.CONTROL_TASKS) {
            Point[] cells = new Point[]{new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0), new Point(5, 0),
                    new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4), new Point(5, 5), new Point(4, 5), new Point(3, 5),
                    new Point(2, 5), new Point(1, 5), new Point(0, 5), new Point(0, 4), new Point(0, 3), new Point(0, 2), new Point(0, 1)};
            theory.makeWork(0, 0, cells, CommandEnum.FOR_ALGORITHMS, "Обойти поле", 8);
        }
        theory.getFrame().repaint();
    }
}
