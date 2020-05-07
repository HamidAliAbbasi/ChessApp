package Pieces;

import Application.GameDefinition;

/**
 * Abstract class for a ChessPiece. 
 * Class is meant to be overridden by each chess piece as each piece has their own class
 * player1 is a boolean of if it is player1 or player2
 * 
 * first is a boolean of its the first turn
 * 
 * isDead is a boolean determining if the piece has been killed
 * 
 * 
 * 
 * @author Omar Atieh (oa183)
 * @author Hamid Abbasi (ha354)
 */	

public abstract class ChessPiece {
	
	public boolean player1;
	public boolean enPassant = false; 
	boolean isDead = false;
	boolean first = true;
	
	
	public ChessPiece(boolean player1){
		this.player1 = player1;
	
	}
/**
 * @author Hamid Abbasi (ha354)
 * @param start array of xy start position
 * @param end array of xy end position
 * @param game instance of the current gameboard
 * @return boolean true if move is legal or illegal
 */
	public abstract boolean validTurn(int[] start, int[] end, GameDefinition gameBoard);

}
