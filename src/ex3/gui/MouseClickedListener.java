/**
 *Name ---> Ron Amsalem.
 *ID ---> 326029600.
 * An interface defining a listener for mouse-click events on the screen.
 * Implementations of this interface can be registered to receive notifications when the screen is clicked.
 */
package ex3.gui;
import java.awt.event.MouseEvent;

public interface MouseClickedListener {
    /**
     * Method called when a mouse-click event occurs on the screen.
     * Implementations should define the behavior to be executed in response to the mouse-click event.
     * @param e ---> The MouseEvent object containing information about the mouse-click event.
     */
    void onScreenMouseClicked(MouseEvent e);
}
