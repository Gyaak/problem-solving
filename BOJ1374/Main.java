package BOJ1374;

import java.io.*;
import java.util.*;

public class Main {

    static class Lecture implements Comparable<Lecture> {
        int st, ed;

        Lecture(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.st==o.st)   return this.ed-o.ed;
            else                return this.st-o.st;
        }
    }

    static int N;
    static Lecture[] lectures;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N];
        for(int i = 0; i<N; i++) {
            String[] inputStrings = br.readLine().split(" ");
            int st = Integer.parseInt(inputStrings[1]);
            int ed = Integer.parseInt(inputStrings[2]);
            lectures[i] = new Lecture(st, ed);
        }
        Arrays.sort(lectures);
        br.close();
    }

    static int findNum() {
        int maxRoom = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<N; i++) {
            int curTime = lectures[i].st;
            while(!pq.isEmpty() && pq.peek()<=curTime) {
                pq.poll();
            }
            pq.add(lectures[i].ed);
            maxRoom = Math.max(maxRoom, pq.size());
        }
        return maxRoom;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(findNum());
    }
}
