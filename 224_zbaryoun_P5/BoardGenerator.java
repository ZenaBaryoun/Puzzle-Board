import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * provides static methods for generating various kinds of initial states for a Board
 * @author ZenaBaryoun
 */
public class BoardGenerator {
    /**
     * reads data from a text file and creates the respective Board
     * @param filename
     * @return
     */
    public static Board<Integer> loadIntegersFromFile(String filename) {
        // using file reader class read file
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            //Delimiter for spaces
            sc.useDelimiter("\\s+");

            // state that first number = rows and second number = col (special case)
            int rows = Integer.parseInt(sc.next());
            int cols = Integer.parseInt(sc.next());
            //make a board
            Board<Integer> board = new Board<Integer>(rows, cols);
            //while loop
            for (int row = 0; row < board.getHeight(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    String element = sc.next();
                    if (!element.equals("-")) {
                        Integer integer = Integer.valueOf(element);
                        board.setElement(row, col, integer);
                    }
                }
            }
            return board;
        } catch (FileNotFoundException exception) {
            return null;
        }
    }

    /**
     * private method to read a data from a text file
     * then creates an array of integers that holds the sums of each row or col of a board.
     * @param filename
     * @return
     */
    private static int[] getValuesFromFile(String filename) {
        try {
            // using file reader class to read file
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\\s+");
            //state that first number = number of rows
            int length = sc.nextInt();
            int[] result = new int[length];
            int x = 0;
            //increment index and add data in array
            while (sc.hasNext()) {
                result[x] = sc.nextInt();
                x++;
            }
            return result;
        } catch (FileNotFoundException exception) {
            //do nothing when exception occurs
            return new int[0];
        }
    }

    /**
     * uses private method to find row sum
     * @param filename
     * @return
     */
    public static int[] loadRowSumsFromFile(String filename) {
        //uses private method
        return getValuesFromFile(filename);
    }

    /**
     * uses private method to find col sum
     * @param s
     * @return
     */
    public static int[] loadColSumsFromFile(String s) {
        //uses private method
        return getValuesFromFile(s);
    }

    /**
     * Creates and returns a board with dimensions height x width
     * The elements are in the range 1 - maxRandomNumber
     * All elements within a row and a col must be unique
     * The rowSum and the colSum are populated with the sum of the elements in each row or col
     * After the calculations for rowSum and colSum, nullElements are the number of elements that will be randomly selected and nulled
     * @param height
     * @param width
     * @param maxRandomNumber
     * @param nullElements
     * @param rowSum
     * @param colSum
     * @return
     */
    public static Board<Integer> randomIntegers(int height, int width, int maxRandomNumber, int nullElements, int[] rowSum, int[] colSum) {
        // create board with dimensions height x width
        Board<Integer> board = new Board<Integer>(height, width);
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            // make sure none of the same elements are in each row
            int[] rowNumbers = new int[width];
            for (int col = 0; col < width; col++) {
                // generating a random number
                int ranNumber = random.nextInt(maxRandomNumber);
                boolean newNumber = true;
                while (newNumber) {
                    newNumber = false;
                    for (int prevCol = 0; prevCol < col; prevCol++) {
                        //make sure none of the same elements are in each row
                        if (ranNumber == rowNumbers[prevCol]) {
                            // if the number already has been used, generate a new number
                            ranNumber = random.nextInt(maxRandomNumber);
                            // start my loop over
                            newNumber = true;
                            break;
                        }
                    }
                    for (int prevRow = 0; prevRow < row; prevRow++) {
                        if (ranNumber == board.getElement(prevRow, col)) {
                            // if the number already has been used, generate a new number
                            ranNumber = random.nextInt(maxRandomNumber);
                            // start my loop over
                            newNumber = true;
                            break;
                        }
                    }
                }

                // setting that element into the board
                board.setElement(row, col, ranNumber);
                // keep track of the numbers we've inserted
                rowNumbers[col] = ranNumber;
                // adding inserted numbers
                rowSum[row] += ranNumber;
                colSum[col] += ranNumber;
            }
        }
        // pick <nullElements> random spots and make them null
        while( nullElements > 0){
            int randomRow = random.nextInt(height);
            int randomCol = random.nextInt(width);
            //have to check if its not null first
            if (board.getElement(randomRow, randomCol) != null) {
                board.setElement(randomRow, randomCol, null);
                nullElements--;
            }
        }
        return board;
    }

    /**
     * sum for the size
     * @param size
     * @return
     */
    private static int summationForSize(int size){
        int sum = 0;
        for(int i = size; i > 0; i--) {
            sum += i;
        }
        return sum;
    }

    /**
     * makes an array for the sums of the rows or cols of the board
     * @param size
     * @return
     */
    private static int[] getSquareBoardSums(int size) {
        int sum = summationForSize(size);
        int[] result = new int[size];
        for(int i = 0; i < size; i++){
            result[i] = sum;
        }
        return result;
    }

    /**
     * creates a square sized board of random integers in a certain range
     * places null elements in board
     * @param size
     * @param nullElements
     * @return
     */
    public static Board<Integer> completeSquareBoard(int size, int nullElements) {
        return randomIntegers(size, size, size, nullElements, getSquareBoardSums(size), getSquareBoardSums(size));
    }
}
