import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.*;


public class OperacjaKalkulatora implements ActionListener {
    private JTextField pole;
    private ArrayList<JButton> listaNumerow;
    private JButton bPlus, bMinus, bRazy, bDziel, bRowna, bKropka, bKasuj, bPM;
    private JLabel ekranIO;
    private String op1 = null;
    private String op2 = null;
    private String operator = null;

    public OperacjaKalkulatora(JTextField pole, JButton bPlus, JButton bMinus, JButton bRazy, JButton bDziel, JButton bRowna, JButton bKropka, JButton bKasuj, ArrayList<JButton> listaNumerow, JButton bPM, JLabel ekranIO) {
        this.pole = pole;
        this.bPlus = bPlus;
        this.bMinus = bMinus;
        this.bRazy = bRazy;
        this.bDziel = bDziel;
        this.bRowna = bRowna;
        this.bKropka = bKropka;
        this.bKasuj = bKasuj;
        this.bPM = bPM;
        this.listaNumerow = listaNumerow;
        this.ekranIO = ekranIO;
    }

    public void rownaj(String op1, String op2, String operator) {
        op1 = op1.trim();
        op2 = op2.trim();
        try {

        if (op1.indexOf(".") != -1 || op2.indexOf(".") != -1) {
            // dla doubli sprawdzamy czy w inpucie znajduje sie kropka.
                double opD1 = Double.parseDouble(op1);
                double opD2 = Double.parseDouble(op2);

                switch (operator) {
                    case "+":
                        ekranIO.setText(String.valueOf(opD1+opD2));
                        break;
                    case "-":
                        ekranIO.setText(String.valueOf(opD1-opD2));
                        break;
                    case "*":
                        ekranIO.setText(String.valueOf(opD1*opD2));
                        break;
                    case "/":
                        ekranIO.setText(String.valueOf(opD1/opD2));
                        break;
                    default:
                        ekranIO.setText("0");
                        break;
                };
            } else {
                // dla intów
                int opI1 = Integer.parseInt(op1);
                int opI2 = Integer.parseInt(op2);

                switch (operator) {
                    case "+":
                        ekranIO.setText(String.valueOf(opI1+opI2));
                        break;
                    case "-":
                        ekranIO.setText(String.valueOf(opI1-opI2));
                        break;
                    case "*":
                        ekranIO.setText(String.valueOf(opI1*opI2));
                        break;
                    case "/":
                        ekranIO.setText(String.valueOf(opI1/opI2));
                        break;
                    default:
                        ekranIO.setText("0");
                        break;
            };

            };
        } catch (Exception e) {
            ekranIO.setText("Forbidden operation");
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        String dzialanie = ekranIO.getText();

        switch (e.getActionCommand()) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "0":
            case "6":
            case "7":
            case "8":
            case "9":
                ekranIO.setText(dzialanie + e.getActionCommand());
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                op1 = dzialanie;
                operator = operator = e.getActionCommand();
                ekranIO.setText("");
                break;
            case ".":
                ekranIO.setText(dzialanie + ".");
                break;
            case "C":
                ekranIO.setText("");
                break;
            case "-/+":
                ekranIO.setText("-");
                break;
            case "=":
                op2 = ekranIO.getText();
                System.out.println(op2);
                // w tej funkcji parsowanie i logika mat.
                rownaj(op1, op2, operator);
                break;
            default:
                ekranIO.setText("");
                break;
    }

};

    }

    // Tutaj jakby Pan chciał sobie zobaczyć to była poprzednia wersja logiki, w której chciałem zrobić tak żeby całe działanie
// wyświetlało się na ekranie i żeby można było wprowadzać liczby i operatory z klawiatury. Generalnie logika jak
// najbardziej działała, do momentu dodania znaku negacji do drugiego operanda
// z jakiegoś powodu

//    public void rownaj() {
//        try {
//            boolean ujemnyOp1 = false;
//            boolean ujemnyOp2 = false;
//            String operator;
//            String[] operatory = {"*","+","/"};
//            String dzialanie = pole.getText();
//            if (dzialanie.startsWith("-") == true) {
//                dzialanie = dzialanie.substring(1);
//                ujemnyOp1 = true;
//            };
//
//            for (String i : operatory) {
//                if (dzialanie.contains(i) == true && dzialanie.contains("-")) {
//                    ujemnyOp2 = true;
//                    operator = i;
//                };
//            };
//
//            String[] operandy = new String[2];
//            operandy = dzialanie.split("[+\\-*/]");
//            System.out.println(operandy[0] +" "+ operandy[1]);
//            if (dzialanie.indexOf(".") != -1) {
//                double opD1 = Double.parseDouble(operandy[0]);
//                double opD2 = Double.parseDouble(operandy[1]);
//
//                if (ujemnyOp1 == true) opD1 = -opD1;
//                if (ujemnyOp2 == true) opD2 = -opD2;
//
//                if (dzialanie.indexOf('+') != -1) pole.setText(String.valueOf(opD1+opD2));
//                if (dzialanie.indexOf('-') != -1) pole.setText(String.valueOf(opD1-opD2));
//                if (dzialanie.indexOf('/') != -1) pole.setText(String.valueOf(opD1/opD2));
//                if (dzialanie.indexOf('*') != -1) pole.setText(String.valueOf(opD1*opD2));
//            } else {
//                int op1 = Integer.parseInt(operandy[0]);
//                int op2 = Integer.parseInt(operandy[1]);
//                if (ujemnyOp1 == true) op1 = -op1;
//                if (ujemnyOp2 == true) op2 = -op2;
//
//                if (dzialanie.indexOf('+') != -1) pole.setText(String.valueOf(op1+op2));
//                if (dzialanie.indexOf('-') != -1) pole.setText(String.valueOf(op1-op2));
//                if (dzialanie.indexOf('/') != -1) pole.setText(String.valueOf(op1/op2));
//                if (dzialanie.indexOf('*') != -1) pole.setText(String.valueOf(op1*op2));
//            };
//        } catch (Exception ne) {
//            pole.setText("");
//        };
//    }
//        String dzialanie = pole.getText();
//        switch (e.getActionCommand()) {
//            case "1":
//                pole.setText(dzialanie + "1");
//                break;
//            case "2":
//                pole.setText(dzialanie + "2");
//                break;
//            case "3":
//                pole.setText(dzialanie + "3");
//                break;
//            case "4":
//                pole.setText(dzialanie + "4");
//                break;
//            case "5":
//                pole.setText(dzialanie + "5");
//                break;
//            case "0":
//                pole.setText(dzialanie + "0");
//                break;
//            case "6":
//                pole.setText(dzialanie + "6");
//                break;
//            case "7":
//                pole.setText(dzialanie + "7");
//                break;
//            case "8":
//                pole.setText(dzialanie + "8");
//                break;
//            case "9":
//                pole.setText(dzialanie + "9");
//                break;
//            case "+":
//                pole.setText(dzialanie + "+");
//                break;
//            case "-":
//                pole.setText(dzialanie + "-");
//                break;
//            case "*":
//                pole.setText(dzialanie + "*");
//                break;
//            case "/":
//                pole.setText(dzialanie + "/");
//                break;
//            case ".":
//                pole.setText(dzialanie + ".");
//                break;
//            case "C":
//                pole.setText("");
//                break;
//            case "-/+":
//                pole.setText("-");
//                break;
//            case "=":
//                rownaj();
//                break;
//            default:
//                pole.setText("0");
//                break;
//        }
