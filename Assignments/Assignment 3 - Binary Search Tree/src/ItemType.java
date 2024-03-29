/**
 * Represents an item in the data structure.
 */
public class ItemType {

    private int value;

    /**
     * Default constructor.
     * Initializes the instance member by parameter num.
     * 
     * @param num the value which will initialize the instance
     */
    public ItemType(int num) {
        this.value = num;
    } // ItemType(int num)

    /**
     * Compares the value of item with the current object's
     * value.
     * 
     * @param item the object which is to be compared to the calling object
     * @return -1 if value of the calling object is less than the value in item,
     *         0 if equal, and 1 if greater
     */
    public int compareTo(ItemType item) {
        return Integer.signum(this.value - item.value);
    } // compareTo(ItemType)

    /**
     * Getter method.
     * 
     * @return the instance member called value of int type
     */
    public int getValue() {
        return value;
    } // getValue()

} // ItemType