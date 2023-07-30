import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		boolean[][] arr = new boolean[H][W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				arr[j][i] = true;
			}
		}
		int total = 0;
		for(int i = 0; i < H; i++) {
			int start = -1;
			for(int j = 0; j < W; j++) {
				if(start == -1 && arr[i][j]) {
					start = j;
				}
				else if(start != -1) {
					if(arr[i][j]) {
						if(j == start + 1) {
							start = j;
						}
						else {
							total += j - start - 1;
							if(j != W - 1 && !arr[i][j+1]) {
								start = j;
							}
							else {
								start = -1;
							}
						}
					}
				}
			}
		}
		System.out.println(total);
		br.close();
	}

}