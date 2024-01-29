import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
@author         Ryong
@since          2024-01-29
@see            https://www.acmicpc.net/problem/2800
@performance
@category
@note
*/

public class Main {

    static char[] original;
    static int size;
    static List<String> ans;
    static Map<Integer, int[]> pos;

    public static void checkNum(){
        Deque<Integer> stack = new ArrayDeque<>();
        int[] num = new int[11];
        int openNum = 0;

        for(int i = 0; i < original.length; i++){
            if(original[i] == '('){
                num[++openNum] = i;
                stack.push(openNum);
            }
            else if(original[i] == ')'){
                if(!stack.isEmpty()){
                    int open = stack.pop();
                    pos.put(open, new int[]{num[open], i});
                }
            }
        }
        size = openNum;
    }

    public static void makeComb(int nth, int startIdx, int len, boolean[] check){
        if(nth == len){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < original.length; i++){
                if(!check[i]) sb.append(original[i]);
            }
            String s = sb.toString();
            if(!ans.contains(s)) {
                ans.add(sb.toString());
            }
            return;
        }

        for(int i = startIdx; i <= size; i++){
            int[] p = pos.get(i);
            check[p[0]] = true;
            check[p[1]] = true;
            makeComb(nth+1, i+1, len, check);
            check[p[0]] = false;
            check[p[1]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        original = br.readLine().toCharArray();
        pos = new HashMap<>();
        ans = new ArrayList<>();

        checkNum();

        for(int i = 1; i <= size; i++){
            makeComb(0, 1, i, new boolean[original.length]);
        }
        Collections.sort(ans);

        StringBuilder sb = new StringBuilder();
        for(String s: ans){
            sb.append(s).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}