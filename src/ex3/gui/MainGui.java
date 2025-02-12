/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 */
package ex3.gui;
import ex3.Index2D;
import ex3.Pixel2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import static ex3.gui.StdDraw.OPTIONS_SIZE;
/**
 * The MainGui class represents the graphical user interface (GUI) for displaying a matrix.
 * It provides the user interface elements needed to interact with the board
 * The GUI allows users to draw shapes on the board, choose colors and perform various actions.
 * It handles user interactions such as mouse clicks and menu item selections and updates the display accordingly.
 */
public class MainGui implements MenuItemListener, MouseClickedListener {
    private int boardIndex = 1;
    private final static int CANVAS_SIZE = 500;
    private Circle[][] circles;
    private Color currentPenCircleColor = Color.magenta;
    private DrawAction currentDrawAction = null;
    private Circle firstCircleClicked = null;
    private Circle secondCircleClicked = null;
    private ex3.Map map;
    /**
     * Constructor for the MainGui class.
     * Initializes the GUI components and sets event listeners.
     * The GUI is configured with a fixed size canvas, allowing users to interact with the game board.
     */
    public MainGui() {
        StdDraw.setMenuItemListener(this);
        StdDraw.setMouseClickListener(this);
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setXscale(0, CANVAS_SIZE);
        StdDraw.setYscale(0, CANVAS_SIZE);
        initBoard();
    }
    /**
     * Initializes the game board based on the selected board size.
     * Creates an array of Circle objects that represent the game board cells.
     * The circles are drawn on the canvas in appropriate colors and positions.
     */
    private void initBoard() {
        StdDraw.clear();
        initMatrix(OPTIONS_SIZE.get(boardIndex));
        drawCircles();
    }
    /**
     * Draws the circles on the game board.
     * Each circle represents a cell on the board and is drawn in a specified color and position.
     * offset ---> Calculates the distance between the centers of adjacent circles. It divides the canvas width (500) by the number of circles in a row to determine the spacing.
     * temp ---> Temporary variable to store the current y-coordinate of the circle.
     * rowTemp---> Starting x-coordinate for the circles. It represents the leftmost position where circles will be drawn.
     */
    private void drawCircles() {
        int offset =500 / circles.length;
        int temp;
        int rowTemp = 10;
        for (int i = 0; i < circles.length; i++) {
            temp =10;
            if (i != 0) {
                rowTemp += offset;
            }
            for (int j = 0; j < circles.length; j++) {
                Circle circle = circles[i][j];
                circle.setCenterX(rowTemp);
                circle.setCenterY(temp);
                circle.setColor(Color.magenta);
                circle.reDraw();
                temp += offset;
            }
        }
    }

