package Application;

import java.util.ArrayList;

import Pieces.*;

/**
 * This class runs an instance of the game. Methods of this class enable the condition of piece and board to continue. Also has methods for moves.
 *
 * @author Omar Atieh (oa183)
 * @author Hamid Abbasi (ha354)
 */

public class GameDefinition{
	
	public ChessPiece[][] boardPos; 
	boolean player1Turn = true;
	boolean checking = false;
    
    /**
	 * No-arg constructor that initializes the board and add pieces onto the board
	 * @author Hamid Abbasi (ha354)
	 */


	public GameDefinition(){
		boardPos = new ChessPiece[8][8];
		
		boardPos[0][0] = new Rook(false);
		boardPos[0][1] = new Knight(false);
		boardPos[0][2] = new Bishop(false);
		boardPos[0][3] = new Queen(false);
		boardPos[0][4] = new King(false);
		boardPos[0][5] = new Bishop(false);
		boardPos[0][6] = new Knight(false);
		boardPos[0][7] = new Rook(false);
		
		for(int i = 0; i < 8; i++){
			boardPos[1][i] = new Pawn(false);
		}
		

		for(int i = 0; i < 8; i++){
			boardPos[2][i] = null;
			boardPos[3][i] = null;
			boardPos[4][i] = null;
			boardPos[5][i] = null;
		}

		for(int i = 0; i < 8; i++){
			boardPos[6][i] = new Pawn(true);
		}
		
		boardPos[7][0] = new Rook(true);
		boardPos[7][1] = new Knight(true);
		boardPos[7][2] = new Bishop(true);
		boardPos[7][3] = new Queen(true);
		boardPos[7][4] = new King(true);
		boardPos[7][5] = new Bishop(true);
		boardPos[7][6] = new Knight(true);
		boardPos[7][7] = new Rook(true);
		
	}
    
    /**
	 * This method gets a set of coordinates from the input string.
	 * @author Omar Atieh (oa183)
	 * @param str	The input string with the coordinates
	 * @return Array of cooridnates [y][x] which represent file, and rank respectively. Coordinates range from 0-8 inclusively.
	 */

	public static int[] getIndex(String str){
		int x = 0, y;
		int[] coordinates = new int[2];
		
		if(str.charAt(0) == 'a') {
			x = 0;
		}
        
        else if(str.charAt(0) == 'b') {
        	x = 1;
        }
        
        else if(str.charAt(0) == 'c') {
        	x = 2;
        }
        
        else if(str.charAt(0) == 'd') {
        	x = 3;
        }

		else if(str.charAt(0) == 'e') {
			x = 4;
		}
		else if(str.charAt(0) == 'f') {
			x = 5;
		}
        
        else if(str.charAt(0) == 'g') {
        	x = 6;
        }
        
        else if(str.charAt(0) == 'h') {
        	x = 7;
        }
		
		
		y = 8 - (str.charAt(1)-'0');
		
		coordinates[0] = y;
		coordinates[1] = x;
	
		return coordinates;
	}

    /**
	 * This No-arg method prints the chess board.
	 * Call either before or after a move to let the players know the current board condition.
	 * This method returns nothing.
	 *
	 * @author Hamid Abbasi (ha354)
	 */

	public void printChessBoard(){
		
		boolean printBlack = false;
		for(int i = 0; i < 8; i++){
			
			for(int j = 0; j < 8; j++){
				
				if(this.boardPos[i][j] != null) System.out.print(this.boardPos[i][j] + " ");
                
                else{
					
					if (!printBlack){
                       
                        System.out.print("  ");
                    
                    } else {
                        
                        System.out.print("##  ");
                    }
				}
				
				printBlack = !printBlack;

			}
			
			System.out.println(8-i);
			printBlack = !printBlack;
		}
		
		char axis = 'a';

		for(int i = 0; i < 8; i++){
			
			System.out.print(" " + axis + " ");
			axis++;
		}
		
		System.out.println();
		System.out.println();

	}
    
