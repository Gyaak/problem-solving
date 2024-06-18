package BOJ20055;

import java.io.*;
import java.util.*;

public class Main {
    static final int EMPTY = -1;

    static class Conveyor {
		// num : 컨베이어 길이
        // load : 올리는 위치, unload : 내리는 위치
        // broken : 내구도가 0인 칸의 개수
        private int num, load, unload, broken;
        private int[] durabilities;
        private boolean[] robots;

        public Conveyor(String input) {
            durabilities = Arrays.stream(input.split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            this.num = durabilities.length;
            robots = new boolean[num];
            load = 0;
            unload = num/2-1;
            broken = 0;
        }

        public int getBroken() {
            return broken;
        }

        public void moveConveyor() {
            load = (load+num-1)%num;
            unload = (unload+num-1)%num;
            // 내리는 위치에 로봇 존재하면 제거
            robots[unload] = false;
        }

        public void moveRobot() {
            // load ~ unload 구간에만 로봇이 존재
            // unload쪽에 가까울수록 먼저 올라온 로봇
            for(int cur = unload; cur!=load; cur=(cur+num-1)%num) {
                if(!robots[cur])   continue;
                int next = (cur+1)%num;
                // 내구도가 1이상이고, 다른 로봇이 없으면 이동
                if(durabilities[next]>0 && !robots[next]) {
                    robots[cur] = false;
                    if(--durabilities[next]==0) broken++;
                    if(next!=unload)    robots[next] = true;
                }
            }
        }

        public void loadRobot() {
            if(!robots[load] && durabilities[load]>0) {
                robots[load] = true;
                if(--durabilities[load]==0) broken++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Conveyor conveyor = new Conveyor(br.readLine());
        int process = 0;
        while(conveyor.getBroken()<K) {
            conveyor.moveConveyor();
            conveyor.moveRobot();
            conveyor.loadRobot();
            process++;
        }
        System.out.println(process);
    }
}