import javax.swing.*;
import java.awt.*;

public class Kalkulator extends JFrame {

    public Kalkulator() {
        JFrame frame = new JFrame("Kalkulator");

        frame.setLayout(new FlowLayout());

        frame.setSize(600, 100);

        JButton b1 = new JButton("+");
        JButton b2 = new JButton("-");
        JButton b3 = new JButton("*");
        JTextField t1 = new JTextField(10);
        JTextField t2 = new JTextField(10);
        JLabel wynik = new JLabel("???");

        frame.add(new JLabel("Operand 1"));
        frame.add(t1);
        frame.add(new JLabel("Operand 2"));
        frame.add(t2);
        frame.add(new JLabel("Przyciski operacji"));
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(new JLabel("Wynik operacji"));
        frame.add(wynik);

        OperacjaKalkulatora listener = new OperacjaKalkulatora(t1, t2, b1, b2, b3, wynik);
        t1.addActionListener(listener);
        t2.addActionListener(listener);
        b1.addActionListener(listener);
        b1.setActionCommand("+");
        b2.addActionListener(listener);
        b2.setActionCommand("-");


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
