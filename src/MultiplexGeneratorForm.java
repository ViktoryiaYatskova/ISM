import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MultiplexGeneratorForm extends SequenceForm{
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton generateButton;
    private JButton backButton;
    private JButton showDiagramButton;

    public double[] getSequence() {
        return sequence;
    }

    public void setSequence(double[] sequence) {
        this.sequence = sequence;
    }


    public MultiplexGeneratorForm(final JFrame parent) {
        super(parent);
        this.add(panel1);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = Integer.parseInt(textField1.getText());
                int a = Integer.parseInt(textField2.getText());
                int Xo = Integer.parseInt(textField3.getText());
                sequence = GeneratorSequence.generateMultiplexSequence(a, Xo, m);
                showResult(textPane1);
                afterGenerate();
            }
            
        });
        backButton.addActionListener(new ActionListener() {
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
