/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 * Class representing a circle object.
 * The Circle class encapsulates the attributes and behaviors of a circle,
 * including its center coordinates, color, and drawing functionality.
 */
package ex3.gui;
import java.awt.*;
public class Circle {
//  These are integer fields representing the x and y coordinates of the center of the circle.
    private int centerX;
    private int centerY;
    private Color color; //  This is a field of type `Color` representing the color of the circle.
    private Shape circleInScreen; //This is a field of type `Shape` representing the shape to be drawn on the screen. It is a circle shape.
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    private int i, j;
    public Circle(int i, int j, int centerX, int centerY, Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
        this.j = j;
        this.i = i;
    }
    /**
     * This method sets the x-coordinate of the center of the circle to the provided value `centerX`.
     * @param centerX  ---> The new x-coordinate value.
     */
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    /**
     * This method sets the y-coordinate of the center of the circle to the provided value `centerY`.
     * @param centerY ---> centerY The new y-coordinate value.
     */
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    /**
     * This method sets the color of the circle to the provided `Color` object `color`.
     * @param color ---> The new color value.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * This method returns the shape to be drawn on the screen. It likely returns the circle shape stored in the `circleInScreen` field.
     * @return ---> The circle shape to be drawn on the screen.
     */
    public Shape getShape() {
        return circleInScreen;
    }
    /**
     * This method redraws the circle on the screen using the current attributes (center coordinates and color).
     * It sets the pen color to the color of the circle (`color`).
     * It draws a filled circle on the screen at the specified center coordinates (`centerX`, `centerY`) with a radius of 2.8 units.
     * It updates the `circleInScreen` field to reference the newly drawn shape.
     */
    public void reDraw() {
        StdDraw.setPenColor(color);
        circleInScreen = StdDraw.filledCircle(centerX, centerY, 2.8);
    }
}
