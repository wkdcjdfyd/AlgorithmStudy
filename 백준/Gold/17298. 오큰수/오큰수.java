import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-30
@see            https://www.acmicpc.net/problem/17298
@performance    
@category       #구현
@note          
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        int[] ans = new int[N];
        Deque<int[]> q = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(st.nextToken());

            while(!q.isEmpty()){
                int[] peek = q.peekLast();
                if(peek[1] >= n) break;

                int[] now = q.pollLast();
                ans[now[0]] = n;
            }
            q.offerLast(new int[]{i, n});
        }

        if(!q.isEmpty()){
            for(int[] left: q){
                ans[left[0]] = -1;
            }
        }

        for(int i = 0; i < N; i++){
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}