package BOJ1783;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    static int calc() {
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return Math.min((M - 1) / 2 + 1, 4);
        } else {
            if (M < 7) {
                return Math.min(M,4);
            } else {
                return M - 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(calc());
    }
}
