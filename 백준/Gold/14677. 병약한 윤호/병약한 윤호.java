import java.io.*;
import java.util.*;

class Main {

    static class Status {
        int st;
        int ed;
        int time;

        Status(int st, int ed, int time) {
            this.st = st;
            this.ed = ed;
            this.time = time;
        }

        int encode() {
            return st * 10000 + ed;
        }
    }

    static int N;
    static int answer = 0;
    static int[] drugs;
    static Deque<Status> q;
    static boolean[] visited = new boolean[20000000];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 3*Integer.parseInt(br.readLine());
        drugs = new int[N];
        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'L') {
                drugs[i] = 1;
            } else if (str.charAt(i) == 'D') {
                drugs[i] = 2;
            }
        }
        q = new ArrayDeque<>();
    }

    static void BFS() {
        Status initStatus = new Status(0, N-1, 0);
        q.add(initStatus);
        visited[initStatus.encode()] = true;
        while (!q.isEmpty()) {
            Status cur = q.poll();
            int st = cur.st;
            int ed = cur.ed;
            int time = cur.time;

            if (st > ed) {
                answer = N;
                return;
            }
            
            if (drugs[st] == time) {
                Status newStatus = new Status(st+1, ed, (time+1)%3);
                int encoded = newStatus.encode();
                if (!visited[encoded]) {
                    q.add(newStatus);
                    visited[encoded] = true;
                }
            }
            if (drugs[ed] == time) {
                Status newStatus = new Status(st, ed-1, (time+1)%3);
                int encoded = newStatus.encode();
                if (!visited[encoded]) {
                    q.add(newStatus);
                    visited[encoded] = true;
                }
            }

            answer = Math.max(answer, N-1 - (ed-st));
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        BFS();
        System.out.println(answer);
    }
}