package BOJ25689;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx, num, move; // num = 127 이면 모든 칸 다 방문 = 종료조건

        Node(int idx, int num, int move) {
            this.idx = idx;
            this.num = num;
            this.move = move;
        }
    }
    
    static int R, C;
    static List<Integer>[] graph = new List[25];
    static int[][] map  = new int[5][5];
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static boolean[][] visited = new boolean[25][1<<7];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i<25; i++) {
            graph[i] = new ArrayList<>();
            makeGraph(i);
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=5 || c>=5)
            return false;
        return true;
    }

    static void makeGraph(int n) {
        for(int i = 0; i<4; i++) {
            int r = n/5;
            int c = n%5;
            for(int j = 0; j<5; j++) {
                r += dr[i];
                c += dc[i];
                if(!isValid(r, c) || map[r][c]==-1) {
                    if(j>1)
                        graph[n].add(5*(r-dr[i])+(c-dc[i]));
                    break;
                }
                if(j==0) {
                    graph[n].add(5*r+c);
                    if(map[r][c]==7)
                        break;
                }
                if(map[r][c]==7) {
                    graph[n].add(5*r+c);
                    break;
                }
            }    
        }
    }

    static int BFS() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(5*R+C,1,0));
        visited[5*R+C][1] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i : graph[cur.idx]) {
                int tmpNum = cur.num;
                if(0<map[i/5][i%5]&&map[i/5][i%5]<7)
                    tmpNum |= (1<<map[i/5][i%5]);
                if(tmpNum==127) {
                    return cur.move+1;
                }
                if(visited[i][tmpNum])
                    continue;
                q.add(new Node(i, tmpNum, cur.move+1));
                visited[i][tmpNum] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
