import coursework.Futoshiki;
import coursework.FutoshikiPuzzle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for coursework 1 and 2
 * @author 184504
 */
public class FutoshikiPuzzleTest {
    
    public FutoshikiPuzzleTest() {
    }
    
    /**
     * Adds a new valid number into valid position on grid
     * @result the number 4 should be in the grid at 2,2
     */
    @Test
    public void testSetSquare(){
        FutoshikiPuzzle puz1 = new FutoshikiPuzzle(5);
        puz1.setSquare(2,2,4);
        assertEquals(4,puz1.getNumber(2,2));  
    }
    
    /**
     * Adds a new valid number into an invalid position on grid
     * @result there should be no change to the puzzle
     * */
     
    @Test
    public void testSetSquareBound(){
        FutoshikiPuzzle puz2 = new FutoshikiPuzzle(5);
        puz2.fillPuzzle();
        String before = puz2.displayString();
        puz2.setSquare(0,0,4);
        assertEquals(before,puz2.displayString());
    }
    
    /**
     * Adds a invalid number into an valid position on grid
     * @result there should be no change to the puzzle
     * */
     
    @Test
    public void testSetSquareInvalidData(){
        FutoshikiPuzzle puz3 = new FutoshikiPuzzle(5);
        puz3.fillPuzzle();
        puz3.setSquare(2,2,6);
        assertNotEquals(6.0,puz3.getNumber(2,2)); 
    }
    
    /**
     * Adds a new valid constraint into an valid position on grid
     * @result a new constraint should be on the puzzle
     * */
     
    @Test
    public void testSetRowConstraint(){
        FutoshikiPuzzle puz4 = new FutoshikiPuzzle(5);
        puz4.fillPuzzle();
        puz4.setRowConstraint(2,2,"<");
        assertEquals("<",puz4.getRow(2,2));
    }
    
    /**
     * Adds a new valid constraint into an invalid position on grid
     * @result no change to puzzle
     */
    @Test
    public void testSetRowConstraintBound(){
        FutoshikiPuzzle puz5 = new FutoshikiPuzzle(5);
        String before = puz5.displayString();
        puz5.setRowConstraint(0,0,">");
        assertEquals(before,puz5.displayString());
    }
    
    /**
     * Adds an invalid constraint into an valid position on grid
     * @result no change to puzzle
     */
    @Test
    public void testSetRowConstraintInvalidData(){
        FutoshikiPuzzle puz6 = new FutoshikiPuzzle(5);
        String before = puz6.displayString();
        puz6.setRowConstraint(2,2,".");
        assertEquals(before,puz6.displayString());
    }
    
    /**
     * Adds a new valid constraint into an valid position on grid
     * @result a new constraint should be on the puzzle
     * */
     
    @Test
    public void testSetColumnConstraint(){
        FutoshikiPuzzle puz7 = new FutoshikiPuzzle(5);
        puz7.fillPuzzle();
        puz7.setColumnConstraint(2,2,"^");
        assertEquals("^",puz7.getCol(2,2));
    }
    
    /**
     * Adds a new valid constraint into an invalid position on grid
     * @result no change to puzzle
     */
    @Test
    public void testSetColumnConstraintBound(){
        FutoshikiPuzzle puz8 = new FutoshikiPuzzle(5);
        String before = puz8.displayString();
        puz8.setColumnConstraint(0,0,"v");
        assertEquals(before,puz8.displayString());
    }
    
    /**
     * Adds an invalid constraint into an valid position on grid
     * @result no change to puzzle
     */
    @Test
    public void testSetColumnConstraintInvalidData(){
        FutoshikiPuzzle puz9 = new FutoshikiPuzzle(5);
        String before = puz9.displayString();
        puz9.setRowConstraint(2,2,".");
        assertEquals(before,puz9.displayString());
    }
    
    /**
     * Tests if fillPuzzle actually adds data to the puzzle
     * @result data should have been added to grid
     */
    @Test
    public void testFillPuzzle(){
        FutoshikiPuzzle puz10 = new FutoshikiPuzzle(5);
        String before = puz10.displayString();
        puz10.fillPuzzle(); 
        assertNotEquals(before,puz10.displayString());
    }
    
    /**
     * Tests displayString by changing a value in grid
     * @result the puzzle should change
     */
    @Test
    public void testDisplayStringAddNum(){
        FutoshikiPuzzle puz11 = new FutoshikiPuzzle(5);
        puz11.setSquare(2,2,1);
        String before = puz11.displayString();
        puz11.setSquare(2,2,4);
        assertNotEquals(before,puz11.displayString());
    }
    
