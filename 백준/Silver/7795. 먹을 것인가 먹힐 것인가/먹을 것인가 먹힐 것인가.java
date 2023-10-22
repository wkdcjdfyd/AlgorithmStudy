import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-22
 * @see  			https://www.acmicpc.net/problem/7795
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {

    public static int binarySearch(int[] arr, int target){
        int s = 0;
        int e = arr.length;

        while(s < e){
            int mid = (s + e) / 2;

            if(target < arr[mid]){
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
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }

            int[] b = new int[M];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                b[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(b);

            int cnt = 0;
            for(int i = 0; i < N; i++){
                cnt += binarySearch(b, a[i]-1);
            }
            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}