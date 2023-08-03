import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] isPrime;
	static int n;
	
	public static void makeIsPrime() {
		int size = n == 8 ? 7 : n;
		isPrime = new boolean[(int) Math.pow(10, size)];
		
		for(int i = 2; i < (int) Math.pow(10, size); i++) {
			isPrime[i] = true;
		}
		
		for(int i = 2; i < (int) Math.pow(10, size); i++) {
			if(isPrime[i]) {
				for(int j = i*2; j < (int) Math.pow(10, size); j += i) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
	
		// 에라토스테네스의 채로 소수 판별 배열 생성, 다만 n이 8일 때는 7자리까지만 
		makeIsPrime();
		
		if(n != 8) {
			MainLoop1:
			for(int num = (int) Math.pow(10, n-1); num < (int) Math.pow(10, n); num++) {
				int temp = num;
	
				while(temp > 0) {
					if(!isPrime[temp]) {
						continue MainLoop1;
					}
					temp = temp / 10;
				}
				sb.append(num + "\n");
			}
		}
		else {
			MainLoop2:
			for(int num = (int) Math.pow(10, n-1); num < (int) Math.pow(10, n); num++) {
				for(int i = (int) Math.pow(10, n-1); i > 1; i /= 10) {
					int now = num / i;
					if(!isPrime[now]) {
						num += i;
						num--;
						continue MainLoop2;
					}
				}

				for(int div = 2; div <= (int)Math.sqrt(num); div++) {
					if(num % div == 0) {
						continue MainLoop2;
					}
				}
				sb.append(num + "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}