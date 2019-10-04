package com.company;

import com.company.commands.CommandEnum;
import com.company.commands.CommandFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by acer on 27.09.2015.
 */
public class ControlButton {
    private JButton button;
    private Image bgr;

    public ControlButton (int x, int y, int width, int height, Image bgr, CommandEnum actionCommand) {
        this.bgr = bgr;
        UIManager.put("Button.background", new Color (0, 0, 0, 0));
        createButton(actionCommand);
        button.setBounds(x, y, width, height);
    }


    private void createButton(CommandEnum actionCommand) {
        CommandFactory factory = new CommandFactory(actionCommand);
        ImageIcon icon = null;
        if (bgr != null)
            icon = new ImageIcon(bgr);
        button = factory.createButton(icon);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 14));

        button.setActionCommand(actionCommand.toString());
    }

    public void show(JPanel panel) {
        panel.add(button);
    }

    public JButton getButton() {
        return button;
    }

    public static class RoundButton extends JButton {
        public RoundButton(Icon icon) {
            super(icon);
        }

        public RoundButton (String text) {
            super(text);
        }

        protected void paintBorder(Graphics g) {
            super.paintBorder(g);
            g.setColor(getForeground());
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        }
    }

}

