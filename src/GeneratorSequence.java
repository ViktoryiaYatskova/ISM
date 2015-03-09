import javax.swing.*;

public class GeneratorSequence {
    static int N = 300;
    
    public static int getN() {
        return N;
    }
    public static void setN(int n) {
        N = n;
    }
    
    static public int[] generateLinearSequence(int a, int c, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        int[] res = new int[n];
        res[0] = mod(a*Xo + c, m) ;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod(a*res[i] + c, m) ;
        }
        return res;
    }

    static public int[] generateMultiplexSequence(int a, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        int[] res = new int[n];
        res[0] = mod(a*Xo,m) ;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod(a*res[i], m) ;
        }
        return res;
    }

    static public int[] generateSquareSequence(int a, int b, int c, int Xo, int m){
        int n = Math.min(Math.max(m, N), 252);
        int[] res = new int[n];
        res[0] = mod(a*Xo + c, m) ;
        for(int i = 0; i < n-1; i++){
            res[i+1] = mod(a*res[i]*res[i] + b*res[i] + c, m);
        }
        return res;
    }

    static public int[] generateMSequence(int[] Xn, int[] Yn, int m, int k){
        int[] V = new int[k];
        int[] res = new int[k];
        int periodXn = findPeriod(Xn);

        System.arraycopy(Xn, 0, V, 0, k);
        
        for(int i = 0; i < k; i++){
            int j = (Yn[i]*k)%m;
            if(j >= V.length) j %= V.length;
            res[i] = V[j];
            V[j] = get(Xn, periodXn, i+k);//(int)(Math.random()*k)
        }
        return res;
    }

    private static int get(int[] Xn, int period, int i) {
        while(i >= Xn.length)
            i -= period;
        return Xn[i];
    }

    public static void showResult(int[] sequence, JTextPane panel, int m) {
        String str = "";
        int length = Math.max(findPeriod(sequence)*2, 20);
        for (int i = 0; i < length; i++) str += sequence[i] + " ";
        panel.setText(str);
    }
    
    public static int findPeriod(int[] Xn){
        boolean begin;
        int period = -1, prevPeriod = -1, k, beg = 0, step = 1;
        for(k = 0; k < 1000; k++) {
            begin = false;
            step++;
            for (int i = 0, j = 0; i < Xn.length && j < Xn.length; i += step, j++) {
                if (Xn[i] == Xn[j] && i != j) {
                    if (!begin) {
                        begin = true;
                        beg = j;
                    } else {
                        if (prevPeriod == -1){
                            prevPeriod = j - beg;
                            beg = j;
                        }
                        else {
                            if (period == prevPeriod) {
                                return checkPeriod(Xn, period, j);
                            }
                            else
                                period = j - beg;
                        }
                    }
                }
            }
        }
        return period;
    }

    private static int checkPeriod(int[] Xn, int period, int startIndex) {
        for(int i = startIndex+period; i < Xn.length; i++){
            if(Xn[i] != Xn[i-period]) {
                System.out.println(Xn[i] + " != " + Xn[i - period] + " indexes: " + i + " & " + (i - period) + "\nperiod != " + period);
                return -1;
            }
        }
        return period;
    }

    public static double findAverage(int[] Xn){
        int period = findPeriod(Xn);
        int length = Xn.length;
        int res = 0;
        if(period > 0)
            length = period;
        for(int i = 0; i < length; i ++){
            res += Xn[i];
        }
        return res/(int)length;
    }

    public static double getCentralMoment(int[] sequence, int order) {
        int period = findPeriod(sequence);
        double average = findAverage(sequence);
        double res = 0;
        int length = sequence.length;
        if(period > 0)
            length = period;
        
        for(int i = 0; i < length; i++){
            res += Math.pow(sequence[i]-average, order);
        }
        
        return res/(double)length;
    }

    public static double getStartMoment(int[] sequence, int order) {
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
    
    static int mod(int X, int m){
        return (/*X < m ? X: */X%m);
    }
}
