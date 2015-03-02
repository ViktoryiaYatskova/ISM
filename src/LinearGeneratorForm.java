import sun.nio.cs.Surrogate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Viktoria on 16.02.2015.
 */
public class LinearGeneratorForm extends SequenceForm {
    private JTextPane textPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textFieldB;
    private JButton generateButton;
    private JButton backButton;
    private JButton showDiagramButton;
    private JButton momentsButton;

    public LinearGeneratorForm(final JFrame parent) {
        super(parent);
        this.add(panel1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m = Integer.parseInt(textField1.getText());
                int a = Integer.parseInt(textField2.getText());
                int Xo = Integer.parseInt(textField3.getText());
                int b = Integer.parseInt(textFieldB.getText());
                sequence = GeneratorSequence.generateLinearSequence(a, b, Xo, m);
                showResult(textPane1);
                afterGenerate();
            }
            
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBackBtn();
            }
        });
        this.setBounds(100, 100, 500, 300);
        this.setVisible(true);
        
        showDiagramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDiagramBtn();
            }
        });
        momentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMomentDialog();
            }
        });
    }
}
