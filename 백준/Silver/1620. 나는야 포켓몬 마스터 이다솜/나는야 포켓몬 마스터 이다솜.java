import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      HashMap<Integer, String> dict1 = new HashMap<>();
      HashMap<String, Integer> dict2 = new HashMap<>();
      
      for(int i = 1; i < N+1; i++) {
         String name = br.readLine();
         dict1.put(i, name);
         dict2.put(name, i);
      }
      
      for(int i = 0; i < M; i++) {
         String s = br.readLine();

         if(Character.isDigit(s.charAt(0))) {
            sb.append(dict1.get(Integer.parseInt(s)) + "\n");
         }
         else {
            sb.append(dict2.get(s) + "\n");
         }
      }
      System.out.println(sb.toString());
      br.close();
   }

}