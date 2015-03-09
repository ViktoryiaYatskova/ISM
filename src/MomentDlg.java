import javax.swing.*;
import java.awt.event.*;

public class MomentDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane textPane1;
    private JTextField textField1;
    private int[] sequence;

    public MomentDlg(int[] seq) {
        getRootPane().setDefaultButton(buttonOK);
        sequence = seq;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setContentPane(contentPane);
        setModal(true);
        pack();
        setVisible(true);
    }

    private void onOK() {
        int order = Integer.parseInt(textField1.getText());
        double average = GeneratorSequence.findAverage(sequence);
        double centMoment = GeneratorSequence.getCentralMoment(sequence, order);
        double startMoment = GeneratorSequence.getStartMoment(sequence, order);
        String str = "Average: \n"+average+"\n\nCentral moment: \n" + centMoment+"\n\nStart moment: \n" + startMoment;
        textPane1.setText(str);
    }

    private void onCancel() {
        dispose();
    }
}
