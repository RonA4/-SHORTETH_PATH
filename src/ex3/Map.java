/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 */
package ex3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 */

/**
 ----> READ ME <----
 * In this task we were asked to deal with algorithms from two-dimensional arrays, object-oriented and JUnit.
 * The main task in this task is to implement a set of algorithms for two-dimensional arrays (representing a two-dimensional maze),
   and we were asked to deal with finding the shortest route between two points or between several given points considering the cells in the maze that are considered obstacles.
 *In the assignment we also deal with tests related to performance such as runtime as well as tests related to the correctness of the code I wrote.
 *Then we worked on creating a basic GUI that simulates the two-dimensional array that simulates the maze, with the help of the GUI you can tangibly see the shortest route between two points given obstacles.
 * We were asked to implement the following functions from the Map2D interface:
 (1) 'init(int w, int h, int v)' ---> Initializes a 2D matrix with the given width, height, and initial value for all entries.
 (2) 'init(int[][] arr)' ---> Initializes a 2D  map from a given 2D integer array, creating a deep copy.
 (3) 'getMap()' ---> Returns a deep copy of the underlying 2D matrix.
 (4) 'getWidth()' ---> Returns the width (number of columns) of the 2D map.
 (5) 'getHeight()' ---> Returns the height (number of rows) of the 2D map.
 (6) 'getPixel(int x, int y)' ---> Returns the value at the specified coordinates (x, y) in the map.
 (7) 'getPixel(Pixel2D p)' ---> Returns the value at the coordinates specified by a Pixel2D object.
 (8) 'setPixel(int x, int y, int v)' ---> Sets the value at the specified coordinates (x, y) in the map to the given value (v).
 (9) 'setPixel(Pixel2D p, int v)' ---> Sets the value at the coordinates specified by a Pixel2D object to the given value.
 (10) 'isInside(Pixel2D p)' ---> Checks if the specified pixel coordinates are within the bounds of the map.
 (11) 'isCyclic()' ---> Returns true if the map is considered cyclic (wraps around at the edges).
 (12) 'setCyclic(boolean cy)' ---> Sets the cyclic flag of the map.
 (13) 'fill(Pixel2D p, int new_v)' ---> Fills the connected component of a specified pixel with a new color.
 (14) 'shortestPath(Pixel2D p1, Pixel2D p2, int obsColor)' ---> Computes the shortest valid path between two pixels, avoiding obstacles.
 (15) 'shortestPath(Pixel2D[] points, int obsColor)' ---> Computes the shortest path visiting all specified points, avoiding obstacles.
 (16) 'allDistance(Pixel2D start, int obsColor)' ---> Computes a new map with the shortest path distances from a starting point, avoiding obstacles.
 (17) 'numberOfConnectedComponents(int obsColor)' ---> Computes the number of connected components in the map, considering obstacles.
 - To implement these functions I used the BFS algorithm which is a breadth first search algorithm, before each function there is an explanation detailing the algorithmic methods of each function.
 */
