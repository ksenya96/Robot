package com.company;

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by acer on 11.10.2015.
 */
public class Theory {
    private JFrame frame;
    private JPanel panel;
    private CommandEnum command;
    private boolean isTheory = true;
    private boolean isWork = false;
    private Button linearAlg;  //кнопка "Линейные алгоритмы"
    private Button ifAlg;      //кнопка "Условные алгоритмы"
    private Button forAlg;     //кнопка "Циклические алгоритмы"
    private Button procAlg;    //кнопка "Подпрограммы"
    private Button controlAlg;  //кнопка "Контрольные задания"
    private Button back;         //кнопка "Назад"
    private Button first;        //кнопка первого задания
    private Button second;       //кнопка второго задания
    private Button third;        //кнопка третьего задания
    private Button forth;        //кнопка четвертого задания
    private Task task;
    private int xOnTitle;
    private int yOnTitle;

    public Theory(JFrame frame) {
        this.frame = frame;
        makeButtons();

    }

    public Button getBack() {
        return back;
    }

    public Task getTask() {
        return task;
    }

    public boolean isWork() {
        return isWork;
    }

    public void draw(Graphics g) {
        int robot_x = 90;
        int title_y = 70;  //Y заголовка
        Image robot = Toolkit.getDefaultToolkit().getImage("src/com/company/robot.png");
        Image phrase;
        String s;
        //главная страница
        if (isTheory) {
            panel.removeAll();
            Font font = new Font("Sreda", Font.BOLD, 45);
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(font);
            s = "Алгоритмический игровой исполнитель";
            g.setColor(new Color(139, 9, 12)/*new Color(85, 26, 139)*/);
            g.drawString(s, frame.getWidth() / 2 - metrics.stringWidth(s) / 2, title_y);
            s = (char) 171 + "Робот" + (char) 187;
            g.drawString(s, frame.getWidth() / 2 - metrics.stringWidth(s) / 2, title_y + metrics.getHeight() + 10);

            phrase = Toolkit.getDefaultToolkit().getImage("src/com/company/phrase1.png");
            xOnTitle = frame.getWidth() / 2 - (int) (frame.getWidth() * 0.065886);
            yOnTitle = (int) (frame.getHeight() * 0.57);
            g.drawImage(robot, frame.getWidth() / 2 - robot_x / 2, yOnTitle, robot_x, 100, panel); //90
            g.drawImage(phrase, frame.getWidth() / 2 - robot_x / 2 - robot_x, yOnTitle - phrase.getHeight(null), panel); //130

            int hButton = 100;
            int hCoord = 70;
            int x = (int) (xOnTitle * 0.15);
            int y0 = (int) (yOnTitle * 0.55);
            int y = y0;
            int w_button = (int) (frame.getWidth() * 0.2196);

            linearAlg.getButton().setBounds(x, y, w_button, hButton);
            int h_rect = 7;
            g.fillRect(x + h_rect, y + h_rect, w_button, hButton);
            linearAlg.addOnPanel();

            Image leaf = Toolkit.getDefaultToolkit().getImage("src/com/company/leaf.png");
            g.drawImage(leaf, x + w_button / 3, y - 20 - 100, 100, 100, panel);

            y = y + hCoord + hButton;
            ifAlg.getButton().setBounds(x + 30, y, w_button, hButton);
            g.fillRect(x + 30 + h_rect, y + h_rect, w_button, hButton);
            ifAlg.addOnPanel();

            Image computer = Toolkit.getDefaultToolkit().getImage("src/com/company/computer.jpg");
            g.drawImage(computer, x + w_button / 3, y + hCoord + hButton, 120, 120, panel);

            x = frame.getWidth() - x - w_button;
            y = y0;
            forAlg.getButton().setBounds(x, y, w_button, hButton);
            g.fillRect(x + h_rect, y + h_rect, w_button, hButton);
            forAlg.addOnPanel();

            Image block = Toolkit.getDefaultToolkit().getImage("src/com/company/block.jpg");
            g.drawImage(block, x + w_button / 3, y - 15 - 100, 100, 100, panel);

            y = y + hButton + hCoord;
            procAlg.getButton().setBounds(x - 30, y, w_button, hButton);
            g.fillRect(x - 30 + h_rect, y + h_rect, w_button, hButton);
            procAlg.addOnPanel();

            g.drawImage(leaf, x + w_button / 3, y + 20 + 100, 100, 100, panel);

            x = x - (x - (int) (xOnTitle * 0.15) - w_button) / 2 - w_button / 2;
            y = y + hButton + hButton / 2;
            controlAlg.getButton().setBounds(x, y, w_button, hButton);
            g.fillRect(x + h_rect, y + h_rect, w_button, hButton);
            controlAlg.addOnPanel();
        }

        //содержимое экрана
        if (command != null) {
            CommandFactory factory = new CommandFactory(command);
            factory.drawTheoryOrTask(g, this);
        }
    }

