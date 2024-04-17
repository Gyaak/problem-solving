package BOJ1047;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Tree {
        int x, y, len;

        Tree(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    static int N;
    static Tree[] trees;
    static List<Integer> xList;
    static List<Integer> yList;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        trees = new Tree[N];
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            trees[i] = new Tree(x, y, len);
            xSet.add(x);
            ySet.add(y);
        }
        Arrays.sort(trees,(i,j)->(j.len-i.len));
        xList = new ArrayList<>(xSet);
        yList = new ArrayList<>(ySet);
        Collections.sort(xList);
        Collections.sort(yList);
        br.close();
    }

    static boolean isValid(int x1, int x2, int y1, int y2, Tree target) {
        if(xList.get(x1)<=target.x && target.x <=xList.get(x2) && yList.get(y1)<=target.y && target.y<=yList.get(y2)) {
            return true;
        }
        else {
            return false;
        }
    }

    static int solve() {
        int num = Integer.MAX_VALUE;

        for(int x1 = 0; x1<xList.size(); x1++) {
            for(int x2 = x1; x2<xList.size(); x2++) {
                for(int y1 = 0; y1<yList.size(); y1++) {
                    for(int y2 = y1; y2<yList.size(); y2++) {
                        int dist = 2*(xList.get(x2)-xList.get(x1)+yList.get(y2)-yList.get(y1));
                        int tmpNum = 0;
                        for(int i = 0; i<N; i++) {
                            if(!isValid(x1, x2, y1, y2, trees[i])) {
                                dist -= trees[i].len;
                                tmpNum++;
                            }
                        }
                        for(int i = 0; i<N; i++) {
                            if(isValid(x1, x2, y1, y2, trees[i])) {
                                if(dist<=0)
                                    break;
                                dist -= trees[i].len;
                                tmpNum++;
                            }
                        } 
                        num = Math.min(num,tmpNum);
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
