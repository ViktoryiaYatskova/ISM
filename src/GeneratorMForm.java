import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratorMForm extends SequenceForm {
    private JTextPane textPane1;
    private JPanel panel1;
    private JButton generateButton;
    private JButton backButton;
    private JButton generateXBtn;
    private JButton generateYBtn;
    private JTextPane panelXn;
    private JTextPane panelYn;
    private JButton showDiagramButton;
    private JButton momentsButton;
    private double[] Yn;
    private double[] Xn;

    private int m2;
    private int m1;

    private int K = 64;
    private JFrame self;
    
    private SequenceForm sequenceXn;
    private SequenceForm sequenceYn;

    public GeneratorMForm(final JFrame parent) {
        super(parent);
        self = this;
        this.add(panel1);        
        GeneratorSequence.setN(K);
        
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                if(Xn != null && Yn != null) {                    
                    sequence = GeneratorSequence.generateMSequence(Xn, Yn, m2, K);
                    showResult(textPane1);
                    showPeriod();
                }
            }                   
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.setVisible(true);
            }
        });        
        generateXBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sequenceXn = new LinearGeneratorForm(self);
                sequenceXn.setDisposeAfterGenerate(true);
            }
        });
        generateYBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sequenceYn = new SquareGeneratorForm(self);
                sequenceYn.setDisposeAfterGenerate(true);
            }
        });
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

    public void initSequence(){
        if(sequenceYn != null) {
            Yn = sequenceYn.getSequence();
            if(Yn != null) {
                m = m2 = sequenceYn.getM();
                GeneratorSequence.showResult(Yn, panelYn, m2);
            }
        }         
        if(sequenceXn != null) {
            Xn = sequenceXn.getSequence();
            if(Xn != null) {
                m1 = sequenceXn.getM();
                GeneratorSequence.showResult(Xn, panelXn, m1);
            }
        }
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }
    public void setM2(int m) {
        this.m2 = m;
    }
}
