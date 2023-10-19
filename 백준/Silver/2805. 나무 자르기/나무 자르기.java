import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-20
@see            https://www.acmicpc.net/problem/2805
@performance
@category       #이분탐색
@note
*/

public class Main {

    static int N, M;
    static int[] height;

    public static int binarySearch(){
        int s = 0;
        int e = Integer.MAX_VALUE;

        while(s < e){
            int mid = s + ((e - s) / 2);

            if(getWoodLength(mid) < M){
                e = mid;
            }
            else{
                s = mid + 1;
            }
        }
        return s-1;
    }

    public static long getWoodLength(int cutHeight){
        long sum = 0;
        for(int i = 0; i < N; i++){
            if(cutHeight < height[i]){
                sum += height[i] - cutHeight;
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        int ans = binarySearch();

        System.out.println(ans);
        br.close();
    }
}