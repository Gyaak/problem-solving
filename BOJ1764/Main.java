package BOJ1764;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            set.add(br.readLine());
        }
        for(int j = 0; j<M; j++) {
            String s = br.readLine();
            if(set.contains(s))
                list.add(s);
        }
        Collections.sort(list);
        StringBuilder ans = new StringBuilder(list.size()+"\n");
        for(int i = 0; i<list.size(); i++) {
            ans.append(list.get(i));
            if(i<list.size()-1)
                ans.append("\n");
        }
        System.out.println(ans);
    }
}
