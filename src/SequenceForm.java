import javax.swing.*;

public class SequenceForm extends JFrame {
    protected JFrame parent;
    protected double[] sequence;
    protected int m;

    public boolean disposeAfterGenerate;

    public SequenceForm(JFrame parent){
        this.parent = parent;
        disposeAfterGenerate = false;
        setVisible(true);
        setBounds(100, 100, 500, 630);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void onDiagramBtn(){
        setVisible(false);
        new DiagramForm(sequence, this);
    }
    
    public void onBackBtn(){
        dispose();
        parent.setVisible(true);        
    }

    public void showResult(JTextPane textPane1) {
        String str = "";
        for (double a : sequence) str += (a)/((double)m) + " ";

        textPane1.setText(str);
    }
    
    public void afterGenerate(){
        showPeriod();
        if(disposeAfterGenerate) {
            dispose();
            parent.setVisible(true);
            if(parent instanceof GeneratorMForm)
                ((GeneratorMForm)parent).initSequence();
        }
    }

    public double[] getSequence() {
        return sequence;
    }
    public void setSequence(double[] sequence) {
        this.sequence = sequence;
    }
    public int getM() {
        return m;
    }   
    public void showPeriod(){
        JOptionPane.showMessageDialog(null, GeneratorSequence.findPeriod(sequence));        
    }
    public void setDisposeAfterGenerate(boolean disposeAfterGenerate) {
        this.disposeAfterGenerate = disposeAfterGenerate;
    }
}
