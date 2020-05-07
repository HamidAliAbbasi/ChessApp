package Pieces;

import Application.GameDefinition;

public class Pawn extends ChessPiece {
	
	public Pawn(boolean player1) {
	
		super(player1);
	
	}
	
	public String toString(){
		
		if(player1){
		
			return "wp";
		}
		
		return "bp";
	}
	/**
	 * Using the chess rules of Pawn this method ensures that whatever the inputed 
	 * start position and end position correspond with the moves allowed for Pawn
	 * 
	 * Pawn is allowed to move forward one spot and can only kill in diagonals. And first move is allowed 
	 * to move forward two steps.
	 * 
	 * @author Omar Atieh (oa183)
	 * @author Hamid Abbasi (ha354)
	 * 
	 * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * 
	 * @return true if the move is allowed
	 * @return false if the move is not allowed
	 */

	public boolean validTurn (int[] start, int[] end, GameDefinition game) {

		
		ChessPiece endDestination = game.boardPos[end[0]][end[1]];
		int comparison = start[0] -1;
		if (endDestination == null && end[1] == start[1]) {
			
			if(player1) {
				
			
				if(first && (end[0] == comparison || end[0] ==comparison -1)) {
					
					if(end[0] == comparison -1 && game.boardPos[end[0]+1][end[1]] != null) {
						return false;
					}
					
					enPassant = true;
					first = false;
					return true;
					
				}
				
				if(end[0] == comparison) {
					return true;
				}
				return false;
					
			} else {
				
				comparison = start[0] +1;
				if(first && (end[0] == comparison || end[0] ==comparison +1)) {
					
					if(end[0] == comparison +1 && game.boardPos[end[0]-1][end[1]] != null) {
						return false;
					}
					
					enPassant = true;
					first = false;
					return true;
					
				}
				
				if(end[0] == comparison) return true;
				return false;
			}
		} else {
			if(player1 && start[0] == 3 && Math.abs(start[1] - end[1]) == 1 && start[0] - end[0] == 1) {
				if(game.boardPos[end[0]][end[1]] == null) {
					
					ChessPiece temp = game.boardPos[end[1]+1][end[1]];
					if(temp.enPassant == true && !temp.player1) {
						
						game.boardPos[end[0]+1][end[1]].isDead =true;
						game.boardPos[end[0]+1][end[1]] = null;
						return true;
						
					}
				}
			} else if (!player1 && start[0] == 4 && Math.abs(start[1] - end[1]) == 1 && end[0] - start[0] == 1) {
				if(game.boardPos[end[0]][end[1]] == null) {
					
					ChessPiece temp = game.boardPos[end[1]-1][end[1]];
					if(temp.enPassant == true && temp.player1) {
						
						game.boardPos[end[0]-1][end[1]].isDead =true;
						game.boardPos[end[0]-1][end[1]] = null;
						return true;
						
					}
				}
		}
		

		
		if(game.boardPos[end[0]][end[1]] == null) return false;
		
		
		if(player1 && endDestination.player1) return false;
		
		if(!player1 && !endDestination.player1) return false;
		
		if((start[1] + 1) == end[1] || (start[1] - 1) == end[1]){
			
			if((player1 && end[0] == (start[0] - 1)) || (!player1 && end[0] == (start[0] + 1))) return true;
			
		return false;
		
		}
	}
	
	
	return false;
		
	}
}
