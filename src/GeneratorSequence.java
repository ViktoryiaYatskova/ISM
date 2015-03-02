import javax.swing.*;

public class GeneratorSequence {
    static int N = 64;
    
    public static int getN() {
        return N;
    }
    public static void setN(int n) {
        N = n;
    }
    
    static public double[] generateLinearSequence(int a, int c, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        double[] res = new double[n];
        res[0] = mod(a*Xo + c, m) /m;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod((int)(a*res[i] + c), m) /m;
        }
        return res;
    }

    static public double[] generateMultiplexSequence(int a, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        double[] res = new double[n];
        res[0] = ((double)(a*Xo% m)) /m;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod((int)(a*res[i]), m) /m;
        }
        return res;
    }

    static public double[] generateSquareSequence(int a, int b, int c, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        double[] res = new double[n];
        res[0] = mod(a*Xo + c, m) /m;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod((int)(a*res[i]*res[i] + b*res[i] + c), m)/m;
        }
        return res;
    }

    static public double[] generateMSequence(double[] Xn, double[] Yn, int m, int k){
        double[] V = new double[k];
        double[] res = new double[k];
        int periodXn = findPeriod(Xn);

        System.arraycopy(Xn, 0, V, 0, k);
        
        for(int i = 0; i < k; i++){
            int j = (int)(Yn[i]*k);
            res[i] = V[j];
            V[j] = get(Xn, periodXn, i+k);//(int)(Math.random()*k)
        }
        return res;
    }

    private static double get(double[] Xn, int period, int i) {
        while(i >= Xn.length)
            i -= period;
        return Xn[i];
    }

    public static void showResult(double[] sequence, JTextPane panel, int m) {
        String str = "";
        int length = Math.max(findPeriod(sequence)*2, 20);
        for (int i = 0; i < length; i++) str += sequence[i] + " ";
        panel.setText(str);
    }
    
    public static int findPeriod(double[] Xn){
        boolean begin;
        int period = -1, prevPeriod = -1, k, beg = 0;
        for(k = 0; k < 1000; k++) {
            begin = false;
            for (int i = 0, j = 0; i < Xn.length && j < Xn.length; i += 2, j++) {
                if ((Math.abs(Xn[i] - Xn[j]) < 10e-7) && i != j) {
                    if (!begin) {
                        begin = true;
                        beg = j;
                    } else {
                        if (prevPeriod == -1)
                            prevPeriod = j - beg;
                        else {
                            if (period == prevPeriod) {
                                return checkPeriod(Xn, period, j);
                            }
                            else
                                period = Math.max(prevPeriod, j - beg);
                        }
                    }
                }
            }
        }
        return period;
    }

    private static int checkPeriod(double[] Xn, int period, int startIndex) {
        for(int i = startIndex+period; i < Xn.length; i++){
            if(Math.abs(Xn[i] - Xn[i-period]) > 10e-7) {
                System.out.println(Xn[i] + " != " + Xn[i - period] + " indexes: " + i + " & " + (i - period) + "\nperiod != " + period);
                return -1;
            }
        }
        return period;
    }

    public static double findAverage(double[] Xn){
        int period = findPeriod(Xn);
        int length = Xn.length;
        double res = 0;
        if(period > 0)
            length = period;
        for(int i = 0; i < length; i ++){
            res += Xn[i];
        }
        return res/(double)length;
    }

    public static double getCentralMoment(double[] sequence, int order) {
        int period = findPeriod(sequence);
        double average = findAverage(sequence),
               res = 0;
        int length = sequence.length;
        if(period > 0)
            length = period;
        
        for(int i = 0; i < length; i++){
            res += Math.pow(sequence[i]-average, order);
        }
        
        return res/(double)length;
    }

    public static double getStartMoment(double[] sequence, int order) {
        int period = findPeriod(sequence);
        double res = 0;
        int length = sequence.length;
        if(period > 0)
            length = period;

        for(int i = 0; i < length; i++){
            res += Math.pow(sequence[i], order);
        }

        return res/(double)length;
    }
    
    static double mod(int X, int m){
        return (double)(/*X < m ? X: */X%m);
    }
}
