import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 			Ryong
@since 				2023. 8. 4.
@see				https://www.acmicpc.net/problem/2023
@performance		12208kb	88ms
@category 			#소수판정
@note
1. 메모리
메모리 제한 : 4MB = 4,000,000 Byte
'에라토스테네스의 체' 방법을 사용해 소수를 구하려면 배열을 구하려는 수만큼의 길이로 만들어야한다.
Boolean(1Byte) 배열을 선언할 경우  4,000,000 이하의 길이로 선언해야 한다.
따라서 N = 6, N = 7일 경우 메모리 초과가 발생할 수 있다.
-----> '에라토스테네스의 체' 방법 사용 불가!
--------------------------------------------------------------------------
2. 시간
시간제한 : 2초
어떤 숫자(N)가 소수인지 판별하려면 2부터 root(N)까지의 수로 N를 나눴을 때 전부 나머지가 0인지 확인해야 한다. -> O(root(N))
X이하의 모든 소수를 구해야 한다면 O(N(root(N))

WORST CASE - N = 8일 때 예상 동작
for 10^7이상 10^8미만의 수를 확인			-> 순회 횟수 : 10^8 - 10^7 번
	for자리수 별로 잘라서 확인				-> 순회 횟수 : 8번
		for 자리수 만큼 자른 수(X) 확인	-> 순회 횟수 : root(X)
		
X가 계속 바뀌지만 고려하지 않는다면 (10^8 - 10^7) * 8 * root(X) 만큼 순회한다.
보통 1초에 1억 정도로 고려하므로 2억번 이하면 가능하지만 X의 값이 충분히 큰 최악의 상황을 가정하면 2억번을 초과한다.
--------------------------------------------------------------------------
3. Solution

어떤 수를 확인 할 때 맨 앞자리의 수는 반드시 소수인지 확인하게 된다.
7331이 신기한 소수인지 확인할 때 733 73 7 모두 소수인지 확인한다.
한자리 수가 소수인지 미리 판별해 놓고 맨 앞자리의 수가 소수가 아니라면 그 수로 시작하는 수를 전부 건너뛴다.

위와 비슷하게 맨 앞자리 수 뿐 아니라 다른 자리 수도 적용해 볼 수 있다.

이 방법을 사용하면 위에서 계산했던 WORST CASE 예시에서 가장 바깥의 10^7이상 10^8 의 횟수를 줄일 수 있다.
한자리수인 소수는 2, 3, 5, 7 밖에 없으므로 절반이상으로 줄일 수 있고 다른 자리수에서도 추가적으로 줄일 수 있으므로 시간초과에서 벗어날 수 있다!
*/

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		MainLoop:
		for(int num = (int) Math.pow(10, n-1); num < (int) Math.pow(10, n); num++) {
			for(int scale = (int) Math.pow(10, n-1); scale > 0; scale /= 10) {
				int now = num / scale;
				if(now == 1) {
					num += scale;
					num--;
					continue MainLoop;
				}

				// 2부터 루트 now까지 나머지 없이 나눠지는지 확인
				for(int div = 2; div <= (int)Math.sqrt(now); div++) {
					if(now % div == 0) {
						num += scale;
						num--;
						continue MainLoop;
					}
				}
			}
			sb.append(num + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}