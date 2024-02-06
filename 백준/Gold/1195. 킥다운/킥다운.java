import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* @author         Ryong
* @since          2024-02-06
* @see            https://www.acmicpc.net/problem/1195
* @performance    
* @category       #구현
* @note          
*/

public class Main {

    static char[] s1;
    static char[] s2;
    static int ans;

    private static boolean checkMiddle(){
        loop:
        for(int start = 0; start < s1.length - s2.length; start++){
            for(int i = 0; i < s2.length; i++){
                if(s2[i] == '2' && s1[start+i] == '2') continue loop;
            }
            ans = Math.min(ans, s1.length);
            return true;
        }
        return false;
    }

    private static void checkSide(){
        loop:
        for(int len = 1; len <= s2.length; len++){
            for(int i = 0; i < len; i++){
                if(s2[s2.length - len + i] =='2' && s1[i] == '2') continue loop;
            }
            ans = Math.min(ans, s1.length + s2.length - len);
        }
        
        loop:
        for(int len = 1; len <= s2.length; len++){
            for(int i = 0; i < len; i++){
                if(s2[i] == '2' && s1[s1.length - len + i] == '2') continue loop;
            }
            ans = Math.min(ans, s1.length + s2.length - len);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();
        ans = s1.length + s2.length;

        if(s1.length < s2.length){
            char[] temp = s1;
            s1 = s2;
            s2 = temp;
        }

        if(checkMiddle()){
            System.out.println(ans);
        }
        else{
            checkSide();
            System.out.println(ans);
        }
        br.close();
    }
}