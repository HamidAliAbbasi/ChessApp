package Pieces;

import Application.GameDefinition;

/**
 * 
 * 
 * @author Hamid Abbasi (ha354)
 * implements Bishop ChessPiece
 * 
 *
 */
public class Bishop extends ChessPiece {

	public Bishop(boolean player1) {
		super(player1);
	}
	public String toString(){
		if(player1){
			return "wB";
		}
		return "bB";
	}

	/**
	 * @author Hamid Abbasi (ha354)
	 * 
	 * creates an instance of BishopTurnCheck b. And Passes in the following parameters that 
	 * are needed to determine if the Bishop piece is allowed to move given the start and end position
	 * 
     * @param start array of the x and y values of current piece position
	 * @param end array of the x and y values of destination of the piece
	 * @param gameBoard	current gameBoard with all the pieces
	 * 
	 * @return true or false depending if the check was true or false
	 * 
	 */
	public boolean validTurn(int[] start, int[] end, GameDefinition gameBoard) {

	BishopTurnCheck b = new BishopTurnCheck();
	return b.bvalidTurn(start, end, gameBoard, player1);



	}
}