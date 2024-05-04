import javax.swing.*;
import java.awt.*;

public class Kalkulator2  extends JFrame {

    public Kalkulator2() {
        super("Kalkulator-2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));
        setSize(400,150);
        setVisible(true);
        JButton buttonDodawanie = new JButton("+");
        JButton buttonOdejmowanie = new JButton("-");
        JButton buttonMnozenie = new JButton("*");
        JTextField poleOp1 = new JTextField(10);
        JTextField poleOp2 = new JTextField(10);
        JLabel wynik = new JLabel("???");
        JLabel oper1 = new JLabel("Operand 1");
        JLabel oper2 = new JLabel("Operand 2");
        poleOp1.setHorizontalAlignment(JTextField.CENTER);
        poleOp2.setHorizontalAlignment(JTextField.CENTER);
        wynik.setHorizontalAlignment(JTextField.CENTER);
        oper1.setHorizontalAlignment(JTextField.CENTER);
        oper2.setHorizontalAlignment(JTextField.CENTER);

        this.add(oper1);
        this.add(oper2);
        this.add(new JLabel("Wynik operacji"));
        this.add(poleOp1);
        this.add(poleOp2);
        this.add(wynik);
//        this.add(new JLabel("Przyciski operacji"));
        this.add(buttonDodawanie);
        this.add(buttonOdejmowanie);
        this.add(buttonMnozenie);

        OperacjaKalkulatora listener = new OperacjaKalkulatora(poleOp1, poleOp2, buttonDodawanie, buttonOdejmowanie, buttonMnozenie, wynik);
        poleOp1.addActionListener(listener);
        poleOp2.addActionListener(listener);
        buttonDodawanie.addActionListener(listener);
        buttonDodawanie.setActionCommand("+");
        buttonOdejmowanie.addActionListener(listener);
        buttonOdejmowanie.setActionCommand("-");
    }

}
