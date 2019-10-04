package com.company;

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;
import com.company.commands.For;


import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 27.09.2015.
 */
public class ProgramField {
    private JTextArea textArea;
    private String begin = "01  begin";
    private String end = "end.";
    private int numberOfCurrentString = 1; //номер очередной строки
    private int nFor; //кол-во итераций цикла for
    private boolean isProcedure; //является ли блок процедурой
    private boolean isForBlock = false; //является ли блок циклическим
    private String procedure = "procedure Proc;" + "\n" + "begin";


    public ProgramField(int x, int y, int w, int h) {
        textArea = new JTextArea(begin + "\n" + "02  " + end);
        textArea.setBounds(x, y, w, h);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 19));
    }

    public void addOnPanel(JPanel panel) {
        panel.add(textArea);
    }


    //добавить текст на textArea
    public void addText(CommandEnum command) {
        CommandFactory factory = new CommandFactory(command);
        if (command == CommandEnum.FOR) {
            For f = (For) (factory.getCommand());
            f.setNFor(nFor);
        }
        String text = factory.getTextForProgramField();

        if (isForBlock) {
            text = "  " + text;
            isForBlock = false;
        }
        numberOfCurrentString++;
        if (numberOfCurrentString > 0 && numberOfCurrentString < 10)
            begin = begin + "\n" + "0" + numberOfCurrentString + "  " + "  " + text;
        else
            begin = begin + "\n" + numberOfCurrentString + "  " + "  " + text;
        textArea.removeAll();
        String procedure = "";
        if (isProcedure) {
            procedure = this.procedure + "\n" + "end;" + "\n" + "\n";
        }
        if (numberOfCurrentString + 1 > 0 && numberOfCurrentString + 1 < 10)
            textArea.setText(procedure + begin + "\n" + "0" + (numberOfCurrentString + 1) + "  " + end);
        else
            textArea.setText(procedure + begin + "\n" + (numberOfCurrentString + 1) + "  " + end);
    }

    public void addTextToProcedure(CommandEnum commandEnum) {
        textArea.removeAll();
        procedure = procedure + "\n" + "  " + commandEnum.getName() + ";";
        String procedure = this.procedure + "\n" + "end;" + "\n" + "\n";
        if (numberOfCurrentString + 1 > 0 && numberOfCurrentString + 1 < 10)
            textArea.setText(procedure + begin + "\n" + "0" + (numberOfCurrentString + 1) + "  " + end);
        else
            textArea.setText(procedure + begin + "\n" + (numberOfCurrentString + 1) + "  " + end);
    }

    public void removeString() {
        String newString = "";
        int i = begin.length() - 1;
        while (i >= 0 && begin.charAt(i) != '\n')
            i--;
        i--;
        for (int j = i; j >= 0; j--)
            newString = begin.charAt(j) + newString;
        numberOfCurrentString--;
        begin = newString;
        textArea.removeAll();
        String procedure = "";
        if (isProcedure)
            procedure = this.procedure + "\n" + "end;" + "\n" + "\n";
        if (numberOfCurrentString + 1 > 0 && numberOfCurrentString + 1 < 10)
            textArea.setText(procedure + begin + "\n" + "0" + (numberOfCurrentString + 1) + "  " + end);
        else
            textArea.setText(procedure + begin + "\n" + (numberOfCurrentString + 1) + "  " + end);
    }

    public void removeStringFromProcedure() {
        String newString = "";
        int i = procedure.length() - 1;
        while (i >= 0 && procedure.charAt(i) != '\n')
            i--;
        i--;
        for (int j = i; j >= 0; j--)
            newString = procedure.charAt(j) + newString;
        procedure = newString;
        textArea.removeAll();
        String procedure = this.procedure + "\n" + "end;" + "\n" + "\n";
        if (numberOfCurrentString + 1 > 0 && numberOfCurrentString + 1 < 10)
            textArea.setText(procedure + begin + "\n" + "0" + (numberOfCurrentString + 1) + "  " + end);
        else
            textArea.setText(procedure + begin + "\n" + (numberOfCurrentString + 1) + "  " + end);
    }

    public void setNFor(int n) {
        nFor = n;
    }

    public void setProcedure(boolean b) {
        isProcedure = b;
        if (isProcedure) {
            textArea.removeAll();
            textArea.setText(procedure + "\n" + "end;" + "\n" + "\n" + begin + "\n" + "02  " + end);
        }
    }

    public void setForBlock(boolean isForBlock) {
        this.isForBlock = isForBlock;
    }
}

