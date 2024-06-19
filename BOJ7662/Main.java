package BOJ7662;

import java.io.*;
import java.util.*;

public class Main {
    static class DualPriorityQueue {
        static class Node {
            int val;
            boolean isValid;

            Node(int val) {
                this.val = val;
                this.isValid = true;
            }
        }

        // 오버플로우에 주의
        private PriorityQueue<Node> maxQueue = new PriorityQueue<>((i,j)->i.val<j.val?1:-1);
        private PriorityQueue<Node> minQueue = new PriorityQueue<>((i,j)->i.val>j.val?1:-1);

        // 이미 제거된 노드들을 각 큐에서 제거
        private void clean() {
            while(!maxQueue.isEmpty() && !maxQueue.peek().isValid)
                maxQueue.poll();
            while(!minQueue.isEmpty() && !minQueue.peek().isValid)
                minQueue.poll();
        }

        public void add(int val) {
            Node node = new Node(val);
            maxQueue.add(node);
            minQueue.add(node);
        }

        public void poll(int flag) {
            clean();
            if(flag == 1 && !maxQueue.isEmpty()) {
                Node node = maxQueue.poll();
                node.isValid = false;
            }
            if(flag == -1 && !minQueue.isEmpty()) {
                Node node = minQueue.poll();
                node.isValid = false;
            }
        }

        public String printVal() {
            clean();
            if(maxQueue.isEmpty()||minQueue.isEmpty())
                return "EMPTY\n";
            StringBuilder sb = new StringBuilder();
            sb.append(maxQueue.peek().val);
            sb.append(" ");
            sb.append(minQueue.peek().val);
            sb.append("\n");
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for(int t = 0; t<T; t++) {
            DualPriorityQueue dpq = new DualPriorityQueue();
            int N = Integer.parseInt(br.readLine());
            for(int i = 0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int val = Integer.parseInt(st.nextToken());
                if(type=='I')   dpq.add(val);
                if(type=='D')   dpq.poll(val);
            }
            ans.append(dpq.printVal());
        }
        System.out.print(ans);
    }
}
