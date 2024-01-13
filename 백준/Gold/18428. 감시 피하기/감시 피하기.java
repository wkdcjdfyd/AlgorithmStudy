import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-13
@see            https://www.acmicpc.net/problem/18428
@performance
@category       #조합
@note
*/

public class Main {

    static int N;
    static char[][] graph;
    static ArrayList<int[]> teacherLoc;

    public static void combination(int nth, int startIdx){
        if(nth == 3){
            if(isOk()){
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for(int i = startIdx; i < N*N; i++){
            int x = i / N;
            int y = i % N;
            if(graph[x][y] == 'X'){
                graph[x][y] = 'O';
                combination(nth+1, startIdx+1);
                graph[x][y] = 'X';
            }
        }
    }

    public static boolean isOk(){
        boolean flag = true;
        for(int[] loc : teacherLoc){
            for(int i = loc[0]+1; i < N; i++){
                if(graph[i][loc[1]] == 'S') return false;
                else if(graph[i][loc[1]] == 'T' || graph[i][loc[1]] == 'O') break;
            }
            for(int i = loc[0]-1; i >= 0; i--){
                if(graph[i][loc[1]] == 'S') return false;
                else if(graph[i][loc[1]] == 'T' || graph[i][loc[1]] == 'O') break;
            }

            for(int j = loc[1]+1; j < N; j++){
                if(graph[loc[0]][j] == 'S') return false;
                else if(graph[loc[0]][j] == 'T' || graph[loc[0]][j] == 'O') break;
            }
            for(int j = loc[1]-1; j >= 0; j--){
                if(graph[loc[0]][j] == 'S') return false;
                else if(graph[loc[0]][j] == 'T' || graph[loc[0]][j] == 'O') break;
            }
        }

        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        teacherLoc = new ArrayList<>();

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = st.nextToken().charAt(0);
                if(graph[i][j] == 'T'){
                    teacherLoc.add(new int[]{i, j});
                }
            }
        }

        combination(0, 0);
        System.out.println("NO");
        br.close();
    }
}