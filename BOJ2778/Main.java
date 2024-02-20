package BOJ2778;

import java.io.*;
import java.util.*;

public class Main {

    static double[] a = new double[3];
    static double[] b = new double[3];
    static double[] c = new double[3];
    static double[] x;
    static double[] y;

    static void setInput(BufferedReader br) throws IOException {
        for(int i = 0; i<3; i++) {
            String[] s = br.readLine().split(" ");
            a[i] = Double.parseDouble(s[0]);
            b[i] = Double.parseDouble(s[1]);
            c[i] = -Double.parseDouble(s[2]);
        }
    }
    
    static void findPoint() {
        x = new double[3];
        y = new double[3];
        for(int i = 0; i<3; i++) {
            int j = (i+1)%3;
            double det = a[i]*b[j]-a[j]*b[i];
            x[i] = b[j]*c[i]-b[i]*c[j];
            x[i] /= det;
            y[i] = a[i]*c[j]-a[j]*c[i];
            y[i] /= det;
        }
    }

    static double calc() {
        return Math.abs((x[0]*y[1]+x[1]*y[2]+x[2]*y[0])-(x[1]*y[0]+x[2]*y[1]+x[0]*y[2]))/2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder("");
        for(int i = 0; i<T; i++) {
            setInput(br);
            // 두 직선의 기울기가 같으면 삼각형이 만들어 지지 않음
            if(a[0]*b[1]==a[1]*b[0] || a[1]*b[2]==a[2]*b[1] || a[2]*b[0]==a[0]*b[2]) {
                answer.append("0.0000");
            } else {
                findPoint();
                if(x[0]==x[1]&&x[1]==x[2]&&x[2]==x[0]) {
                    answer.append("0.0000");
                } else {
                    answer.append(String.format("%.4f", calc()));
                }
            }
            answer.append("\n");
        }
        System.out.print(answer);
        br.close();
    }
    
}
