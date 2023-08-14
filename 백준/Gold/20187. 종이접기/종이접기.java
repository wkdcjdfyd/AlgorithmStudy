import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 3. 손수건 접기[25점]
 * 
 * 손수건을 접는 순서대로 주어지므로 펼칠 때는 역순으로 고려해야 한다.
 * 반드시 가로 k번, 세로 k번을 접는다. 따라서 완전히 접고난 크기는 1x1.
 * 펼칠 때 펼치는 방향의 길이를 2배한 배열을 만든 후 복사해서 return하는 방식으로 구현.
 */

public class Main {
	static int K;
	static char[] cmd;
	static int R;
	static int C;
	
	// R방향으로 접은 걸 펼칠 때
	public static int[][] openR(int[][] paper) {
		int[][] nxtPaper = new int[R][2*C];
		int j;
		for(int i = 0; i < R; i++) {
			// 펼쳐지는 곳은 원본을 뒤집어서 복사
			for(j = 0; j < C; j++) {
				if(paper[i][C-1-j] == 3 || paper[i][C-1-j] == 1) {
					nxtPaper[i][j] = paper[i][C-1-j] - 1;
				}
				else {
					nxtPaper[i][j] = paper[i][C-1-j] + 1;
				}
			}
			// 원래 위치는 그대로 복사
			for(;j < 2*C; j++) {
				nxtPaper[i][j] = paper[i][j-C];
			}
		}
		C = 2 * C;
		return nxtPaper;
	}
	
	// L방향으로 접은 걸 펼칠 때
	public static int[][] openL(int[][] paper) {
		int[][] nxtPaper = new int[R][2*C];
		int j;
		for(int i = 0; i < R; i++) {
			// 원래 위치는 그대로 복사
			for(j = 0; j < C; j++) {
				nxtPaper[i][j] = paper[i][j];
			}
			// 펼쳐지는 곳은 원본을 뒤집어서 복사
			for(;j < 2*C; j++) {
				if(paper[i][j-C] == 3 || paper[i][j-C] == 1) {
					nxtPaper[i][j] = paper[i][j-C] - 1;
				}
				else {
					nxtPaper[i][j] = paper[i][j-C] + 1;
				}
			}
		}
		C = 2 * C;
		return nxtPaper;
	}
		
	// U방향으로 접은 걸 펼칠 때
	public static int[][] openU(int[][] paper) {
		int[][] nxtPaper = new int[2*R][C];
		int i;
		for(int j = 0; j < C; j++) {
			// 원래 위치는 그대로 복사
			for(i = 0; i < R; i++) {
				nxtPaper[i][j] = paper[i][j];
			}
			// 펼쳐지는 곳은 원본을 뒤집어서 복사
			for(;i < 2*R; i++) {
				if(paper[i-R][j] == 0 || paper[i-R][j] == 1) {
					nxtPaper[i][j] = paper[i-R][j] + 2;
				}
				else {
					nxtPaper[i][j] = paper[i-R][j] - 2;
				}
			}
		}
		R = 2 * R;
		return nxtPaper;
	}	
	
	// D방향으로 접은 걸 펼칠 때
	public static int[][] openD(int[][] paper) {
		int[][] nxtPaper = new int[2*R][C];
		int i;
		for(int j = 0; j < C; j++) {
			// 펼쳐지는 곳은 원본을 뒤집어서 복사
			for(i = 0; i < R; i++) {
				if(paper[R-1-i][j] == 0 || paper[R-1-i][j] == 1) {
					nxtPaper[i][j] = paper[R-1-i][j] + 2;
				}
				else {
					nxtPaper[i][j] = paper[R-1-i][j] - 2;
				}
			}
			// 원래 위치는 그대로 복사
			for(;i < 2*R; i++) {
				nxtPaper[i][j] = paper[i-R][j];
			}
		}
		R = 2 * R;
		return nxtPaper;
	}	
	
	public static int[][] opener(char cmd, int[][] paper){
		// 방향에 따라 펼치는 함수 호출
		switch(cmd) {
		case 'U':
			paper = openU(paper);
			break;
		case 'D':
			paper = openD(paper);
			break;
		case 'L':
			paper = openL(paper);
			break;
		case 'R':
			paper = openR(paper);
			break;
		}
		return paper;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		K = Integer.parseInt(br.readLine());
		cmd = new char[2*K];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2*K; i++) {
			cmd[i] = st.nextToken().charAt(0);
		}
		
		int h = Integer.parseInt(br.readLine());
		R = 1;
		C = 1;
		int[][] paper = new int[R][C];
		paper[0][0] = h;
		
		// 접은 순서의 역순으로 펼치기
		for(int i = 2*K-1; i >= 0; i--) {
			paper = opener(cmd[i], paper);
		}
		
		// 결과 출력
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(paper[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}