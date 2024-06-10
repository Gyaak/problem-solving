package BOJ2564;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Point {
        int r, c, dirc;
        Point(int r, int c, int dirc) {
            this.r = r;
            this.c = c;
            this.dirc = dirc;
        }
    }

    static int R, C, N;
    static Point cur;
    static List<Point> stores = new ArrayList<>();

    static Point convert(int d, int p) {
        if(d==1)        return new Point(0,p,d);
        else if(d==2)   return new Point(R,p,d);
        else if(d==3)   return new Point(p,0,d);
        else            return new Point(p,C,d);
    }

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            stores.add(convert(d, p));
        }
        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        cur = convert(d, p);
    }

    static int findDist(Point p1, Point p2) {
        int dist = Integer.MAX_VALUE;
        int dist1 = p1.r+p1.c;
        int dist2 = p2.r+p2.c;
        if(p1.dirc==p2.dirc) {
            return Math.abs(dist1-dist2);
        }
        if((p1.dirc==2&&p2.dirc==3)||(p1.dirc==3&&p2.dirc==2)||(p1.dirc==1&&p2.dirc==4)||(p1.dirc==4&&p2.dirc==1)) {
            dist = Math.min(dist, Math.abs(dist1-dist2));
        } else {
            dist = Math.min(dist, dist1+dist2);
            dist = Math.min(dist, 2*(R+C)-(dist1+dist2));
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = 0;
        for(Point s : stores) {
            ans += findDist(cur, s);
        }
        System.out.println(ans);
    }
}