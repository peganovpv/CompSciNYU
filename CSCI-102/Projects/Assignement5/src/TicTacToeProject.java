public class TicTacToeProject {
	static final int X =1, O=2;
	static final int NUM_COLUMNS = 3;
	static final int NUM_IN_ROW=3;
	static int p1,p2,p3=0;
	static long cnt=0;

	public static void main(String[] args) {
		
		for (int k=0; k<5; k++) {
			int[ ][ ] list = new int[NUM_COLUMNS][NUM_COLUMNS];
			p1=0;p2=0;p3=0;cnt=0;
	 
			switch(k) {
			case 0:   Play(list , X); break;// no first move
			case 1: list[0][0]=X; Play(list , O);break; // corner
			case 2: list[0][1]=X; Play(list , O);break; // side
			case 3: list[1][1]=X; Play(list , O);break; //middle
			case 4: list[2][0]=X; Play(list , O);break; // corner
			}
			 
			System.out.println("X-wins: "+p1 +" O-Wins:"+p2   +" Draws:"+p3 +"  Recursion calls: " + cnt);
			 
		}
	}
	public static void Play(int[][] inlist, int inplayer) {
		
		int res=checkBoard(inlist, 3-inplayer);
		// 0 - board full, 1- X wins  2 = O wins   3-keep playing
		if (res < 3) { 
			if (res == 0) {p3++; return ;
			} else {
			 	if (res == X) {p1++; return ;} else {p2++; return ;}
			}
		}	
		 
		cnt++;
		// for each space that can be the next move
		//    make a copy of board (next lines)	
		//   update the board for this move
		
		for (int row = 0; row<NUM_COLUMNS;row++  ){
			for (int col = 0; col<NUM_COLUMNS;col++  ){
				if (inlist[row][col] == 0) {
					int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
					for (int x = 0;x <NUM_COLUMNS;x++  ){
						for (int y = 0; y<NUM_COLUMNS;y++  ){
							clonelist[x][y] = inlist[x][y] ;
						}
					}
					clonelist[row][col] = inplayer;
				//  recursively call Play
				    Play(clonelist,   3-inplayer);
				}
			}
		}
		
		return  ;
	}
	public static boolean isFull(int[][] inlist ){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	public static int checkBoard(int[][] inlist , int player){
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == player) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return player;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == player) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return player;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == player) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return player;}	 
			}  else {
				colcnt =0;
			}
		}
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == player) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return player;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
