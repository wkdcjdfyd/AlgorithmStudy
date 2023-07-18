import java.util.Scanner;

public class Main {
	public static char[][] board;
	public static int ans = 64;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			for(int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		for(int i = 0; i < N-7; i++) {
			for(int j = 0; j < M-7; j++) {
				count(i, j);
			}
		}
		
		System.out.println(ans);
		sc.close();
	}
	
	public static void count(int x, int y) {
		int cnt = 0;
		char now = board[x][y];
		
		for(int i = x; i < x + 8; i++) {
			for(int j = y; j < y + 8; j++) {
				if(board[i][j] != now) {
					cnt++;
				}
				now = (now == 'W')?'B':'W';
			}
			now = (now == 'W')?'B':'W';
		}
		cnt = Math.min(cnt, 64 - cnt);
		ans = Math.min(ans, cnt);
	}

}