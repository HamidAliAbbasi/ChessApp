
	package Pieces;

	import Application.*;

	public class Queen extends ChessPiece {

		public Queen(boolean player1) {
			super(player1);
		}

		public String toString(){
			if(player1){
				return "wQ";
			}
			return "bQ";
		}
/**
		 * Using the chess rules of Queen this method ensures that whatever the inputed 
		 * start position and end position correspond with the moves allowed for Queen
		 * 
		 * Queen can move in the same moves as rook and bishop combined therefore we call on their
		 * check functions to see if they are allowed.
		 * 
		 * 
		 * @author Omar Atieh (oa183)
		 * 
		 * @param start array of the x and y values of current piece position
		 * @param end array of the x and y values of destination of the piece
		 * @param gameBoard	current gameBoard with all the pieces
		 * 
		 * 
		 * @return true if the move is allowed
		 * @return false if the move is not allowed
		 * 
*/
		public boolean validTurn(int[] start, int[] end, GameDefinition gameBoard) {
			BishopTurnCheck b = new BishopTurnCheck();
			RookTurnCheck r = new RookTurnCheck();
			
			//if its the same coordinates then it means the player did not move
			
			if(start[1] ==end[1] && start[0] ==end[0]){
				return false;
			}
			
			if(b.bvalidTurn(start,end,gameBoard,player1)){
				return true;
			
			//Queen movement is horizontal or lateral, same as Rook	
			}else if(r.rvalidTurn(start, end, gameBoard,player1)){
				return true;
			}else{
				return false;
			}
		}
	}