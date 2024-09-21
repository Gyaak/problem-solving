package BOJ3019;

import java.io.*;
import java.util.*;

public class Main {
    static int C, P;
    static int[] H;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        H = new int[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<C; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int block1() {
        int cnt = C;
        for(int i = 0; i<C-3; i++) {
            if(H[i]==H[i+1] && H[i+1]==H[i+2] && H[i+2]==H[i+3]) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block2() {
        int cnt = 0;
        for(int i = 0; i<C-1; i++) {
            if(H[i]==H[i+1]) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block3() {
        int cnt = 0;
        for(int i = 0; i<C-2; i++) {
            if(H[i]==H[i+1] && H[i+1]+1==H[i+2]) {
                cnt++;
            }
        }
        for(int i = 0; i<C-1; i++) {
            if(H[i]==H[i+1]+1) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block4() {
        int cnt = 0;
        for(int i = 0; i<C-2; i++) {
            if(H[i]==H[i+1]+1 && H[i+1]==H[i+2]) {
                cnt++;
            }
        }
        for(int i = 0; i<C-1; i++) {
            if(H[i]+1==H[i+1]) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block5() {
        int cnt = 0;
        for(int i = 0; i<C-2; i++) {
            if(H[i]==H[i+1] && H[i+1]==H[i+2]) {
                cnt++;
            }
            if(H[i]==H[i+1]+1 && H[i+1]+1==H[i+2]) {
                cnt++;
            }
        }
        for(int i = 0; i<C-1; i++) {
            if(H[i]==H[i+1]+1) {
                cnt++;
            }
            if(H[i]+1==H[i+1]) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block6() {
        int cnt = 0;
        for(int i = 0; i<C-1; i++) {
            if(H[i]==H[i+1]) {
                cnt++;
            }
            if(H[i]==H[i+1]+2) {
                cnt++;
            }
        }
        for(int i = 0; i<C-2; i++) {
            if(H[i]==H[i+1] && H[i+1]==H[i+2]) {
                cnt++;
            }
            if(H[i]+1==H[i+1] && H[i+1]==H[i+2]) {
                cnt++;
            }
        }
        return cnt;
    }

    static int block7() {
        int cnt = 0;
        for(int i = 0; i<C-1; i++) {
            if(H[i]==H[i+1]) {
                cnt++;
            }
            if(H[i]+2==H[i+1]) {
                cnt++;
            }
        }
        for(int i = 0; i<C-2; i++) {
            if(H[i]==H[i+1] && H[i+1]==H[i+2]) {
                cnt++;
            }
            if(H[i]==H[i+1] && H[i+1]==H[i+2]+1) {
                cnt++;
            }
        }
        return cnt;
    }

    static int solve() {
        switch (P) {
            case 1: return block1();
            case 2: return block2();
            case 3: return block3();
            case 4: return block4();
            case 5: return block5();
            case 6: return block6();
            case 7: return block7();
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
