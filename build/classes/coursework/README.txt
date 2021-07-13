Displaying the data:

I use a GridPane to store the grid. It is sized twice as big as the grid 
to fit in all the constraints. The squares themselves are buttons and the
constraints are labels. 

The initial grid is filled by searching through the 
different positions and assigning text values based on the arrays in 
FutoshikiPuzzle. If a number is 0 then the button is made blank, whereas if
the number is bigger than 0, it sets the text to that number and makes the
background grey. The constraints are stored in the similar way.

The GUI is kept updated by having buttons in the grid, which means each time 
a position in the grid that is editable is clicked the grid is updated with a 
changed value.

Editing the data

When an button is an editable one it has an action for on click. 
When clicked it will change the value of the button text depending on what value
is stored in the corresponding position in FutoshikiPuzzle's squares.
If the number is less than the size of the grid, it increments by 1 e.g. 0
becomes 1 which means a blank square put a 1 in it. Whereas if the number is 
equal to the size then it makes the number = 0 and the text of the button is
made blank.

Optional Extras:

New Game button:
Creates a new instance of the start method that bring the game back to the
options screen to make a new puzzle

Complete button:
Checks if a puzzle is complete, if not it prints the unfinished grid to console
and says not complete yet. If it is complete it prints congrats to console and
exits the program

Name:
Adding the ability to enter the users name. This was intended to be part of a
leaderboard system but this was not implemented in time.