package BOJ15997;

import java.io.*;
import java.util.*;

public class Main {

    static class Prediction {
        int team1, team2;
        double win, draw, lose;

        Prediction(int team1, int team2, double win, double draw, double lose) {
            this.team1 = team1;
            this.team2 = team2;
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
    }

    static Map<String, Integer> country;
    static double[][] probability;
    static Prediction[] predictions;

    static void setInput() throws IOException {
        country = new HashMap<>();
        probability = new double[4][4];
        predictions = new Prediction[6];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++) {
            country.put(st.nextToken(), i);
        }
        for(int i = 0; i<6; i++) {
            st = new StringTokenizer(br.readLine());
            int u = country.get(st.nextToken());
            int v = country.get(st.nextToken());
            double p1 = Double.parseDouble(st.nextToken());
            double p2 = Double.parseDouble(st.nextToken());
            double p3 = Double.parseDouble(st.nextToken());
            predictions[i] = new Prediction(u,v,p1,p2,p3);
        }
    }

    static double[] calcProb(int[] score, double p) {
        double[] prob = new double[4];
        if(p==0) {
            return prob;
        }
        int[] cnt = new int[10];
        for(int s : score) {
            cnt[s]++;
        }
        int empty = 2;
        for(int i = 9; i>=0 && empty>0; i--) {
            if(cnt[i]>0) {
                if(cnt[i]>empty) {
                    for(int j = 0; j<4; j++) {
                        if(score[j]==i) {
                            prob[j] += p/cnt[i]*empty;
                        }
                    }
                } else {
                    for(int j = 0; j<4; j++) {
                        if(score[j]==i) {
                            prob[j] += p;
                        }
                    }
                }
                empty -= cnt[i];
            }
        }
        return prob;
    }

    static double[] calc(int val) {
        int[] score = new int[4];
        double p = 1;
        for(int i = 0; i<6; i++) {
            int result = val%3;
            if(result == 0) {
                p *= predictions[i].win;
                score[predictions[i].team1] += 3;
            } else if(result == 1) {
                p *= predictions[i].draw;
                score[predictions[i].team1] += 1;
                score[predictions[i].team2] += 1;
            } else if(result == 2) {
                p *= predictions[i].lose;
                score[predictions[i].team2] += 3;
            }
            val /= 3;
        }
        return calcProb(score, p);
    }

    static double[] solve() {
        double[] prob = new double[4];
        for(int i = 0; i<729; i++) {
            double[] tmp = calc(i);
            for(int j = 0; j<4; j++) {
                prob[j] += tmp[j];
            }
        }
        return prob;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        StringBuilder sb = new StringBuilder();
        for(double p : solve()) {
            sb.append(p).append("\n");
        }
        System.out.print(sb);
    }
}