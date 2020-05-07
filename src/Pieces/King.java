package Pieces;

import Application.*;

public class King extends ChessPiece {

	public King(boolean player1) {
		super(player1);
	}


	public String toString(){
		if(player1){
			return "wK";
		}
		return "bK";
	}
	/** 
	 * 
	 * @author Hamid Abbasi (ha354)
	 * 
	 * King method accounts for two things, he is allowed to move only one space in all directions, However 
	 * they are not allowed to have in a spot where a player can kill them next turn. Also accounts for castling 
	 * explained below where they switch spots with the rook
	 * 
	 * 
	 * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * 
	 * 
	 * @return true if the move is allowed
	 * @return false if the move is not allowed
*/
	public boolean validTurn(int[] start, int[] end, GameDefinition gameBoard) {
	
		ChessPiece destPiece = gameBoard.boardPos[end[0]][end[1]];
		
		
		
		/**
		 * Handles Castling is whenCastling is a move in the game of chess involving a player's 
		 * king and either of the player's original rooks. 
		 * It is the only move in chess in which a player moves two pieces in the same move, 
		 * and it is the only move aside from the knight's move where a piece can be said to "jump over" another.
		 */
		/*
		 * start[0] is the Y component and start[1] is the x
		 */
		int m = start[1]+2;
		int n = start[1]+1;
		int o = start[1]-2;
		int p = start[1]-1;
		//exchange of the right one rook
		if(first && start[0] == end[0]){
			if(end[1] == m){ 
				for(int i = n; i < 7; i++){
					ChessPiece tempPiece = gameBoard.boardPos[end[0]][i];
					if(tempPiece != null){
						return false;
					}
				}
				//Grabbing positioning of the rookPiece
				ChessPiece rookPiece = gameBoard.boardPos[end[0]][7];
				if(!rookPiece.first){
					return false;
				}
				
				gameBoard.boardPos[end[0]][end[1]-1] = rookPiece;
				gameBoard.boardPos[end[0]][7] = null;
				first = false;
				rookPiece.first = false;
				return true;
				
				//Exchange if possible with the left rook piece
				
				
				
				
			}else if(end[1] == o){ 
				for(int i = p; i > 0; i--){
					ChessPiece tempPiece = gameBoard.boardPos[end[0]][i];
					if(tempPiece != null){
						return false;
					}
				}
				ChessPiece rookPiece = gameBoard.boardPos[end[0]][0];
				if(!rookPiece.first){
					return false;
				}
				
				gameBoard.boardPos[end[0]][end[1]+1] = rookPiece;
				gameBoard.boardPos[end[0]][0] = null;
				first = false;
				rookPiece.first = false;
				return true;
			}
		}
		//Finished with castleing
		
		
		//Cannot not move at all 
		if(start[1] == end[1] && start[0] == end[0]){
			return false;
		}
		

        if(destPiece != null){
			if(player1 && destPiece.player1) {
				return false;
			}
			if(!player1 && !destPiece.player1) {
				return false;
			}
		}
		if(Math.abs(start[0]-end[0]) > 1){
			return false;
		}
		if(Math.abs(start[1]-end[1]) > 1){
			return false;
		}
		
		
		
		/*
		 * check to see if you can can move up or down one space or left or right one space
		 * and diagonals
		 */
		
		
		// up and down 
		if(start[1] == end[1]){ 
			if(start[0] != end[0]+1 && start[0] != end[0]-1){
				return false;
			}
			
			
			/// left or right 
		}else if(start[0] == end[0]){ 
			if(start[1] != end[1]+1 && start[1] != end[1]-1){
				return false;
			}
			
			//Diagonals
		}else if(start[0] == end[0]+1 || start[0] == end[0]-1){ 
			if(start[1] != end[1]+1 && start[1] != end[1]-1){
				return false;
			}
		}
		
		if(!gameBoard.checkStatus()){
			first = false;
		}

		return true;
	}

	
	
	
	
	
}