package Pieces;

import Application.GameDefinition;

class BishopTurnCheck {
	/**
	 * Using the chess rules of bishop this method ensures that whatever the inputed 
	 * start position and end position correspond with the moves allowed for Bishop
	 * 
	 * Bishops are allowed to move in diagonals in any direction as many places as they want
	 * method also checks for in proper start and end coordinants as well as obstruction from different pieces
	 * 
	 * 
	 * @author Omar Atieh (oa183)
	 * 
	 * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * @param player1 boolean value of which player it is (white or Black)
	 * 
	 * @return true if the move is allowed
	 * @return false if the move is not allowed
	 */
	
	
	public boolean bvalidTurn(int[] start, int[] end, GameDefinition gameBoard, boolean player1) {
		boolean right;
		boolean up;
		boolean left;
		boolean down;
		//This piece will only move diagonally
		ChessPiece bishopDest = gameBoard.boardPos[end[0]][end[1]];
	
		if(start[1] > end[1]){
			right = false;
			left = true;
		}else{
			right = true;
			left = false;
		}
		
		if(start[0] > end[0]){
			up = true;
			down =false;
		}else{
			up = false;
			down = true;
		}
		
		//Check if the player even moved
		if(start[1] == end[1] || start[0] == end[0]){
			return false;
		}
	
		if(Math.abs(start[1] - end[1]) != Math.abs(start[0] - end[0])){
			return false;
		}
		

		if(bishopDest == null){
			if(!down){
				if(!left){
					//up right
					int tempY = start[0]-1;
					int x = start[1]+1;
					for(int tempX = x; tempX < end[1]; tempX++){
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY--;
					}
				}else{
					//up left
					int tempY = start[0]-1;
					int x = start[1]-1;
					for(int tempX = x; tempX > end[1]; tempX--){
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY--;
					}
				}
			}else{
				if(right){
					// down right
					int tempY = start[0]+1;
					int X = start[1]+1;
					for(int tempX = X; tempX < end[1]; tempX++){
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY++;
					}
				}else{
					//see if you can move down left
					int tempY = start[0]+1;
					int t = start[1]-1;
					for(int tempX = t; tempX > end[1]; tempX--){			
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY++;
					}
				}
			}
			
		//	attacking
		}else{ 
			if(player1 && bishopDest.player1) {
				return false;
			}
			if(!player1 && !bishopDest.player1) {
				return false;
			}
			if(up){
				if(!left){
					//up right for obstruction
					int tempY = start[0]-1;
					int t = start[1]+1;
					for(int tempX = t; tempX < end[1]; tempX++){
							if(gameBoard.boardPos[tempY][tempX] != null){
								return false;
							}
						tempY--;
					}
				}else{
					//up left for obstruction
					int tempY = start[0]-1;
					int tx =start[1]-1;
					for(int tempX = tx; tempX > end[1]; tempX--){
							if(gameBoard.boardPos[tempY][tempX] != null){
								return false;
							}
						
					}
				}
				
				
			}else{
				if(right){
				
					int tempY = start[0]+1;
					int ty =start[1]+1;
					for(int tempX = ty; tempX < end[1]; tempX++){
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY++;
					}
				}else{
					// down left
					int tempY = start[0]+1;
					int ty = start[1]-1;
					for(int tempX = ty; tempX > end[1]; tempX--){
						if(gameBoard.boardPos[tempY][tempX] != null){
							return false;
						}
						tempY++;
					}
				}
			}
		}
		return true;
	}
}
