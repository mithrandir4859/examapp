package ua.util;

/**
 * Created by Adevi on 8/9/2014.
 */
public class Tuple<T0, T1> {
    private final T0 elem0;
    private final T1 elem1;

    public Tuple(T0 elem0, T1 elem1) {
        this.elem0 = elem0;
        this.elem1 = elem1;
    }

    public T0 get0() {
        return elem0;
    }

    public T1 get1() {
        return elem1;
    }
}
