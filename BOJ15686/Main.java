package BOJ15686;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] D;
    static List<Integer> chickens, house;

    static int pairToInt(int r, int c) {
        return 100 * r + c;
    }

    static int[] intToPair(int val) {
        int[] pair = new int[2];
        pair[0] = val / 100;
        pair[1] = val % 100;
        return pair;
    }

    static int calcMenhattanDist(int place1, int place2) {
        int[] pair1 = intToPair(place1);
        int[] pair2 = intToPair(place2);
        return Math.abs(pair1[0]-pair2[0]) + Math.abs(pair1[1]-pair2[1]);
    }

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        house = new ArrayList<>();
        chickens = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if(val == 1) {
                    house.add(pairToInt(i, j));
                }
                if(val == 2) {
                    chickens.add(pairToInt(i, j));
                }
            }
        }

        D = new int[house.size()][chickens.size()];
        for(int i = 0; i<house.size(); i++) {
            for(int j = 0; j<chickens.size(); j++) {
                D[i][j] = calcMenhattanDist(house.get(i), chickens.get(j));
            }
        }
    }

    static int calcChickenDist(int houseIdx, int selected) {
        int dist = Integer.MAX_VALUE;
        for(int i = 0; i<chickens.size(); i++) {
            if((selected&(1<<i)) != 0) {
                dist = Math.min(dist, D[houseIdx][i]);
            }
        }
        return dist;
    }

    static int calcCityChickenDist(int selected) {
        int dist = 0;
        for(int houseIdx = 0; houseIdx<house.size(); houseIdx++) {
            dist += calcChickenDist(houseIdx, selected);
        }
        return dist;
    }

    static void DFS(int idx, int depth, int selected) {
        if(depth == M) {
            ans = Math.min(ans, calcCityChickenDist(selected));
            return;
        }
        for(int i = idx; i<chickens.size(); i++) {
            if(chickens.size()-i < M-depth) {
                break;
            }
            DFS(i+1, depth+1, selected|(1<<i));
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        DFS(0, 0, 0);
        System.out.println(ans);
    }
}
