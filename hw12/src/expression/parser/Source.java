package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Source {
    boolean hasNext();
    char next();
    RuntimeException error(final String message);
}
