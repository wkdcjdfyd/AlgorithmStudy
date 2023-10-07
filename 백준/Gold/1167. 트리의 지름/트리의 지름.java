import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-7
 * @see             https://www.acmicpc.net/problem/1167
 * @performance
 * @category 		#dfs
 * @note
 */

public class Main {

    static int V;
    static ArrayList<ArrayList<Edge>> graph;
    static int maxLen;
    static int nodeNum;

    static class Edge{
        int from, to, len;

        public Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }
    }

    public static void dfs(int now, int length, boolean[] visited){
        boolean isNxt = false;
        for(Edge nxt : graph.get(now)){
            if(!visited[nxt.to]){
                isNxt = true;

                visited[nxt.to] = true;
                dfs(nxt.to, length + nxt.len, visited);
                visited[nxt.to] = false;
            }
        }
        if(!isNxt){
            if(maxLen < length){
                maxLen = length;
                nodeNum = now;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for(int i = 0; i < V+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i < V+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while(true){
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int len = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Edge(from, to, len));
            }
        }

        for (Edge nxt : graph.get(1)) {
            boolean[] visited = new boolean[V + 1];
            visited[nxt.from] = true;
            visited[nxt.to] = true;
            dfs(nxt.to, nxt.len, visited);
            visited[nxt.to] = false;
        }

        int len = maxLen;
        maxLen = 0;
        for (Edge nxt : graph.get(nodeNum)) {
            boolean[] visited = new boolean[V + 1];
            visited[nxt.from] = true;
            visited[nxt.to] = true;
            dfs(nxt.to, nxt.len, visited);
            visited[nxt.to] = false;
        }

        if(len < maxLen){
            System.out.println(maxLen);
        }
        else{
            System.out.println(len);
        }
        br.close();
    }
}