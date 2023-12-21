import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-12-21
@see            https://www.acmicpc.net/problem/21758
@performance
@category
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int sum = 0;
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int tempSum = sum - arr[0];
        int subSum = 0;

        for(int i = 1; i < N-1; i++){
            subSum += arr[i];
            max = Math.max(max, tempSum * 2 - arr[i] - subSum);
        }

        tempSum = sum - arr[N-1];
        subSum = 0;

        for(int i = N-2; i > 0; i--){
            subSum += arr[i];
            max = Math.max(max, tempSum * 2 - arr[i] - subSum);
        }

        tempSum = sum - arr[0] - arr[N-1];
        for(int i = 1; i < N-1; i++){
            max = Math.max(max, tempSum + arr[i]);
        }

        System.out.println(max);
        br.close();
    }
}