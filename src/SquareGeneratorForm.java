import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Viktoria on 22.02.2015.
 */
public class SquareGeneratorForm extends SequenceForm{
    private JTextField textFieldB;
    private JButton generateButton;
    private JTextPane textPane1;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textFieldC;
    private JTextField textFieldA;
    private JButton backBtn;
    private JPanel panel1;
    private JButton showDiagramButton;

    public SquareGeneratorForm(final JFrame parent) {
        super(parent);
        add(panel1);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m = Integer.parseInt(textField1.getText());
                int a = Integer.parseInt(textFieldA.getText());
                int b = Integer.parseInt(textFieldB.getText());
                int Xo = Integer.parseInt(textField3.getText());
                int c = Integer.parseInt(textFieldC.getText());
                sequence = GeneratorSequence.generateSquareSequence(a, b, c, Xo, m);
                showResult(textPane1);
                afterGenerate();
            }

        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.setVisible(true);
            }
        });
        showDiagramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDiagramBtn();
            }
        });
    }
}
