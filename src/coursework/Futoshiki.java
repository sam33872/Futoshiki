package coursework;

import java.util.ArrayList;

/**
 * Class to run game
 * @version 2.0
 * @author 184504
 */
public class Futoshiki {
    int row;
    int col;
    /**
     * Constructor for Futoshiki
     * Loop to run command line
     * @param num the size of the puzzle e.g. 2 means 2x2
     */
    
    /*
    public Futoshiki(int num){
        FutoshikiPuzzle puz = new FutoshikiPuzzle(num);
        puz.fillPuzzle();
        Parser p = new Parser();
        Command c = null;
        int i = 0;
        int val;
        do {
            val = 0;
            System.out.println(puz.displayString());
            System.out.print("Enter a command>");
            c = p.getCommand();
            System.out.println(c);
            // MARK - Adds legal value into editable square
            if (c.getCommand() == CommandWord.getCommandWord("MARK")){
                puz.setSquare(c.getRow(),c.getColumn(),c.getValue());
                // If the puzzle is complete, end the loop and return complete to user 
                if(puz.isPuzzleComplete() == true){
                    i = 1;
                    System.out.println("Puzzle complete");
                    System.out.println("Final grid was:");
                    System.out.println(puz.displayString());
                }
                else{
                    System.out.println(puz.getErrors());
                }
            }
            // CLEAR - empties a square if it is editable
            if (c.getCommand() == CommandWord.getCommandWord("CLEAR")){
                puz.empty(c.getRow(),c.getColumn());
            }
            // NEW - creates new puzzle of size entered
            if (c.getCommand() == CommandWord.getCommandWord("NEW")){
                val = c.getValue();
                if(val > 1){   
                    i = 1;
                    Futoshiki newGame = new Futoshiki(val);
                }
            }
            // QUIT - Exits the game
            if (c.getCommand() == CommandWord.getCommandWord("QUIT")){
                i = 1;
            }
        } while (c != null && i == 0);
    }
    */
    
    public Futoshiki(){
    }
    
    public boolean solve(FutoshikiPuzzle puz, int size){
        row = 0;
        col = 0;
        if(findEmptySquare(puz,size) == false){
            return true;
        }
        
        for(int i = 1; i <= size; i++){
            if(testNum(i,puz,size) == true){
                puz.setNum(row+1, col+1, i);
                if(solve(puz,size)){
                    for(int a = 0; a < size; a++){
                        for(int b = 0; b < size; b++){
                            if(puz.getSquare(a, b).isEditable()){
                                puz.getSquare(a, b).setNum(0);
                            }
                        }
                    }
                    return true;
                }
                puz.setNum(row+1, col+1, 0);
            }
        }
        return false;
    }
    
    private boolean findEmptySquare(FutoshikiPuzzle puz, int size){
        boolean found = false;
        row = 0;
        col = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                    if(puz.getNumber(i+1,j+1) == 0){
                        row = i;
                        col = j;
                        found = true;
                    }
            }
        }
        return found;
    }
    
    private boolean testNum(int num,FutoshikiPuzzle puz, int size){
        puz.setNum(row+1,col+1,num);
        if(puz.isLegal() == true){
            return true;
        }
        else{
            puz.setNum(row+1, col+1, 0);
            System.out.println("invalid puz at: " + row + "," + col);
            return false;
        }
    }
}
