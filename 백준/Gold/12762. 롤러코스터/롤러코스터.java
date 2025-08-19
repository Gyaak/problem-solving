import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr, dp_left, dp_right;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp_left = new int[N];
        dp_right = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void setLeft() {
        for (int i = 0; i < N; i++) {
            dp_left[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] > arr[i]) {
                    dp_left[i] = Math.max(dp_left[i], dp_left[j] + 1);
                }
            }
        }
    }

    static void setRight() {
        for (int i = N - 1; i >= 0; i--) {
            dp_right[i] = 1;
            for(int j = i + 1; j < N; j++) {
                if(arr[j] > arr[i]) {
                    dp_right[i] = Math.max(dp_right[i], dp_right[j] + 1);
                }
            }
        }
    }

    static int solve() {
        setLeft();
        setRight();
        int max = 0;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, dp_left[i] + dp_right[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }

}