    /**
     * Tests displayString by changing a value in rowConstraint
     * @result the puzzle should change
     */
    @Test
    public void testDisplayStringAddRowCon(){
        FutoshikiPuzzle puz12 = new FutoshikiPuzzle(5);
        String before = puz12.displayString();
        puz12.setRowConstraint(2,2,"<");
        assertNotEquals(before,puz12.displayString());
    }
    
    /**
     * Tests displayString by changing a value in columnConstraint
     * @result the puzzle should change
     */
    @Test
    public void testDisplayStringAddColCon(){
        FutoshikiPuzzle puz13 = new FutoshikiPuzzle(5);
        String before = puz13.displayString();
        puz13.setColumnConstraint(2,2,"^");
        assertNotEquals(before,puz13.displayString());
    }
    
    /**
     * Tests that the grid is actually empty at the beginning 
     * @result the puzzle should have nothing in it
     * */
     
    @Test
    public void testEmptyGrid(){
        FutoshikiPuzzle puz14 = new FutoshikiPuzzle(5);
        int found = 0;
        for(int i = 1; i < 6; i++){
            for(int j = 1; j < 6; j++){
                if(!(puz14.getNumber(i,j) == 0)){
                    found = 1;
                }
            }
        }
        for(int i = 1; i < 6; i++){
            for(int j = 1; j < 5; j++){
                if(!(puz14.getRow(i,j).equals(" "))){
                    found = 1;
                }
                if(!(puz14.getCol(i,j).equals(" "))){
                    found = 1;
                }
            }
        }
        assertEquals(0,found);
    }
    
    // Coursework 2
    
    /** 
     * Test to see if isLegal correctly fails if two of same number in one row
     * @result The test value should be false
     */
    @Test
    public void testIsLegalRowCheck(){
        FutoshikiPuzzle puz15 = new FutoshikiPuzzle(5);
        puz15.setSquare(1,1,4);
        puz15.setSquare(1,2,4);
        boolean test = puz15.isLegal();
        assertFalse(test);
    }
    
    /** 
     * Test to see if isLegal correctly fails if two of same number in one column
     * @result The test value should be false
     */
    @Test
    public void testIsLegalColCheck(){
        FutoshikiPuzzle puz16 = new FutoshikiPuzzle(5);
        puz16.setSquare(1,1,4);
        puz16.setSquare(2,1,4);
        boolean test = puz16.isLegal();
        assertFalse(test);
    }
    
    /** 
     * Test to see if getProblems returns an error 1000 if it occurs
     * The grid only contains something to pop error 1000 and nothing else
     * Error 1000 is where there is an invalid number in the grid
     * @result The error string will not be empty
     * */
     
    @Test
    public void testGetProblemsError1000(){
        FutoshikiPuzzle puz17 = new FutoshikiPuzzle(5);
        puz17.setNum(1,1,6);
        String error = puz17.getProblems(puz17);
        assertNotEquals("",error);
    }
   
