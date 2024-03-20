package BOJ9466;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] student;
    static int[] visited;

    static void setInput(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        student = new int[n];
        visited = new int[n];
        Arrays.fill(visited, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++)
            student[i] = Integer.parseInt(st.nextToken())-1;
    }

    static int solve() {
        int cnt =0;
        for(int i = 0; i<n; i++) {
            if(visited[i]==-1) {
                int cur = i;
                while(visited[cur]==-1||visited[cur]==i) {
                    if(visited[cur]==-1) {
                        visited[cur] = i;
                    } else {
                        visited[cur] = -2;
                        cnt++;
                    }
                    cur = student[cur];
                }
            }
        }
        return n-cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            bw.write(solve()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
