import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-24
@see            https://www.acmicpc.net/problem/1561
@performance
@category       #이분탐색
@note
*/

public class Main {

    public static long counter(int[] opTime, long time){
        long cnt = 0;
        for(int i = 0; i < opTime.length; i++){
            if(time % opTime[i] == 0){
                cnt += time / opTime[i];
            }
            else{
                cnt += (time / opTime[i]) + 1;
            }
        }
        return cnt;
    }

    public static long binarySearch(int[] opTime, int target){
        long s = 0;
        long e = 60_000_000_001L;

        while(s < e){
            long mid = s + (e - s) / 2;

            if(target <= counter(opTime, mid)){
                e = mid;
            }
            else{
                s = mid + 1;
            }
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] opTime = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            opTime[i] = Integer.parseInt(st.nextToken());
        }

        if(N <= M){
            System.out.println(N);
            br.close();
            return;
        }

        long time = binarySearch(opTime, N);
        long cnt = counter(opTime, time-1);

        int ans = 0;
        for(int i = 0; i < M; i++){
            if(opTime[i] == 1 || time % opTime[i] == 1){
                if(++cnt == N){
                    ans = i;
                    break;
                }
            }
        }

        System.out.println(ans+1);
        br.close();
    }
}