import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-09-29
 * @see             https://www.acmicpc.net/problem/1920
 * @performance
 * @category 		#이분탐색
 * @note
 */

public class Main {
    static int N;
    static int[] arr;

    public static boolean binarySearch(int target){
        int s = 0;
        int e = N;

        while(s < e){
            int mid = (s+e) / 2;

            if(target <= arr[mid]){
                e = mid;
            }
            else{
                s = mid + 1;
            }
        }

        if(s >= 0 && s < N && arr[s] == target) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            if(binarySearch(target)){
                sb.append(1).append("\n");
            }
            else{
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}