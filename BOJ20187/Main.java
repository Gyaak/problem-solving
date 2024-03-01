package BOJ20187;

import java.io.*;

public class Main {
    static int k;

    static int[][] unfold(int[][] cur, char direction) {
        int[][] paper;

        if(direction=='U' || direction=='D') {
            paper = new int[2*cur.length][cur[0].length];
            if(direction=='U') {
                for(int i = 0; i<cur.length; i++) {
                    for(int j = 0; j<cur[0].length; j++) {
                        paper[i][j] = cur[i][j];
                        paper[2*cur.length-1-i][j] = (cur[i][j]+2)%4;
                    }
                }
            } else {
                for(int i = 0; i<cur.length; i++) {
                    for(int j = 0; j<cur[0].length; j++) {
                        paper[cur.length+i][j] = cur[i][j];
                        paper[cur.length-1-i][j] = (cur[i][j]+2)%4;
                    }
                }
            }
        } else {
            paper = new int[cur.length][2*cur[0].length];
            if(direction=='L') {
                for(int i = 0; i<cur.length; i++) {
                    for(int j = 0; j<cur[0].length; j++) {
                        paper[i][j] = cur[i][j];
                        paper[i][2*cur[0].length-1-j] = (cur[i][j]%2==0)?(cur[i][j]+1):(cur[i][j]-1);
                    }
                }
            } else {
                for(int i = 0; i<cur.length; i++) {
                    for(int j = 0; j<cur[0].length; j++) {
                        paper[i][cur[0].length+j] = cur[i][j];
                        paper[i][cur[0].length-1-j] = (cur[i][j]%2==0)?(cur[i][j]+1):(cur[i][j]-1);
                    }
                }
            }
        }

        return paper;
    }
    static void showPaper(int[][] paper) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i<paper.length; i++) {
            for(int j = 0; j<paper[0].length; j++) {
                sb.append(paper[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String[] inputStrings = br.readLine().split(" ");
        int h = Integer.parseInt(br.readLine());
        int[][] cur = new int[1][1];
        cur[0][0] = h;
        for(int i = 2*k-1; i>=0; i--) {
            cur = unfold(cur, inputStrings[i].charAt(0));
        }
        showPaper(cur);
    }
}
