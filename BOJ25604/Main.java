package BOJ25604;

import java.io.*;
import java.util.*;

public class Main {

    static class Item {
        int num, m, r;
        Item(int num, int m, int r) {
            this.num = num;
            this.m = m;
            this.r = r;
        }
    }
    
    static int N, M, T;
    static int[][] ans;
    static Deque<Item>[] airplanes;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        airplanes = new Deque[2];
        airplanes[0] = new ArrayDeque<>();
        airplanes[1] = new ArrayDeque<>();
        ans = new int[N][2];
        for(int i = 0; i<N; i++) {
            ans[i][0] = -1;
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int m  = Integer.parseInt(st.nextToken());
            int r  = Integer.parseInt(st.nextToken());
            airplanes[d].addLast(new Item(i, m, r));
        }
    }

    static void solve() {
        int pos = 0;
        int time = 0;
        int loaded = M;
        while(!airplanes[0].isEmpty() || !airplanes[1].isEmpty()) {
            int diff1 = Integer.MAX_VALUE;
            int diff2 = Integer.MAX_VALUE;
            int nextPos = ~pos & 1;
            if(!airplanes[pos].isEmpty()) {
                diff1 = Math.max(airplanes[pos].peekFirst().r-time,0);
            } else {
                // 현재 비행기가 모두 분해되면 반대쪽 비행기로 바로 이동
                pos = nextPos;
                time += T;
                loaded = M;
                continue;
            }
            if(!airplanes[nextPos].isEmpty()) {
                diff2 = Math.max(airplanes[nextPos].peekFirst().r-time,0);
            }
            if(diff1>0) {
                // 짐을 싣고 있고, 현재 위치에 준비된 짐이 없다면, 반대편으로 이동
                if(loaded != M) {
                    pos = nextPos;
                    time += T;
                    loaded = M;
                }
                // 현재위치에 준비된 짐이 없고 반대편에 준비된 짐이 있으면, 반대편으로 이동
                else if(diff2 == 0) {
                    pos = nextPos;
                    time += T;
                } else {
                    // 양 비행기 모두 부품이 준비되지 않은 경우
                    // 부품이 먼저 준비된 쪽으로 이동
                    time += Math.min(diff1, diff2);
                }
                continue;
            }
            // 더 이상 적재할 수 없으면 반대편으로 이동
            if(loaded == 0) {
                time += T;
                loaded = M;
                pos = nextPos;
                continue;
            }
            Item item = airplanes[pos].peekFirst();
            if(ans[item.num][0] == -1) {
                ans[item.num][0] = time;
            }
            if(item.m <= loaded) {
                ans[item.num][1] = time + T;
                loaded -= item.m;
                airplanes[pos].pollFirst();
            }
            // 적재량을 꽉 채웠으면 반대편으로 이동
            else {
                item.m -= loaded;
                time += T;
                loaded = M;
                pos = nextPos;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        solve();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            sb.append(ans[i][0] + " " + ans[i][1] + "\n");
        }
        System.out.print(sb);
    }
}
