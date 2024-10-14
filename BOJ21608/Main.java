package BOJ21608;

import java.io.*;
import java.util.*;

public class Main {

    static class Student {
        int n;
        boolean[] favorite;

        Student(int n, boolean[] favorite) {
            this.n = n;
            this.favorite = favorite;
        }
    }
    static int N;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    static int[][] classroom;
    static List<Student> students;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];
        students = new ArrayList<>();
        for(int i = 0; i<N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            boolean[] favorite = new boolean[N*N+1];
            for(int j = 0; j<4; j++) {
                int f = Integer.parseInt(st.nextToken());
                favorite[f] = true;
            }
            students.add(new Student(n, favorite));
        }
    }

    static boolean isValid(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= N) {
            return false;
        }
        return true;
    }

    static int findPriority(Student student, int r, int c) {

        int p1 = 1_000_000;
        int p2 = 10_000;
        int p3 = 100;
        int priority = p3 * (20 - r) + (20 - c);
        for(int i = 0; i<4; i++) {
            int tmpR = r + dr[i];
            int tmpC = c + dc[i];
            if(isValid(tmpR, tmpC)) {
                int n = classroom[tmpR][tmpC];
                if(student.favorite[n]) {
                    priority += p1;
                }
                if(classroom[tmpR][tmpC] == 0) {
                    priority += p2;
                }
            }
            
        }
        return priority;
    }

    static void findSeat(Student student) {
        int r = 0;
        int c = 0;
        int maxPriority = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if(classroom[i][j] != 0) {
                    continue;
                }
                int priority = findPriority(student, i, j);
                if(maxPriority < priority) {
                    r = i;
                    c = j;
                    maxPriority = priority;
                }
            }
        }
        classroom[r][c] = student.n;
    }

    static int calcSatisfaction() {
        students.sort((s1,s2)->s1.n-s2.n);
        int[] f = {0, 1, 10, 100, 1000};
        int satisfaction = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                int favoriteNum = 0;
                for(int k = 0; k<4; k++) {
                    int r = i + dr[k];
                    int c = j + dc[k];
                    if(isValid(r, c) && students.get(classroom[i][j] - 1).favorite[classroom[r][c]]) {
                        favoriteNum++;
                    }
                }
                satisfaction += f[favoriteNum];
            }
        }
        return satisfaction;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(Student student : students) {
            findSeat(student);
        }
        System.out.println(calcSatisfaction());
    }
}