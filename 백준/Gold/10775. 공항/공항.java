import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] gate = new int[G+1];

        int ans = 0;

        loop:
        for(int i = 0; i < P; i++){
            int g = Integer.parseInt(br.readLine());

            if(gate[g] == 0){
                gate[g] = g;
                ans++;
                continue loop;
            }
            else{
                int nxt = gate[g] - 1;
                while(nxt > 0){
                    if(gate[nxt] == 0){
                        gate[nxt] = nxt;
                        gate[g] = nxt;
                        ans++;
                        continue loop;
                    }
                    nxt = gate[nxt] - 1;
                }
            }

            break;
        }

        System.out.println(ans);
        br.close();
    }
}