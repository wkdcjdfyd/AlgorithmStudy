import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since          2023-12-26
@see            https://www.acmicpc.net/problem/12919
@performance
@category
@note
*/

public class Main {

    static String s;

    public static String reverse(String t){
        StringBuffer sb = new StringBuffer(t);
        return sb.reverse().toString();
    }

    public static void dfs(String t, boolean d){
        if(t.length() == s.length()){
            if((d && t.equals(s)) || (!d && t.equals(reverse(s)))){
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if(d){
            if(t.charAt(t.length()-1) == 'A'){
                dfs(t.substring(0, t.length()-1), d);
            }
            if(t.charAt(0) == 'B'){
                dfs(t.substring(1), !d);
            }
        }
        else{
            if(t.charAt(0) == 'A'){
                dfs(t.substring(1), d);
            }
            if(t.charAt(t.length()-1) == 'B'){
                dfs(t.substring(0, t.length()-1), !d);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        String t = br.readLine();

        dfs(t, true);
        System.out.println(0);
        br.close();
    }
}