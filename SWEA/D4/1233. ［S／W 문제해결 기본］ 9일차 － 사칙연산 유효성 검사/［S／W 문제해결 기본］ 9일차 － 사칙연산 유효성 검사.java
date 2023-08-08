import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static char[] arr;
	static boolean[] visited;
	static int N;
	static boolean turn;
	static boolean ans;
	
	public static void dfs(int idx) {
		visited[idx] = true;
		
		int leftChild = idx * 2;
		int rightChild = (idx * 2) + 1;
		
		if(leftChild < N+1 && !visited[leftChild]) {
			dfs(leftChild);
		}
		
		if(!turn && arr[idx] != '1') {
			ans = false;
		}
		if(turn && arr[idx] == '1') {
			ans = false;
		}
		turn = !turn;
		
		if(rightChild < N+1 && !visited[rightChild]) {
			dfs(rightChild);
		}
			
	}
	
	public static boolean isNumeric(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t < 11; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N+1];
			visited = new boolean[N+1];
			
			for(int i = 1; i < N+1; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String s = st.nextToken();
				if(isNumeric(s)) {
					arr[idx] = '1';
				}
				else {
					arr[idx] = s.charAt(0);
				}

			}
			ans = true;
			turn = false;
			dfs(1);
			
			if(ans) {
				sb.append("#" + t + " " + 1 + "\n");
			}
			else {
				sb.append("#" + t + " " + 0 + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}