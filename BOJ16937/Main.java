package BOJ16937;

import java.io.*;
import java.util.*;

public class Main {

    static class Sticker {
        int r, c;

        Sticker(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int H, W, N;

    static boolean isPossible(int R, int C, Sticker target) {
        if(target.r<=R && target.c<=C)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        Sticker[] stickers = new Sticker[N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stickers[i] = new Sticker(r, c);
        }

        int ans = 0;
        for(int i = 0; i<N; i++) {
            for(int j = i+1; j<N; j++) {
                int area = stickers[i].r*stickers[i].c + stickers[j].r*stickers[j].c;
                if(area<ans)    continue;
                boolean flag = false;
                if(isPossible(H, W, stickers[i])) {
                    if(isPossible(H-stickers[i].r, W, stickers[j]))
                        flag = true;
                    else if(isPossible(W, H-stickers[i].r, stickers[j]))
                        flag = true;
                    else if(isPossible(H, W-stickers[i].c, stickers[j]))
                        flag = true;
                    else if(isPossible(W-stickers[i].c, H, stickers[j]))
                        flag = true;
                }
                if(!flag && isPossible(W, H, stickers[i])) {
                    if(isPossible(W-stickers[i].r, H, stickers[j]))
                        flag = true;
                    else if(isPossible(H, W-stickers[i].r, stickers[j]))
                        flag = true;
                    else if(isPossible(W, H-stickers[i].c, stickers[j]))
                        flag = true;
                    else if(isPossible(H-stickers[i].c, W, stickers[j]))
                        flag = true;
                }
                if(flag)    ans = Math.max(ans, area);
            }
        }
        bw.write(ans+"\n");
        bw.flush();
        br.close();
    }
}
