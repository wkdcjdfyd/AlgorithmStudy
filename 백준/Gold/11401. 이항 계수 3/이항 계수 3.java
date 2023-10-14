import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-14
 * @see             https://www.acmicpc.net/problem/11401
 * @performance
 * @category 		#수학
 * @note
 */

public class Main {

    static final int MOD = 1_000_000_007;
    static long[] fact;

    public static long pow(long a, long b){
        if(b == 1) return a;

        long div = pow(a, b/2);

        if(b % 2 == 0){
            return div * div % MOD;
        }
        else{
            return div * div % MOD * a % MOD;
        }
    }

    public static void initFactorial(int n){
        fact = new long[n+1];
        fact[0] = 1;
        fact[1] = 1;

        for(int i = 2; i <= n; i++){
            fact[i] = (fact[i-1] * i) % MOD;
        }
    }

    public static int nCr(int n, int r){
        if(r == 0 || r == n) return 1;

        long val1 = fact[n];
        long val2 = pow(fact[n-r], MOD-2);
        long val3 = pow(fact[r], MOD-2);

        return (int)(val1 * val2 % MOD * val3 % MOD);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        initFactorial(N);

        System.out.println(nCr(N, K));
        br.close();
    }
}