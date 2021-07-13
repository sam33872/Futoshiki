package coursework;

/**
 * Class to hold constraints
 * @author 184504
 */
public class FutoshikiConstraints {
    private String con;
    private FutoshikiSquare before;
    private FutoshikiSquare after;
    private boolean comp;
    
    /**
     * Constructor for constraints
     * @param con constraint
     * @param b square before (left or above constraint)
     * @param a square after (right or below constraint)
     */
    public FutoshikiConstraints(String con, FutoshikiSquare b, FutoshikiSquare a){
        this.con = con;
        before = b;
        after = a;
    }
    
    /**
     * Uses stored constraints and squares to compare whether the constraints are violated
     * @param size the size of the grid e.g. 5 for 5x5 grid
     * @param errors the errors string
     * @return 
     */
    public String compare(int size, String errors){
        comp = true;
        if(before.getNum() != 0 && after.getNum() != 0)
        {
            if(con.equals(">") || con.equals("v")){
                if(!(before.getNum() > after.getNum())){
                    comp = false;
                    errors+="Error 6000: Constraint violation\n";
                }
                if(after.getNum() == size){
                    comp = false;
                    errors+="Error 6100: Constraint violation, something cannot be bigger than the max\n";
                }
                if(before.getNum() == 1){
                    comp = false;
                    errors+="Error 6200: Constraint violation, something cannot be smaller than the min\n";
                }
            }
            else if (con.equals("<") || con.equals("^")){
                if(!(before.getNum() < after.getNum())){
                    comp = false;
                    errors+="Error 6000: Constraint violation\n";
                }
                if(before.getNum() == size){
                    comp = false;
                    errors+="Error 6100: Constraint violation, something cannot be bigger than the max\n";
                }
                if(after.getNum() == 1){
                    comp = false;
                    errors+="Error 6200: Constraint violation, something cannot be smaller than the min\n";
                }
            }
        }
        else{
            comp = false;
        }
        return errors;
    }
    
    /**
     * Getter for comp value;
     * @return false if any violations occur in compare, true otherwise 
     */
    public boolean returnComp(){
        return comp;
    }
    
    /**
     * Returns data within position of
     * @return String from constraint
     */
    public String getCon(){
        return con;
    }
    
    /**
     * Overrides setRowConstraint/setColumnConstraint and puts any constraint into the puzzle
     * Used to test other methods than setRowConstraint/setColumnConstraint
     * @param con the constraint to be entered into the grid
     */
    public void setCon(String con){
        this.con = con;
    }
}
