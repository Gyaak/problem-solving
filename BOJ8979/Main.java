package BOJ8979;

import java.io.*;
import java.util.*;

public class Main {

    static class Country implements Comparable<Country> {
        int gold, silver, bronze;

        Country(int gold, int silver, int bronze) {
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if(this.gold==o.gold) {
                if(this.silver==o.silver) {
                    return o.bronze-this.bronze;
                }
                return o.silver-this.silver;
            }
            return o.gold-this.gold;
        }

        public boolean equals(Country c) {
            if(this.gold==c.gold && this.silver==c.silver && this.bronze==c.bronze)
                return true;
            else
                return false;
        }
    }

    static int N, K;
    static Country[] countries;
    static long[] scores;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        countries = new Country[N];
        scores = new long[N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            countries[num] = new Country(gold, silver, bronze);
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int rank = 1;
        Country target = countries[K-1];
        Arrays.sort(countries);
        for(Country c : countries) {
            if(target.equals(c))    break;
            rank++;
        }

        System.out.println(rank);
    }
}