
package com.company;


import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by acer on 27.09.2015.
 */
public class Task {
    private int fieldX = 30;
    private int fieldY;
    private final int FIELD_WIDTH = 6, FIELD_HEIGHT = 6;

    private int programFieldX = fieldX + Field.SQUARE_SIZE * 7 + 40 + 15;
    private int programFieldY;
    private int programFieldWidth;
    private int programFieldHeight = FIELD_HEIGHT * Field.SQUARE_SIZE;

    private int robotX = fieldX;
    private int robotY;
    private final int ROBOT_WIDTH = Field.SQUARE_SIZE, ROBOT_HEIGHT = Field.SQUARE_SIZE + 10;

    private Field field;
    private Robot robot;
    private ProgramField programField;
    private Blocks blocks;

    private int nFor;
    private CommandEnum signal;
    private int ms = 50;
    private int lineLimit;
    private int lineLimitInProcedure;
    private int numberOfCommands = 0;


    //список команд
    private ArrayList<CommandEnum> commands = new ArrayList<>();
    private ArrayList<CommandEnum> commandsInProcedure = new ArrayList<>();
    private JFrame frame;
    private JPanel panel;
    private int num = -1;
    private int numForProcedure = -1;

    public final Timer TIMER = new Timer(ms, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            Graphics g = panel.getGraphics();
            if (num < commands.size() - 1 && robot.getX() + ROBOT_WIDTH <= fieldX + Field.SQUARE_SIZE * FIELD_WIDTH
                    && robot.getY() + ROBOT_HEIGHT <= fieldY + Field.SQUARE_SIZE * FIELD_HEIGHT
                    && robot.getX() >= fieldX
                    && robot.getY() >= robotY) {

                if (signal != CommandEnum.PROCEDURE_ALGORITHMS) {
                    num++;
                    field.drawWay(commands.get(num));
                    robot.move(commands.get(num));
                } else {
                    if (numForProcedure < commandsInProcedure.size() - 1) {
                        numForProcedure++;
                        field.drawWay(commandsInProcedure.get(numForProcedure));
                        robot.move(commandsInProcedure.get(numForProcedure));
                    } else {
                        numForProcedure = -1;
                        num++;
                    }

                }

            } else if ((num < commands.size()) && (robot.getX() + ROBOT_WIDTH > fieldX + Field.SQUARE_SIZE * FIELD_WIDTH
                    || robot.getY() + ROBOT_HEIGHT > fieldY + Field.SQUARE_SIZE * FIELD_HEIGHT
                    || robot.getX() < fieldX
                    || robot.getY() < robotY)) {

                int pos = num;
                if (signal == CommandEnum.PROCEDURE_ALGORITHMS)
                    pos++;
                if (!(commands.get(pos) == CommandEnum.JUMP_LEFT) || commands.get(pos) == CommandEnum.JUMP_UP || commands.get(pos) == CommandEnum.JUMP_RIGHT || commands.get(pos) == CommandEnum.JUMP_DOWN
                        || commands.get(pos) == CommandEnum.PROCEDURE) {
                    pos = pos / (Field.SQUARE_SIZE / 5);

                }

                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                g.drawString("Ошибка! Выход за границы поля. Строка " + (pos + 2), programFieldX + 10, programFieldY + programFieldHeight + 15 + metrics.getHeight());
            } else if (field.isAllCellsCrossed()) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                g.drawString("Молодец!", programFieldX + 10, programFieldY + programFieldHeight + 15 + metrics.getHeight());

            }

        }
    });

    private String title;

    private ControlButton GoDown;
    private ControlButton GoRight;
    private ControlButton GoUp;
    private ControlButton GoLeft;
    private ControlButton If;
    private ControlButton Proc;
    private ControlButton JumpDown;
    private ControlButton JumpRight;
    private ControlButton JumpUp;
    private ControlButton JumpLeft;
    private ControlButton Start;
    private ControlButton Cancel;
    private ControlButton CancelProc;
    private ControlButton Return;
    private ControlButton Help;


    public Task(JFrame frame, JPanel panel, int robot_x, int robot_y, CommandEnum signal) {
        this.frame = frame;
        this.panel = panel;
        fieldY = frame.getHeight() / 2 - FIELD_HEIGHT * Field.SQUARE_SIZE / 2 - 10;
        programFieldWidth = frame.getWidth() * 22 / 100;
        programFieldY = fieldY;
        robotY = fieldY - 14;


        field = new Field(fieldX, fieldY, FIELD_WIDTH, FIELD_HEIGHT);
        robot = new Robot(robotX + robot_x * Field.SQUARE_SIZE, robotY + robot_y * Field.SQUARE_SIZE, ROBOT_WIDTH, ROBOT_HEIGHT, frame);
        field.setRobotCoords(new Point(fieldX + robot_x * Field.SQUARE_SIZE, fieldY + robot_y * Field.SQUARE_SIZE));
        blocks = new Blocks(programFieldX + programFieldWidth + 15, programFieldY, programFieldWidth, programFieldHeight);
        this.signal = signal;
        if (signal == CommandEnum.IF_ALGORITHMS)
            ms = 500;
        TIMER.setDelay(ms);
        makeButtons();
        programField = new ProgramField(programFieldX, programFieldY, programFieldWidth, programFieldHeight);
        if (signal == CommandEnum.PROCEDURE_ALGORITHMS)
            programField.setProcedure(true);
        else
            programField.setProcedure(false);
    }

    public void draw(Graphics g) {
        Font font = new Font("Sreda", Font.BOLD, 45);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(new Color(139, 9, 12));
        g.drawString(title, frame.getWidth() / 2 - metrics.stringWidth(title) / 2, 50);
        field.draw(g);
        robot.draw(g);
        blocks.draw(g);

        //окаймовка program_field
        g.setColor(new Color(139, 9, 12));
        g.fillRect(programFieldX - 5, programFieldY - 5, programFieldWidth + 10, programFieldHeight + 10);

        g.drawRect(programFieldX, programFieldY + programFieldHeight + 15, programFieldWidth * 2 + 15, 50);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    //показать все формы на панели
    public void showForms() {
        if (signal == CommandEnum.LINEAR_ALGORITHMS || signal == CommandEnum.FOR_ALGORITHMS || signal == CommandEnum.PROCEDURE_ALGORITHMS) {
            GoDown.show(panel);
            GoRight.show(panel);
            GoUp.show(panel);
            GoLeft.show(panel);
        }
        if (signal == CommandEnum.IF_ALGORITHMS) {
            If.show(panel);
            JumpDown.show(panel);
            JumpRight.show(panel);
            JumpUp.show(panel);
            JumpLeft.show(panel);
        }
        if (signal == CommandEnum.PROCEDURE_ALGORITHMS) {
            Proc.show(panel);
            CancelProc.show(panel);
        }
        Start.show(panel);
        Cancel.show(panel);
        Return.show(panel);
        Help.show(panel);
        programField.addOnPanel(panel);
    }

    //сделать кнопки
    private void makeButtons() {

        int y = fieldY;
        int w = Field.SQUARE_SIZE, h = w;
        int h_y = 20;
        int x = fieldX + Field.SQUARE_SIZE * FIELD_WIDTH + h_y + 15;
        ControlButtonActionListener controlButtonActionListener = new ControlButtonActionListener();

        //кнопки "Идти" и "Прыгнуть" создаются в зависимости от надобности
        //кнопка "Идти вниз"
        if (signal != CommandEnum.IF_ALGORITHMS) {
            GoDown = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/GoDown.png"), CommandEnum.GO_DOWN);
            GoDown.getButton().addActionListener(controlButtonActionListener);
        } else {
            JumpDown = new ControlButton(x, y, w, h, null, CommandEnum.JUMP_DOWN);
            JumpDown.getButton().addActionListener(controlButtonActionListener);
            JumpDown.getButton().setEnabled(false);
        }

        //кнопка "Идти вправо"
        y = y + h + h_y;
        if (signal != CommandEnum.IF_ALGORITHMS) {
            GoRight = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/GoRight.png"), CommandEnum.GO_RIGHT);
            GoRight.getButton().addActionListener(controlButtonActionListener);
        } else {
            JumpRight = new ControlButton(x, y, w, h, null, CommandEnum.JUMP_RIGHT);
            JumpRight.getButton().addActionListener(controlButtonActionListener);
            JumpRight.getButton().setEnabled(false);
        }

        y = y + h + h_y;
        if (signal != CommandEnum.IF_ALGORITHMS) {
            GoUp = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/GoUp.png"), CommandEnum.GO_UP);
            GoUp.getButton().addActionListener(controlButtonActionListener);
        } else {
            JumpUp = new ControlButton(x, y, w, h, null, CommandEnum.JUMP_UP);
            JumpUp.getButton().addActionListener(controlButtonActionListener);
            JumpUp.getButton().setEnabled(false);
        }

        y = y + h + h_y;
        if (signal != CommandEnum.IF_ALGORITHMS) {
            GoLeft = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/GoLeft.png"), CommandEnum.GO_LEFT);
            GoLeft.getButton().addActionListener(controlButtonActionListener);
        } else {
            JumpLeft = new ControlButton(x, y, w, h, null, CommandEnum.JUMP_LEFT);
            JumpLeft.getButton().addActionListener(controlButtonActionListener);
            JumpLeft.getButton().setEnabled(false);
        }

        if (signal == CommandEnum.IF_ALGORITHMS) {
            y = y + h + h_y;
            If = new ControlButton(x, y, w, h, null, CommandEnum.IF);
            If.getButton().addActionListener(controlButtonActionListener);
        }

        if (signal == CommandEnum.FOR_ALGORITHMS) {
            y = y + h + h_y;
            JComboBox<String> cmb = new JComboBox<String>(new String[]{" n:= ", "2", "3", "4", "5"});
            cmb.addActionListener(new CMBActionListener());
            cmb.setBounds(x, y + h / 2 - h / 6, w, h / 3);
            panel.add(cmb);
        }

        if (signal == CommandEnum.PROCEDURE_ALGORITHMS) {
            y = y + h + h_y;
            Proc = new ControlButton(x, y, w, h, null, CommandEnum.PROCEDURE);
            Proc.getButton().addActionListener(controlButtonActionListener);
        }

        w = w + h_y;
        h = 50;
        x = fieldX;
        y = fieldY + FIELD_HEIGHT * Field.SQUARE_SIZE + h_y + 15;
        Start = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/start.png"), CommandEnum.START);
        Start.getButton().addActionListener(controlButtonActionListener);

        x = x + w + 20;
        Cancel = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/cancel.png"), CommandEnum.CANCEL);
        Cancel.getButton().addActionListener(controlButtonActionListener);

        if (signal == CommandEnum.PROCEDURE_ALGORITHMS) {
            x = x + w + 20;
            CancelProc = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/cancel_proc.png"), CommandEnum.CANCEL_IN_PROCEDURE);
            CancelProc.getButton().addActionListener(controlButtonActionListener);
        }

        x = x + w + 20;
        Return = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/return.png"), CommandEnum.RETURN);
        Return.getButton().addActionListener(controlButtonActionListener);

        x = x + w + 20;
        Help = new ControlButton(x, y, w, h, Toolkit.getDefaultToolkit().getImage("src/com/company/help.png"), CommandEnum.HELP);
        Help.getButton().addActionListener(controlButtonActionListener);
    }

    public int getLineLimit() {
        return lineLimit;
    }

    public CommandEnum getSignal() {
        return signal;
    }

    public int getLineLimitInProcedure() {
        return lineLimitInProcedure;
    }

    public ProgramField getProgramField() {
        return programField;
    }

    public ArrayList<CommandEnum> getCommandsInProcedure() {
        return commandsInProcedure;
    }

    public int getNumberOfCommands() {
        return numberOfCommands;
    }

    public void setNumberOfCommands(int numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    public ArrayList<CommandEnum> getCommands() {
        return commands;
    }

    public int getNFor() {
        return nFor;
    }

    public Blocks getBlocks() {
        return blocks;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ControlButton getIf() {
        return If;
    }


    public ControlButton getJumpDown() {
        return JumpDown;
    }

    public ControlButton getJumpRight() {
        return JumpRight;
    }

    public ControlButton getJumpUp() {
        return JumpUp;
    }

    public ControlButton getJumpLeft() {
        return JumpLeft;
    }

    public ControlButton getReturn() {
        return Return;
    }

    //слушатель кнопок
    public class ControlButtonActionListener implements ActionListener {
        private int numberOfCommandsInProcedure = 0;

        public int getNumberOfCommandsInProcedure() {
            return numberOfCommandsInProcedure;
        }

        public void setNumberOfCommandsInProcedure(int numberOfCommandsInProcedure) {
            this.numberOfCommandsInProcedure = numberOfCommandsInProcedure;
        }

        public void actionPerformed(ActionEvent ae) {
            CommandEnum command = CommandEnum.valueOf(ae.getActionCommand());
            TIMER.stop();

            CommandFactory factory = new CommandFactory(command);
            factory.setParametersInControlButtonActionListener(Task.this, this);
        }
    }

    public Field getField() {
        return field;
    }

    private class CMBActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            JComboBox box = (JComboBox) ae.getSource();
            String item = (String) box.getSelectedItem();
            try {
                if (numberOfCommands < lineLimit) {
                    nFor = Integer.parseInt(item);
                    programField.setNFor(nFor);
                    blocks.setNFor(nFor);

                    programField.addText(CommandEnum.FOR);
                    commands.add(CommandEnum.FOR);
                    blocks.addCommand(CommandEnum.FOR);
                    numberOfCommands++;
                    frame.repaint();
                }
            } catch (Exception e) {
            }
        }
    }

    public void setLineLimit(int lineLimit) {
        this.lineLimit = lineLimit;
    }

    public void setLineLimitInProcedure(int lineLimitInProcedure) {
        this.lineLimitInProcedure = lineLimitInProcedure;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNumForProcedure(int numForProcedure) {
        this.numForProcedure = numForProcedure;
    }
}

