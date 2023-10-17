import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-18
@see            https://www.acmicpc.net/problem/11437
@performance
@category       #LCA
@note
*/

public class Main {
    static int N, M;
    static int maxDepth;
    static ArrayList<ArrayList<Integer>> adjNodeList;
    static int[] depth;
    static int[][] parents;

    public static void initTree(int now, int parent){
        depth[now] = depth[parent] + 1;
        parents[now][0] = parent;

        for(int i = 1; i <= maxDepth; i++){
            parents[now][i] = parents[parents[now][i-1]][i-1];
        }

        for(int nxt : adjNodeList.get(now)){
            if(nxt == parent) continue;
            initTree(nxt, now);
        }
    }

    public static int LCA(int a, int b){
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int i = maxDepth; i >= 0; i--){
            if(Math.pow(2, i) <= depth[a] - depth[b]){
                a = parents[a][i];
            }
        }
        if(a == b) return a;

        for(int i = maxDepth; i >= 0; i--){
            if(parents[a][i] != parents[b][i]){
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        adjNodeList = new ArrayList<>();
        for(int i = 0; i < N+1; i++){
            adjNodeList.add(new ArrayList<>());
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjNodeList.get(x).add(y);
            adjNodeList.get(y).add(x);
        }

        maxDepth = (int)Math.floor(Math.log(N) / Math.log(2));
        depth = new int[N+1];
        parents = new int[N+1][maxDepth+1];

        depth[0] = -1;
        initTree(1, 0);

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}