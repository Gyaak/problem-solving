package BOJ19237;

import java.io.*;
import java.util.*;

public class Main {

    static class Shark {
        int num, r, c, curDirc;
        int[][] dirc;
        boolean isLive;

        Shark(int num) {
            this.num = num;
            this.isLive = true;
        }
    }

    static class Scent {
        int sharkNum, time;
    }
    
    static int N, M, k;
    static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
    static int[][] map;
    static List<Shark> sharks;
    static Shark[][] pos;
    static Scent[][] scents;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        sharks = new LinkedList<>();
        pos = new Shark[N][N];
        for(int i = 1; i<=M; i++) {
            sharks.add(new Shark(i));
        }
        scents = new Scent[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>0) {
                    Shark s = sharks.get(map[i][j]-1);
                    s.r = i;
                    s.c = j;
                }
                scents[i][j] = new Scent();
            }
        }
        st = new StringTokenizer(br.readLine());
        for(Shark s : sharks) {
            s.curDirc = Integer.parseInt(st.nextToken()) - 1;
        }
        for(Shark shark : sharks) {
            int[][] dirc = new int[4][4];
            for(int i = 0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<4; j++) {
                    dirc[i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
            shark.dirc = dirc;
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N) {
            return false;
        }
        return true;
    }

    static void spreadScent(int curTime) {
        for(Shark shark : sharks) {
            Scent curScent = scents[shark.r][shark.c];
            curScent.sharkNum = shark.num;
            curScent.time = curTime;
        }
    }

    static void moveShark(Shark shark, int curTime) {
        Scent emptyScent = null;
        int myR = -1, myC = -1, myD = -1;
        for(int i = 0; i<4; i++) {
            int d = shark.dirc[shark.curDirc][i];
            int tmpR = shark.r + dr[d];
            int tmpC = shark.c + dc[d];
            if(isValid(tmpR, tmpC)) {
                Scent scent = scents[tmpR][tmpC];
                if(curTime - scent.time > k) {
                    scent.sharkNum = 0;
                }
                if(scent.sharkNum == 0) {
                    emptyScent = scent;
                    shark.r = tmpR;
                    shark.c = tmpC;
                    shark.curDirc = d;
                    break;
                }
                if(scent.sharkNum == shark.num && myR == -1) {
                    myR = tmpR;
                    myC = tmpC;
                    myD = d;
                }
            }
        }
        if(emptyScent == null) {
            shark.r = myR;
            shark.c = myC;
            shark.curDirc = myD;
        }

        if(pos[shark.r][shark.c] != null) {
            if(pos[shark.r][shark.c].num>shark.num) {
                pos[shark.r][shark.c].isLive =false;
                pos[shark.r][shark.c] = shark;
            } else {
                shark.isLive = false;
            }
        } else {
            pos[shark.r][shark.c] = shark;
        }
    }

    static void excuteStep(int curTime) {
        for(int i = 0; i<N; i++) {
            Arrays.fill(pos[i],null);
        }
        for(Shark shark : sharks) {
            moveShark(shark, curTime);
        }
        Iterator<Shark> itr = sharks.iterator();
        while(itr.hasNext()) {
            Shark shark = itr.next();
            if(!shark.isLive) {
                itr.remove();
            }
        }
        spreadScent(curTime);
    }

    static int solve() {
        spreadScent(0);
        for(int i = 1; i<=1000+1; i++) {
            if(sharks.size() == 1) {
                return i - 1;
            }
            excuteStep(i);
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
