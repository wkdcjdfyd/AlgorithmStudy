import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
@author         Ryong
@since          2024-01-22
@see            https://www.acmicpc.net/problem/20437
@performance
@category       #구현
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            ArrayList<ArrayList<Integer>> alpha = new ArrayList<>();
            for(int i = 0; i < 'z'-'a'+1; i++){
                alpha.add(new ArrayList<>());
            }

            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            for(int i = 0; i < W.length(); i++){
                char c = W.charAt(i);
                alpha.get(c - 'a').add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = 0;

            for(int i = 0; i < 'z'-'a'+1; i++){
                ArrayList<Integer> now = alpha.get(i);

                for(int idx = K-1; idx < now.size(); idx++){
                    int s = now.get(idx - (K-1));
                    int e = now.get(idx);
                    int len = e - s + 1;

                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }

            if(max == 0){
                sb.append(-1).append("\n");
            }
            else{
                sb.append(min).append(" ").append(max).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}