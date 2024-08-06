package BOJ5052;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Node[] children;

        Node() {
            children = new Node[10];
        }
    }
    
    static int t, n;
    static Node root;
    static List<String> phoneNumbers;

    // 매 테스트 케이스마다 static 변수들을 초기화 시켜줌
    static void setInput(BufferedReader br) throws IOException {

        n = Integer.parseInt(br.readLine());
        phoneNumbers = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            // 게시판 반례 중 끝에 공백이 들어가 있어 에러나는 경우 발생
            phoneNumbers.add(br.readLine().trim());
        }
        root = new Node();
        // 왜 길이로만 정렬하면 될까?
        // -> 두 전화번호가 같은 경우는 없으므로 길이가 같다면 탐색 우선순위도 같음
        phoneNumbers.sort((i,j)->j.length()-i.length());
    }

    // 전화번호의 각 자리들을 트리의 노드로 생각
    // 만약 각 트리의 부모는 10개의 자식노드(0~9)를 가지고 있으며,
    // children[i]!=null 이라는 뜻은 다음 숫자가 i로 시작하는 전화번호가 존재한다는 의미
    static boolean solve() {
        for(String p : phoneNumbers) {
            int idx = 0;
            Node cur = root;
            boolean isOverLapped = true;
            while(idx<p.length()) {
                int num = p.charAt(idx)-'0';
                if(cur.children[num]==null) {
                    cur.children[num] = new Node();
                    // 새로 자식을 생성한다는것은 이전까지 없던 번호가 추가되는것이므로
                    // isOverLapped를 false로 설정
                    isOverLapped = false;
                }
                cur = cur.children[num];
                idx++;
            }
            if(isOverLapped) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++) {
            setInput(br);
            bw.write(solve()?"YES\n":"NO\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
