import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		List<String> arr = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			arr.add(br.readLine());
		}
		Collections.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() != o2.length()?o1.length() - o2.length():o1.compareTo(o2);
			}
		});
		for(int i=arr.size()-1; i >= 1; i--) {
			if(arr.get(i).equals(arr.get(i-1))) {
				arr.remove(i);
			}
		}
		
		for(String s: arr) {
			sb.append(s + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
