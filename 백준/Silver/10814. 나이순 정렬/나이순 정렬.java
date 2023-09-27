import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 27.
@see			https://www.acmicpc.net/problem/10814
@performance	
@category 		#정렬
@note			
*/

public class Main {
	
	static class User implements Comparable<User>{
		int age, time;
		String name;
		
		public User(int age, String name, int time) {
			this.age = age;
			this.name = name;
			this.time = time;
		}
		
		@Override
		public int compareTo(User o) {
			if(this.age == o.age) {
				return Integer.compare(this.time, o.time);
			}
			return Integer.compare(this.age, o.age);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		User[] users = new User[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			users[i] = new User(age, name, i);
		}
		Arrays.sort(users);
		
		for(int i = 0; i < n; i++) {
			sb.append(users[i].age).append(" ").append(users[i].name).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}