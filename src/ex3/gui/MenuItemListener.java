/**
 *Name ---> Ron Amsalem.
 * ID ---> 326029600.
 * An interface defining a listener for menu item click events.
 * Implementations of this interface can be registered to receive notifications when a menu item is clicked.
 */
package ex3.gui;
import java.awt.event.ActionEvent;
public interface MenuItemListener {
    /**
     * Method called when a menu item is clicked.
     * Implementations should define the behavior to be executed in response to the menu item click event.
     * @param e ---> The ActionEvent object containing information about the menu item click event.
     */
    void onMenuItemClicked(ActionEvent e);
}
