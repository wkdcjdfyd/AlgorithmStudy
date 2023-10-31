import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since          2023-11-1
@see            https://www.acmicpc.net/problem/1300
@performance
@category       #이분탐색
@note
*/

public class Main {

    public static int binarySearch(int N, int target){
        int s = 1;
        int e = target;

        while(s < e){
            int mid = s + ((e - s) / 2);

            long cnt = 0;
            for(int i = 1; i <= N; i++){
                cnt += Math.min(mid / i, N);
            }

            if(target <= cnt){
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
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(N, k));
        br.close();
    }
}