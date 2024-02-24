import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long k = Long.parseLong(br.readLine());
        long num = k;
        long div = 1;

        List<Long> ans = new ArrayList<>();

        while(num != 1){
            div++;

            if(div >= Math.sqrt(k)){
                ans.add(num);
                break;
            }

            while(num % div == 0){
                ans.add(div);
                num /= div;
            }
        }

        sb.append(ans.size()).append("\n");
        for(long n : ans){
            sb.append(n).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}