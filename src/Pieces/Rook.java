package Pieces;

import Application.*;
/**
 * 
 * 
 * @author Hamid Abbasi (ha354)
 * implements rook chess piece
 * 
 *
 */
public class Rook extends ChessPiece{

	public Rook(boolean player1) {
		super(player1);
	}

	
	public String toString(){
		if(player1){
			return "wR";
		}
		return "bR";
	}
	
	/**
	 * @author Hamid Abbasi (ha354)
	 * 
	 * creates an instance of RookTurnCheck r. And Passes in the following parameters that 
	 * are needed to determine if the rook piece is allowed to move given the start and end position
	 * 
     * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * @return true or false depending if the check was true or false
	 * 
	 */
	
	public boolean validTurn(int[] start, int[] end, GameDefinition gameBoard) {
		RookTurnCheck r = new RookTurnCheck();
	
		return r.rvalidTurn(start, end, gameBoard, player1);
}
	}