import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-25
@see            https://www.acmicpc.net/problem/1072
@performance
@category       #이분탐색
@note
*/

public class Main {
    static int X, Y;

    public static int winRate(long extraGame){
        return (int)((100.0 * (extraGame + Y) / (extraGame + X)));
    }

    public static long binarySearch(int target){
        long s = 0;
        long e = X;

        while(s < e){
            long mid = s + (e - s) / 2;

            if(target < winRate(mid)){
                e = mid;
            }
            else {
                s = mid + 1;
            }

        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int org = winRate(0);

        if(org == 100 || org == 99){
            System.out.println(-1);
            br.close();
            return;
        }

        long extraGame = binarySearch(org);

        System.out.println(extraGame);
        br.close();
    }
}