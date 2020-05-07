package Application;
import java.util.Scanner;
import Pieces.*;



/**
 * This class runs the game and scanner to retrieve users inputs
 *
 * @author Omar Atieh (oa183)
 * @author Hamid Abbasi (ha354)
 */
public class chess {
	/**
	 * The following method takes value of the user and validates if the 
	 * command is allowed. The checks include that they follow chess protocol letter than digit 
	 * 
	 * 
	 * @author Hamid Abbasi (ha354)
	 * @param input takes in the scanner.in and check the char values
	 * @return return true if the input is allowed
	 */
	

	public static boolean isInputCorrect(String input){
		
		input = input.toLowerCase();
		if(input.length() == 0) return false;
		
		if("draw".equals(input)) return true;
		if("resign".equals(input)) return true;

		if(input.charAt(0) < 'a' || input.charAt(0) > 'h') {
			return false;
		}
		if(input.charAt(4) < '1' || input.charAt(4) > '8') {
			return false;
		}
		if(input.charAt(3) < 'a' || input.charAt(3) > 'h') {
			return false;
		}
		if(input.charAt(1) < '1' || input.charAt(1) > '8') {
			return false;
		}

		if(!Character.isAlphabetic(input.charAt(0))) {
			return false;
		}
		if(!Character.isAlphabetic(input.charAt(3))) {
			return false;
		}
		if(!Character.isDigit(input.charAt(1))) {
			return false;
		}
		if(!Character.isDigit(input.charAt(4))) {
			return false;
		}
		
		return true;
	}

	
	
	@SuppressWarnings({ "unused", "static-access" })
	public static void main(String[] args) {
		
		GameDefinition game = new GameDefinition();
		boolean player1Turn = true;
		
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		boolean draw = false;
	
		boolean checkPlayer2 = false;
        boolean checkPlayer1 = false;

		while(true){
			
			System.out.println();
			game.printChessBoard();
			
			if(!player1Turn){
				
				game.isenPassant(false);
				System.out.print("Player 2's turn: ");
			
            } else{
				
				game.isenPassant(true);
				System.out.print("Player 1's turn: ");
            }
			
			String input = scanner.nextLine();
			
			while(!isInputCorrect(input)){
				
				System.out.print("Make sure input is correct. Input: ");
				input = scanner.nextLine();
			}
			
			if("resign".equals(input)){
				
				if(player1Turn){
                    
                    System.out.print("Black wins");
                } else {
                 
                    System.out.print("White wins");
                }
                return;

			}else if(input.contains("draw")){
				
				if(draw){
					
					System.out.println("draw");
					return;
				
				} else draw = true;

			} else draw = false;
			
			String start = input.substring(0, 2);
			String end = input.substring(3);
			
			int[] starting = game.getIndex(start);
			int[] ending = game.getIndex(end);

			while(!game.move(starting, ending, player1Turn)){
				
				System.out.print("Illegal move, try again");
				input = scanner.nextLine();
				
				while(!isInputCorrect(input)){
					
					System.out.println();
					System.out.print("Make sure input is correct. Input: ");
					input = scanner.nextLine();
				}
				
				if("resign".equals(input)){
					
					if(player1Turn){
                        
                        System.out.print("Black wins");
                    } else {
                        
                        System.out.print("White wins");
                    } 
                    return;
				
				}else if(input.contains("draw")){
					
					if(draw){
						
						System.out.println("draw");
						return;
					
					} else draw = true;
				
				} else draw = false;
				
				start = input.substring(0, 2);
				end = input.substring(3);
				
				starting = game.getIndex(start);
				ending = game.getIndex(end);
			
			}
			
			
			ChessPiece ChessPieceDisplaced = game.boardPos[ending[0]][ending[1]];
			
			if(Pawn.class.isInstance(ChessPieceDisplaced)){
				
				char player = '0';
				char type = '0';
				
				if(input.length() == 7) {
                    
                    type = input.charAt(6);
                }
                
                if(ending[0] == 0 && ChessPieceDisplaced.player1){
                    
                    player = '1';
                } else if(ending[0] == 7 && !ChessPieceDisplaced.player1){
                    
                    player = '2';
                }

				ChessPiece promoted = pawnUpgrade(type, player); 
				if(promoted != null) game.boardPos[ending[0]][ending[1]] = promoted;
			}

			
			if(game.isCheck(ending, false) && player1Turn){
                
                System.out.println("Check");
				if(game.isCheckMate(false)){
                    
                    game.printChessBoard();
					System.out.println("Checkmate");
					System.out.print("White wins");
					return;
                }
                
			} else if(game.isCheck(ending, true) && !player1Turn){
                
                System.out.println("Check");
				if(game.isCheckMate(true)){
                    
                    game.printChessBoard();
					System.out.println("Checkmate");
					System.out.print("Black wins");
					return;
				}
                
                checkPlayer1 = false;
			}
            
            player1Turn = !player1Turn;
		}
	}
	
	/**
	 *The following method handles the chess function of upgrading a pawn piece
	 *when a pawn reaches the end of the opponents board they can promote to any piece.
	 *
	 * @author Omar Atieh (oa183)
	 * @param Takes in a char to determine what new piece to create
	 * @param Takes in char to determine if it is player 1 or player 2 
	 * @return Returns the proper chess piece for the player.
	 */



	private static ChessPiece pawnUpgrade(char type, char player) {
		if(player == '0') {
            
            return null;
        }
        
		boolean white;
		if(player == '1') {
            
            white = true;
        } else {
            
            white = false;
        }

		if(Character.toUpperCase(type) == '2') {
            
            return new Bishop(white);
        }

		if(Character.toUpperCase(type) == 'R') {
            
            return new Rook(white);
        }

		if(Character.toUpperCase(type) == 'P') {
            
            return new Pawn(white);
        }

		if(Character.toUpperCase(type) == 'N') {
            
            return new Knight(white);
        }

		return new Queen(white);
	}
		
}