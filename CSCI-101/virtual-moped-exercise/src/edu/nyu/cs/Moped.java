package edu.nyu.cs;

import java.util.Arrays;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as indicated.
 * Create as many additional properties or methods as you want, as long as the given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction (i.e. the black box metaphor) and encapsulation (i.e. methods and properties belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {

    /*
    Advertising Locations
     * at 79th St. and 8th Ave., the Moped should output an ad for the American Museum of Natural History.
     * at 74th St. and 1st Ave., the Moped should output an ad for Memorial Sloan Kettering.
     * at 56th St. and 3rd Ave., the Moped should output an ad for Tina's Cuban Cuisine restaurant.
     * at 12th St. and 4th Ave., the Moped should output an ad for The Strand book shop.
     */
    /*
     * Starting Location: 10th street and 5th ave facing south
     * 10 Avenues and 200 streets
     * Avenues increase when heading west
     * Streets increase when heading north
     */

    private int x;
    private int y;
    private String orientation;
    private int gasLevel;
    private boolean atStrand;
    private boolean atCubanCuisine;
    private boolean atMuseum;
    private boolean atMemorial;


    public Moped() {
        x = 10;
        y = 5;
        orientation = "south";
        gasLevel = 100;
    }

     /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        // set the location of the moped
        x = location[0];
        y = location[1];
    }

    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() {
        // get the location of the moped after each command has run
        int[] location = {x, y};
        return location;
    }

    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation.toLowerCase();
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return orientation.toLowerCase();       
    }

    private String formatLocation( String location) {
        if(atMuseum){
            return String.format("Now at %s, facing %s.  Did you know the American Museum of Natural History has 45 permanent exhibition halls, spanning 28 million square feet?", location, orientation);
        } else if(atMemorial){
            return String.format("Now at %s, facing %s.  Did you know Memorial Sloan Kettering has 1,000 beds, 2,000 physicians, and 12,000 employees?", location, orientation);
        } else if(atCubanCuisine){
            return String.format("Now at %s, facing %s.  Did you know Tina's Cuban Cuisine has been serving authentic Cuban food since 1985?", location, orientation);
        } else if(atStrand){
            return String.format("Now at %s, facing %s.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?", location, orientation);
        } else {
            return String.format("Now at %s, facing %s.", location, orientation);
        }
    }

    private String formatCoordinates(int x, int y){
        String suffix;
        if (x == 1) {
            suffix = "st";
        } else if (x == 2) {
            suffix = "nd";
        } else if (x == 3) {
            suffix = "rd";
        } else {
            suffix = "th";
        }
        return String.format("%d%s St. and %d%s Ave", x, suffix, y, suffix);
    }

    private String formatParking(int x, int y){
        String suffix;
        if (x == 1) {
            suffix = "st";
        } else if (x == 2) {
            suffix = "nd";
        } else if (x == 3) {
            suffix = "rd";
        } else {
            suffix = "th";
        }
        return String.format("%d%s St. and %d%s Ave", x, suffix, y, suffix);
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        String location = formatCoordinates(x, y);
        if (x == 12 && y == 4) {
            atStrand = true;
        } else if(x == 74 && y == 1){
            atMemorial = true;
        } else if(x == 56 && y == 3){
            atCubanCuisine = true;
        } else if(x == 79 && y == 8){
            atMuseum = true;
        }
        System.out.println(formatLocation(location));
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        getGasLevel();
        
        if (getGasLevel() < 5){
            System.out.println("Not enough gas to turn!");
        } else {
            // check if it has gone off the map
            if (orientation.equals("north")) {
                orientation = "west";
                if(y < 10){
                    y++;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("west")) {
                orientation = "south";
                if(x>1){
                    x--;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("south")) {
                orientation = "east";
                if(y > 1){
                    y--;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("east")) {
                orientation = "north";
                if(x < 200){
                    x++;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            }
        }
        getGasLevel();
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        getGasLevel();
        if (getGasLevel() < 5){
            System.out.println("Not enough gas to turn!");
        } else {
            // check if it has gone off the map
            if (orientation.equals("north")) {
                orientation = "east";
                if(y > 1){
                    y--;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("east")) {
                orientation = "south";
                if(x>1){
                    x--;
                    gasLevel -= 5;
                } else{
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("south")) {
                orientation = "west";
                if(y < 10){
                    y++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("west")) {
                orientation = "north";
                if(x < 200){
                    x++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            }
        }
        getGasLevel();
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        getGasLevel();
        if (getGasLevel() < 5){
            System.out.println("Not enough gas to move!");
        } else {
            // check if it has gone off the map
            if (orientation.equals("north")) {
                if(x < 200){
                    x++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("east")) {
                if(y > 1){
                    y--;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("south")) {
                if(x > 1){
                    x--;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("west")) {
                if(y < 10){
                    y++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            }
        }
        getGasLevel();
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        getGasLevel();
        if (getGasLevel() < 5){
            System.out.println("Not enough gas to move!");
        } else {
            // check if it has gone off the map
            if (orientation.equals("north")) {
                if(x > 1){
                    x--;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("east")) {
                if(y < 10){
                    y++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("south")) {
                if(x < 200){
                    x++;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            } else if (orientation.equals("west")) {
                if(y > 1){
                    y--;
                    gasLevel -= 5;
                } else {
                    System.out.println("You can't go off the map!");
                }
            }
        }
        getGasLevel();
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        // return the gasLevel
        return gasLevel;
    }

    public String checkGas() {
        if (gasLevel == 0) {
            System.exit(0);
            return "We have run out of gas.  Bye bye!";
        } else {
            return "";
        }
    }
    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        // print the gas level

        if (gasLevel == 0) {
            System.out.println("We have run out of gas.  Bye bye!");
        } else {
            System.out.println("The gas tank is currently " + getGasLevel() + "% full.");
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        // fill the gas tank to 100
        gasLevel = 100;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.  
     * You can use System.exit(0); to cause a program to quit with status code 0, which indicates a normal graceful exit. 
     * (In case you were wondering, status code 1 represents quitting as a result of an error of some kind).
     */
    public void park() {
        // park moped at current location 
        System.out.println("Parking at " + formatParking(x, y));
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {
        // end up at 15th Street and 8th Avenue from current location
        // check gas level at all times
            
            // for orientation north
        if(orientation.equals("north") && x < 15){
            while(x < 15){
                 checkGas();
                 goStraight();
                 gasLevel -= 5;
                 x++;
            }
            if(y < 8){
                 checkGas();
                 goRight();
                 gasLevel -=5;
                 while(y < 8){
                     checkGas();
                     goStraight();
                     gasLevel -= 5;
                     y++;
                 }
             } else if(y > 8){
                    checkGas();
                 goLeft();
                 gasLevel -= 5;
                 while(y > 8){
                        checkGas();
                     goStraight();
                     gasLevel -= 5;
                     y--;
                 }
            }
         } else if(orientation.equals("north") && x > 15){
             while(x > 15){
                    checkGas();
                 goBackwards();
                 gasLevel -= 5;
                 x--;
             }
             if(y < 8){
                    checkGas();
                 goRight();
                 gasLevel -=5;
                 while(y < 8){
                     goStraight();
                     gasLevel -= 5;
                     y++;
                 }
             } else if(y > 8){
                    checkGas();
                 goLeft();
                 gasLevel -= 5;
                 while(y > 8){
                     checkGas();
                     goStraight();
                     gasLevel -= 5;
                     y--;
                 }
             }
         }
         // for orientation east
         if(orientation.equals("east") && y < 8){
             while(y < 8){
                    checkGas();
                 goStraight();
                 gasLevel -= 5;
                 y++;
             }
             if(x < 15){
                    checkGas();
                 goRight();
                 gasLevel -=5;
                 while(x < 15){
                     checkGas();
                     goStraight();
                     gasLevel -= 5;
                     x++;
                 }
             } else if(x > 15){
                    checkGas();
                 goLeft();
                 gasLevel -= 5;
                 while(x > 15){
                     checkGas();
                     goStraight();
                     gasLevel -= 5;
                     x--;
                 }
             }
         } else if(orientation.equals("east") && y > 8){
             while(y > 8){
                    checkGas();
                 goBackwards();
                 gasLevel -= 5;
                 y--;
             }
             if(x < 15){
                    checkGas();
                 goRight();
                 gasLevel -=5;
                 while(x < 15){
                        checkGas(); 
                     goStraight();
                     gasLevel -= 5;
                     x++;
                 }
             } else if(x > 15){
                    checkGas();
                 goLeft();
                 gasLevel -= 5;
                 while(x > 15){
                        checkGas();
                     goStraight();
                     gasLevel -= 5;
                     x--;
                 }
             }
         }
         //for orientation west
         if(orientation.equals("west") && y < 8){
             while(y < 8){
                 goBackwards();
                 gasLevel -= 5;
                 y++;
             }
             if(x < 15){
                 goRight();
                 gasLevel -=5;
                 while(x < 15){
                     goStraight();
                     gasLevel -= 5;
                     x++;
                 }
             } else if(x > 15){
                 goLeft();
                 gasLevel -= 5;
                 while(x > 15){
                     goStraight();
                     gasLevel -= 5;
                     x--;
                 }
             }
         } else if(orientation.equals("west") && y > 8){
             while(y > 8){
                 goStraight();
                 gasLevel -= 5;
                 y--;
             }
             if(x < 15){
                 goRight();
                 gasLevel -=5;
                 while(x < 15){
                     goStraight();
                     gasLevel -= 5;
                     x++;
                 }
             } else if(x > 15){
                 goLeft();
                 gasLevel -= 5;
                 while(x > 15){
                     goStraight();
                     gasLevel -= 5;
                     x--;
                 }
             }
         }
         //for orientation south
         if(orientation.equals("south") && x < 15){
             while(x < 15){
                 goBackwards();
                 gasLevel -= 5;
                 x++;
             }
             if(y < 8){
                 goRight();
                 gasLevel -=5;
                 while(y < 8){
                     goStraight();
                     gasLevel -= 5;
                     y++;
                 }
             } else if(y > 8){
                 goLeft();
                 gasLevel -= 5;
                 while(y > 8){
                     goStraight();
                     gasLevel -= 5;
                     y--;
                 }
             }
         } else if(orientation.equals("south") && x > 15){
             while(x > 15){
                 goStraight();
                 gasLevel -= 5;
                 x--;
             }
             if(y < 8){
                 goRight();
                 gasLevel -=5;
                 while(y < 8){
                     goStraight();
                     gasLevel -= 5;
                     y++;
                 }
             } else if(y > 8){
                 goLeft();
                 gasLevel -= 5;
                 while(y > 8){
                    checkGas();
                     goStraight();
                     gasLevel -= 5;
                     y--;
                 }
             }
         

        }
        
        
        // Print out completion message
        System.out.println(formatCoordinates(x, y) + "Now at Xi'an famous foods");   
    }

   /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        
        String help = "Commands: \n" + "\"straight on\" (moped goes straight one block \n" + "\"back up\" (moped backs up one block \n" + "\"go right\" (moped turns right and goes one block) \n" + "\"go left\" (moped turns left and goes one block) \n" + "\"how we doin'?\" (check gas level) \n" + "\"fill it up\" (fills up moped to 100% gas) \n" + "\"go to Xi'an Famous Foods\" (moped goes to Xi'an Famous Foods) \n";
        System.out.println(help);
        return help;


    }

}
