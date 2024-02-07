import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> ans;

    public static void dfs(int now, int target){
        if(!visited[arr[now]]){
            visited[arr[now]] = true;
            dfs(arr[now], target);
            visited[arr[now]] = false;
        }
        if(arr[now] == target) ans.add(target);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        ans = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++){
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        sb.append(ans.size()).append("\n");
        Collections.sort(ans);
        for(int num: ans){
            sb.append(num).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}