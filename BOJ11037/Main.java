package BOJ11037;

import java.io.*;
import java.util.stream.Stream;

public class Main {

    static int val(int[] arr) {
        int v = 0;
        for(int n : arr)
            v = 10*v+n;
        return v;
    }

    static int findNum(int[] target) {
        int[] check = new int[10];
        // 0을 포함하면 안되므로, 0은 무조건 중복처리
        check[0] = 1;
        int cur;
        for(cur = 0; cur<target.length; cur++) {
            check[target[cur]]++;
            if(check[target[cur]]>1) {
                break;
            }
        }

        // 이미 중복 없는 수인 경우 바로 반환
        if(cur==target.length) {
            return val(target);
        }

        // 바꿀 수 있는 자리수까지 앞으로 이동
        while(cur>=0) {
            check[target[cur]]--;
            for(int i = target[cur]+1; i<10; i++) {
                if(check[i]==0) {
                    check[i]++;
                    // 바꿀 수 있는 수가 있으면 바꾸고
                    target[cur] = i;
                    int n = 0;
                    // 그 뒤 자리수는 작은 수부터 중복없이 채워 넣기
                    for(int j = cur+1; j<target.length; j++) {
                        while(check[n]>0)
                            n++;
                        check[n]++;
                        target[j] = n;
                    }
                    return val(target);
                }
            }
            cur--;
        }

        // 자리수가 늘어나는 경우
        int num = 0;
        for(int i = 1; i<=target.length+1; i++) {
            num *= 10;
            num += i;
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder ans = new StringBuilder("");
        String inputString = "";
        while((inputString = br.readLine()) != null && !inputString.isEmpty()) {
            // 주어진 수보다 '큰' 중복 없는 수를 찾아야함..
            int targetVal = Integer.parseInt(inputString) + 1;
            // 가장 큰 중복 없는 수는 987654321
            if(targetVal>987654321) {
                ans.append("0\n");
            } else {
                int[] target = Stream.of(String.valueOf(targetVal).split(""))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                ans.append(findNum(target)+"\n");
            }
        }   
        System.out.print(ans);
    }
}