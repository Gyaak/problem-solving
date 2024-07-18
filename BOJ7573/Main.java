package BOJ7573;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Fish {
        int r, c;

        Fish(int r, int c) {
            this.r = r;
            this.c =c;
        }

        public boolean isTrapped(int r1, int c1, int r2, int c2) {
            if(r1<=this.r && this.r<=r2 && c1<=this.c && this.c<=c2) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    static int N, I, M;
    static int[] xPoints, yPoints;
    static Fish[] fishes;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken())>>1;
        M = Integer.parseInt(st.nextToken());
        fishes = new Fish[M];
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            fishes[i] = new Fish(r, c);
        }
    }

    static int solve() {
        int num = 0;
        for(Fish fish : fishes) {
            for(int w = 1; w<I; w++) {
                int h = I - w;
                for(int i = 0; i<=w; i++) {
                    int tmp = 0;
                    int r1 = fish.r;
                    int c1 = fish.c - i;
                    int r2 = fish.r + h;
                    int c2 = fish.c - i + w;
                    for(Fish f : fishes) {
                        if(f.isTrapped(r1, c1, r2, c2))
                            tmp++;
                    }
                    num = Math.max(num,tmp);
                }
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
