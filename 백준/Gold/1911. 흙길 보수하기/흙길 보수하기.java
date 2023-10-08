import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-8
 * @see  			https://www.acmicpc.net/problem/1911
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {

    static class Water implements Comparable<Water>{
        int start, end, len;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
            this.len = this.end - this.start + 1;
        }

        @Override
        public int compareTo(Water o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Water[] waters = new Water[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            waters[i] = new Water(start, end);
        }
        Arrays.sort(waters);

        int cnt = 0;
        int now = -1;
        for(int i = 0; i < N; i++){
            if(waters[i].start > now){
                now = waters[i].start;
            }
            if(waters[i].end >= now){
                   while(waters[i].end > now){
                       now += L;
                       cnt++;
                   }
            }
        }
        System.out.println(cnt);
        br.close();
    }
}