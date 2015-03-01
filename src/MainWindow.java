import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    private JButton linearGeneratorBtn;
    private JButton squareGeneratorBtn;
    private JPanel panel;
    private JButton generatorMBtn;
    private JButton multiplexGeneratorBtn;
    private JFrame self;

    public MainWindow() {
        super();
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        self = this;
        
        linearGeneratorBtn.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LinearGeneratorForm(self);
            }
        });
        squareGeneratorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SquareGeneratorForm(self);
            }
        });
        generatorMBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GeneratorMForm(self);
            }
        });
        multiplexGeneratorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MultiplexGeneratorForm(self);
            }
        });
    }
}
