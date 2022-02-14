/**
 * a class that solves a certain mathematical board puzzle
 * given a board that is incomplete, it finds the missing elements while satisfying certain constraints
 * @author ZenaBaryoun
 */
public class Puzzle {

    /**
     * check if number can be put in row
     * @param board
     * @param row
     * @param n
     * @return
     */
    private static boolean containsInRow(Board<Integer> board, int row, int n) {
        // loop over elements in row
        for (int searchCol = 0; searchCol < board.getWidth(); searchCol++) {
            Integer element = board.getElement(row, searchCol);
            // if element is not null and n is in row then return true
            if (element != null && element == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if number can be put in col
     * @param board
     * @param col
     * @param n
     * @return
     */
    private static boolean containsInCol(Board<Integer> board, int col, int n) {
        // loop over element in col
        for (int searchRow = 0; searchRow < board.getHeight(); searchRow++) {
            Integer element = board.getElement(searchRow, col);
            // if element is not null and n is in row then return true
            if ( element != null && element == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * private method to add all elements in a row
     * @param board
     * @param row
     * @return
     */
    private static int getSumOfRow(Board<Integer> board, int row) {
        int sum = 0;
        // loop over elements in row
        for (int searchCol = 0; searchCol < board.getWidth(); searchCol++) {
            Integer element = board.getElement(row, searchCol);
            // if element is not null then add all elements to get sum of the row
            if (element != null) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * private method to add all elements in a col
     * @param board
     * @param col
     * @return
     */
    private static int getSumOfCol(Board<Integer> board, int col) {
        int sum = 0;
        // loop over elements in col
        for (int searchRow = 0; searchRow < board.getHeight(); searchRow++) {
            Integer element = board.getElement(searchRow, col);
            // if element is not null then add all elements to get sum of the col
            if (element != null) {
                sum += element;
            }
        }
        return sum;
    }

    /**
     * checks for all constraints on a potential value n
     * @param board
     * @param row
     * @param col
     * @param n
     * @param maxNum
     * @param rowTotal
     * @param colTotal
     * @return
     */
    private static boolean isAllowed(Board<Integer> board, int row, int col, int n, int maxNum, int rowTotal, int colTotal){
        return n >= 1
                && n <= maxNum
                && !containsInCol(board, col, n)
                && !containsInRow(board, row, n)
                && n <= rowTotal - getSumOfRow(board, row)
                && n <= colTotal - getSumOfCol(board, col);
    }

    /**
     * uses solve method
     * @param board
     * @param maxNum
     * @param rowSum
     * @param colSum
     */
    public static void solution(Board<Integer> board, int maxNum, int[] rowSum, int[] colSum) {
        solve(board, maxNum, rowSum, colSum);
   }

    /**
     * looks for correct number to set into the board
     * @param board
     * @param maxNum
     * @param rowSum
     * @param colSum
     * @return
     */
   private static boolean solve(Board<Integer> board, int maxNum, int[] rowSum, int[] colSum) {
       // loop over row
       for (int row = 0; row < board.getHeight(); row++) {
           // loop over col
            for (int col = 0; col < board.getWidth(); col++) {
                Integer currentSpot = board.getElement(row, col);
                // if current spot = null then try every number from the min to the max until we find a value that works and recurse
                if (currentSpot == null) {
                    for(int number=1; number <= maxNum; number++) {
                        if(isAllowed(board, row, col, number, maxNum, rowSum[row], colSum[col])){
                            board.setElement(row, col, number);
                            // the recursion
                            if(solve(board, maxNum, rowSum, colSum)) {
                                return true;
                            }
                            // if fails we have to set back to null and try with a different number
                            else {
                                board.setElement(row, col, null);
                            }
                        }
                    }
                    return false;
                }

            }
        }
        // case 1: there are no more nulls
        return true;
    }
}