public class Map implements Map2D, Serializable {
    private int[][] _map;
    private boolean _cyclicFlag = true;
    /**
     * Constructs a w*h 2D raster map with an init value v.
     * @param w
     * @param h
     * @param v
     */
    public Map(int w, int h, int v) {
        init(w, h, v);
    }
    /**
     * Constructs a square map (size*size).
     *
     * @param size
     */
    public Map(int size) {
        this(size, size, 0);
    }
    /**
     * Constructs a map from a given 2D array.
     * @param data
     */
    public Map(int[][] data) {
        init(data);
    }
    /**
     * This method creates a two-dimensional array representing a map with the given width and height,
     * and initializes all elements of the map with the specified initial value.
     * @param w ---> the width of the underlying 2D array.
     * @param h ---> the height of the underlying 2D array.
     * @param v ---> the init value of all the entries in the 2D array.
     */
    @Override
    public void init(int w, int h, int v) {
        _map = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                _map[i][j] = v;
            }
        }
    }
    /**
     * This method initializes the map with the contents of the provided two-dimensional array.
     * It performs checks to ensure the array is not null, not empty, and not ragged.
     * If any of these conditions are violated, it throws a RuntimeException
     * @param arr a 2D int array.
     * @throws RuntimeException if the provided array is null, empty, or ragged.
     */
    @Override
    public void init(int[][] arr) {
        if (arr == null) {
            throw new RuntimeException("the array is null");
        }
        if (arr.length == 0 || arr[0].length == 0) {
            throw new RuntimeException("the array is empty");
        }
        int value = arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != value) {
                throw new RuntimeException("the array is not legal");
            }
        }
        _map = new int[arr.length][arr[0].length];
        for (int i = 0; i < _map.length; i++) {
            for (int j = 0; j < _map[0].length; j++) {
                _map[i][j] = arr[i][j];
            }
        }
    }
    /**
     * This method returns a copy of the current map as a two-dimensional array of integers.
     * @return ---> a copy of the current map as a two-dimensional array of integers.
     */
    @Override
    public int [][] getMap() {
        int[][] ans = new int[this.getWidth()][this.getHeight()];
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                ans[i][j] = _map[i][j];
            }
        }
        return ans;
    }
    @Override
    public int getWidth() {
        return _map.length;
    }
    @Override
    public int getHeight() {
        return _map[0].length;
    }
    @Override
    public int getPixel(int x, int y) {
        return _map[x][y];
    }
    @Override
    public int getPixel(Pixel2D p) {
        return this.getPixel(p.getX(), p.getY());
    }
    @Override
    public void setPixel(int x, int y, int v) {
        _map[x][y] = v;
    }
    @Override
    public void setPixel(Pixel2D p, int v) {
        setPixel(p.getX(), p.getY(), v);
    }
    /**
     * Fills the connected region of pixels in the map starting from the specified pixel with the new color.
     * The algorithm utilizes BFS traversal to efficiently fill connected regions of pixels in the map. By systematically exploring neighboring pixels, it ensures that all reachable pixels with the same color as the starting pixel are filled with the new color.
     * During each iteration of the while loop, the method processes one pixel at a time.
     * It removes the first pixel from the 'list' and examines its neighbors.
     * If a neighbor has the same color as the starting pixel, it changes its color to the new color and adds it to the `list` for further processing.
     * The variable `ans` is incremented each time a neighboring pixel is successfully filled with the new color. This variable ultimately represents the total count of pixels that have been filled.
     * @param xy    ---> the pixel to start from.
     * @param new_v ---> the new "color" to be filled in p's connected component.
     * @return ans ---> The total count of pixels filled with the new color, including the starting pixel.
     */
    @Override
    /**
     * Fills this map with the new color (new_v) starting from p.
     * https://en.wikipedia.org/wiki/Flood_fill
     */
    public int fill(Pixel2D xy, int new_v) {
        int ans = 0;
        Pixel2D start = new Index2D(xy);
        if (this.isInside(start) == false) {
            return ans ;
        }
        if (this.getPixel(start) == new_v) {
            return ans;
        }
        ArrayList<Pixel2D> list = new ArrayList<Pixel2D>();
        list.add(start);
        int color = this.getPixel(start);
        this.setPixel(start, new_v);
        ans++;
        while (list.isEmpty() == false) {
            start = list.remove(0);
            if (isCyclic()) {
                Pixel2D left = new Index2D(start.getX() - 1 , start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);

                if (start.getX() == 0) {
                    left = new Index2D(getWidth() - 1, start.getY());
                }
                if (start.getX() == this.getWidth() - 1) {
                    right = new Index2D(0, start.getY());
                }
                if (start.getY() == 0) {
                    down = new Index2D(start.getX(), getHeight() - 1);
                }
                if (start.getY() == getHeight() - 1) {
                    up = new Index2D(start.getX(), 0);
                }
                if (isInside(left)) {
                    if (this.getPixel(left) == color) {
                        this.setPixel(left, new_v);
                        list.add(left);
                        ans++;
                    }
                }
                if (isInside(right)) {
                    if (this.getPixel(right) == color) {
                        this.setPixel(right, new_v);
                        list.add(right);
                        ans++;

                    }
                }
                if (isInside(up)) {
                    if (this.getPixel(up) == color) {
                        this.setPixel(up, new_v);
                        list.add(up);
                        ans++;
                    }
                }
                if (isInside(down)) {
                    if (this.getPixel(down) == color) {
                        this.setPixel(down, new_v);
                        list.add(down);
                        ans++;
                    }
                }
            }

            else {
                Pixel2D left = new Index2D(start.getX() - 1, start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);
                if (isInside(left)) {
                    if (this.getPixel(left) == color) {
                        this.setPixel(left, new_v);
                        list.add(left);
                        ans++;
                    }
                }
                if (isInside(right)) {
                    if (this.getPixel(right) == color) {
                        this.setPixel(right, new_v);
                        list.add(right);
                        ans++;
                    }
                }
                if (isInside(up)) {
                    if (this.getPixel(up) == color) {
                        this.setPixel(up, new_v);
                        list.add(up);
                        ans++;
                    }
                }
                if (isInside(down)) {
                    if (this.getPixel(down) == color) {
                        this.setPixel(down, new_v);
                        list.add(down);
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * Computes the distance of the shortest path (minimal number of consecutive neighbors) from p1 to p2.
     * Notes: the distance is using computing the shortest path and returns its length-1, as the distance fro  a point
     * to itself is 0, while the path contains a single point.
     */
    /**
     * This method returns the shortest path distance between two pixels in the map, given an obstacle color.
     * If no path exists, it returns -1. The distance is calculated as the number of steps needed to traverse from one pixel to another.
     * @param p1       ---> The starting pixel of the path.
     * @param p2       ---> The destination pixel of the path.
     * @param obsColor ---> The color value indicating obstacles in the map.
     * @return ---> The shortest path distance between the two pixels, or -1 if no path exists.
     */
    public int shortestPathDist(Pixel2D p1, Pixel2D p2, int obsColor) {
        Pixel2D[] temp = shortestPath(p1, p2, obsColor);
        if (temp == null) {
            return -1;
        }
        return temp.length - 1;
    }

    /**
     * Finds the shortest path between two pixels in the map, avoiding obstacles of a specified color.
     * Uses Breadth-First Search (BFS) algorithm to traverse the map and calculate the shortest path.
     * Examination of extreme cases:
     * (1) If either of them is outside the map, it returns null, indicating that no path exists.
     * (2) If either of the given pixels is an obstacle (having the same color as the specified `obsColor`). If either pixel is an obstacle, it returns null.
     * (3) It checks if the two given pixels are the same , If they are, it returns an array containing only that pixel.
     *
     * @param p1       ---> first coordinate (start point).
     * @param p2       ---> second coordinate (end point).
     * @param obsColor ---> the color which is addressed as an obstacle.
     * @return ---> An array of Pixel2D objects representing the shortest path between p1 and p2, or null if no path exists.
     */
    @Override
    /**
     * BFS like shortest the computation based on iterative raster implementation of BFS, see:
     * https://en.wikipedia.org/wiki/Breadth-first_search
     */
    public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
        Pixel2D[] ans = null;  // the result.
        if (isInside(p1) == false || isInside(p2) == false) {
            return ans;
        }
        if (this.getPixel(p1) == obsColor || this.getPixel(p2) == obsColor) {
            return ans;
        }
        if (p1.equals(p2)) {
            Pixel2D[] ans2 = {p1};
            return ans2;
        }
        Map2D allDistances = allDistance(p1, obsColor);
        if (allDistances.getPixel(p2) == -1) {
            return null;
        }
        ans = new Pixel2D[allDistances.getPixel(p2) + 1];
        ans[0] = new Index2D(p2);
        int pointer = 1;
        Pixel2D start = new Index2D(p2);
        while (allDistances.getPixel(start) != 0) {
            int num = allDistances.getPixel(start);
            // if the map is cyclic
            if (isCyclic()) {
                Pixel2D left = new Index2D(start.getX() - 1, start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);
                if (start.getX() == 0) {
                    left = new Index2D(getWidth() - 1, start.getY());
                }
                if (start.getX() == this.getWidth() - 1) {
                    right = new Index2D(0, start.getY());
                }
                if (start.getY() == 0) {
                    down = new Index2D(start.getX(), getHeight() - 1);
                }
                if (start.getY() == getHeight() - 1) {
                    up = new Index2D(start.getX(), 0);
                }
                if (isInside(left)) {
                    if (allDistances.getPixel(left) == num - 1) {
                        ans[pointer] = new Index2D(left);
                        pointer++;
                        start = left;
                        continue;
                    }
                }
                if (isInside(right)) {
                    if (allDistances.getPixel(right) == num - 1) {
                        ans[pointer] = new Index2D(right);
                        pointer++;
                        start = right;
                        continue;
                    }
                }
                if (isInside(up)) {
                    if (allDistances.getPixel(up) == num - 1) {
                        ans[pointer] = new Index2D(up);
                        pointer++;
                        start = up;
                        continue;
                    }
                }
                if (isInside(down)) {
                    if (allDistances.getPixel(down) == num - 1) {
                        ans[pointer] = new Index2D(down);
                        pointer++;
                        start = down;
                    }
                }
            }
            else {
                Pixel2D left = new Index2D(start.getX() - 1, start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);
                if ( isInside(left)) {
                    if (allDistances.getPixel(left) == num - 1) {
                        ans[pointer] = new Index2D(left);
                        pointer++;
                        start = left;
                        continue;
                    }
                }
                if (isInside(right)) {
                    if (allDistances.getPixel(right) == num - 1) {
                        ans[pointer] = new Index2D(right);
                        pointer++;
                        start = right;
                        continue;
                    }
                }
                if (isInside(up)) {
                    if (allDistances.getPixel(up) == num - 1) {
                        ans[pointer] = new Index2D(up);
                        pointer++;
                        start = up;
                        continue;
                    }
                }
                if (isInside(down)) {
                    if (allDistances.getPixel(down) == num - 1) {
                        ans[pointer] = new Index2D(down);
                        pointer++;
                        start = down;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * This method finds the shortest path between points given a set of points and an obstacle color.
     * It first checks if the `points` array is null or contains less than 2 points. If so, it returns null, indicating that a valid path cannot be found.
     * It generates all possible permutations of the given points using the `generatePermutations` function.
     * It initializes variables `shortestPath` to store the shortest path found so far and `shortestLength` to store its length.
     * It iterates through each permutation of points and computes the length of the path using the `computePathLength`
     * If a valid path is found (`length != -1`) and its length is shorter than the current shortest length, it updates `shortestLength` and `shortestPath`.
     * After iterating through all permutations, if a shortest path is found (`shortestPath != null`), it invokes the `getShortestPathTest` function to obtain the actual shortest path segment.
     * Finally, it reverses the order of the points in the path segment and returns it as the shortest path. If no valid path is found, it returns null.
     * @param points an array with a set of points.
     * @param obsColor the color which is addressed as an obstacle.
     * @return --->  an array of Pixel2D points representing the shortest path, or null if no valid path is found.
     */
    @Override

    public Pixel2D[] shortestPath(Pixel2D[] points, int obsColor) {
        if (points == null || points.length < 2) {
            return null;
        }
        List<List<Pixel2D>> permutations = generatePermutations(points);
        List<Pixel2D> shortestPath = null;
        int shortestLength = Integer.MAX_VALUE;
        for (List<Pixel2D> permutation : permutations) {
            int length = computePathLength(permutation, obsColor);
            if (length != -1 && length < shortestLength) {
                shortestLength = length;
                shortestPath = permutation;
            }
        }
        if (shortestPath != null) {
            Pixel2D[] tempResult = getShortestPathTest(shortestPath, obsColor);
            int length = tempResult.length;
            for (int i = 0; i < length / 2; i++) {
                Pixel2D temp = tempResult[i];
                tempResult[i] = tempResult[length - i - 1];
                tempResult[length - i - 1] = temp;
            }
            return tempResult;
        }
        else {
            return null;
        }
    }
    @Override
    public boolean isInside(Pixel2D p) {
        return isInside(p.getX(), p.getY());
    }
    @Override
    public boolean isCyclic() {
        return _cyclicFlag;
    }
    @Override
    public void setCyclic(boolean cy) {
        _cyclicFlag = cy;
    }
    private boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < this.getWidth() && y < this.getHeight();
    }
    /**
     * Calculates the distances from a given starting pixel to all other pixels in the map.
     * The distances are calculated based on the number of steps required to reach each pixel from the starting point.
     * If a pixel is unreachable or an obstacle, it is marked with a distance of -1.
     * First case ---> Check if the starting point is outside the map so update the map with -1.
     * Second case ---> Check if the starting point is an obstacle color,  so update the map with -1.
     * list---> Open an array list to store pixels.
     * @param start    the source (starting) point.
     * @param obsColor the color representing obstacles.
     * @return --->  the map containing distance values from the starting point to each pixel.
     */
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor) {
        Map2D ans;
        if (this.isInside(start) == false) {
            ans = new Map(getWidth(), getHeight(), -1);
            return ans;
        }
        if (this.getPixel(start) == obsColor) {
            ans = new Map(getWidth(), getHeight(), -1);
            return ans;
        }
        ArrayList<Pixel2D> list = new ArrayList<Pixel2D>();
        list.add(start);
        ans = new Map(getWidth(), getHeight(), 0);
        update(ans, obsColor);
        ans.setPixel(start, 0);
        while (list.isEmpty() == false) {
            start = list.remove(0);
            int num = ans.getPixel(start);
            // if the map is cyclic
            if (isCyclic()) {
                Pixel2D left = new Index2D(start.getX() - 1, start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);

                if (start.getX() == 0) {
                    left = new Index2D(getWidth() - 1, start.getY());
                }
                if (start.getX() == this.getWidth() - 1) {
                    right = new Index2D(0, start.getY());
                }
                if (start.getY() == 0) {
                    down = new Index2D(start.getX(), getHeight() - 1);
                }
                if (start.getY() == getHeight() - 1) {
                    up = new Index2D(start.getX(), 0);
                }

                if (isInside(left)) {
                    if (ans.getPixel(left) == -2) {
                        ans.setPixel(left, num + 1);
                        list.add(left);
                    }
                }
                if (isInside(right)) {
                    if (ans.getPixel(right) == -2) {
                        ans.setPixel(right, num + 1);
                        list.add(right);
                    }
                }
                if (isInside(up)) {
                    if (ans.getPixel(up) == -2) {
                        ans.setPixel(up, num + 1);
                        list.add(up);
                    }
                }
                if (isInside(down)) {
                    if (ans.getPixel(down) == -2) {
                        ans.setPixel(down, num + 1);
                        list.add(down);
                    }
                }
            }
            // if the map is not cyclic
            else {
                Pixel2D left = new Index2D(start.getX() - 1, start.getY());
                Pixel2D right = new Index2D(start.getX() + 1, start.getY());
                Pixel2D down = new Index2D(start.getX(), start.getY() - 1);
                Pixel2D up = new Index2D(start.getX(), start.getY() + 1);
                if (isInside(left)) {
                    if (ans.getPixel(left) == -2) {
                        ans.setPixel(left, num + 1);
                        list.add(left);
                    }
                }
                if (isInside(right)) {
                    if (ans.getPixel(right) == -2) {
                        ans.setPixel(right, num + 1);
                        list.add(right);
                    }
                }
                if (isInside(up)) {
                    if (ans.getPixel(up) == -2) {
                        ans.setPixel(up, num + 1);
                        list.add(up);
                    }
                }
                if (isInside(down)) {
                    if (ans.getPixel(down) == -2) {
                        ans.setPixel(down, num + 1);
                        list.add(down);
                    }
                }
            }
        }
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (ans.getPixel(i, j) == -2) {
                    ans.setPixel(i, j, -1);
                }
            }
        }
        return ans;
    }
    /**
     * Counts the number of connected components in the map with respect to obstacles.
     * It creates a copy of the map to avoid modifying the original map.
     * It updates the copy of the map to replace all obstacle pixels with -1, making it easier to identify connected components.
     * It initializes a variable `ans` to count the number of connected components.
     * It iterates over each pixel in the map and if it encounters an obstacle (-2), it increments the count of connected components and performs a flood fill to mark all pixels connected to this obstacle.
     * After iterating over all pixels, it returns the total count of connected components.
     * @param obsColor --->  The color code representing obstacles.
     * @return (ans)--->  The number of connected components in the map.
     */
    @Override
    public int numberOfConnectedComponents(int obsColor) {
		Map copyMap = new Map(this.getMap());
		update(copyMap, -1);
		int ans = 0;
		for (int i = 0; i < copyMap.getWidth(); i++) {
			for (int j = 0; j < copyMap.getHeight(); j++) {
				if (getPixel(i, j) == -2) {
					ans++;
					fill(new Index2D(i, j), -1);
				}
			}
		}
		return ans;
	}
    /**
     * Checks if this Map2D object is equal to another object.
     * Two Map2D objects are considered equal if they have the same dimensions, and contain the same pixel values at corresponding positions.
     * Checks if the two maps are in the same state i.e. both are cyclic or not.
     * @param ob ---> The object to compare for equality.
     * @return ---> true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object ob) {
        if (ob.getClass() != Map.class || ob == null) {
            return false;
        }
        Map2D map = (Map) ob;
        if (this.isCyclic() != map.isCyclic()) {
            return false;
        }
        if (map.getHeight() != this.getHeight() ||map.getWidth() != this.getWidth()) {
            return false;
        }
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (this.getPixel(i, j) != map.getPixel(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    ////////////////////// Private Methods ///////////////////////
    // add you code here
    ////////////////////
    /**
     * This method iterates over each pixel in the current map and checks if it matches the obstacle color.
     * (1) If a pixel matches the obstacle color, it sets the corresponding pixel in the provided Map2D object to -1.
     * (2) If a pixel does not match the obstacle color, it sets the corresponding pixel in the provided Map2D object to -2.
     * @param map      --->  the Map2D object to be updated.
     * @param obsColor --->  the color representing obstacles in the current map.
     */
    private void update(Map2D map, int obsColor) {
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (this.getPixel(i, j) != obsColor) {
                    map.setPixel(i, j, -2);
                } else {
                    map.setPixel(i, j, -1);
                }
            }
        }
    }
    /**
     *This method calculates the length of the given path in terms of the number of steps or segments required to traverse it.
     *  It initializes a variable `length` to store the total length of the path.
     *  It iterates through the list of points in the `path` starting from the first point (`i = 0`) up to the second-to-last point (`i < path.size() - 1`) because it computes the length between consecutive points.
     *  For each pair of consecutive points (`current` and `next`), it calls the `shortestPath` function to find the shortest path segment between them. This function returns an array of Pixel2D points representing the segment.
     *  If `shortestPath` returns `null`, indicating that there is no valid path between `current` and `next`, it returns `-1` to signify that the length cannot be computed.
     *  Otherwise, it adds the length of the returned segment (i.e., the length of the `result` array) to the total `length`.
     * @param path --->  A list of Pixel2D points representing the path to compute the length of.
     * @param obsColor --->  An integer representing the color code of obstacles.
     * @return --->  It returns the length of the path.
     */
    private int computePathLength(List<Pixel2D> path, int obsColor) {
        int length = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Pixel2D current = path.get(i);
            Pixel2D next = path.get(i + 1);
            Pixel2D[] result = shortestPath(current, next, obsColor);
            if (result == null) {
                return -1;
            }
            length += result.length;
        }
        return length;
    }
    /**
     * This function iterates over each pair of consecutive points in the given path. For each pair, it invokes the `shortestPath` function to find the shortest path segment between them.
       It then reverses the resulting path segment to ensure correct ordering and adds it to the result list. If it's not the first iteration, it skips the first point in the segment to avoid duplication.
       Finally, it converts the list of points into an array and returns it as the shortest path.
     * (if i != 0) ---> this code snippet ensures that when adding elements from the `temp` array to the `result` list, it skips the first element if it's not the first iteration (i.e., if `i` is not equal to 0). This prevents duplication of elements in the `result` list when generating the shortest path between points in the given path.
     * @param path ---> A list of Pixel2D points representing the path.
     * @param obsColor
     * @return --->An array of Pixel2D points representing the shortest path.
     */
    private Pixel2D[] getShortestPathTest(List<Pixel2D> path, int obsColor) {
        List<Pixel2D> result = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            Pixel2D current = path.get(i);
            Pixel2D next = path.get(i + 1);
            Pixel2D[] temp = shortestPath(current, next, obsColor);
            // Reverse the temp array
            for (int j = 0; j < temp.length / 2; j++) {
                Pixel2D tempPixel = temp[j];
                temp[j] = temp[temp.length - 1 - j];
                temp[temp.length - 1 - j] = tempPixel;
            }
            if (i != 0) {
                result.addAll(Arrays.asList(temp).subList(1, temp.length));
            }
            else {
                result.addAll(Arrays.asList(temp));
            }
        }
        return result.toArray(new Pixel2D[0]);
    }
    /**
     * The `generatePermutations` function generates all possible permutations of Pixel2D points.
     * This function serves as a wrapper for the `generatePermutationsHelper` function, which performs the actual permutation generation.
       It initializes an empty list to store the permutations, then calls the `generatePermutationsHelper` function with the starting index set to 0. Finally, it returns the list containing all generated permutations.
     * @param points ---> an array of Pixel2D points to generate permutations from.
     * @return ---> A list containing all possible permutations of Pixel2D points.
     */
    private List<List<Pixel2D>> generatePermutations(Pixel2D[] points) {
        List<List<Pixel2D>> permutations = new ArrayList<>();
        generatePermutationsHelper(points, 0, permutations);
        return permutations;
    }
    /**
     * This function generates all possible permutations of points and adds them to the list of permutations.
     (1) If the current index equals the length of the points array, it means that a complete permutation has been generated.
         In this case, it creates a new list (`permutation`) and adds all points from the `points` array to it. Then, it adds this permutation to the `permutations` list.
     (2) If the current index is less than the length of the points array, it means that the permutation is not complete yet.
         In this case, the function iterates over the remaining elements of the `points` array starting from the current index.
         For each element, it swaps the element at the current index with the element at the current iteration index, recursively calls itself with the updated index, and then swaps the elements back to their original positions to backtrack and explore other permutations.
     * @param points ---> an array of Pixel2D points to generate permutations from.
     * @param index  --->  The current index in the permutation generation process.
     * @param permutations ---> A list to store generated permutations.
     */
    private void generatePermutationsHelper(Pixel2D[] points, int index, List<List<Pixel2D>> permutations) {
        if (index == points.length) {
            List<Pixel2D> permutation = new ArrayList<>();
            for (Pixel2D point : points) {
                permutation.add(point);
            }
            permutations.add(permutation);
        }
        else {
            for (int i = index; i < points.length; i++) {
                swap(points, index, i);
                generatePermutationsHelper(points, index + 1, permutations);
                swap(points, index, i);
            }
        }
    }
    /**
     * Swaps two elements in an array.
     * @param points ---> an array of Pixel2D points.
     * @param i --->   The index of the first element to swap.
     * @param j ---> The index of the second element to swap.
     */
    private void swap(Pixel2D[] points, int i, int j) {
        Pixel2D temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }


}
