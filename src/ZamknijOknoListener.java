import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZamknijOknoListener implements ActionListener {

    private JFrame okno;

    public ZamknijOknoListener(JFrame okno) {
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        okno.dispose();
    }
}