    /** 
     * Test to see if getProblems returns an error 2000 if it occurs
     * The grid only contains something to pop error 2000 and nothing else
     * Error 2000 is where there is two of the same number in a row
     * @result The error string will not be empty
     */
    @Test
    public void testGetProblemsError2000(){
        FutoshikiPuzzle puz18 = new FutoshikiPuzzle(5);
        puz18.setSquare(1,1,4);
        puz18.setSquare(1,2,4);
        String error = puz18.getProblems(puz18);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 3000 if it occurs
     * The grid only contains something to pop error 3000 and nothing else
     * Error 3000 is where there is two of the same number in a column
     * @result The error string will not be empty
     */
    @Test
    public void testGetProblemsError3000(){
        FutoshikiPuzzle puz19 = new FutoshikiPuzzle(5);
        puz19.setSquare(1,1,4);
        puz19.setSquare(2,1,4);
        String error = puz19.getProblems(puz19);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 4000 if it occurs
     * The grid only contains something to pop error 4000 and nothing else
     * Error 4000 is where there is an invalid row constraint
     * @result The error string will not be empty
     * */
     
    @Test
    public void testGetProblemsError4000(){
        FutoshikiPuzzle puz20 = new FutoshikiPuzzle(5);
        puz20.setRow(1,1,"#");
        String error = puz20.getProblems(puz20);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 5000 if it occurs
     * The grid only contains something to pop error 5000 and nothing else
     * Error 5000 is where there is an invalid column constraint
     * @result The error string will not be empty
     * */
     
    @Test
    public void testGetProblemsError5000(){
        FutoshikiPuzzle puz21 = new FutoshikiPuzzle(5);
        puz21.setCol(1,1,"#");
        String error = puz21.getProblems(puz21);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6000 if it occurs
     * The grid only contains something to pop error 6000 and nothing else
     * Error 6000 is where there is a row constraint violation
     * @result The error string will not be empty
     */
    @Test
    public void testGetProblemsError6000(){
        FutoshikiPuzzle puz22 = new FutoshikiPuzzle(5);
        puz22.setSquare(1,1,4);
        puz22.setSquare(1,2,5);
        puz22.setRowConstraint(1,1,">");
        String error = puz22.getProblems(puz22);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6000 if no violation occurs
     * The grid only contains something that tests for error 6000 and nothing else
     * Error 6000 is where there is a row constraint violation
     * @result The error string will be empty
     */
    @Test
    public void testGetProblemsNotError6000(){
        FutoshikiPuzzle puz23 = new FutoshikiPuzzle(5);
        puz23.setSquare(1,1,4);
        puz23.setSquare(1,2,5);
        puz23.setRowConstraint(1,1,"<");
        String error = puz23.getProblems(puz23);
        assertEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6100 if it occurs
     * The grid only contains something to pop error 6100 and nothing else
     * Error 6100 is where there is a row constraint violation
     * @result The error string will not be empty
     */
    @Test
    public void testGetProblemsError6100(){
        FutoshikiPuzzle puz24 = new FutoshikiPuzzle(5);
        puz24.setSquare(1,1,5);
        puz24.setSquare(1,2,2);
        puz24.setRowConstraint(1,1,"<");
        String error = puz24.getProblems(puz24);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6100 if no violation occurs
     * The grid only contains something that tests for error 6100 and nothing else
     * Error 6100 is where there is a row constraint violation
     * @result The error string will be empty
     */
    @Test
    public void testGetProblemsNotError6100(){
        FutoshikiPuzzle puz25 = new FutoshikiPuzzle(5);
        puz25.setSquare(1,1,4);
        puz25.setRowConstraint(1,1,"<");
        String error = puz25.getProblems(puz25);
        assertEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6200 if it occurs
     * The grid only contains something to pop error 6200 and nothing else
     * Error 6200 is where there is a row constraint violation
     * @result The error string will not be empty
     */
    @Test
    public void testGetProblemsError6200(){
        FutoshikiPuzzle puz26 = new FutoshikiPuzzle(5);
        puz26.setSquare(1,1,5);
        puz26.setSquare(1,2,2);
        puz26.setColumnConstraint(1,1,"^");
        String error = puz26.getProblems(puz26);
        assertNotEquals("",error);
    }
    
    /** 
     * Test to see if getProblems returns an error 6200 if no violation occurs
     * The grid only contains something that tests for error 6200 and nothing else
     * Error 6200 is where there is a row constraint violation
     * @result The error string will be empty
     */
    @Test
    public void testGetProblemsNotError6200(){
        FutoshikiPuzzle puz27 = new FutoshikiPuzzle(5);
        puz27.setSquare(1,1,4);
        puz27.setColumnConstraint(1,1,"^");
        String error = puz27.getProblems(puz27);
        assertEquals("",error);
    }

    /**
     * Tests the bottom right corner to make sure that it works for the whole grid.
     * This one tests the row constraints
     * @result The puzzle should be legal
     */
    @Test 
    public void testMaxRowBound(){
        FutoshikiPuzzle puz30 = new FutoshikiPuzzle(5);
        puz30.setSquare(5,4,4);
        puz30.setSquare(5,5,5);
        puz30.setRowConstraint(5,4,"<");
        assertEquals("",puz30.getProblems(puz30));
    }
    
    /**
     * Tests the bottom right corner to make sure that it works for the whole grid.
     * This one tests the column constraints
     * @result The puzzle should be legal
     */
    @Test 
    public void testMaxColBound(){
        FutoshikiPuzzle puz31 = new FutoshikiPuzzle(5);
        puz31.setSquare(4,5,5);
        puz31.setSquare(5,5,4);
        puz31.setColumnConstraint(5,4,"^");
        assertEquals("",puz31.getProblems(puz31));
    }
    
    /**
     * Tests if fillPuzzle always returns a true value
     * @result All puzzles should be legal
     */
    @Test
    public void testFillPuzzleAlwaysTrue(){
        FutoshikiPuzzle puz32 = new FutoshikiPuzzle(5);
        puz32.fillPuzzle();
        assertTrue(puz32.isLegal());
        FutoshikiPuzzle puz33 = new FutoshikiPuzzle(5);
        puz33.fillPuzzle();
        assertTrue(puz33.isLegal());
        FutoshikiPuzzle puz34 = new FutoshikiPuzzle(5);
        puz34.fillPuzzle();
        assertTrue(puz34.isLegal());
        FutoshikiPuzzle puz35 = new FutoshikiPuzzle(5);
        puz35.fillPuzzle();
        assertTrue(puz35.isLegal());
        FutoshikiPuzzle puz36 = new FutoshikiPuzzle(5);
        puz36.fillPuzzle();
        assertTrue(puz36.isLegal());
    }
    
    /**
     *  Test to see if the number gets changed for an unchangeable position 
     *  @result Position 1,1 should remain 2 as it is now un-editable
     */
    @Test
    public void testSetSquareOnlyWhenEditable(){
        FutoshikiPuzzle puz37 = new FutoshikiPuzzle(5);
        puz37.setSquare(1,1,2);
        puz37.makeUneditable(1,1);
        puz37.setSquare(1,1,3);
        assertEquals(2,puz37.getNumber(1,1));
    }
    
    /**
     *   Test to see if the number is empty correctly clears the square when it is editable
     *   @result The square is emptied 
     */
    @Test
    public void testEmptyWhenEditable(){
        FutoshikiPuzzle puz38 = new FutoshikiPuzzle(5);
        puz38.setSquare(1,1,2);
        puz38.empty(1,1);
        assertEquals(0,puz38.getNumber(1,1));
    }
    
    /**
     *   Test to see if the number is not cleared when the square is un-editable
     *   @result The square is not emptied 
     */
    @Test
    public void testEmptyWhenUneditable(){
        FutoshikiPuzzle puz38 = new FutoshikiPuzzle(5);
        puz38.setSquare(1,1,2);
        puz38.makeUneditable(1,1);
        puz38.empty(1,1);
        assertNotEquals(0,puz38.getNumber(1,1));
    }
    
    /**
     * Test to see if a legal configuration would pass the isComplete test
     * @result returns true as valid complete puzzle
     */
    @Test
    public void testIsPuzzleCompleteTrue(){
        FutoshikiPuzzle puz39 = new FutoshikiPuzzle(2);
        puz39.setSquare(1,1,1);
        puz39.setSquare(1,2,2);
        puz39.setSquare(2,1,2);
        puz39.setSquare(2,2,1);
        puz39.setRow(1,1,"<");
        puz39.setCol(1,1,"^");
        assertTrue(puz39.isPuzzleComplete());        
    }
    
    /**
     * Test to see if an illegal configuration would pass the isComplete test
     * @result returns false as constraint violation
     */
    @Test
    public void testIsPuzzleCompleteFalseConstraintError(){
        FutoshikiPuzzle puz39 = new FutoshikiPuzzle(2);
        puz39.setSquare(1,1,1);
        puz39.setSquare(1,2,2);
        puz39.setSquare(2,1,2);
        puz39.setSquare(2,2,1);
        // Inserting invalid constraint to cause error
        puz39.setRow(1,1,">");
        puz39.setCol(1,1,"^");
        assertFalse(puz39.isPuzzleComplete());        
    }
    
    /**
     * Test to see if an illegal configuration would pass the isComplete test
     * @result returns false as duplicate numbers in the rows
     */
    @Test
    public void testIsPuzzleCompleteFalseDupNumsInRow(){
        FutoshikiPuzzle puz39 = new FutoshikiPuzzle(2);
        // Adding duplicate nums into row 
        puz39.setSquare(1,1,1);
        puz39.setSquare(1,2,1);
        puz39.setSquare(2,1,2);
        puz39.setSquare(2,2,2);
        assertFalse(puz39.isPuzzleComplete());        
    }
    
    /**
     * Test to see if an illegal configuration would pass the isComplete test
     * @result returns false as duplicate numbers in the columns
     */
    @Test
    public void testIsPuzzleCompleteFalseDupNumsInCol(){
        FutoshikiPuzzle puz39 = new FutoshikiPuzzle(2);
        // Adding duplicate nums into columns 
        puz39.setSquare(1,1,1);
        puz39.setSquare(1,2,2);
        puz39.setSquare(2,1,1);
        puz39.setSquare(2,2,2);
        assertFalse(puz39.isPuzzleComplete());        
    }
    
    // Tests for part 3
    
    /**
     * Test to see if a illegal configuration fails solve 
     * @result if puzzle is invalid, solve returns true
     */
    @Test
    public void solveFailTest(){
        FutoshikiPuzzle puz40 = new FutoshikiPuzzle(2);
        Futoshiki futoshiki = new Futoshiki();
        puz40.setSquare(1,1,2);
        puz40.setSquare(1,2,2);
        assertFalse(futoshiki.solve(puz40,2));
    }
    
    /**
     * Test to see if a legal configuration passes solve 
     * @result if puzzle is valid, solve returns true
     */
    @Test
    public void solveWorkTest(){
        FutoshikiPuzzle puz41 = new FutoshikiPuzzle(2);
        Futoshiki futoshiki = new Futoshiki();
        puz41.setSquare(1,1,2);
        puz41.setSquare(1,2,1);
        assertTrue(futoshiki.solve(puz41,2));
    }
}
