import java.util.*;

class AnswerSet {
    public int winNum;
    public List<Integer> diceList;
    
    AnswerSet(int winNum, List<Integer> diceList) {
        this.winNum = winNum;
        this.diceList = diceList;
    }
}
class Solution {
    private final int[] POW6 = {1, 6, 36, 216, 1296, 7776};
    private int N;
    private AnswerSet answerSet;
    private boolean[] selected;
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        selected = new boolean[N];
        DFS(0, 0, dice);
        int[] ans = new int[N/2];
        for(int i = 0; i<N/2; i++) {
            ans[i] = answerSet.diceList.get(i);
        }
        return ans;
    }
    
    private void DFS(int depth, int idx, int[][] dice) {
        if(depth == N/2) {
            int num = calc(dice);
            if(answerSet == null || answerSet.winNum < num) {
                List<Integer> diceList = new ArrayList<>();
                for(int i = 0; i<N; i++) {
                    if(selected[i]) {
                        diceList.add(i + 1);
                    }
                }
                answerSet = new AnswerSet(num, diceList);
            }
            return;
        }
        for(int i = idx; i<N; i++) {
            selected[i] = true;
            DFS(depth + 1, i + 1, dice);
            selected[i] = false;
        }
    }
    
    private int calc(int[][] dice) {
        int winNum = 0;
        List<Integer> selectedA = new ArrayList<>();
        List<Integer> selectedB = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            if(selected[i]) {
                selectedA.add(i);
            } else {
                selectedB.add(i);
            }
        }
        List<Integer> scoreA = getScoreList(selectedA, dice);
        List<Integer> scoreB = getScoreList(selectedB, dice);
        int idxA = 0, idxB = 0, len = scoreA.size();
        int num = 0;
        while(idxA < len) {
            while(idxB < len && scoreA.get(idxA) > scoreB.get(idxB)) {
                idxB++;
                num++;
            }
            winNum += num;
            idxA++;
        }
        return winNum;
    }
    
    private List<Integer> getScoreList(List<Integer> diceList, int[][] dice) {
        List<Integer> scoreList = new ArrayList<>();
        for(int i = 0; i<POW6[N/2]; i++) {
            int val = i;
            int score = 0;
            for(int j = 0; j<N/2; j++) {
                score += dice[diceList.get(j)][val % 6];
                val /= 6;
            }
            scoreList.add(score);
        }
        Collections.sort(scoreList);
        return scoreList;
    }
}