import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-26
@see            https://www.acmicpc.net/problem/23294
@performance
@category       #구현
@note
*/

public class Main {

    static int[] webPageSize;
    static Browser browser;

    static class Browser{
        Deque<Integer> back;
        Deque<Integer> front;
        int currentPage, cache, maxCacheSize;

        public Browser(){
            back = new ArrayDeque<>();
            front = new ArrayDeque<>();
            currentPage = 0;
            cache = 0;
            maxCacheSize = 0;
        }

        public void goBack(){
            if(!back.isEmpty()){
                front.offerFirst(currentPage);
                currentPage = back.pollFirst();
            }
        }

        public void goFront(){
            if(!front.isEmpty()){
                back.offerFirst(currentPage);
                currentPage = front.pollFirst();
            }
        }

        public void access(int webPage){
            if(!front.isEmpty()) {
                int frontSum = 0;
                for (int page : front) {
                    frontSum += webPageSize[page];
                }
                cache -= frontSum;
                front.clear();
            }

            if(currentPage != 0) {
                back.offerFirst(currentPage);
            }
            currentPage = webPage;
            cache += webPageSize[webPage];

            while(cache > maxCacheSize){
                if(!back.isEmpty()){
                    int page = back.pollLast();
                    cache -= webPageSize[page];
                }
            }
        }

        public void compress(){
            if(back.isEmpty()) return;

            Deque<Integer> newBack = new ArrayDeque<>();

            int last = 0;
            int backSum = 0;
            int newBackSum = 0;
            for(int page : back){
                backSum += webPageSize[page];
                if(last == 0) {
                    last = page;
                    continue;
                }
                if(page != last){
                    newBack.offerLast(last);
                    newBackSum += webPageSize[last];
                    last = page;
                }
            }
            newBack.offerLast(last);
            newBackSum += webPageSize[last];

            cache -= backSum;
            cache += newBackSum;
            back = newBack;
        }

        public String printStatus(){
            StringBuilder sb = new StringBuilder();

            sb.append(browser.currentPage).append("\n");
            if(!browser.back.isEmpty()) {
                for(int page : browser.back){
                    sb.append(page).append(" ");
                }
            }
            else{
                sb.append(-1);
            }
            sb.append("\n");

            if(!browser.front.isEmpty()) {
                for(int page : browser.front){
                    sb.append(page).append(" ");
                }
            }
            else{
                sb.append(-1);
            }
            sb.append("\n");

            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        webPageSize = new int[N+1];
        browser = new Browser();
        browser.maxCacheSize = C;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            webPageSize[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);

            switch(op){
                case 'B':
                    browser.goBack();
                    break;
                case 'F':
                    browser.goFront();
                    break;
                case 'A':
                    int page = Integer.parseInt(st.nextToken());
                    browser.access(page);
                    break;
                case 'C':
                    browser.compress();
                    break;
            }
        }

        System.out.print(browser.printStatus());
    }
}