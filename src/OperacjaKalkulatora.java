import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class OperacjaKalkulatora implements ActionListener {
    private JTextField operand1;
    private JTextField operand2;
    private JButton b1;
    private JButton b3;
    private JButton b2;
    private JLabel wynik;

    public OperacjaKalkulatora(JTextField operand1, JTextField operand2, JButton b1, JButton b2, JButton b3, JLabel wynik) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.wynik = wynik;
        this.b1.addActionListener(this);
        this.b2.addActionListener(this);
        this.b3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(operand1.getText());
            double num2 = Double.parseDouble(operand2.getText());
            if ("+".equals(e.getActionCommand())) {
                wynik.setText(Double.toString(num1 + num2));
                System.out.println(wynik.getText());

            } else if ("-".equals(e.getActionCommand())) {
                wynik.setText(Double.toString(num1 - num2));
            } else {
                wynik.setText(Double.toString(num1 * num2));
            }

        } catch (Exception ex) {
            System.out.println("Cos poszlo nie tak");
        }


    }
}
