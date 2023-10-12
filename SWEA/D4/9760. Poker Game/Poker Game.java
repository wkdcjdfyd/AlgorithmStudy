import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 12.
@see			https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AXEN3aEKDrsDFAVX
@performance	
@category 		#
@note			
*/

public class Solution {
	
	static final int CARD_NUM = 5;
	
	static final int SPADE = -1;
	static final int DIAMOND = -2;
	static final int HEART = -3;
	static final int CLOVER = -4;
	
	static final int A = 1;
	static final int T = 10;
	static final int J = 11;
	static final int Q = 12;
	static final int K = 13;
	
	static class Card implements Comparable<Card>{
		int suit;
		int rank;
		
		public Card(int suit, int rank) {
			this.suit = suit;
			this.rank = rank;
		}

		@Override
		public int compareTo(Card o) {
			return Integer.compare(this.rank, o.rank);
		}
	}
	
	public static int convertSuit(char c) {
		switch(c) {
			case 'S':
				return SPADE;
			case 'D':
				return DIAMOND;
			case 'H':
				return HEART;
			case 'C':
				return CLOVER;
		}
		return c;
	}
	
	public static int convertRank(char c) {
		switch(c) {
			case 'A':
				return A;
			case 'T':
				return T;
			case 'J':
				return J;
			case 'Q':
				return Q;
			case 'K':
				return K;
		}
		return c - '0';
	}
	
	public static String getCardHand(Card[] cards, int[] suitCnt, int[] rankCnt) {
		int suitMax = 0;
		int rankMax = 0;
		int isRankTwoCnt = 0;
		boolean isRankContinuous = true;
		
		for(int suit = 1; suit < suitCnt.length; suit++) {
			suitMax = Math.max(suitCnt[suit], suitMax);
		}
		
		for(int rank = 1; rank < rankCnt.length; rank++) {
			rankMax = Math.max(rankCnt[rank], rankMax);
			if(rankCnt[rank] == 2) {
				isRankTwoCnt++;
			}
		}
		
		for(int i = 1; i < CARD_NUM; i++) {
			if(cards[i-1].rank + 1 != cards[i].rank) {
				isRankContinuous = false;
			}
		}
		
		if(suitMax == 5) {
			if(isRankContinuous) {
				return "Straight Flush";
			}
			return "Flush";
		}
		else if(isRankContinuous) {
			return "Straight";
		}
		else if(rankMax == 4) {
			return "Four of a Kind";
		}
		else if(rankMax == 3) {
			if(isRankTwoCnt > 0) {
				return "Full House";
			}
			return "Three of a kind";
		}
		else if(isRankTwoCnt == 2){
			return "Two pair";
		}
		else if(isRankTwoCnt == 1) {
			return "One pair";
		}
		else {
			return "High card";
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			Card[] cards = new Card[5];
			int[] suitCnt = new int[5];
			int[] rankCnt = new int[15];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < CARD_NUM; i++) {
				String s = st.nextToken();
				
				int suit = convertSuit(s.charAt(0));
				int rank = convertRank(s.charAt(1));
				
				suitCnt[-suit]++;
				rankCnt[rank]++;
				cards[i] = new Card(suit, rank);
			}
			Arrays.sort(cards);
			
			String ans = getCardHand(cards, suitCnt, rankCnt);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}