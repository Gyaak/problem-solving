import java.io.*;
import java.util.*;

class Solution {
    
    private final int INVALID = Integer.MAX_VALUE;
    // weight[i][j] := i->j로 손가락을 이동시켜 누를때 가중치
    private final int[][] weight = {
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };
    
    private int[][] cache = new int[10][10], tmp = new int[10][10];
    
    public int solution(String numbers) {
        int answer = INVALID;
        
        for(int i = 0; i<10; i++) {
            Arrays.fill(cache[i], INVALID);
        }
        cache[4][6] = 0;
        
        for(int i = 0; i<numbers.length(); i++) {
            press(numbers.charAt(i)-'0');
        }
        
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                answer = Math.min(answer, cache[i][j]);
            }
        }
        
        return answer;
    }
    
    private void press(int num) {
        for(int i = 0; i<10; i++) {
            Arrays.fill(tmp[i], INVALID);
        }
        
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                if(cache[i][j] != INVALID) {
                    if(i == num) {
                        tmp[num][j] = Math.min(tmp[num][j], cache[i][j] + weight[i][num]);
                    } else if(j == num) {
                        tmp[i][num] = Math.min(tmp[i][num], cache[i][j] + weight[j][num]);
                    } else {
                        tmp[num][j] = Math.min(tmp[num][j], cache[i][j] + weight[i][num]);
                        tmp[i][num] = Math.min(tmp[i][num], cache[i][j] + weight[j][num]);
                    }
                }
            }
        }
        
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                cache[i][j] = tmp[i][j];
            }
        }
    }
}