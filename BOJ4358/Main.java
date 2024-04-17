package BOJ4358;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> species = new HashMap<>();
        List<String> nameList = new ArrayList<>();
        String input;
        Double num = 0D;
        while((input=br.readLine())!=null && !input.isEmpty()) {
            if(species.containsKey(input)) {
                species.replace(input, species.get(input)+1);
            } else {
                species.put(input, 1);
                nameList.add(input);
            }
            num++;
        }
        num /= 100;
        Collections.sort(nameList);
        for(String s : nameList) {
            Double rate = species.get(s)/num;
            bw.write(s);
            bw.write(String.format(" %.4f", rate));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
