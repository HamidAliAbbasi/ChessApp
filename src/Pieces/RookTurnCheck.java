package Pieces;

import Application.GameDefinition;

class RookTurnCheck {
	/**
	 * Using the chess rules of rook this method ensures that whatever the inputed 
	 * start position and end position correspond with the moves allowed for rook
	 * 
	 * Rooks are only allowed to move left and right as many places as they want
	 * method also checks for in proper start and end cooridants as well as obstruction from pieces
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
	
	public boolean rvalidTurn(int[] start, int[] end, GameDefinition gameBoard, boolean player1) {
		boolean right;
		boolean left;
		boolean up;
		boolean lateral;
		boolean down;

		//Checking if it is moving in a straight line
		if(start[1]== end[1] && start[0] == end[0]){
			return false;
		}
		
		//Diagonal movement
		if(start[1]!= end[1] && start[0] != end[0]){ 
			return false;
		}
		
		ChessPiece destPiece = gameBoard.boardPos[end[0]][end[1]];
		if(start[0] != end[0]){
			lateral = true;
		}else{
			lateral = false;
		}
		if(start[1]> end[1]){
			right = false;
			left = true;
		}else{
			right = true;
			left = false;
		}
		
		if(start[0] > end[0]){
			up = true;
			down = false;
		}else{
			up = false;
			down = true;
		}
		
		
	
		
		if(destPiece == null){ 
			if(lateral){
				if(!down){ 
					for(int i = start[0]-1; i > end[0]; i--){
						if(gameBoard.boardPos[i][end[1]] != null){
							return false;
						}
					}
					
				/*
				 * look downward laterally to see if any pieces in the way
				 */
				}else{ 
					for(int i = start[0]+1; i < end[0]; i++){
						if(gameBoard.boardPos[i][end[1]] != null){
							return false;
						}
					}
				}
				
				/*
				 * Checks lateral horizontal to right if obstruction
				 */
				
			}else{
				if(!left){ 
					for(int i = start[1]+1; i < end[1]; i++){
						if(gameBoard.boardPos[end[0]][i] != null){
							return false;
						}
					}
				}else{  //looking left
					for(int i = start[1]-1; i > end[1]; i--){
						if(gameBoard.boardPos[end[0]][i] != null){
							return false;
						}
					}
				}
			}
		}else{
			//Attacking 
			if(player1 && destPiece.player1) return false;
			if(!player1 && !destPiece.player1) return false;
			if(lateral){
				
				//checks up
				if(up){ 
					for(int i = start[0]-1; i > end[0]; i--){
						if(gameBoard.boardPos[i][end[1]] != null){
							return false;
						}
					}
					
					//checks down
				}else{ 
					for(int i = start[0]+1; i < end[0]; i++){
						if(gameBoard.boardPos[i][end[1]] != null){
							return false;
						}
					}
				}
			}else{
				
				//check right obstruction
				if(right){ 
					for(int i = start[1]+1; i < end[1]; i++){
						if(gameBoard.boardPos[end[0]][i] != null){
							return false;
						}
					}
					
					//check left obstruction
				}else{  
					for(int i = start[1]-1; i > end[1]; i--){
						if(gameBoard.boardPos[end[0]][i] != null){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
