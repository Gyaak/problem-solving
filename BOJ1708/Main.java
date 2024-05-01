package BOJ1708;

import java.io.*;
import java.util.*;

public class Main {
    

    static class Point{
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + (this.x) + ", " + (this.y) + ")";
        }
    }

    static int N;
    
    static Point[] points;
    static Point startPoint;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        startPoint = new Point(Long.MAX_VALUE, Long.MAX_VALUE);
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        Arrays.sort(points, (p1, p2) -> {
            if(p1.y==p2.y)
                return (int)(p1.x-p2.x);
            else
                return (int)(p1.y-p2.y);
        });
        
        for(int i = N-1; i>=0; i--) {
            points[i].x -= points[0].x;
            points[i].y -= points[0].y;
        }
        
        Arrays.sort(points, 1, N, (Point p1, Point p2)-> {
            long val = -p1.y*p2.x+p2.y*p1.x;
            if(val>0)       return -1;
            else if(val<0)  return 1;
            else {
                long d1 = p1.x*p1.x+p1.y*p1.y;
                long d2 = p2.x*p2.x+p2.y*p2.y;
                return d1>d2?1:-1;
            }
        });

    }

    static long CCW(Point p1, Point p2, Point p3) {
        return p1.x*p2.y+p2.x*p3.y+p3.x*p1.y - p2.x*p1.y-p3.x*p2.y-p1.x*p3.y;
    }

    static int solve() {
        Deque<Point> stack = new ArrayDeque<>();
        stack.addLast(points[0]);
        stack.addLast(points[1]);
        for(int i = 2; i<N; i++) {
            Point cur = stack.pollLast();
            Point prev = stack.pollLast();
            while(CCW(prev, cur, points[i])<=0) {
                cur = prev;
                prev = null;

                if(stack.isEmpty())  break;
                prev = stack.pollLast();
            }
            if(prev!=null)
            stack.addLast(prev);
            stack.addLast(cur);
            stack.addLast(points[i]);
        }
        Point cur = stack.pollLast();
        Point prev = stack.pollLast();
        while(CCW(prev, cur, points[0])<=0) {
            cur = prev;
            if(stack.isEmpty())  break;
            prev = stack.pollLast();
        }
        if(prev!=null)
        stack.addLast(prev);
        stack.addLast(cur);

        return stack.size();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }


}
