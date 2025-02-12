/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 * Enum representing reasons for different colors on the map.
 * The ColorReason enum defines reasons associated with specific colors on the map, such as obstacles and targets.
 */
package ex3.gui;
public enum ColorReason {
    OBSTACLE(-1), // Represents an obstacle on the map.
    TARGET(-2);  // Represents a target on the map.

    private final int value; //The integer value associated with the color reason.

    /**
     * Constructs a ColorReason with the specified integer value.
     * @param value ---> value The integer value associated with the color reason.
     */
    ColorReason(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value associated with the color reason.
     * @return ---> The integer value associated with the color reason.
     */
    public int getValue() {
        return value;
    }
}