import javax.swing.*;

public class GeneratorSequence {
    static int N = 100;
    
    public static int getN() {
        return N;
    }
    public static void setN(int n) {
        N = n;
    }
    
    static public double[] generateLinearSequence(int a, int c, int Xo, int m){
        double[] res = new double[N];
        res[0] = Xo;
        for(int i = 0; i < N-1; i++){
            res[i+1] = Math.floorMod((int)(a*res[i] + c), m);
        }
        return res;
    }

    static public double[] generateMultiplexSequence(int a, int Xo, int m){
        double[] res = new double[N];
        res[0] = Xo;
        for(int i = 0; i < N-1; i++){
            res[i+1] = Math.floorMod((int) (a*res[i]), m);
        }
        return res;
    }

    static public double[] generateSquareSequence(int a, int b, int c, int Xo, int m){
        double[] res = new double[N];
        res[0] = Xo;
        for(int i = 0; i < N-1; i++){
            res[i+1] = Math.floorMod((int) (a*res[i]*res[i] + b*res[i] + c), m);
        }
        return res;
    }

    static public double[] generateMSequence(double[] Xn, double[] Yn, int m, int k){
        double[] V = new double[k];
        double[] res = new double[k];

        System.arraycopy(Xn, 0, V, 0, k);
        
        for(int i = 0; i < k; i++){
            int j = (int)Yn[i]*k/m;
            res[i] = V[j];
            V[j] = Xn[(int)(Math.random()*k)];
        }
        return res;
    }

    public static void showResult(double[] sequence, JTextPane panel, int m) {
        String str = "";
        for (double a : sequence) str += a + " ";
        panel.setText(str);
    }
    
    public static int findPeriod(double[] Xn){
        boolean begin = false; int period = 0;
        for(int i = 0, j = 0; i < Xn.length && j < Xn.length; i += 2, j++){
            if(Xn[i] == Xn[j]){
                if(!begin) {
                    begin = true;
                    period = j;
                }
                else {
                    period = j - period;
                    return period;
                }
            }   
        }
        return -1;
    }
}
