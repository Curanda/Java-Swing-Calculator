import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class OperacjaKalkulatora2 implements ActionListener {
    private JTextField pole;
    private ArrayList<JButton> listaNumerow;
    private JButton bPlus, bMinus, bRazy, bDziel, bRowna, bKropka, bKasuj, bPM;
    boolean ujemnyOp1 = false;
    boolean ujemnyOp2 = false;
    String dzialanie;
    String[] operandy = new String[2];

    public OperacjaKalkulatora2(JTextField pole, JButton bPlus, JButton bMinus, JButton bRazy, JButton bDziel, JButton bRowna, JButton bKropka, JButton bKasuj, ArrayList<JButton> listaNumerow, JButton bPM) {
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
    }

    public void skomplikowaneParsowanie() {
        /* Tutaj jest moje opus magnum. Dlaczego opus magnum? Otóż chciałem umożliwić podgląd całego działania.
        Żeby to zrobić podjąłem się żmudnego wysiłku wykombinowania logiki dla przypadku:
        - drugi operand jest ujemny. Przy czym symbol między operandami też może być ujemny. Czyli dwa minusy.
        - całe mnóśtwo edge case'ów, które znalazłem przy testowaniu.
        Jestem pewien że można to rozwiązać za pomocą pattern matcher zliczający wystąpienia, ale
        intuicja podpowiadała, że musi być jakiś lżejszy sposób.
        W niniejszych komentarzach omówię jak to uzyskałem.
        */

        //Najpierw rozwazamy, czy pierwszy operand jest ujemny:
        if (dzialanie.startsWith("-")) {
            // Jeśli tak, to wycinamy ten pierwszy znak,
            dzialanie = dzialanie.substring(1);
            // ale flagujemy, że będzie ujemny do późniejszego obliczenia.
            ujemnyOp1 = true;
        };

        // Teraz jeżeli druga liczba ma znak ujemny, to wycinamy go przez
        // podmianę i flagujemy drugi operand jeśli zachodzi taka potrzeba :
        if (dzialanie.contains("+-")) {
            dzialanie = dzialanie.replace("+-", "-");
        }
        if (dzialanie.contains("--")) {
            dzialanie = dzialanie.replace("--", "+");
        }
        if (dzialanie.contains("/-")) {
            dzialanie = dzialanie.replace("/-", "/");
            ujemnyOp2 = true;
        }
        if (dzialanie.contains("*-")) {
            dzialanie = dzialanie.replace("*-", "*");
            ujemnyOp2 = true;
        }
        // Teraz przedostatni krok, czyli splitujemy stringa do dwóch osobnych stringów, reprezentujących
        // dwa operandy. Jeśli wprowadzimy więcej niż dwa operandy na raz, tylko dwa pierwsze zostaną podliczone,
        // ponieważ array operandy ma dlugosc 2. Ale, z tym podejściem można jak najbardziej rozszerzyć listę
        // i zainicjować więcej operandów, a to otwiera drogę do bardziej skomplikowanych operacji matematycznych.
        operandy = dzialanie.split("[+,*,/,-]");
    }

    public void rownaj() {
        try {
            skomplikowaneParsowanie();

            // Oryginalnie robiłem weryfikację całego stringa "dzialanie"
            // po obecności kropki, czyli if (dzialanie.indexOf(".") != -1) żeby prowadzić obliczenia na doublach lub
            // intach wedle tego co zostało wprowadzone, ale okazało się że w przypadku wyników ułamkowych dla
            // operandów typu int, wynik jest zaokrąglany, a więc nieprawidłowy. W związku z tym przyjąłem że optymalne
            // rozwiązanie to prowadzić działania wyłącznie na doublach i na koniec sparsować wszystko z zerem
            // po przecinku do stringa który wygląda jak "int".

            // Parsujemy operandy do doubli.
            double opD1 = Double.parseDouble(operandy[0]);
            double opD2 = Double.parseDouble(operandy[1]);

            // Dla przejrzystości będę wypełniał pole zmienną wynikową zadeklarowaną tutaj:
            String wynikTxt = "";

            // Weryfikacja flag i przekształcenie operandów:
            if (ujemnyOp1) opD1 = -opD1;
            if (ujemnyOp2) opD2 = -opD2;

            // Sprawdzamy, z którym operatorem mat. mamy do czynienia w dzialaniu i wykonujemy operację.
            if (dzialanie.indexOf('+') != -1) wynikTxt = String.valueOf(opD1 + opD2);
            if (dzialanie.indexOf('/') != -1) wynikTxt = String.valueOf(opD1 / opD2);
            if (dzialanie.indexOf('*') != -1) wynikTxt = String.valueOf(opD1 * opD2);
            if (dzialanie.indexOf('-') != -1) wynikTxt = String.valueOf(opD1 - opD2);

            // Tutaj weryfikacja czy wynik ma zero na ostatnim indeksie i kropkę na przedostatnim indeksie:
            if ((wynikTxt.indexOf(".") == wynikTxt.length()-2) && wynikTxt.lastIndexOf("0") == wynikTxt.length()-1) {
                // Po to żeby, tak jak na prawdziwym kalkulatorze wyświetlały się inty tam, gdzie wynikiem są
                // okrągłe liczby, a double tam gdzie wynikiem są ułamki, pomimo że tutaj wszystkie operacje mat. są
                // prowadzone na doublach.
                wynikTxt = wynikTxt.substring(0, wynikTxt.length()-2);
            }

            System.out.println("dzialanie="+dzialanie);
            System.out.println("op1="+ opD1);
            System.out.println("op2="+opD2);
            System.out.println("wynik="+wynikTxt);

            // Wypisujemy wynik na kalkulator :
            pole.setText(wynikTxt);

            // Zauważyłem, że po kilku operacjach na ujemnych liczbach flagi zaczynają się mieszać.
            // więc dodałem zerowanie flag w miejscach, które na zdrowy rozum są za najbardziej narażone
            // na występowanie tego buga.
            ujemnyOp1 = false;
            ujemnyOp2 = false;
        } catch (Exception ne) {
            // Chwytamy błędy przy wpisywaniu znaków niekonwertowalnych, czyli duplikatów operatorów, spacji, liter itp.
            pole.setText("Operacja zabroniona");
            ujemnyOp1 = false;
            ujemnyOp2 = false;
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dzialanie = pole.getText();
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
                pole.setText(dzialanie + e.getActionCommand());
                break;
            case "+":
            case "-":
            case "*":
            case ".":
            case "/":
                pole.setText(dzialanie + e.getActionCommand());
                break;
            case "C":
                pole.setText("");
                ujemnyOp1 = false;
                ujemnyOp2 = false;
                break;
            case "-/+":
                pole.setText(dzialanie + "-");
                break;
            case "=":
                rownaj();
                break;
            default:
                pole.setText("0");
                break;
        }
    };
}