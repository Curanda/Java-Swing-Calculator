import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;

public class Tlumacz extends JFrame {
    public Tlumacz() {
        JFrame tlumacz = new JFrame("Tlumacz");
//        tlumacz.pack();
        tlumacz.setLayout(new BorderLayout());
        tlumacz.setSize(500, 100);
        JPanel panelGorny = new JPanel(new GridLayout(1,4));
        JPanel panelDolny = new JPanel(new BorderLayout());
        JLabel poAng = new JLabel("Po angielsku");
        JLabel poPol = new JLabel("Po polsku");
        JTextField poAngT = new JTextField(10);
        JTextField poPolT = new JTextField(10);
        JButton button = new JButton("Pokaz następne słówko");
        EmptyBorder padding = new EmptyBorder(1, 10, 1, 10);

        panelGorny.add(poAng);
        panelGorny.add(poAngT);
        panelGorny.add(poPol);
        panelGorny.add(poPolT);
        panelDolny.add(button);
        tlumacz.add(panelGorny, BorderLayout.NORTH);
        tlumacz.add(panelDolny, BorderLayout.SOUTH);
        panelGorny.setBorder(new EmptyBorder(1, 10, 1, 10));
        panelGorny.setMaximumSize(new Dimension(400, 30));
        panelDolny.setMaximumSize(new Dimension(80, 30));
        panelDolny.setBorder(new EmptyBorder(1, 90, 1, 90));









        tlumacz.setVisible(true);
        tlumacz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
