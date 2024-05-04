import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModalZamknij extends JFrame {
    public ModalZamknij() {
        super("Zamknij");
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        setSize(500,200);
        JButton zamknij = new JButton("Zamknij");
        panel.add(zamknij);
        add(panel, BorderLayout.SOUTH);
        zamknij.addActionListener(e -> this.dispose());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
