import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since             2023. 8. 1.
@see            https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
@performance    
@category #
@note            SWEA 2805. 농작물 수확하기
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t < T+1; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] graph = new int[N][N];
            int mid = N / 2;
            int sum = 0;
            
            for(int i = 0; i < N; i++) {
                String s = br.readLine();
                for(int j = 0; j < N; j++) {
                    graph[i][j] = Character.getNumericValue(s.charAt(j));
                }
            }
            
            int i = 0;
            for(i = 0; i < mid+1; i++) {
                for(int j = mid - i; j <= mid + i; j++) {
                    sum += graph[i][j];
                }
            }
            for(i = mid+1; i < N; i++) {
                for(int j = mid - (N - i - 1); j <= mid + (N - i - 1); j++) {
                    sum += graph[i][j];
                }
            }
            sb.append("#" + t + " " + sum + "\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}