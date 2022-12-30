import java.util.Arrays;

public class Evaluate {

	//Initialize the variables for the game
	private int size, tilesToWin, maxLevels;
	//Initialize the game board- 2d array
	private char[][] gameBoard; 
	//Evaluate constructor- sets up the game board
	public Evaluate (int size, int tilesToWin, int maxLevels) {
		this.size= size; 
		this.tilesToWin= tilesToWin; 
		this.maxLevels= maxLevels; 
		//initalizes a square game board of the specified size
		char[][] gameBoard= new char[size][size]; 
		//found this function online to fill all the spaces of the array with the letter e- https://www.techiedelight.com/initialize-2d-array-with-specific-value-java/#:~:text=The%20recommended%20approach%20to%20initialize,array%20using%20a%20for%2Dloop. 
		for (char[] row : gameBoard) {
			Arrays.fill(row, 'e'); 
		}
	
		
	}
	//returns an empty dictonary of the specifed size
	public Dictionary createDictonary() {
		Dictionary dic = new Dictionary(6151); //read online that 6151 is a good prime number that creates decreased collisions
		return dic; 
	}
	
	//checks if the key exists in the dictonary and returns it if it exists
	public Record repeatedState(Dictionary dict) {
		String s = "";
		//represents the contents of the game board as a string
	    for (int i=0; i < gameBoard.length; i++) {
	        for (int j=0; j < gameBoard[i].length; j++) {
	            s += gameBoard[i][j];
	        }
	    }
	    Record rec= dict.get(s); 
	    //checks if the key exists in the dictionary
	    if (dict.get(s) != null) {
	    	//if the key exists return it
	    	return rec; 
	    } else {
	    	//else return null
	    	return null; 
	    }
	}
	//inserts a key into the dictionary
	public void insertState(Dictionary dict, int score, int level) {
		String s = "";
		//converts the gameboard into a string
	    for (int i=0; i < gameBoard.length; i++) {
	        for (int j=0; j < gameBoard[i].length; j++) {
	            s += gameBoard[i][j];
	        }
	    }
	    //creates a record with this key
	    Record rec= new Record(s, score, level); 
	    //inserts the record into the dictonary
	    dict.put(rec); 
	}
	//stores the play into the game board in the specifed spot
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col]= symbol; 
	}
	//returns true if the square is empty
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == 'e') {
			return true;
		} else return false; 
	}
	//returns true if the tile is of computer
	public boolean tileOfComputer(int row, int col) {
		if (gameBoard[row][col] == 'c') {
			return true; 
		} else return false; 
	}
	//returns true if the tile is of human
	public boolean tileOfHuman(int row, int col) {
		if (gameBoard[row][col] == 'h') {
			return true; 
		} else return false; 
	}
	//returns true if the player wins
	public boolean wins(char symbol) {
		int rowCount=0, colCount=0, diagLeft = 0, diagRight = 0; 
		
		//Checks rows.
	    for (int i = 0; i < size; i++) {
	    	for (int j = 0; j < size; j++) {
	    			if (gameBoard[i][j] == symbol) {
	                    rowCount++; 
	                }
	                if (rowCount == tilesToWin) {
	                	return true; 
	                }
	                //if there isn't an adjacent tile with the identical symbol, reset
	                if (gameBoard[i][j] != symbol) {
	                	rowCount=0; 
	                }
	                
	            }
	        } 
	   //Check Columns 
	    for (int i = 0; i < size; i++) {
	    	for (int j = 0; j < size; j++) {
	    			if (gameBoard[j][i] == symbol) {
	                    colCount++; 
	                }
	                if (colCount == tilesToWin) {
	                	return true; 
	                }
	              //if there isn't an adjacent tile with the identical symbol, reset
	                if (gameBoard[j][i] != symbol) {
	                	colCount=0; 
	                }
	                
	            }
	        } 

		//Check diagonals from left to right
	    for(int i = size-1; i >=0; i--) {
	    	for (int j=0; j< size; j++) {
	    		if (gameBoard[i][j] == symbol) {
	    			diagLeft++; 
	    		}
	    		if (gameBoard[j][i] == symbol) {
	    			diagRight++; 
	    		}
	    		if (diagLeft == tilesToWin) {
                	return true; 
                }
	    		if (diagRight== tilesToWin) {
                	return true; 
                }
	    		//if there isn't an adjacent tile with the identical symbol, reset
	    		if (gameBoard[j][i] != symbol) {
	    			diagRight=0; 
	    		}
	    		//if there isn't an adjacent tile with the identical symbol, reset
	    		if (gameBoard[i][j] != symbol) {
	    			diagLeft=0; 
	    		}
	    	}
	    	
	    }
	    diagRight=0; 
	    diagLeft=0; 
	    //Check diagonal from right to left
	    for(int i = 0; i <= size; i++) {
	    	for (int j=size-1; j >= 0; j--) {
	    		if (gameBoard[i][j] == symbol) {
	    			diagLeft++; 
	    		}
	    		if (gameBoard[j][i] == symbol) {
	    			diagRight++; 
	    		}
	    		if (diagLeft == tilesToWin) {
                	return true; 
                }
	    		if (diagRight== tilesToWin) {
                	return true; 
                }
	    		//if there isn't an adjacent tile with the identical symbol, reset
	    		if (gameBoard[j][i] != symbol) {
	    			diagRight=0; 
	    		}
	    		//if there isn't an adjacent tile with the identical symbol, reset
	    		if (gameBoard[i][j] != symbol) {
	    			diagLeft=0; 
	    		}
	    	}
	    	
	    }
		return false; 
	}
	//returns true if there was a draw
	public boolean isDraw() {
		int emptyCounter=0; 
		for (int i=0; i < size; i++) {
	        for (int j=0; j < size; j++) {
	        	//increment the number of empty spaces
	        	if (squareIsEmpty(i,j)) {
	        		emptyCounter++; 
	        	}
	        }
	    }
		//if there was any empty spaces, there was not a draw
		if (emptyCounter != 0) {
			return false; 
		}
		//otherwise there was a draw because tehre were no empty spaces
		return true; 
	}
	//return 2 if there was a draw
	public int evalBoard() {
		if (wins('c') == true) {
			return 3; 
		}
		if (wins('h')== true) {
			return 0; 
		}
		if (isDraw()) {
			return 2; 
		}
		//return one if there was not a draw
		return 1; 
	}
}
