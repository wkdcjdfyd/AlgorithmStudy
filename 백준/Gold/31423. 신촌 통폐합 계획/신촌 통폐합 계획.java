import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] parents;
    static int result;
    static LinkedList[] group;

    static class Node{
        Node prev;
        Node next;
        String s;

        public Node(String s) {
            this.prev = null;
            this.next = null;
            this.s = s;
        }
    }

    static class LinkedList{
        Node head;
        Node tail;

        public LinkedList(Node start) {
            this.head = new Node(null);
            this.tail = new Node(null);

            this.head.next = start;
            start.prev = this.head;
            this.tail.prev = start;
            start.next = this.tail;
        }

        public void append(LinkedList list){
            list.head.next.prev = this.tail.prev;
            this.tail.prev.next = list.head.next;

            list.tail.prev.next = this.tail;
            this.tail.prev = list.tail.prev;
        }
    }

    public static boolean union(int x, int y){
        int px = find(x);
        int py = find(y);

        if(px == py) return false;
        parents[py] = px;
        result = px;
        group[px].append(group[py]);
        return true;
    }

    public static int find(int x){
        if(parents[x] == x) return x;
        return parents[x] = find(x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        group = new LinkedList[N+1];

        for(int i = 1; i < N+1; i++){
            String name = br.readLine();
            parents[i] = i;
            group[i] = new LinkedList(new Node(name));
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        LinkedList linkedList = group[result];
        Node now = linkedList.head.next;

        while(now.s != null){
            sb.append(now.s);
            now = now.next;
        }

        System.out.println(sb);
        br.close();
    }
}