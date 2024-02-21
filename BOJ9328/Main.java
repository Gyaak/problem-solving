package BOJ9328;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int h, w;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static char[][] map;
    static boolean[] key;
    static boolean[][] visited;

    static void setInput(BufferedReader br) throws IOException {
        key = new boolean[26];

        String[] inputStrings = br.readLine().split(" ");
        h = Integer.parseInt(inputStrings[0]);
        w = Integer.parseInt(inputStrings[1]);
        map = new char[h][];
        visited = new boolean[h][w];

        for(int i = 0; i<h; i++) {
            map[i] = br.readLine().toCharArray();
        }
        String inputString = br.readLine();
        if(inputString.charAt(0)!='0') {
            for(int i = 0; i<inputString.length(); i++) {
                key[inputString.charAt(i)-'a'] = true;
            }
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=h || c>=w)
            return false;
        return true;
    }

    static int BFS() {
        Queue<Node> q = new ArrayDeque<>();
        List<Node> closed = new LinkedList<>();
        int cnt = 0;
        //가장자리에서 벽이 아닌 부분에서 시작 가능
        for(int i = 0; i<h; i++) {
            for(int j = 0; j<w; j++) {
                if(i==0 || j==0 || i==h-1 || j==w-1) {
                    char stat = map[i][j];
                    visited[i][j] = true;
                    if(stat=='.') {
                        q.add(new Node(i, j));
                    } else if (stat=='$') {
                        q.add(new Node(i, j));
                        cnt++;
                    } else if (stat>='a' && stat<='z') {
                        q.add(new Node(i,j));
                        key[stat-'a'] = true;
                    } else if (stat>='A' && stat<='Z') {
                        closed.add(new Node(i, j));
                    } else {
                        visited[i][j] = false;
                    }
                }
            }
        }
        // 현재 가지고 있는 키로 열수 있는 문이 있는지 확인
        Iterator<Node> it = closed.iterator();
        while(it.hasNext()) {
            Node tmpNode = it.next();
            if(key[map[tmpNode.r][tmpNode.c]-'A']) {
                q.add(tmpNode);
                it.remove();
            }
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0; i<4; i++) {
                int curR = cur.r + dr[i];
                int curC = cur.c + dc[i];
                // 맵 밖으로 나가거나 이미 방문한 위치는 패스
                if(!isValid(curR, curC) || visited[curR][curC])
                    continue;

                char stat = map[curR][curC];
                // 키를 발견한 경우
                if(stat>='a' && stat<='z') {
                    key[stat-'a'] = true;
                    it = closed.iterator();
                    while(it.hasNext()) {
                        Node tmpNode = it.next();
                        if(key[map[tmpNode.r][tmpNode.c]-'A']) {
                            q.add(tmpNode);
                            it.remove();
                        }
                    }
                    q.add(new Node(curR, curC));
                }
                // 문을 발견한 경우
                else if (stat>='A' && stat<='Z') {
                    if(key[stat-'A'])
                        q.add(new Node(curR, curC));
                    else
                        closed.add(new Node(curR,curC));
    
                }
                // 돈이나 빈 공간을 발견한 경우
                else if (stat=='$' || stat=='.') {
                    if(stat == '$') cnt++;
                    q.add(new Node(curR, curC));
                }
                visited[curR][curC] = true;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder("");
        for(int i = 0; i<T; i++) {
            setInput(br);
            answer.append(BFS()+"\n");
        }
        System.out.print(answer);
    }
}
