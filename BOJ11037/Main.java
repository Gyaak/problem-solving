package BOJ11037;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static boolean[] check = new boolean[10];

    static boolean DFS(int[] target, int idx) {
        if(idx==target.length)
            return true;
        
        if(!check[target[idx]]) {
            check[target[idx]] = true;
            if(DFS(target, idx+1)) {
                return true;
            }
            check[target[idx]] = false;
        }
        for(int i = target[idx]+1; i<10; i++) {
            if(!check[i]) {
                check[i] = true;
                target[idx] = i;
                int cur = 1;
                for(int j = idx+1; j<target.length; j++) {
                    while(cur<10 && check[cur])   cur++;
                    target[j] = cur;
                    check[cur] = true;
                }
                return true;
            }
        }
        return false;
    }

    static int findNum(int[] target) {
        if(target.length>9)
            return 0;
        Arrays.fill(check, false);
        check[0] = true;
        int result = 0;
        if(DFS(target, 0)) {
            for(int i : target) {
                result *= 10;
                result += i;
            }
        } else {
            for(int i = 1; i<=target.length+1;i++) {
                result *= 10;
                result += i;
            } 
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder ans = new StringBuilder("");
        String inputString = "";
        while((inputString = br.readLine()) != null && !inputString.isEmpty()) {
            // 주어진 수보다 '큰' 중복되지 않은 수를 찾아야함..
            int targetVal = Integer.parseInt(inputString) + 1;
            
            int[] target = Stream.of(String.valueOf(targetVal).split(""))
                                 .mapToInt(Integer::parseInt)
                                 .toArray();
            ans.append(findNum(target)+"\n");
        }   
        System.out.print(ans);
    }
}