    /**
	 * This method verifies a move. Checks if the move puts the player's king in check, if so move is considered invalid.
	 * As a result, move is not made and the method returns false. However, in case of a valid move, board is updated.
	 *
	 * @author Omar Atieh (oa183)
	 * @param starting	Coordinates of piece to be moved
	 * @param ending	Final coordinates of where to move the piece
	 * @param player1Move	tells whose play it is.
	 * @return false if invalid. True if move is verified and the board is updated.
	 */

	public boolean move(int[] starting, int[] ending, boolean player1Move){
		
		ChessPiece currentPiece = boardPos[starting[0]][starting[1]];

		if(currentPiece == null) return false;
        
        else if((currentPiece.player1 && player1Move) || (!currentPiece.player1 && !player1Move)){
			
			if(currentPiece.validTurn(starting, ending, this)){


				ChessPiece player2Piece = boardPos[ending[0]][ending[1]];
		
				boardPos[ending[0]][ending[1]] = currentPiece;
				boardPos[starting[0]][starting[1]] = null;
				
				for(int i = 0; i < 8; i++){
					
					for(int j = 0; j < 8; j++){
						
						int[] tempStart = {i, j};
						if(isCheck(tempStart, player1Move)){

                            boardPos[starting[0]][starting[1]] = currentPiece;
							boardPos[ending[0]][ending[1]] = player2Piece;
							
							return false;
						
						}
					
					}
					
				}
				return true;
			}else
				
				return false;
		}
		return false;
	}
    
    /**
	 * This method concludes whether the piece moved checks a king.
	 * 
	 * @author Omar Atieh (oa183)
	 * @param starting	Coordinates of the piece potentially putting a king in check
	 * @param player1 	True if king of player1 i.e. white is in check, false otherwise.
	 * @return true if the mentioned piece puts the particular king into check, false otherwise
	 */

	public boolean isCheck(int[] starting, boolean player1){

		int[] ending = getCoordinatesOfKing(player1);
		ChessPiece sourcePiece = boardPos[starting[0]][starting[1]];
		
		if(sourcePiece == null) {
            
            return false;
        }

		checking = true;
		
		if(sourcePiece.validTurn(starting, ending, this)){
			
			checking = false;
			return true;
		}
		
		checking = false;
		return false;
	}
    
    /**
	 * Concludes if game has ended by investigating if the king specified can legally move to get out of the check
	 * returns false if king can move
	 * 
	 * @author Hamid Abbasi (ha354)
	 * @param player1 True if player 1 i.e. white king is in checkmate, false for black king in checkmate.
	 * @return true if no more legal moves left and false otherwise
	 */

	public boolean isCheckMate(boolean player1){
		
		int[] kingPos = getCoordinatesOfKing(player1);
		int[] tempPos = {kingPos[0], kingPos[1]};
		

		tempPos[1] = tempPos[1] - 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }	
		tempPos[0] = tempPos[0] - 1;
		if(kingsMove(kingPos, tempPos, player1)){
            return false;
        } 

