import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-14
@see            https://www.acmicpc.net/problem/18115
@performance
@category       #구현
@note
*/

public class Main {

    static Card head;
    static Card tail;

    static class Card{
        Card prev;
        Card nxt;
        int num;

        public Card() {
            this.prev = null;
            this.nxt = null;
            this.num = 0;
        }
    }

    public static void init(){
        head = new Card();
        tail = new Card();
        head.nxt = tail;
        tail.prev = head;
    }

    public static void addFirst(int n){
        Card card = new Card();
        card.nxt = head.nxt;
        card.prev = head;
        card.num = n;

        head.nxt.prev = card;
        head.nxt = card;
    }

    public static void addSecond(int n){
        Card card = new Card();
        card.nxt = head.nxt.nxt;
        card.prev = head.nxt;
        card.num = n;

        head.nxt.nxt.prev = card;
        head.nxt.nxt = card;
    }

    public static void addLast(int n){
        Card card = new Card();

        card.nxt = tail;
        card.prev = tail.prev;
        card.num = n;

        tail.prev.nxt = card;
        tail.prev = card;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = N-1; i >= 0; i--){
            int n = N - i;

            switch (arr[i]){
                case 1:
                    addFirst(n);
                    break;
                case 2:
                    addSecond(n);
                    break;
                case 3:
                    addLast(n);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        Card now = head.nxt;
        while(true){
            sb.append(now.num).append(" ");
            now = now.nxt;
            if(now.num == 0) break;
        }

        System.out.println(sb);
        br.close();
    }
}