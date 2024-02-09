import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-09
@see            https://www.acmicpc.net/problem/16434
@performance    
@category       #이분탐색
@note          
*/

public class Main {

    static class Hero{
        long maxHp;
        long curHp;
        long atk;

        public Hero(long maxHp, long atk) {
            this.maxHp = maxHp;
            this.curHp = maxHp;
            this.atk = atk;
        }

        public boolean enterRoom(Room room){
            if(room.t == 1){
                return fight(room.a, room.h);
            }
            else{
                takePotion(room.a, room.h);
                return true;
            }
        }

        private boolean fight(long a, long h){
            long atkNum = h / this.atk;
            if(h % this.atk == 0) atkNum--;

            if(this.curHp - a * atkNum <= 0){
                return false;
            }
            this.curHp -= a * atkNum;
            return true;
        }

        private void takePotion(long a, long h){
            this.atk += a;
            this.curHp = Math.min(this.curHp + h, this.maxHp);
        }
    }

    static class Room{
        long t;
        long a;
        long h;

        public Room(long t, long a, long h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    static int N, firstAtk;
    static Room[] rooms;

    public static long binarySearch(){
        long s = 1;
        long e = Long.MAX_VALUE;

        while(s < e){
            long mid = s + (e - s) / 2;

            if(isSuccess(mid)){
                e = mid;
            }
            else{
                s = mid + 1;
            }
        }
        return s;
    }

    public static boolean isSuccess(long hp){
        Hero hero = new Hero(hp, firstAtk);

        for(Room room: rooms){
            if(!hero.enterRoom(room)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        firstAtk = Integer.parseInt(st.nextToken());

        rooms = new Room[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(t, a, h);
        }

        System.out.println(binarySearch());
        br.close();
    }
}