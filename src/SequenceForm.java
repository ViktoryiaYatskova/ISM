import javax.swing.*;

public class SequenceForm extends JFrame {
    private static final int MAX_NUM_SHOW = 30;
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
        
        for(int i = 0; i < Math.min(MAX_NUM_SHOW, sequence.length); i++)
            str += sequence[i] + " ";
        
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
    
    public void showMomentDialog(){
        new MomentDlg(sequence);
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
        int period = GeneratorSequence.findPeriod(sequence);
        if(period > 0)
            JOptionPane.showMessageDialog(null, period);
        else
            JOptionPane.showMessageDialog(null, period, "Wow! Generator is too great. Period not found", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setDisposeAfterGenerate(boolean disposeAfterGenerate) {
        this.disposeAfterGenerate = disposeAfterGenerate;
    }   
}