		tempPos[1] = tempPos[1] + 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }

		tempPos[1] = tempPos[1] + 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }
		tempPos[0] = tempPos[0] + 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }
		tempPos[0] = tempPos[0] + 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }
		tempPos[1] = tempPos[1] - 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }
		tempPos[1] = tempPos[1] - 1;
		if(kingsMove(kingPos, tempPos, player1)) {
            return false;
        }


        ArrayList<int[]> cantMove = new ArrayList<int[]>();
		
		for(int i = 0; i < 8; i++){
			
			for(int j = 0; j < 8; j++){
				
				int[] tempStart = {i, j};
				ChessPiece dontMove = boardPos[i][j];
				
				if(dontMove != null && dontMove.validTurn(tempStart, kingPos, this)) cantMove.add(tempStart);
			}
		}
		
		
		if(cantMove.size() > 1) {
            
            return true;
        }

		if(!cantMove.isEmpty()){
			
			int[] danger = cantMove.get(0);
			
			for(int i = 0; i < 8; i++){
				
				for(int j = 0; j < 8; j++){
					
					int[] saving= {i, j};
					ChessPiece save = boardPos[i][j];
					
					if(save != null && save.validTurn(saving, danger, this)) return false; 
				}
			}
		}
		
		return true;
	}
	
	/**
	 * This method is for a special pawn capture that can only occur immediately after a pawn makes a move
     * of two squares from its starting square, and it could have been captured by an enemy pawn had it 
     * advanced only one square.
	 * The check is done on all pieces in a row and sets their enPassant values to false, as at that point
	 * the possibility to capture that piece via en passante will have passed
	 * 
	 * @author Omar Atieh (oa183)
	 * @param whiteTurn checks if player 1's turn; helps in which [y] value to use when checking
	 */
	
	public void isenPassant(boolean player1Turn){
		if(!player1Turn){
			
			for(int i = 0; i < 8; i++){
				
				if(boardPos[3][i] != null && !boardPos[3][i].player1) boardPos[3][i].enPassant = false;
				
            }
            
		}else{
			for(int i = 0; i < 8; i++){
				
				if(boardPos[4][i] != null && boardPos[4][i].player1) boardPos[4][i].enPassant = false;
			}
			
		}
	}
    
    public boolean checkStatus(){
		return checking;
    }
    
    /**
	 * Finds coordinates of king in question
	 * 
	 * @author Hamid Abbasi (ha354)
	 * @param player1 true if player1 king, false otherwise
	 * @return coordinates of king in question
	 */

	public int[] getCoordinatesOfKing(boolean player1){
		for(int i = 0; i < 8; i++){
			
			for(int j = 0; j < 8; j++){
				
				ChessPiece currentPiece = boardPos[i][j];
				if(currentPiece != null){
					
					if(King.class.isInstance(currentPiece)){
						
						int[] place = {i, j};
						
						if(player1 && currentPiece.player1) return place;
						
						else if (!player1 && !currentPiece.player1)	return place;
						
					}
				}
			}
		}
		
		return null;
    }
    

	/**
	 * Used together with isCheckMate to conclude whether the king can move legally.
	 * 
	 * @author Hamid Abbasi (ha354)
	 * @param starting	starting position of king 
	 * @param ending	potential destination of king 
	 * @param player1	true for king of player 1, false otherwise

	 * @return true if king can move legally, false otherwise
	 */	

	public boolean kingsMove(int[] starting, int[] ending, boolean player1){

		if(ending[0] < 0 || ending[0] > 7 || ending[1] < 0 || ending[1] > 7) {
            
            return false;
        }

        ChessPiece otherPiece = boardPos[ending[0]][ending[1]];
				

        ChessPiece currentPiece = boardPos[starting[0]][starting[1]];


        if(otherPiece != null) {
            
            return false;
        }

		checking = true;
		if(currentPiece.validTurn(starting, ending, this)){
			
            checking = false;
            boardPos[ending[0]][ending[1]] = currentPiece;
			boardPos[starting[0]][starting[1]] = null;
			
			for(int i = 0; i < 8; i++){
				
				for(int j = 0; j < 8; j++){
					
					int[] tempStart = {i, j};
					if(isCheck(tempStart, player1)){
												

                        boardPos[ending[0]][ending[1]] = otherPiece;	
						boardPos[starting[0]][starting[1]] = currentPiece;
						
						return false;
					}
				}
			}
			

            checking = false;
			boardPos[ending[0]][ending[1]] = otherPiece;	
			boardPos[starting[0]][starting[1]] = currentPiece;
			
			return true;
		}else{
			
			checking = false;
			return false;
		}

	}
}