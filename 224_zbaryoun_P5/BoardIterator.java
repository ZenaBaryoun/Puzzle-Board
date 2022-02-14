import java.util.Iterator;

/**
 * A generic class that implements the Iterator interface
 * Its purpose is to provide an iterator for the Board class
 * @param <T>
 * @author ZenaBaryoun
 */
public class BoardIterator<T> implements Iterator<T> {
    private Direction direction;
    private Board<T> board;
    private int currentRow;
    private int currentColumn;
    private boolean reversed;

    public BoardIterator(Direction direction, Board board) {
        this.direction = direction;
        this.board = board;
    }

    @Override
    public boolean hasNext() {
        return board.isValidIndex(currentRow, currentColumn);
    }

    /**
     * Finding the elements in each different kind of board direction
     * @return
     */
    @Override
    public T next() {
        switch (direction) {
            default:
            case HORIZONTAL: {
                if(board.isValidIndex(currentRow, currentColumn)) {
                    T result =  board.getElement(currentRow, currentColumn);
                    if (currentColumn + 1 >= board.getWidth()) {
                        // if there are no more in row get first element of next row (col = 0)
                        currentColumn = 0;
                        currentRow = currentRow + 1;
                    } else {
                        // if there are more in the row, get next element in row
                        currentColumn = currentColumn + 1;
                    }
                    return result;
                }
                throw new IndexOutOfBoundsException();
            }
            case VERTICAL: {
                if(board.isValidIndex(currentRow, currentColumn)) {
                    T result =  board.getElement(currentRow, currentColumn);
                    if (currentRow + 1 >= board.getHeight()) {
                        // if there are no more in column get first element of next column (row = 0)
                        currentRow = 0;
                        currentColumn = currentColumn + 1;
                    } else {
                        // if there are more in the column, get next element in column
                        currentRow = currentRow + 1;
                    }
                    return result;
                }
                throw new IndexOutOfBoundsException();
            }
            case HORIZONTALSNAKE: {
                if(board.isValidIndex(currentRow, currentColumn)) {
                    T result =  board.getElement(currentRow, currentColumn);
                    if (reversed) {
                        // moving to the left
                        if (currentColumn - 1 < 0) {
                            // if there are no more in row get first element of next row (col remains the same)
                            currentRow = currentRow + 1;
                            // switch directions
                            reversed = false;
                        } else {
                            // otherwise, keep going left
                            currentColumn = currentColumn - 1;
                        }
                    } else {
                        // moving to the right
                        if (currentColumn + 1 >= board.getWidth()) {
                            // if there are no more in row get last element of next row (col remains the same)
                            currentRow = currentRow + 1;
                            // switch directions
                            reversed = true;
                        } else {
                            // otherwise, keep going right
                            currentColumn = currentColumn + 1;
                        }
                    }
                    return result;
                }
                throw new IndexOutOfBoundsException();
            }
            case VERTICALSNAKE: {
                if(board.isValidIndex(currentRow, currentColumn)) {
                    T result =  board.getElement(currentRow, currentColumn);
                    if (reversed) {
                        // moving up
                        if (currentRow - 1 < 0) {
                            // if there are no more in column get first element of next column (row remains the same)
                            currentColumn = currentColumn + 1;
                            // switch directions
                            reversed = false;
                        } else {
                            // otherwise, keep going up
                            currentRow = currentRow - 1;
                        }
                    } else {
                        // moving down
                        if (currentRow + 1 >= board.getHeight()) {
                            // if there are no more in column get last element of next column (row remains the same)
                            currentColumn = currentColumn + 1;
                            // switch directions
                            reversed = true;
                        } else {
                            // otherwise, keep going down
                            currentRow = currentRow + 1;
                        }
                    }
                    return result;
                }
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
