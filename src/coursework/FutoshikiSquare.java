package coursework;

/**
 * Class that stores everything about an individual square
 * @author 184504
 */
public class FutoshikiSquare {
    private int num;
    private boolean editable;
    private final int x;
    private final int y;
    
    /**
     * Constructor for squares
     * @param num the number in the grid at this position
     */
    public FutoshikiSquare(int num, int x, int y){
        this.num = num;
        editable = true;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns data within position of
     * @return integer from grid
     */
    public int getNum(){
        return num;
    }
    
    /**
     * Overrides setSquare and puts any number into the puzzle
     * Used to test other methods than setSquare
     * @param num the number to be entered into the grid
     */
    public void setNum(int num){
        if(editable == true){
            this.num = num;
        }
    }
    
    /**
     * Getter for editable variable which defines whether this position can be edited or not
     * @return editable 
     */
    public boolean isEditable(){
        return editable;
    }
    
    /**
     * Setter for editable
     * Used to make square un-editable
     */
    public void makeUneditable(){
        editable = false;
    }
    
    /**
     * Setter for editable
     * Used to make square editable
     */
    public void makeEditable(){
        editable = true;
    }
}
