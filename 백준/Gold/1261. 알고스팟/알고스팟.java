import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int x, y, n;

        Node(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }

    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] maze, visited;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maze = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i<N; i++) {
            String s = br.readLine();
            for (int j = 0; j<M; j++) {
                maze[i][j] = s.charAt(j) == '1';
            }
        }
    }

    static boolean isValid(int x, int y) {
        if(x<0)    return false;
        if(x>=N)   return false;
        if(y<0)    return false;
        if(y>=M)   return false;
        return true;
    }

    static int BFS() {
        Deque<Node> dq = new ArrayDeque<>();
        dq.addLast(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!dq.isEmpty()) {
            Node curNode = dq.pollFirst();

            if(curNode.x == N - 1 && curNode.y == M - 1) {
                return curNode.n;
            }

            for(int i = 0; i<4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if(!isValid(nx, ny))    continue;
                if(visited[nx][ny])     continue;
                
                visited[nx][ny] = true;
                if(maze[nx][ny]) {
                    Node node = new Node(nx, ny, curNode.n + 1);
                    dq.addLast(node);
                }
                else {
                    Node node = new Node(nx, ny, curNode.n);
                    dq.addFirst(node);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException{
        setInput();
        System.out.println(BFS());
    }
    
}
