import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-19
@see            https://www.acmicpc.net/problem/13397
@performance
@category       #파라메트릭 서치
@note
*/

public class Main {

    static int N, M, maxVal;
    static int[] arr;

    public static int counter(int val){
        int cnt = 1;
        int min = maxVal;
        int max = 0;
        int idx = 0;

        while(idx < N){
            min = Math.min(min, arr[idx]);
            max = Math.max(max, arr[idx]);

            if(max - min > val){
                cnt++;
                min = maxVal;
                max = 0;
            }
            else{
                idx++;
            }
        }

        return cnt;
    }

    public static int binarySearch(int start, int end, int key){
        int s = start;
        int e = end;

        while(s < e){
            int mid = (s+e) / 2;

            if(counter(mid) <= key){
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            maxVal = Math.max(maxVal, arr[i]);
        }

        int ans = binarySearch(0, maxVal, M);

        System.out.println(ans);
        br.close();
    }
}