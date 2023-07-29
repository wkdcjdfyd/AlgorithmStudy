import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static boolean[] visited;
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int nxt: list.get(now)) {
				if(visited[nxt]) {
					continue;
				}
				q.add(nxt);
				cnt++;
				visited[nxt] = true;
			}
		}
		System.out.println(cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		
		for(int i = 0; i < N+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		bfs();
		br.close();
	}

}