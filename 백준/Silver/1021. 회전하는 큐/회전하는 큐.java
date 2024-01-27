import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = 0;

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++){
            q.offerLast(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());

            int idx = 0;
            for(Integer now: q){
                if(now == target) {
                    break;
                }
                idx++;
            }
            boolean flag = idx > q.size()-1-idx;


            if(!flag){
                while (true){
                    int now = q.pollFirst();
                    if(now == target) break;
                    q.offerLast(now);
                    ans++;
                }
            }
            else{
                while(true) {
                    int now = q.pollLast();
                    ans++;
                    if (now == target) break;
                    q.offerFirst(now);
                }
            }
        }
        System.out.println(ans);
        br.close();
    }
}