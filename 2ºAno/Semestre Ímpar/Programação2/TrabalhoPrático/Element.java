import java.io.Serializable;

/**
 * Generic class that defines the basic element
 * composed by a number of parameterized things
 */

public class Element<T> implements Serializable {

    // data members

    private int count;
    private T thing;

    // -----------------------------------------
    //
    // Constructors:
    //
    // -----------------------------------------

    public Element(int count, T thing) {
        this.count = count;
        this.thing = thing;
    }

    /** 
     * Returns the number of elements
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of elements
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the parameterized thing
     * @return T
     */
    public T getThing() {
        return thing;
    }

    /**
     * Sets the parameterized thing
     * @param thing
     */
    public void setThing(T thing) {
        this.thing = thing;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Element ["+ getThing().getClass().getSimpleName() + "=" + 
        thing + ", count = " + getCount() + "]";
    }

}