package BOJ3649;

import java.io.*;
import java.util.*;

public class Main {

    static int x, n;
    static int[] blocks;

    static boolean setInput(BufferedReader br) throws IOException {
        String input;
        if((input = br.readLine()) == null || input.isEmpty())
            return false;
        x = Integer.parseInt(input);
        x *= 10_000_000;
        n = Integer.parseInt(br.readLine());
        blocks = new int[n];
        for(int i = 0; i<n; i++) {
            blocks[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(blocks);
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(setInput(br)) {
            int left = 0, right = n-1;
            while(left<right) {
                int length = blocks[left] + blocks[right];
                if(length<x)        left++;
                else if(length>x)   right--;
                else                break;
            }

            if(left==right || n==0) bw.write("danger\n");
            else                    bw.write("yes " + blocks[left] + " " + blocks[right]+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
