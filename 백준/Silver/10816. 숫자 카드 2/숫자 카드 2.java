import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static Integer[] numCard;
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	
	public static void binarySearch(int target) {
		int start = 0;
		int end = N;
		int lowerIdx = 0;
		int upperIdx = 0;
		
		while(start < end) {
			int mid = start + (end - start) / 2;
			if(target <= numCard[mid]) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		lowerIdx = start;
		start = 0;
		end = N;
		
		while(start < end) {
			int mid = start + (end - start) / 2;
			if(target < numCard[mid]) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		upperIdx = start;
		
		sb.append(upperIdx - lowerIdx + " ");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		numCard = list.toArray(new Integer[N]);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			binarySearch(Integer.parseInt(st.nextToken()));
		}

		System.out.println(sb.toString());
		br.close();
	}

}