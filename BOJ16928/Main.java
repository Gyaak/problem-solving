// package BOJ16928;

// import java.io.*;
// import java.util.*;

// public class Main {
    
//     static final int SIZE = 100;

//     static int N, M;
//     static List<Integer>[] graph;

//     static void setInput() throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         graph = new List[SIZE];
//         for(int i = 0; i<SIZE; i++) {
//             graph[i] = new ArrayList<>();
//         }
//         for(int i = 0 ; i<N+M; i++) {
//             st = new StringTokenizer(br.readLine());
//             int from = Integer.parseInt(st.nextToken()) - 1;
//             int to = Integer.parseInt(st.nextToken()) - 1;
//             graph[from].add(to);
//         }
//     }

//     static int BFS() {
        
//     }

// }
