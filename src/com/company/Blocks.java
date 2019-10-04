package com.company;

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;
import com.company.commands.For;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by acer on 10.10.2015.
 */
public class Blocks {
    private int x0, y0, width, height;
    private final String BEGIN = "begin";
    private final String END = "end";
    private final int OVAL_WIDTH; //ширина овала
    private final int RECTANGLE_WIDTH;  //ширина прямоугольника
    public static final int FIGURE_HEIGHT = 25; //высота блока
    public static final int BIG_ARROW_LENGTH = 15; //длина стрелочки
    private ArrayList<CommandEnum> commands = new ArrayList<>();
    public static final int SMALL_ARROW_LENGTH = 5;
    public static final float ALPHA = (float) Math.PI / 6;
    private int nFor;

    public Blocks(int x0, int y0, int width, int height) {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
        OVAL_WIDTH = width / 2;
        RECTANGLE_WIDTH = width / 2 - 10;
    }

    //добавить команду в блок-схему
    public void addCommand(CommandEnum command) {
        commands.add(command);
    }

    //удалить команду из блок-схемы
    public void removeCommand() {
        if (commands.size() != 0)
            commands.remove(commands.size() - 1);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(139, 9, 12));
        g2.fillRect(x0 - 5, y0 - 5, width + 10, height + 10);

        g2.setColor(Color.white);
        g2.fillRect(x0, y0, width, height);

        int y = y0;
        g2.setColor(Color.black);
        Font font = new Font("Courier New", Font.PLAIN, 16);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        int x = x0 + width / 2 - OVAL_WIDTH / 2;

        //слово "begin"
        y += 5;
        g2.drawLine(x, y, x + OVAL_WIDTH, y);
        g2.drawLine(x, y + FIGURE_HEIGHT, x + OVAL_WIDTH, y + FIGURE_HEIGHT);
        g2.drawArc(x - FIGURE_HEIGHT / 2, y, FIGURE_HEIGHT, FIGURE_HEIGHT, 90, 180);
        g2.drawArc(x + OVAL_WIDTH - FIGURE_HEIGHT / 2, y, FIGURE_HEIGHT, FIGURE_HEIGHT, 90, -180);
        g2.drawString(BEGIN, x + OVAL_WIDTH / 2 - metrics.stringWidth(BEGIN) / 2, y + FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);

        //стрелочка

        g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT, x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH);
        g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH, (int) (x0 + width / 2 - SMALL_ARROW_LENGTH / Math.sin(ALPHA)), (int) (y + FIGURE_HEIGHT + BIG_ARROW_LENGTH - SMALL_ARROW_LENGTH / Math.cos(ALPHA)));
        g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH, (int) (x0 + width / 2 + SMALL_ARROW_LENGTH / Math.sin(ALPHA)), (int) (y + FIGURE_HEIGHT + BIG_ARROW_LENGTH - SMALL_ARROW_LENGTH / Math.cos(ALPHA)));

        //введенные команды
        x = x0 + width / 2 - RECTANGLE_WIDTH / 2;
        for (int i = 0; i < commands.size(); i++) {
            y += FIGURE_HEIGHT + BIG_ARROW_LENGTH;
            CommandEnum command = commands.get(i);
            CommandFactory factory = new CommandFactory(commands.get(i));
            if (command == CommandEnum.FOR) {
                For f = (For) (factory.getCommand());
                f.setNFor(nFor);
            }
            factory.drawBlock(g2, new Point(x, y), RECTANGLE_WIDTH);

            if (command == CommandEnum.JUMP_DOWN || command == CommandEnum.JUMP_LEFT || command == CommandEnum.JUMP_RIGHT || command == CommandEnum.JUMP_UP)
                y += 5;
            //отрисовка стрелок для циклического блока
            if (i - 1 >= 0 && commands.get(i - 1) == CommandEnum.FOR) {
                g2.drawLine(x, y + FIGURE_HEIGHT / 2, x - 20, y + FIGURE_HEIGHT / 2);
                g2.drawLine(x - 20, y + FIGURE_HEIGHT / 2, x - 20, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH);
                g2.drawLine(x - 20, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH, x, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH);
                //стрелочка
                g2.drawLine(x, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH, (int) (x - SMALL_ARROW_LENGTH / Math.cos(ALPHA)), (int) (y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH - SMALL_ARROW_LENGTH / Math.sin(ALPHA)));
                g2.drawLine(x, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH, (int) (x - SMALL_ARROW_LENGTH / Math.cos(ALPHA)), (int) (y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH + SMALL_ARROW_LENGTH / Math.sin(ALPHA)));

                g2.drawLine(x + RECTANGLE_WIDTH, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH, x + RECTANGLE_WIDTH + 20, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH);
                g2.drawLine(x + RECTANGLE_WIDTH + 20, y - FIGURE_HEIGHT / 2 - BIG_ARROW_LENGTH, x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT + 5);
                g2.drawLine(x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT + 5, x + RECTANGLE_WIDTH / 2, y + FIGURE_HEIGHT + 5);
                y += 5;
            }

            //стрелочка
            if (commands.get(i) != CommandEnum.IF) {
                g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT, x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH);
                g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH, (int) (x0 + width / 2 - SMALL_ARROW_LENGTH / Math.sin(ALPHA)), (int) (y + FIGURE_HEIGHT + BIG_ARROW_LENGTH - SMALL_ARROW_LENGTH / Math.cos(ALPHA)));
                g2.drawLine(x0 + width / 2, y + FIGURE_HEIGHT + BIG_ARROW_LENGTH, (int) (x0 + width / 2 + SMALL_ARROW_LENGTH / Math.sin(ALPHA)), (int) (y + FIGURE_HEIGHT + BIG_ARROW_LENGTH - SMALL_ARROW_LENGTH / Math.cos(ALPHA)));
            }
        }

        //слово "end"
        x = x0 + width / 2 - OVAL_WIDTH / 2;
        y += FIGURE_HEIGHT + BIG_ARROW_LENGTH;
        g2.drawLine(x, y, x + OVAL_WIDTH, y);
        g2.drawLine(x, y + FIGURE_HEIGHT, x + OVAL_WIDTH, y + FIGURE_HEIGHT);
        g2.drawArc(x - FIGURE_HEIGHT / 2, y, FIGURE_HEIGHT, FIGURE_HEIGHT, 90, 180);
        g2.drawArc(x + OVAL_WIDTH - FIGURE_HEIGHT / 2, y, FIGURE_HEIGHT, FIGURE_HEIGHT, 90, -180);
        g2.drawString(END, x + OVAL_WIDTH / 2 - metrics.stringWidth(END) / 2, y + FIGURE_HEIGHT / 2 + metrics.getHeight() / 3);


    }

    public void setNFor(int n) {
        nFor = n;
    }

    public ArrayList<CommandEnum> getCommands() {
        return commands;
    }


}
