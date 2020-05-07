package Pieces;

import Application.GameDefinition;

public class Knight extends ChessPiece {
	
	public Knight(boolean player1) {
		super(player1);
	}

	
	public String toString(){
		if(player1){
			return "wN";
		}
		return "bN";
	}
	
	/**
	 * 
	 * 
	 * @author Hamid Abbasi (ha354)
	 * implements the knight piece, The knight piece can move in an L position only and does not need a clear
	 * path just needs the destination to be empty.
	 *
	 * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * @return returns the boolean value if it is an allowed move
	 * 
	 * 
	 * 
	 *
	 */
	public boolean validTurn (int[] start, int[] end, GameDefinition game) {
		
		ChessPiece knight = game.boardPos[end[0]][end[1]];
		int locationX = start[1] +1;
		int locationY = start[0] -2;
		
		if(knight == null) {
		
			if(locationX == end[1] || locationX -2 == end[1]) {
				
				if(locationY == end[0] || locationY+4 == end[0]) {
					return true;
				}
			
			} else if(locationX +1 == end[1] || locationX -3 == end[1]) {
				
				if (locationY+1 == end[0] || locationY+3 == end[0]) {
					return true;
				}
			}
			
		} else {
			
			if(player1 && knight.player1) {
				return false;
			}
			
			if(!player1 && !knight.player1) {
				return false;
			}
			
			if (locationX == end[1] || locationX -2 == end[1]) {
				
				if(locationY == end[0] || locationY+4 == end[0]) {
					return true;
				}
				
			} else if(locationX +1 == end[1] || locationX -3 == end[1]) {
				
				if (locationY+1 == end[0] || locationY+3 == end[0]) {
					return true;
				}
			}
			
		}
		return false;
	}


	
	
}