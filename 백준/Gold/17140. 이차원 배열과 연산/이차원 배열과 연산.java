import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static int k;
	static int N = 3;
	static int M = 3;
	static int[][] graph;
	
	public static void operR() {
		int[][] tempGraph = new int[N][];
		int[] arr;
		int maxSize = 0;
		for(int i = 0; i < N; i++) {
			arr = new int[101];
			ArrayList<Num> list = new ArrayList<>();
			for(int j = 0; j < M; j++) {
				arr[graph[i][j]]++;
			}
			for(int j = 1; j < arr.length; j++) {
				if(arr[j] == 0) {
					continue;
				}
				list.add(new Num(j, arr[j]));
			}
			Collections.sort(list);
			tempGraph[i] = new int[list.size() * 2];
			maxSize = Math.max(maxSize, list.size() * 2);
			for(int j = 0; j < tempGraph[i].length; j = j+2) {
				Num now = list.get(j / 2);
				tempGraph[i][j] = now.num;
				tempGraph[i][j+1] = now.cnt;
			}
		}
		M = maxSize > 100 ? 100 : maxSize;
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			int bound = tempGraph[i].length > 100 ? 100 : tempGraph[i].length;
			for(int j = 0; j < bound; j++) {
				graph[i][j] = tempGraph[i][j];
			}
		}
	}
	
	public static void operC() {
		int[][] tempGraph = new int[M][];
		int[] arr;
		int maxSize = 0;
		for(int j = 0; j < M; j++) {
			arr = new int[101];
			ArrayList<Num> list = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				arr[graph[i][j]]++;
			}
			for(int i = 1; i < arr.length; i++) {
				if(arr[i] == 0) {
					continue;
				}
				list.add(new Num(i, arr[i]));
			}
			Collections.sort(list);
			tempGraph[j] = new int[list.size() * 2];
			maxSize = Math.max(maxSize, list.size() * 2);
			
			for(int i = 0; i < tempGraph[j].length; i = i+2) {
				Num now = list.get(i / 2);
				tempGraph[j][i] = now.num;
				tempGraph[j][i+1] = now.cnt;
			}	
		}
		N = maxSize > 100 ? 100 : maxSize;
		graph = new int[N][M];
		for(int j = 0; j < M; j++) {
			int bound = tempGraph[j].length > 100 ? 100 : tempGraph[j].length;
			for(int i = 0; i < bound; i++) {
				graph[i][j] = tempGraph[j][i];
			}
		}
	}

	static class Num implements Comparable <Num> {
		int num;
		int cnt;
		
		public Num(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Num o2) {
			if(this.cnt == o2.cnt) {
				return this.num - o2.num;
			}
			return this.cnt - o2.cnt;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		graph = new int[3][3];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int clock = 0;
		if(r-1 < N && c-1 < M) {
			if(graph[r-1][c-1] == k) {
				System.out.println(clock);
				return;
			}
		}
		
		while(clock <= 100) {
			if(N >= M) {
				operR();
			}
			else {
				operC();
			}
			clock++;
			
			if(r-1 < N && c-1 < M) {
				if(graph[r-1][c-1] == k) {
					System.out.println(clock);
					return;
				}
			}
		}
		System.out.println(-1);
		br.close();
	}

}