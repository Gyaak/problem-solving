package BOJ1186;

import java.io.*;
import java.util.*;

public class Main {

    static class Rectangle implements Comparable<Rectangle>{
        int x1, y1, x2, y2, num, space;
        Rectangle(int x1, int y1, int x2, int y2, int num) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.num = num;
            this.space = 0;
        }

        @Override
        public int compareTo(Rectangle o) {
            return o.space-this.space;
        }
    }

    static ArrayList<Integer> X = new ArrayList<>();
    static ArrayList<Integer> Y = new ArrayList<>();
    static int N, K;
    static int[][] board;
    static boolean[][] visited;
    static Rectangle[] rectangles;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inpuStrings = br.readLine().split(" ");
        N = Integer.parseInt(inpuStrings[0]);
        K = Integer.parseInt(inpuStrings[1]);

        rectangles = new Rectangle[N];
        for(int i = 1; i<=N; i++) {
            inpuStrings = br.readLine().split(" ");
            int x1 = Integer.parseInt(inpuStrings[0]);
            int y1 = Integer.parseInt(inpuStrings[1]);
            int x2 = Integer.parseInt(inpuStrings[2]);
            int y2 = Integer.parseInt(inpuStrings[3]);
            rectangles[i-1] = new Rectangle(x1, y1, x2, y2, i);
            if(!X.contains(x1)) X.add(x1);
            if(!Y.contains(y1)) Y.add(y1);
            if(!X.contains(x2)) X.add(x2);
            if(!Y.contains(y2)) Y.add(y2);
        }
        
        Collections.sort(X);
        Collections.sort(Y);

        board = new int[X.size()-1][Y.size()-1];
        visited = new boolean[X.size()-1][Y.size()-1];
        for(int i = 0; i<X.size()-1; i++) {
            for(int j = 0; j<Y.size()-1; j++) {
                board[i][j] = (X.get(i+1)-X.get(i))*(Y.get(j+1)-Y.get(j));
            }
        }
        
        br.close();
    }

    static void solve() {
        for(int n = N-1; n>=0; n--) {
            int x1_idx = X.indexOf(rectangles[n].x1);
            int y1_idx = Y.indexOf(rectangles[n].y1);
            int x2_idx = X.indexOf(rectangles[n].x2);
            int y2_idx = Y.indexOf(rectangles[n].y2);

            for(int i = x1_idx; i<x2_idx; i++) {
                for(int j = y1_idx; j<y2_idx; j++) {
                    if(!visited[i][j]) {
                        rectangles[n].space += board[i][j];
                        visited[i][j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        solve();
        String ans = Arrays.stream(rectangles)
                  .sorted()
                  .mapToInt(r -> r.num)
                  .limit(K)
                  .sorted()
                  .collect(StringBuilder::new, (sb, num) -> sb.append(num).append(" "), StringBuilder::append)
                  .toString();

        System.out.println(ans);
    }
}
