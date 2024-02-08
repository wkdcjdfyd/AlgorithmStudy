import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int head = -1;
        int tail = 0;
        int cnt = 0;
        int ans = Integer.MAX_VALUE;
        while(head < N-1){
            if(arr[++head] == 1) cnt++;
            if(cnt == K) {
                while (tail <= head && arr[tail] != 1) {
                    tail++;
                }
                ans = Math.min(ans, head - tail + 1);
                if(arr[tail] == 1) {
                    tail++;
                    cnt--;
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        br.close();
    }
}