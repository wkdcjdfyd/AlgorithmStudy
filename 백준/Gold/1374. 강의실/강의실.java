import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* @author         Ryong
* @since          2024-01-23
* @see            https://www.acmicpc.net/problem/1374
* @performance
* @category       #정렬
* @note
*/

public class Main {

    static int N;
    static Lecture[] lectures;

    static class Lecture{
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean isIn(int time){
            return this.start <= time && time < this.end;
        }
    }

    public static int counter(int time){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(lectures[i].isIn(time)) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N];
        PriorityQueue<Lecture> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(start, end);
        }

        Arrays.sort(lectures, Comparator.comparingInt(o -> o.start));

        int ans = 0;
        for(int i = 0; i < N; i++){
            while(!pq.isEmpty() && pq.peek().end <= lectures[i].start){
                pq.poll();
            }
            pq.offer(lectures[i]);
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
        br.close();
    }
}