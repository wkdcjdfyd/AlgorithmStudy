import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-12-23
@see            https://www.acmicpc.net/problem/3020
@performance
@category       #이분탐색
@note
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] up = new int[N/2];
        int[] down = new int[N/2];
        int[] breakNum = new int[N+1];

        for(int i = 0; i < N / 2; i++){
            up[i] = Integer.parseInt(br.readLine());
            down[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int ans = N;
        for(int i = 1; i <= H; i++){
            int num1 = (N / 2) - binarySearch(up, i - 1);
            int num2 = (N / 2) - binarySearch(down, H - i);
            breakNum[num1 + num2]++;
            ans = Math.min(ans, num1 + num2);
        }

        System.out.println(ans + " " + breakNum[ans]);
        br.close();
    }
}