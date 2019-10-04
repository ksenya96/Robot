package com.company.commands;


import com.company.Point;
import com.company.Theory;



/**
 * Created by acer on 20.09.2016.
 */
public class Second extends Command {
    public Second(CommandEnum commandEnum) {
        super(commandEnum);
    }


    @Override
    public void setParametersInButtonActionListener(Theory theory) {
        if (theory.getCommand() == CommandEnum.LINEAR_ALGORITHMS) {
            Point[] cells = new Point[] {new Point(3, 2), new Point(3, 4), new Point(2, 3), new Point(4, 3)};
            theory.makeWork(3, 3, cells, CommandEnum.LINEAR_ALGORITHMS, "Крестик", 7);

        }
        if (theory.getCommand() == CommandEnum.IF_ALGORITHMS) {
            Point[] cells = new Point[] {new Point(2, 1), new Point(4, 3), new Point(0, 3)};
            theory.makeWork(2, 3, cells, CommandEnum.IF_ALGORITHMS, "Вправо, влево, вверх", 10);
            Point[] barrier = new Point[] {new Point(2, 2), new Point(3, 3), new Point(1, 3)};
            theory.getTask().getField().setBarrierCells(barrier);
        }
        if (theory.getCommand() == CommandEnum.FOR_ALGORITHMS) {
            Point[] cells = new Point[] {new Point(2, 1), new Point(3, 1), new Point(4, 1), new Point(4, 2),
            new Point(4, 3), new Point(3, 3), new Point(2, 3), new Point(1, 3)};
            theory.makeWork(1, 1, cells, CommandEnum.FOR_ALGORITHMS, "Подкова", 6);
        }
        if (theory.getCommand() == CommandEnum.PROCEDURE_ALGORITHMS) {
            Point[] cells = new Point[] {new Point(2, 0), new Point(3, 0), new Point(3, 1), new Point(3, 2),
            new Point(2, 2), new Point(1, 2), new Point(3, 3), new Point(3, 4), new Point(2, 4), new Point(1, 4)};
            theory.makeWork(1, 0, cells, CommandEnum.PROCEDURE_ALGORITHMS, "Цифра 3", 2);
            theory.getTask().setLineLimitInProcedure(6);
        }
        if (theory.getCommand() == CommandEnum.CONTROL_TASKS) {
            Point[] cells = new Point[] {new Point(2, 0), new Point(2, 2), new Point(4, 2), new Point(4, 4)};
            theory.makeWork(0, 0, cells, CommandEnum.IF_ALGORITHMS, "Сепрантин с if", 8);
            Point[] barrier = new Point[] {new Point(1, 0), new Point(2, 1), new Point(3, 2), new Point(4, 3)};
            theory.getTask().getField().setBarrierCells(barrier);
        }
        theory.getFrame().repaint();
    }
}
