import java.io.*;
import java.util.*;

class Main {
    static class Network {
        String leader;
        int size;

        Network(String l, int s) {
            this.leader = l;
            this.size = s;
        }
    }

    static int getNetworkSize(Map<String, Set<String>> map, String f1, String f2) {
        boolean isFirst1 = map.get(f1) == null;
        boolean isFirst2 = map.get(f2) == null;

        if(isFirst1 & isFirst2) {
            Set<String> network = new HashSet<>();
            network.add(f1);
            network.add(f2);

            map.put(f1, network);
            map.put(f2, network);

            return 2;
        }
        else if(isFirst1) {
            Set<String> network = map.get(f2);
            network.add(f1);
            map.put(f1, network);
            
            return network.size();
        }
        else if(isFirst2) {
            Set<String> network = map.get(f1);
            network.add(f2);
            map.put(f2, network);

            return network.size();
        }
        else {
            Set<String> network1 = map.get(f1);
            Set<String> network2 = map.get(f2);

            if(network1.contains(f2)) {
                return network1.size();
            }

            if(network1.size() < network2.size()) {
                network2.addAll(network1);
                for(String s : network1) {
                    map.put(s, network2);
                }
                return network2.size();
            }
            else {
                network1.addAll(network2);
                for(String s : network2) {
                    map.put(s, network1);
                }
                return network1.size();
            }
        }
    }

    static void solve(Map<String, Set<String>> map, BufferedReader br, BufferedWriter bw) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String f1 = st.nextToken();
            String f2 = st.nextToken();
            bw.write(getNetworkSize(map, f1, f2) + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i<T; i++) {
            Map<String, Set<String>> map = new HashMap<>();
            solve(map, br, bw);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}