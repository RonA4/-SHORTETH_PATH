/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 * Class representing the size of a board.
 * The BoardSize class encapsulates the width and height dimensions of a board.
 */
package ex3.gui;
public class BoardSize {
    private int width;   // The width dimension of the board.
    private int height;   // The height dimension of the board.
    /**
     * Constructs a BoardSize object with the specified size.
     * The width and height dimensions of the board are set to the provided size.
     * @param size --->  The size value to be used for both width and height dimensions.
     */
    public BoardSize(int size) {
        this.width = size;
        this.height = size;
    }
    /**
     * Retrieves the size value representing the width dimension of the board.
     * @return --->  The width dimension of the board.
     */
    public int getSize() {
        return width;
    }
}
