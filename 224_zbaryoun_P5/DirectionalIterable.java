import java.util.Iterator;

/**
 * an interface that inherits from the interface iterable
 * @param <T>
 * @author ZenaBaryoun
 */
public interface DirectionalIterable<T> extends Iterable<T>{
    //overloads the iterator method that does not have any parameters
    public Iterator<T> iterator(Direction d);

}
