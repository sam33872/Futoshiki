package coursework;

import java.util.Random;

/**FutoshikiPuzzle Class for assignment 2
 * @author 184504
 */
public class FutoshikiPuzzle {
    private FutoshikiSquare[][] squares;
    private FutoshikiConstraints[][] row;
    private FutoshikiConstraints[][] col;
    private final int size;
    private static String errors;
    private int difFactor;
    
    // Constructor
    // @param size the size of the grid
    public FutoshikiPuzzle(int size){
        this.size = size;
        squares = new FutoshikiSquare[size][size];
        row = new FutoshikiConstraints[size][size-1];
        col = new FutoshikiConstraints[size][size-1];
        difFactor = 1;
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                squares[i][j] = new FutoshikiSquare(0,i,j);
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size-1; j++){
                row[i][j] = new FutoshikiConstraints(" ",squares[i][j],squares[i][j+1]);
                col[i][j] = new FutoshikiConstraints(" ",squares[i][j],squares[i][j+1]);
            }
        }
    }
    
    public FutoshikiPuzzle(FutoshikiPuzzle copy, int size){
        this.size = size;
        squares = new FutoshikiSquare[size][size];
        row = new FutoshikiConstraints[size][size-1];
        col = new FutoshikiConstraints[size][size-1];
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                squares[i][j] = copy.getSquare(i,j);
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size-1; j++){
                row[i][j] = copy.getRowConstraints(i, j);
                col[i][j] = copy.getColConstraints(i, j);
            }
        }
    }
    
    // Main method for class FutoshikiPuzzle
    public static void main(String[] args){
        FutoshikiPuzzle game = new FutoshikiPuzzle(5);
        game.fillPuzzle();
        System.out.println(game.displayString());
    }
    
    /**  
     * Method to change value of one of the squares
     * Index of array will inputted as 1,1 instead of 0,0
     * @param x   the x coordinate
     * @param y   the y coordinate
     * @param num the number to be entered into the grid       
     */
    public void setSquare(int x, int y, int num){
        if(num > 0 && num < size+1){
            if(x > 0 && x < size+1){
                if(y > 0 && y < size+1){
                    if(squares[x-1][y-1].isEditable() == true){
                        // X and Y inputs will start at 1 not 0.
                        squares[x-1][y-1].setNum(num); 
                    }
                }
                else{
                    // Error message for invalid y
                    System.out.println("Invalid y value");
                }
            }
            else{
                // Error message for invalid x
                System.out.println("Invalid x value");
                }
        }
        else{
            // Error message for invalid num
            System.out.println("Invalid num input," + num);
        }  
    }
    
    /** 
     * Sets a row constraint onto the puzzle
     * @param x   the x coordinate
     * @param y   the y coordinate
     * @param con the constraint to be entered to puzzle
     */
    public void setRowConstraint(int x, int y, String con){
        if(con.equals("<") || con.equals(">")){
            if(x > 0 && x < size+1){
                if(y > 0 && y < size+1){
                    // X and Y inputs will start at 1 not 0.
                    row[x-1][y-1].setCon(con); 
                }
                else{
                    // Error message for invalid y
                    System.out.println("Invalid y value");
                }
            }
            else{
                // Error message for invalid x
                    System.out.println("Invalid x value");
                }
        }
        else{
            // Error message for invalid con
            System.out.println("Invalid con input");
        }
        
    }
    
    /**
     * Sets a column constraint onto the puzzle
     * @param x   the x coordinate
     * @param y   the y coordinate
     * @param con the constraint to be entered to puzzle
     */
    public void setColumnConstraint(int x, int y, String con){
        if(con.equals("^") || con.equals("v")){
            if(x > 0 && x < size+1){
                if(y > 0 && y < size+1){
                    // X and Y inputs will start at 1 not 0.
                    col[x-1][y-1].setCon(con); 
                }
                else{
                    // Error message for invalid y
                    System.out.println("Invalid y value");
                }
            }
            else{
                // Error message for invalid x
                    System.out.println("Invalid x value");
                }
        }
        else{
            // Error message for invalid con
            System.out.println("Invalid con input");
        }
    }
    
    /**
     * Fills puzzle with a random amount of numbers, row constraints and column constraints
     */
    public void fillPuzzle(){
        int x, y, num;
        boolean legal;
        Random random = new Random();
        errors = "";
        do{
            // Randomly filling numbers
            blankGrid();
            int nums = size - difFactor;
            if(nums < 1){
                nums = 1;
            }
            for(int i = 0; i < nums; i++){
                x = random.nextInt(size) + 1;
                y = random.nextInt(size) + 1;
                num = random.nextInt(size) + 1;
                if(squares[x-1][y-1].isEditable() == true){
                    setNum(x,y,num);
                    squares[x-1][y-1].makeUneditable();
                }
                
            }
            // Randomly generating column constraints
            int cols = difFactor - 1;
            if(cols < 1){
                cols = 1;
            }
            for(int i = 0; i < cols; i++){
                x = random.nextInt(size) + 1;
                y = random.nextInt(size-1) + 1;
                num = random.nextInt(2);
                if(num == 0){
                    setColumnConstraint(x,y,"^");
                }
                else{
                    setColumnConstraint(x,y,"v");
                }
            }
            // Randomly generating row constraints
            int rows = difFactor - 1;
            if(rows < 1){
                rows = 1;
            }
            for(int i = 0; i < rows; i++){
                x = random.nextInt(size) + 1;
                y = random.nextInt(size-1) + 1;
                num = random.nextInt(2);
                if(num == 0){
                    setRowConstraint(x,y,"<");
                }
                else{
                    setRowConstraint(x,y,">");
                }
          
            }
        }while(isLegal() == false);
    }
    
    public void blankGrid(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                squares[i][j].setNum(0);
                squares[i][j].makeEditable();
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size-1; j++){
                row[i][j].setCon(" ");
                col[i][j].setCon(" ");
            }
        }
    }
    /**
     * Puts the complete puzzle into a singular string
     * @return ret returns string containing puzzle
     */
    public String displayString(){
        String ret = "";
        for(int a = 0; a < size; a++){
            // Produces top line of cell
            for(int c = 0; c < size; c++){
                ret+="---";
                if(c < size - 1){
                    ret+=" ";
                }
                else{
                    ret+="\n";
                }
            }
            for(int b = 0; b < size; b++){
                if(squares[a][b].getNum() > 0){
                    String num = '|' + Integer.toString(squares[a][b].getNum()) + '|';
                    ret+=(num);
                }
                else{
                    ret+=("| |");
                }
                if(b < size-1){
                    ret+=(row[a][b].getCon());
                }
            }
            ret+=("\n");
            // Produces bottom line of cell
            for(int d = 0; d < size; d++){
                ret+="---";
                if(d < size - 1){
                    ret+=" ";
                }
                else{
                    ret+="\n";
                }
            }
            for(int b = 0; b < size; b++){
                if(a < size-1){ 
                    ret+=(" " + col[b][a].getCon() + "  ");
                }
            }
            ret+=("\n");
        }    
        return ret;
    }
    
    /**
     * Checks if the puzzle is a legal configuration
     * @return true if valid puzzle and false if it is not
     */
    public boolean isLegal(){
        errors = "";
        boolean legal = true;
        //Test Numbers (Within range)
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if((squares[x][y].getNum() < 0) || (squares[x][y].getNum() > size)){
                    legal = false;
                    errors+="Error 1000: Number out of range\n";
                }
            }
        }
        //Test Rows
        int a = 1;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                for(int z = a; z < size; z++){
                    if((squares[x][y].getNum() == squares[x][z].getNum()) && (squares[x][y].getNum() != 0)){
                        legal = false;
                        errors+="Error 2000: Number repeated in row " + (x+1) + "\n";
                    }
                }
                a = a + 1;
            }
            a = 1;
        }
        //Test Columns
        int b = 1;
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                for(int z = b; z < size; z++){
                    if((squares[y][x].getNum() == squares[z][x].getNum()) && (squares[y][x].getNum() != 0)){
                        legal = false;
                        errors+="Error 3000: Number repeated in column " + (x+1) + "\n";
                    }
                }
                b = b + 1;
            }
            b = 1;
        }
        //Test Row and Column Constraints
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size-1; y++){
                if(!(row[x][y].getCon().equals("<") || row[x][y].getCon().equals(">") || row[x][y].getCon().equals(" "))){
                    legal = false;
                    errors+="Error 4000: Row Constraint invalid\n";
                }
                else{
                    if(!row[x][y].getCon().equals(" ")){
                        errors+= row[x][y].compare(size, errors);
                        if(row[x][y].returnComp() == false){
                            legal = false;
                        } 
                    }
                    
                } 
                if(!(col[x][y].getCon().equals("^") || col[x][y].getCon().equals("v") || col[x][y].getCon().equals(" "))){
                    legal = false;
                    errors+="Error 5000: Column Constraint invalid\n";
                }
                else{
                    if(!col[x][y].getCon().equals(" ")){
                        errors+= col[x][y].compare(size, errors);
                        if(col[x][y].returnComp() == false){
                            legal = false;
                        }
                    }
                    
                }
                
            }
        }
        
        // Return
        return legal;
    }
    
    /**
     * Finds all the problems within the puzzle and returns them within a string
     * @param puz the puzzle to check for errors
     * @return a string with all problems within
     */
    public String getProblems(FutoshikiPuzzle puz){
        // Runs isLegal() to find any errors
        boolean test = puz.isLegal();
        // Returns errors found by calling getErrors which is a getter for the String variable errors;
        return puz.getErrors();
    }
    
    /**
     * If the square x,y is editable, clears the value by setting back to zero
     * @param x 
     * @param y
     * @return true if editable, false if un-editable
     */
    public boolean empty(int x, int y){
        if(squares[x-1][y-1].isEditable() == true){
            squares[x-1][y-1].setNum(0);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * This tests if the puzzle is complete and returns respectively 
     * @return true if puzzle is complete and legal, false otherwise
     */
    public boolean isPuzzleComplete(){
        boolean complete;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(squares[i][j].getNum() == 0){
                    complete = false;
                }
            }
        }   
        if(isLegal() == true){
            complete = true;
        }
        else{
            complete = false;
        }
        return complete;
    }
    
    /**
     * Getter for String variable errors 
     * @return String variable errors
     */
    public String getErrors(){
        return errors;
    }
    
    /**
     * Getter for integer variable stored in certain position within squares 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return number stored within squares
     */
    public int getNumber(int x, int y){
        return squares[x-1][y-1].getNum();
    }
    
    /**
     * Getter for String variable stored in certain position within row 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return String stored within row
     */
    public String getRow(int x, int y){
        return row[x-1][y-1].getCon();
    }
    
    /**
     * Getter for String variable stored in certain position within col 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return String stored within row
     */
    public String getCol(int x, int y){
        return col[x-1][y-1].getCon();
    }
    
    /**
     * Setter for the integer variable within certain position in squares
     * @param x the x coordinate
     * @param y the y coordinate
     * @param num the number to be stored within object squares of type FutoshikiSquare
     */
    public void setNum(int x,int y,int num){
        squares[x-1][y-1].setNum(num);
    }
    
    // Setter for the String variable within certain position in row
    /**
     * Setter for the integer variable within certain position in squares
     * @param x the x coordinate
     * @param y the y coordinate
     * @param con the number to be stored within object row of type FutoshikiConstraints
     */
    public void setRow(int x,int y,String con){
        row[x-1][y-1].setCon(con);
    }
    
    /**
     * Setter for the String variable within certain position in col
     * @param x the x coordinate
     * @param y the y coordinate
     * @param con the String to be stored within object col of type FutoshikiConstraints
     */
    public void setCol(int x,int y,String con){
        col[x-1][y-1].setCon(con);
    }
    
    /**
     * Makes the variable editable false within certain position of squares
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void makeUneditable(int x, int y){
        squares[x-1][y-1].makeUneditable();
    }
    
    /**
     * Getter for value editable inside FutoshikiConstraints
     * @param x
     * @param y
     * @return editable
     */
    public boolean editable(int x, int y){
        return squares[x-1][y-1].isEditable();
    }
    
    /**
     * Returns a square of the grid
     * @param x
     * @param y
     * @return an instance of FutoshikiSquare
     */
    public FutoshikiSquare getSquare(int x, int y){
        return squares[x][y];
    }
    
    /**
     * Returns a constraints object from row
     * @param x
     * @param y
     * @return an instance of FutoshikiConstraints
     */
    public FutoshikiConstraints getRowConstraints(int x, int y){
        return row[x][y];
    }
    
    /**
     * Returns a constraints object from col
     * @param x
     * @param y
     * @return an instance of FutoshikiConstraints
     */
    public FutoshikiConstraints getColConstraints(int x, int y){
        return col[x][y];
    }
    
    /**
     * Tests if the grid has any empty spaces
     * Used for solve method
     * @param size
     * @return true if completely full, false otherwise
     */
    public boolean completelyFullTest(int size){
        boolean full = true;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(squares[i][j].getNum() == 0){
                    full = false;
                }
            }
        }
        return full;
    }
    
    /**
     * Setter for variable difFactor
     * @param dif
     */
    public void setDif(int dif){
        difFactor = dif;
    }
}
