import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-21
 * @see             https://www.acmicpc.net/problem/10815
 * @performance
 * @category 		#이분탐색
 * @note
 */

public class Main {

    public static boolean binarySearch(int[] nums, int target, int N){
        int s = 0;
        int e = N;
        while(s < e){
            int mid = (s + e) / 2;

            if(target <= nums[mid]){
                e = mid;
            }
            else{
                s = mid + 1;
            }
        }

        return s < N && nums[s] == target;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            if(binarySearch(nums, target, N)){
                sb.append(1);
            }
            else{
                sb.append(0);
            }
            sb.append(" ");
        }

        System.out.print(sb);
        br.close();
    }
}