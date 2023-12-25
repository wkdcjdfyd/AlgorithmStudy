import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
@author         Ryong
@since          2023-12-25
@see            https://www.acmicpc.net/problem/12904
@performance
@category
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        char[] t = br.readLine().toCharArray();

        Deque<Character> deque = new ArrayDeque<>();
        for(char c : t){
            deque.offer(c);
        }

        boolean d = true;
        int len = s.length();

        while(deque.size() > len){
            char now = d ? deque.pollLast() : deque.pollFirst();

            if(now == 'B'){
                d = !d;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            if(d) {
                sb.append(deque.pollFirst());
            }
            else{
                sb.append(deque.pollLast());
            }
        }

        if(s.equals(sb.toString())){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
        br.close();
    }
}