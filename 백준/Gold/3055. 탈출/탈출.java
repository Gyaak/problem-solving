import java.util.*;
import java.io.*;

class Main {

    static int R, C;
    static int time = 1;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] forest;
    static boolean[][] visited;
    static List<Queue<Integer>> q_list = Arrays.asList(
        new ArrayDeque<>(),
        new ArrayDeque<>()
    );

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        forest = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (line.charAt(j) == 'D') {
                    end[0] = i;
                    end[1] = j;
                } else if (line.charAt(j) == 'X')
                    forest[i][j] = -1;
                if (line.charAt(j) == '*')
                    forest[i][j] = 1;
            }
        }
    }

    static int xy2int(int x, int y) {
        return x * 100 + y;
    }

    static int[] int2xy(int n) {
        return new int[] {n / 100, n % 100};
    }

    static void spreadWater() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (forest[i][j] == time) {
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && forest[nx][ny] == 0)
                            forest[nx][ny] = time + 1;
                    }
                }
            }
        }
        time++;
        forest[end[0]][end[1]] = 0;
    }

    static boolean search() {
        Queue<Integer> q = q_list.get(time % 2);
        Queue<Integer> next_q = q_list.get((time + 1) % 2);
        while (!q.isEmpty()) {
            int n = q.poll();
            int[] xy = int2xy(n);
            int x = xy[0];
            int y = xy[1];


            // 도착칸에 도착했으면 출력하고 종료
            if (x == end[0] && y == end[1]) {
                System.out.println(time - 1);
                return true;
            }

            // 이미 물에 차 있으면 패스
            if (forest[x][y] > 0)
                continue;

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && forest[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    next_q.add(xy2int(nx, ny));
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        visited[start[0]][start[1]] = true;
        q_list.get(time % 2).add(xy2int(start[0], start[1]));
        while(q_list.get(0).size() > 0 || q_list.get(1).size() > 0) {
            if (search()) {
                return;
            }
            spreadWater();
        }
        System.out.println("KAKTUS");
    }
}
