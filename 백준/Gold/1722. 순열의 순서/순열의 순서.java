import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-23
@see            https://www.acmicpc.net/problem/1722
@performance    
@category       #수학
@note          
*/

public class Main {

    static long[] factorial;

    public static long factorial(int n){
        if(factorial[n] != 0) return factorial[n];
        return factorial[n] = n * factorial(n-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        factorial = new long[N + 1];
        factorial[0] = 1;
        factorial[1] = 1;

        factorial(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int order = Integer.parseInt(st.nextToken());
        if(order == 1){
            long k = Long.parseLong(st.nextToken());
            boolean[] visited = new boolean[N + 1];
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < N; i++){
                long last = 0;
                int cnt = 1;

                for(int j = 1; j < N+1; j++){
                    if(visited[j]) continue;

                    long num = cnt * factorial[N - 1 - i];

                    if(num >= k){
                        k -= last;
                        sb.append(j).append(" ");
                        visited[j] = true;
                        break;
                    }
                    last = num;
                    cnt++;
                }
            }

            for(int j = 1; j < N+1; j++){
                if(visited[j]) continue;
                sb.append(j).append(" ");
            }

            System.out.println(sb);
            br.close();
        }
        else{
            boolean[] visited = new boolean[N+1];
            long ans = 0;
            for(int i = 0; i < N; i++){
                int num = Integer.parseInt(st.nextToken());

                int cnt = 0;
                for(int j = 1; j < num; j++){
                    if(!visited[j]) cnt++;
                }

                ans += cnt * factorial[N - 1 - i];
                visited[num] = true;
            }

            System.out.println(ans+1);
            br.close();
        }
    }
}