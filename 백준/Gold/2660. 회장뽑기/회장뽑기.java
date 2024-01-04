import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-04
@see            https://www.acmicpc.net/problem/2660
@performance    
@category       #플로이드-워셜
@note          
*/

public class Main {

    static class Member implements Comparable<Member>{
        int num;
        int score;

        public Member(int num) {
            this.num = num;
        }


        @Override
        public int compareTo(Member o) {
            if(this.score == o.score){
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.score, o.score);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] adj = new int[N+1][N+1];
        Member[] members = new Member[N];

        for(int a = 1; a < N+1; a++) {
            members[a-1] = new Member(a);
            for(int b = 1; b < N+1; b++) {
                adj[a][b] = (a == b) ? 0 : 10000;
            }
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;
            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        for(int k = 1; k < N+1; k++){
            for(int a = 1; a < N+1; a++){
                for(int b = 1; b < N+1; b++){
                    adj[a][b] = Math.min(adj[a][b], adj[a][k] + adj[k][b]);
                }
            }
        }

        for(int i = 1; i < N+1; i++){
            int max = 0;
            for(int j = 1; j < N+1; j++){
                if(i == j) continue;
                max = Math.max(max, adj[i][j]);
            }
            members[i-1].score = max;
        }

        Arrays.sort(members);

        StringBuilder sb = new StringBuilder();
        sb.append(members[0].num).append(" ");

        int ans = 1;
        for(int i = 0; i < N-1; i++){
            if(members[i+1].score != members[i].score) break;
            ans++;
            sb.append(members[i+1].num).append(" ");
        }

        System.out.println(members[0].score + " " + ans);
        System.out.print(sb.toString());
        br.close();
    }
}