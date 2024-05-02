package BOJ21319;

import java.io.*;
import java.util.*;

public class Main {

    static class Player {
    // idx : 선수의 실제 번호
    // atk : 선수의 공격력
    // num : 이 선수와 동일한 공격력을 가진 선수의 수
        int idx, atk, num;

        Player(int idx, int atk, int num) {
            this.idx = idx + 1;
            this.atk = atk;
            this.num = num;
        }
    }
    static int N;
    static ArrayList<Player> players;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        players = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        players.add(new Player(0, Integer.parseInt(st.nextToken()), 1));
        for(int i = 1; i<N; i++) {
            int atk = Integer.parseInt(st.nextToken());
            // 공격력이 중복되는 경우 리스트에 넣지 않음
            if(players.get(players.size()-1).atk<atk) {
                players.add(new Player(i, atk, 1));
            } else {
                players.get(players.size()-1).num++;
            }
        }
        br.close();
    }

    static boolean isChampion(int n) {
        if(n==0)    return false;

        int atk = 0;
        for(int i = 0; i<n; i++)
            atk += players.get(i).num;
        atk += players.get(n).atk + players.get(n).num - 1;
        for(int i = n+1; i<players.size(); i++) {
            if(players.get(i).atk<atk) {
                atk += players.get(i).num;
            } else {
                return false;
            }
        }

        return true;
    }

    static int BinarySearch() {
        int st = 0;
        int ed = players.size()-1;

        while(st<ed) {
            int mid = (st+ed)/2;
                
            if(isChampion(mid)) ed = mid;
            else                st = mid+1;
        }
        return ed;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        setInput();
        int n = BinarySearch();
        if(N==1) {
            bw.write("1\n");
        } else if(n==0) {
            bw.write("-1\n");
        } else {
            for(int i = n; i<players.size(); i++) {
                bw.write(players.get(i).idx + " ");
            }
            bw.write("\n");
        }
        
        bw.flush();
        bw.close();
    }
}