    /**
     * Initializes the matrix representing the game board.
     * Each cell of the board is represented by a Circle object.
     * @param boardSize ---> boardSize The size of the game board.
     */
    private void initMatrix(BoardSize boardSize) {
        int size = boardSize.getSize();
        circles = new Circle[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                circles[i][j] = new Circle(i, j, 0, 0, Color.magenta);
            }
        }
        map = new ex3.Map(size);
    }

    /**
     * Displays the graphical user interface on the screen.
     * Calls the show() method of the StdDraw library to render the interface elements.
     */

    public void show() {
        StdDraw.show();
        StdDraw.pause(2);
    }

    /**
     * Handles clicks on a menu item.
     * Updating the GUI state based on the selected menu item.
     * @param e ---> The ActionEvent object containing information about the menu item click event.
     */
    @Override
    public void onMenuItemClicked(ActionEvent e) {
        if (e.getActionCommand().equals("5x5")) {
            boardIndex = 0;
            initBoard();
        }
        if (e.getActionCommand().equals("20x20")) {
            boardIndex =1;
            initBoard();
        }
        else if (e.getActionCommand().equals("40x40")) {
            boardIndex = 2;
            initBoard();
        }
        else if (e.getActionCommand().equals("60x60")) {
            boardIndex = 3;
            initBoard();
        }
        else if (e.getActionCommand().equals("Green")) {
            currentPenCircleColor = Color.green;
        }
        else if (e.getActionCommand().equals("Blue")) {
            currentPenCircleColor = Color.BLUE;
        }
        else if (e.getActionCommand().equals("Black")) {
            currentPenCircleColor = Color.BLACK;
        }
        else if (e.getActionCommand().equals("Red")) {
            currentPenCircleColor = Color.RED;
        }
        else if (e.getActionCommand().equals("Yellow")) {
            currentPenCircleColor = Color.YELLOW;
        }
        else if (e.getActionCommand().equals("Pink")) {
            currentPenCircleColor = Color.PINK;
        }
        else if (e.getActionCommand().equals("Rect")) {
            currentDrawAction = DrawAction.Rectangle;
        }
        else if (e.getActionCommand().equals("shortest path")) {
            currentDrawAction = DrawAction.SHORTETH_PATH;
        }
    }
    /**
     * Handles the mouse click event on the screen.
     * Checks if a draw action is selected, if not, displays an error message.
     * Iterates through all circles on the canvas to find the clicked circle.
     * If it's the first click, sets it as the firstCircleClicked and shows a message.
     * If it's the second click, sets it as the secondCircleClicked and shows a message.
     * If both circles are selected, performs the appropriate action based on the current draw action:
     * If drawing a rectangle, fills the area between the two circles with the current pen color and marks it as an obstacle on the map.
     * If finding the shortest path, marks the second circle as the target, calculates the shortest path between the two circles on the map, and highlights the path.
     * Resets the firstCircleClicked and secondCircleClicked after the action is performed.
     * @param e ---> The MouseEvent object representing the mouse click event.
     */
    @Override
    public void onScreenMouseClicked(MouseEvent e) {
        if (currentDrawAction == null) {
            showErrorMsg("No action chosen");
            return;
        }
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles.length; j++) {
                if (circles[i][j].getShape().contains(e.getPoint())) {
                    if (firstCircleClicked == null) {
                        firstCircleClicked = circles[i][j];
                        showCircleClicked();
                    } else if (secondCircleClicked == null) {
                        secondCircleClicked = circles[i][j];
                        showCircleClicked();
                    }
                    break;
                }
            }
        }
        if (firstCircleClicked != null && secondCircleClicked != null) {
            if (currentDrawAction == DrawAction.Rectangle) {
                for (int i = firstCircleClicked.getI(); i <= secondCircleClicked.getI(); i++) {
                    for (int j = secondCircleClicked.getJ(); j <= firstCircleClicked.getJ(); j++) {
                        circles[i][j].setColor(currentPenCircleColor);
                        circles[i][j].reDraw();
                        map.setPixel(i, j, ColorReason.OBSTACLE.getValue());
                    }
                }
            } else if (currentDrawAction == DrawAction.SHORTETH_PATH) {
                if (isCircleInsideObstacle(firstCircleClicked) || isCircleInsideObstacle(secondCircleClicked)) {
                    showErrorMsg("one of the circles inside obstacle");
                    firstCircleClicked = null;
                    secondCircleClicked = null;
                    return;
                }
                map.setPixel(secondCircleClicked.getI(), secondCircleClicked.getJ(), ColorReason.TARGET.getValue());
                Pixel2D from = new Index2D(firstCircleClicked.getI(), firstCircleClicked.getJ());
                Pixel2D to = new Index2D(secondCircleClicked.getI(), secondCircleClicked.getJ());
                Pixel2D[] arr = map.shortestPath(from, to, ColorReason.OBSTACLE.getValue());
                for (int i = 0; i < arr.length; i++) {
                    int x = arr[i].getX();
                    int y = arr[i].getY();
                    circles[x][y].setColor(currentPenCircleColor);
                    circles[x][y].reDraw();
                    System.out.println(String.format("Step - %d - (%d,%d) ", i, y, x));
                }
            }
            firstCircleClicked = null;
            secondCircleClicked = null;
         }
        }
    private boolean isCircleInsideObstacle(Circle circle) {
        int color = map.getPixel(circle.getI(), circle.getJ());
        if (color == -1) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Displays an error message dialog box with the specified error message.
     * @param msg ---> The error message to be displayed.
     */
    private void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Displays an information message dialog box indicating that a circle has been clicked.
     */
    private void showCircleClicked() {
        JOptionPane.showMessageDialog(null, "Circle is clicked!", "", JOptionPane.INFORMATION_MESSAGE);
    }
}