    public void makeTitle(Graphics g, String title, String text, Image block, Image prog, Image img, String theory) {
        panel.removeAll();
        int robot_x = 90;
        int title_y = 70;
        Image robot = Toolkit.getDefaultToolkit().getImage("src/com/company/robot.png");
        Font font = new Font("Sreda", Font.BOLD, 45);
        g.setFont(font);
        g.setColor(new Color(139, 9, 12));
        FontMetrics metrics = g.getFontMetrics(font);
        g.drawString(title, frame.getWidth() / 2 - metrics.stringWidth(title) / 2, title_y);


        font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        metrics = g.getFontMetrics(font);
        int pos = text.indexOf("&");
        String text1 = "";
        //если строку надо разбить
        if (pos > 0) {
            text1 = text.substring(pos + 1, text.length());
            text = text.substring(0, pos);
        }
        if (!title.equals("Контрольные задания")) {
            g.drawString(text, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), title_y + metrics.getHeight() + metrics.getHeight() / 2);
            g.drawString(text1, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), title_y + 2 * metrics.getHeight() + metrics.getHeight() / 2);
        } else {
            g.drawString(text, frame.getWidth() / 2 - metrics.stringWidth(text) / 2, title_y + metrics.getHeight() + metrics.getHeight() / 2);
            g.drawString(text1, frame.getWidth() / 2 - metrics.stringWidth(text) / 2, title_y + 2 * metrics.getHeight() + metrics.getHeight() / 2);
        }


        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        if (!title.equals("Контрольные задания")) {
            g.drawImage(robot, Toolkit.getDefaultToolkit().getScreenSize().width - robot_x * 3, frame.getHeight() - 100 - 50, robot_x, 100, panel);
            Image phrase = Toolkit.getDefaultToolkit().getImage("src/com/company/phrase2.png");
            g.drawImage(phrase, Toolkit.getDefaultToolkit().getScreenSize().width - robot_x * 4, frame.getHeight() - 100 - 50 - phrase.getHeight(null), panel);
        }

        if (!theory.equals("")) {
            if (prog != null)
                g.drawImage(prog, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), frame.getHeight() / 2 - prog.getHeight(null) / 2 - 50, panel);
            if (block != null)
                g.drawImage(block, frame.getWidth() / 2 - block.getWidth(null) / 2, frame.getHeight() / 2 - block.getHeight(null) / 2 - 50, panel);
        } else {
            if (prog != null)
                g.drawImage(prog, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), frame.getHeight() / 2 - prog.getHeight(null) / 2, panel);
            if (block != null)
                g.drawImage(block, frame.getWidth() / 2 - block.getWidth(null) / 2, frame.getHeight() / 2 - block.getHeight(null) / 2, panel);
        }

        if (img != null)
            g.drawImage(img, Toolkit.getDefaultToolkit().getScreenSize().width - robot_x * 3, title_y + 3 * metrics.getHeight(), panel);

        font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        metrics = panel.getFontMetrics(font);

        text1 = "";
        String text2 = "";

        //если строку надо разбить
        if (!theory.equals("")) {
            String[] words = theory.split(" ");
            int i = 0;
            while (i < words.length && metrics.stringWidth(text1 + words[i] + " ") < frame.getWidth() / 2 + block.getWidth(null) / 2) {
                text1 = text1 + words[i] + " ";
                i++;
            }
            theory = "";
            if (i < words.length)
                while (i < words.length && metrics.stringWidth(theory + words[i] + " ") < frame.getWidth() / 2 + block.getWidth(null) / 2) {
                    theory = theory + words[i] + " ";
                    i++;
                }

            for (int j = i; j < words.length; j++)
                text2 = text2 + words[j] + " ";


        }
        if (prog != null) {
            g.drawString(text1, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), frame.getHeight() / 2 + prog.getHeight(null) / 2 + 25);
            g.drawString(theory, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), frame.getHeight() / 2 + prog.getHeight(null) / 2 + 25 + metrics.getHeight());
            g.drawString(text2, frame.getWidth() / 2 - block.getWidth(null) / 2 - 50 - prog.getWidth(null), frame.getHeight() / 2 + prog.getHeight(null) / 2 + 25 + 2 * metrics.getHeight());
        }


        g.setColor(new Color(139, 9, 12));
        int h_rect = 5;
        back.addOnPanel();
        first.addOnPanel();
        g.fillRect(first.getButton().getX() + h_rect, first.getButton().getY() + h_rect, first.getButton().getWidth(), first.getButton().getHeight());
        second.addOnPanel();
        g.fillRect(second.getButton().getX() + h_rect, second.getButton().getY() + h_rect, second.getButton().getWidth(), second.getButton().getHeight());
        third.addOnPanel();
        g.fillRect(third.getButton().getX() + h_rect, third.getButton().getY() + h_rect, third.getButton().getWidth(), third.getButton().getHeight());
        forth.addOnPanel();
        g.fillRect(forth.getButton().getX() + h_rect, forth.getButton().getY() + h_rect, forth.getButton().getWidth(), forth.getButton().getHeight());
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setTheory(boolean isTheory) {
        this.isTheory = isTheory;
    }

    public void setWork(boolean isWork) {
        this.isWork = isWork;
    }

    public void setCommand(CommandEnum command) {
        this.command = command;
    }

    public JFrame getFrame() {
        return frame;
    }

    public CommandEnum getCommand() {
        return command;
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            CommandEnum command = CommandEnum.valueOf(ae.getActionCommand());
            CommandFactory factory = new CommandFactory(command);
            factory.setParametersInButtonActionListener(Theory.this);
        }
    }

    public void makeWork(int robotX, int robotY, Point[] cells, CommandEnum signal, String title, int limit) {
        if (task != null)
            task.TIMER.stop();
        isWork = true;
        panel.removeAll();
        task = new Task(frame, panel, robotX, robotY, signal);
        task.setTitle(title);
        task.showForms();
        task.getField().setNecessaryCells(cells);
        task.setLineLimit(limit);
    }

    private void makeButtons() {
        ButtonActionListener actionListener = new ButtonActionListener();
        UIManager.put("Button.background", new Color(250, 174, 41));
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 15));
        UIManager.put("Button.foreground", new Color(139, 9, 12));

        int w = Field.SQUARE_SIZE, h = 50;
        int x = (int) (xOnTitle * 0.25), y = 250;
        int y0 = y;
        int hCoord = 70;

        linearAlg = new Button(CommandEnum.LINEAR_ALGORITHMS, "src/com/company/linear.png", x, y, w, h, actionListener);

        y = y + h + hCoord;
        ifAlg = new Button(CommandEnum.IF_ALGORITHMS, "src/com/company/if.png", x + 30, y, w, h, actionListener);

        x = x + 3 * hCoord + 2 * w;
        y = y0;
        forAlg = new Button(CommandEnum.FOR_ALGORITHMS, "src/com/company/for.png", x, y, w, h, actionListener);


        y = y + h + hCoord;
        procAlg = new Button(CommandEnum.PROCEDURE_ALGORITHMS, "src/com/company/proc.png", x - 30, y, w, h, actionListener);

        x = x - (3 * hCoord + w) / 2 - w / 2;
        y = y + h + h / 3;
        controlAlg = new Button(CommandEnum.CONTROL_TASKS, "src/com/company/control.png", x, y, w, h, actionListener);

        x = 20;
        y = 20;
        back = new Button(CommandEnum.BACK, null, x, y, w, h, actionListener);
        back.getButton().setBackground(new Color(250, 250, 184));

        y = Toolkit.getDefaultToolkit().getScreenSize().height - hCoord * 2;
        x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - w * 2 - hCoord - hCoord / 2;


        first = new Button(CommandEnum.FIRST, null, x, y, w, h, actionListener);
        first.getButton().setBackground(new Color(248, 250, 18));
        x += h + hCoord;
        second = new Button(CommandEnum.SECOND, null, x, y, w, h, actionListener);
        second.getButton().setBackground(new Color(248, 250, 18));
        x += h + hCoord;
        third = new Button(CommandEnum.THIRD, null, x, y, w, h, actionListener);
        third.getButton().setBackground(new Color(248, 250, 18));
        x += h + hCoord;
        forth = new Button(CommandEnum.FORTH, null, x, y, w, h, actionListener);
        forth.getButton().setBackground(new Color(248, 250, 18));


    }

    public class Button {
        JButton button;

        public Button(CommandEnum command, String icon, int x, int y, int w, int h, ButtonActionListener actionListener) {
            button = new JButton(command.getName());
            button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(icon)));
            button.setActionCommand(command.toString());
            button.setBounds(x, y, w, h);
            button.addActionListener(actionListener);
        }

        public void addOnPanel() {
            panel.add(button);
        }

        public JButton getButton() {
            return button;
        }

    }


}
