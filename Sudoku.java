package sudoku;

import java.util.ArrayList;
import java.util.HashSet;

public class Sudoku {

	private ArrayList<ArrayList<HashSet<Integer>>>  board;
	
	@SuppressWarnings("unchecked") //Used for clone
	
	/** Constructs Sudoku obj */
	public Sudoku(){
		board = new ArrayList<ArrayList<HashSet<Integer>>>(9);
		HashSet<Integer> temp = new HashSet<Integer>();
		for(int i = 1; i < 10; i++)
			temp.add(i);
		for(int i = 0; i < 9; i ++){
			board.add(new ArrayList<HashSet<Integer>>(9));
			for(int q = 0; q < 9; q++)
				board.get(i).add((HashSet<Integer>)temp.clone());
		}
		reduceInitialSquares();
	}
	
	private void reduceInitialSquares(){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int w = 0; w < 3; w++)
			temp.add(w);
		for(int i = 0; i < 3; i++) {
			ArrayList<Integer> valuesToPlace = new ArrayList<Integer>(9);
			for(int q = 1; q < 10; q++)
				valuesToPlace.add(q);
			int curVal = temp.get((int)(Math.random() * temp.size()));
			temp.remove(new Integer(curVal));
			int row = i  * 3;
			for(int e = row; e < row + 3; e++){
				for(int col = curVal * 3; col < (curVal * 3 + 3); col++){
					int tempVal = valuesToPlace.get((int)(Math.random() * valuesToPlace.size()));
					HashSet<Integer> t = new HashSet<Integer>(1);
					t.add(tempVal);
					board.get(e).set(col, t);
					removeRowAndCol(e, col, tempVal);
					valuesToPlace.remove(new Integer(tempVal));
				}
			}
			
		}
		
		
	}
	
	private void removeRowAndCol(int rowStart, int colStart, int val){
		for(int i = 0; i < board.size(); i++){
			for(int q = 0; q < board.get(i).size(); q++){
				if(i == rowStart ^ q == colStart){
					board.get(i).get(q).remove(val);
				}
			}
		} 
	}
	
	public String toString(){
		String res = "";
		for(int i = 0; i < board.size(); i++){
			for(int q = 0; q < board.get(i).size(); q++){
				if(q != 0 && q % 3 == 0)
					res += "| ";
				res += board.get(i).get(q) + " ";
			} 
			if((i+1) % 3 == 0)
				res += "\n------|-------|------";
			res += "\n";
		} 
		return res;
		//return board.toString();
	}
	
}
