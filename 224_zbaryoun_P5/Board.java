import java.util.Iterator;

/**
 * A generic class that implements the DirectionalIterable interface.
 * Its purpose is to store the 2D board and provide various methods for managing and interacting with it.
 *
 * @param <T>
 * @author ZenaBaryoun
 */
public class Board<T> implements DirectionalIterable<T> {
    //array to store 2D board
    private T[][] matrix;

    //Creates square sized board by multiplying its size by each other
    public Board(int size) {
        this(size, size);
    }

    /**
     * Creates a rectangular board with all elements having a null value
     * Uses the @SuppressWarnings annotation to avoid the warnings of the compiler
     *
     * @param height
     * @param width
     */
    @SuppressWarnings("unchecked")
    public Board(int height, int width) {
        matrix = (T[][]) new Object[height][width];
    }

    /**
     * getter for elements in 2D board
     *
     * @param row
     * @param col
     * @return matrix
     */
    public T getElement(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Setter for the elements of the 2D board
     *
     * @param row
     * @param col
     * @param el
     */
    public void setElement(int row, int col, T el) {
        matrix[row][col] = el;
    }

    /**
     * Getter for the height of the 2D board
     *
     * @return matrix length at the first col
     */
    public int getHeight() {
        return matrix.length;
    }

    /**
     * Getter for the width of the 2D board
     *
     * @return matrix length
     */
    public int getWidth() {
        return matrix[0].length;
    }

    /**
     * if the row is smaller than the height and the col is smaller than the width return it
     * otherwise it does not have a valid index
     *
     * @param row
     * @param col
     * @return row and col
     */
    public boolean isValidIndex(int row, int col) {
        return row < getHeight() && col < getWidth();
    }

    /**
     * printing the 2d board with indications of when the col starts and when it ends
     *
     * @return
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (T[] row : matrix) {
            output.append("Row{");
            for (T element : row) {
                if (element == null) {
                    output.append("null");
                } else {
                    output.append(element);
                }
                output.append("\t");
            }
            output.append("}\n");
        }
        return output.toString();
    }

    /**
     * returns new board iterator object
     *
     * @param d
     */
    @Override
    public Iterator<T> iterator(Direction d) {
        return new BoardIterator(d, this);
    }

    /**
     * returns a new board iterator object
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new BoardIterator(Direction.HORIZONTAL, this);
    }
}
