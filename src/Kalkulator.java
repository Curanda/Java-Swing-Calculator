import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;

public class Kalkulator extends JFrame {

    public Kalkulator() {
        JFrame kalkulator = new JFrame("Kalkulator");
        kalkulator.setLayout(new BorderLayout());
        setSize(500,500);

        JTextField pole = new JTextField(15);
        JButton bPlus = new JButton("+");
        JButton bMinus = new JButton("-");
        JButton bRazy = new JButton("*");
        JButton bDziel = new JButton("/");
        JButton bRowna = new JButton("=");
        JButton bKropka = new JButton(".");
        JButton bKasuj = new JButton("C");
        JButton bPM = new JButton("-/+");
        JLabel ekranIO = new JLabel(" ");
        // Postanowiłem zrobić to programatycznie, bo straszne spaghetti wychodzi z tego dodawania klawiatury numerycznej.
        ArrayList<JButton> listaNumerow = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listaNumerow.add(new JButton(""+Integer.toString(i)));
        };


        JPanel klawiaturaPanel = new JPanel(new GridLayout(4,4));
        JPanel dolnyPanel = new JPanel(new GridLayout(1,2));
        dolnyPanel.add(bRowna);
        dolnyPanel.add(bPM);
        for (JButton button : listaNumerow) {
            klawiaturaPanel.add(button);
            switch (button.getText()) {
                case "2":
                    klawiaturaPanel.add(bPlus);
                case "5":
                    klawiaturaPanel.add(bMinus);
                case "8":
                    klawiaturaPanel.add(bRazy);
                case "9":
                    klawiaturaPanel.add(bKasuj);
                    klawiaturaPanel.add(bKropka);
                    klawiaturaPanel.add(bDziel);
            }
        }
        ekranIO.setHorizontalAlignment(SwingConstants.RIGHT);
        ekranIO.setPreferredSize(new Dimension(200, 30));
        EmptyBorder padding = new EmptyBorder(5, 10, 5, 10);
        LineBorder outline = new LineBorder(Color.BLACK, 1);
        CompoundBorder compoundBorder = new CompoundBorder(padding, outline);
        ekranIO.setBorder(compoundBorder);
        kalkulator.add(new JPanel().add(ekranIO), BorderLayout.PAGE_START);
        kalkulator.add(new JPanel().add(klawiaturaPanel), BorderLayout.CENTER);
        kalkulator.add(new JPanel().add(dolnyPanel), BorderLayout.PAGE_END);

        OperacjaKalkulatora listener = new OperacjaKalkulatora(pole, bPlus, bMinus, bRazy, bDziel, bRowna, bKropka, bKasuj, listaNumerow, bPM, ekranIO);
        pole.addActionListener(listener);
        bPlus.addActionListener(listener);
        bPlus.setActionCommand("+");
        bMinus.addActionListener(listener);
        bMinus.setActionCommand("-");
        bRazy.addActionListener(listener);
        bRazy.setActionCommand("*");
        bDziel.addActionListener(listener);
        bDziel.setActionCommand("/");
        bRowna.addActionListener(listener);
        bRowna.setActionCommand("=");
        bKropka.addActionListener(listener);
        bKropka.setActionCommand(".");
        bKasuj.addActionListener(listener);
        bKasuj.setActionCommand("C");
        bPM.addActionListener(listener);
        bPM.setActionCommand("-/+");
        for (int i = 0; i < 10; i++) {
            listaNumerow.get(i).addActionListener(listener);
            listaNumerow.get(i).setActionCommand(Integer.toString(i));
        };

        kalkulator.pack();
        kalkulator.setVisible(true);
        kalkulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
