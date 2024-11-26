//package kernel360;

import java.io.*;
import java.util.*;

public class Main {
	static int N, C, W, maxLen;
	static int[] tree;

	static  void setInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		tree = new int[N];
		maxLen = 0;
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, tree[i]);
		}
	}

	static long cutTree(int len) {
		long cost = 0;
		for(int t : tree) {
			long cutCnt = t / len - 1 + (t % len == 0 ? 0 : 1);
			long val = (long)W * (t - t % len) - C * cutCnt;
			if(val > 0) {
				cost += val;
			}
		}
		return cost;
	}

	public static void main(String[] args) throws  IOException {
		setInput();
		long ans = 0;
		for(int i = 1; i<=maxLen; i++) {
			ans = Math.max(ans, cutTree(i));
		}
		System.out.println(ans);
	}
}