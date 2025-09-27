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

    static class Passenger {
        int num;
        int[] start, end;

        Passenger(int num, int sx, int sy, int dx, int dy) {
            this.num = num;
            this.start = new int[]{sx - 1, sy - 1};
            this.end = new int[]{dx - 1, dy - 1};
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M, fuel;
    static int[] taxi; // 현재 택시 위치
    static boolean[][] map; // true : 벽 , false : 빈칸
    static List<Passenger> passengers = new ArrayList<>(); // 남은 승객 리스트

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new boolean[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<N; j++) {
                if(st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new int[]{
            Integer.parseInt(st.nextToken()) - 1,
            Integer.parseInt(st.nextToken()) - 1
        };

        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            Passenger p = new Passenger(
                i,
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
            passengers.add(p);
        }
    }

    static int BFS(int[] start, int[] end) {
        boolean[][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start[0], start[1], 0));

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if(curNode.x == end[0] && curNode.y == end[1]) {
                return curNode.n;
            }

            for(int i = 0; i<4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N )
                    continue;
                
                if(visited[nx][ny] || map[nx][ny])
                    continue;

                q.add(new Node(nx, ny, curNode.n + 1));
                visited[nx][ny] = true;
            }
        }

        return Integer.MAX_VALUE - 1;
    }

    static boolean move() {
        int dist = Integer.MAX_VALUE;
        Passenger target = null;
        
        for(Passenger p : passengers) {
            int d = BFS(taxi, p.start);

            if(target == null) {
                target = p;
                dist = d;
                continue;
            }

            if(d < dist) {
                dist = d;
                target = p;
            }
            // 거리가 같으면 행이 작을수록, 행이 같으면 열이 작을수록 우선순위
            else if(d == dist) {
                int n1 = 100 * p.start[0] + p.start[1];
                int n2 = 100 * target.start[0] + target.start[1];
                if(n1 < n2) {
                    target = p;
                }
            }
        }
        // 다음 승객을 확정하면 남은 승객리스트에서 제거
        passengers.remove(target);

        // 승객을 태우기 위해 승객 위치까지 이동하며 연료 소모
        if(fuel < dist) return false;
        fuel -= dist;

        // 승객을 목적지까지 데려다주고 소모한 연료의 두배를 채움
        int mov_dist = BFS(target.start, target.end);
        if(fuel < mov_dist) return false;
        fuel += mov_dist;

        // 택시 위치 갱신
        taxi = target.end;

        return true;
    }

    static boolean solve() {
        while(passengers.size() > 0) {
            if(!move()) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        if(solve()) System.out.println(fuel);
        else        System.out.println(-1);
    }
}
