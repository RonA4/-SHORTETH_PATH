/**
 * Name ---> Ron Amsalem.
 * ID ---> 326029600.
 --->read me<---
 -This department checks both correctness and performance of the functions.
 Performance is at the end.
 */
package ex3.test;
import ex3.Index2D;
import ex3.Map;
import ex3.Map2D;
import ex3.Pixel2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
This is a very basic Testing class for Map - please note that this JUnit
 contains only a very limited testing method and should be added many other
 methods for testing all the functionality of Map2D - both in correctness and in runtime.
*/
 class MapTest {
    /**
     * _m_3_3 =
     * 0,1,0
     * 1,0,1
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * 0,1,0
     *
     * _m0 =
     * 1,1,1,1,1
     * 1,0,1,0,1
     * 1,0,0,0,1
     * 1,0,1,0,1
     * 1,1,1,1,1
     * 1,0,1,0,1
     *
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     * 1,-1,-1,-1, 1
     * 1,-1, 1,-1, 1
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     *
     * m2[3][2] = 0, m2[1][2] = 10, |sp|=11 (isCiclic = false;}
     * =============
     * 7, 8, 9, 1, 7
     * 6,-1,10,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     *
     * m[3][2] = 0, m2[1][2] = 5, |sp|=5 (isCiclic = true;}
     * 5, 4, 3, 4, 5
     * 6,-1, 4,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     */
    private int[][] _map = {{1,1,1,1,1}, {1,0,1,0,1}, {1,0,0,0,1},  {1,0,1,0,1},  {1,1,1,1,1}, {1,0,1,0,1}};
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};

    // Added static variables that are mine.
    public static final int[][] MYARR = {{-2,-2,-2,-2,-1},{-1,-1,-2,-1,-2},{-2,-2,-2,-1,-1},{-1,-2,-1,-1,-2}};
    public static final Map MYMAP = new Map(MYARR);

    private Map2D _m0, _m1, _m2, _m3, _m3_3;
    @BeforeEach
    public void setuo() {
        _m0 = new Map(_map);
        _m1 = new Map(_map); _m1.setCyclic(true);
        _m2 = new Map(_map); _m2.setCyclic(false);
        _m3 = new Map(_map);
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1);
    }

    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        assertEquals(_m0,_m3);
        assertNotEquals(_m1,_m2);
        _m3.setPixel(2,2,17);
        assertNotEquals(_m0,_m3);
    }
    @Test
    void getMap() {
        int[][] m0 = _m0.getMap();
        _m1.init(m0);
        assertEquals(_m0,_m1);
    }


    @Test
    void testFill0() {
        Pixel2D p1 = new Index2D(0,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,21);
    }
    @Test
    void testFill1() {
        Pixel2D p1 = new Index2D(0,1);
        _m0.setPixel(p1,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,9);
        _m0.setCyclic(false);
        int f2 = _m0.fill(p1,3);
        assertEquals(f2,8);
    }
    @Test
    void testAllDistance() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,0);
        Map2D m00 = _m0.allDistance(p1, 0);
        assertEquals(6, m00.getPixel(p2));
    }

    @Test
    void testShortestPath() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,2);
        Pixel2D[] path = _m0.shortestPath(p1, p2, 0);
        assertEquals(5, path.length);
        path = _m2.shortestPath(p1, p2, 0);
        assertEquals(11, path.length);
    }
    // Tests I made

    /**
     *This method tests the init(int width, int height, int value) method in the Map class.
     *It verifies whether the map is correctly initialized with the specified width, height,and initial pixel value.
     * w ---> the width.
     * h ---> the height.
     * v ---> the color what I want.
     */
    @Test
    public void testInit(){
        int w = 5;
        int h= 5;
        int v = 4 ;
        int [] [] arr= {{0,0,0,0,0},{1,1,1,1,1},{0,1,1,1,1},{0,0,1,1,0},{1,0,1,0,1}};
       Map map = new Map(arr);
       map.init(w,h,v);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                Assertions.assertEquals(4, map.getPixel(i,j));
            }
        }
    }

    /**
     * This test case verifies that the Map object correctly initializes with a valid array
     */
    @Test
    public void testInitWithValidArray() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Map map = new Map(arr);
        map.init(arr);
        assertArrayEquals(arr, map.getMap());
    }

    @Test
    /**
     * tests that getMap() returns the correct array.
     * This method tests the functionality of the getMap() method in the Map class.
     * It verifies whether the returned map matches the original array and ensures
     * that modifying the returned map does not affect the original array.
     */
    public void testGetMap(){
        Map m1 = new Map(MYARR);
        int[][] arr1 = m1.getMap();
        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                assertEquals(arr1[i][j], MYARR[i][j]);
            }
        }
        arr1[2][2] = 9;
        assertNotEquals(arr1[2][2], MYARR[2][2]);
    }
    @Test
    /**
     * This method tests the getWidth() method in the Map class.
     * It verifies that the method returns the correct width of the map.
     * tests that getWidth() returns the correct width:
     */
    public void testGetWidth(){
        int w = MYMAP.getWidth();
        assertEquals(4, w);
    }

    @Test
    /**
     * This method tests the getHeight() method in the Map class.
     * It verifies that the method returns the correct height of the map.
     * tests that getHeight() returns the correct height:
     */
    public void testGetHeight(){
        int h = MYMAP.getHeight();
        assertEquals(5, h);
    }

    @Test
    /**
     * tests that getPixel(int x, int y) returns the correct value, on specific points and on the whole array
     * This method tests the getPixel(int x, int y) method in the Map class.
     * It verifies that the method returns the correct pixel value at the specified coordinates.
     */
    public void testGetPixelXY(){
        int v1 = MYMAP.getPixel(2, 3);
        assertEquals(-1, v1);
        int v2 = MYMAP.getPixel(1, 2);
        assertEquals(-2, v2);
        for(int i=0; i<MYARR.length; i++){
            for(int j=0; j<MYARR[0].length; j++){
                assertEquals(MYMAP.getPixel(i, j), MYARR[i][j]);
            }
        }
    }
    @Test
    /**
     * tests that getPixel(Pixel2D p) returns the correct value .
     */
    public void testGetPixelP(){
        Pixel2D p;
        int e, v;
        for(int i=0; i<MYARR.length; i++){
            for(int j=0; j<MYARR[0].length; j++){
                p = new Index2D(i, j);
                e = MYARR[i][j];
                v = MYMAP.getPixel(p);
                assertEquals(e, v);
            }
        }
    }
    @Test
    /**
     * tests that setPixel(int x, int y, int v) sets the pixel to the correct value:
     */
    public void testSetPixelXYV(){
        Map m1 = new Map(MYARR);
        assertNotEquals(0, m1.getPixel(3, 2));
        m1.setPixel(3, 2, 0);
        assertEquals(0, m1.getPixel(3, 2));
    }
    @Test
    /**
     * tests that setPixel(Pixel2D p, int v) sets the pixel to the correct value:
     */
    public void testSetPixelPV(){
        Map m1 = new Map(MYARR);
        Pixel2D p = new Index2D(3, 2);
        assertNotEquals(0, m1.getPixel(p));
        m1.setPixel(p, 0);
        assertEquals(0, m1.getPixel(p));
    }

    @Test
    /**
     * This method tests the isInside(Pixel2D pixel) method in the Map class.
     * It verifies whether the given pixel is inside the bounds of the map.
     */
    public void testIsInside(){
        Pixel2D p1 = new Index2D(-1, 2);
        assertFalse(MYMAP.isInside(p1));
        Pixel2D p2 = new Index2D(2, -1);
        assertFalse(MYMAP.isInside(p2));
        Pixel2D p3 = new Index2D(0, 6);
        assertFalse(MYMAP.isInside(p3));
        Pixel2D p4 = new Index2D(5, 1);
        assertFalse(MYMAP.isInside(p4));
        Pixel2D p5 = new Index2D(3, 2);
        assertTrue(MYMAP.isInside(p5));
        Pixel2D p6 = new Index2D();
        assertTrue(MYMAP.isInside(p6));
    }
    @Test
    /**
     * tests the allDistance function, with not cyclic maps.
     #arr1
     * -2, -2, -1, -1, -2
     * -2, -2, -2, -2, -2
     * -1, -2, -1, -2, -2
     * -1, -2, -2, -1, -1
     * #map1
     * 0, 1, -1, -1, 6
     * 1, 2, 3, 4, 5
     * -1, 3, -1, 5, 6
     * -1, 4, 5, -1, -1
     */
    public void testAllDistance2() {
        int[][] arr1 = {{-2, -2, -1, -1, -2}, {-2, -2, -2, -2, -2}, {-1, -2, -1, -2, -2}, {-1, -2, -2, -1, -1}};
        Map2D map1 = new Map(arr1);
        map1.setCyclic(false);
        int[][] exp2 = {{0, 1,-1, -1, 6}, {1, 2, 3,4, 5}, {-1, 3, -1, 5, 6}, {-1, 4, 5, -1, -1}};
        Pixel2D p3 = new Index2D(0, 0);
        Map2D map2 = map1.allDistance(p3, -1);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                Assertions.assertEquals(exp2[i][j], map2.getPixel(i,j));
            }
        }
    }
    @Test
    /**
     * tests the allDistance function, with not cyclic maps.
     #arr1
     * -2, -2, -1, -1, -2
     * -2, -2, -2, -2, -2
     * -1, -2, -1, -2, -2
     * -1, -2, -2, -1, -1
     * #map1
     *  0, 1, -1, -1, 1
     *  1, 2, 3, 3, 2
     * -1, 3, -1,4, 3
     * -1, 2, 3, -1, -1
     */
    public void testAllDistance3() {
        int[][] arr1 = {{-2, -2, -1, -1, -2}, {-2, -2, -2, -2, -2}, {-1, -2, -1, -2, -2}, {-1, -2, -2, -1, -1}};
        Map2D map1 = new Map(arr1);
        map1.setCyclic(true);
        int[][] exp2 = {{0, 1, -1, -1, 1}, {1, 2, 3, 3, 2}, {-1, 3, -1, 4, 3}, {-1, 2, 3, -1, -1}};
        Pixel2D p3 = new Index2D(0, 0);
        Map2D map2 = map1.allDistance(p3, -1);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                Assertions.assertEquals(exp2[i][j], map2.getPixel(i, j));
            }
        }
    }

    /**
     * This test case verifies the correctness of the shortestPathDist() method in the Map2D class.
     * It checks whether the shortest distance between two points on the map is calculated correctly.
     * The map is not cyclic
     * p1 ---> the index2D start point, (0,0).
     * p2 ---> thr index2D end point, (2,3).
     */

    @Test
    public void testShortTestPathDist (){
        int[][] arr = {{-2, -2, -1, -1, -2},
                {-2, -2, -2, -2, -2},
                {-1, -2, -1, -2, -2},
                {-1, -2, -2, -1, -1}};
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(2, 3);
        Map2D map = new Map(arr);
        map.setCyclic(false);
        int way = ((Map) map).shortestPathDist(p1,p2,-1);
        assertEquals(5,way);
    }
    /**
     * This test case verifies the correctness of the shortestPathDist() method in the Map2D class.
     * It checks whether the shortest distance between two points on the map is calculated correctly.
     * The map is  cyclic
     * p1 ---> the index2D start point, (0,0).
     * p2 ---> thr index2D end point, (2,3).
     */

    @Test
    public void testShortTestPathDist1(){
        int[][] arr = {{-2, -2, -1, -1, -2},
                {-2, -2, -2, -2, -2},
                {-1, -2, -1, -2, -2},
                {-1, -2, -2, -1, -1}};
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(2, 3);
        Map2D map = new Map(arr);
        map.setCyclic(true);
        int way = ((Map) map).shortestPathDist(p1,p2,-1);
        assertEquals(4,way);
    }
        @Test
        /**
         * This method tests the shortestPath() method in the Map class.
         * It verifies that the shortest path between two points on the map is calculated correctly, considering obstacles represented by a specified color.
         * The test case constructs a map with given obstacle positions and tests the shortest path between two specified points, ensuring that the returned path matches the expected path.
         * arr ---> create array2D with value -1 and -2:
         -1 ---> obstacle.
         -2 ---> A place where it is allowed to pass.
         * tests shortestPath, with not cyclic maps.
         * exp ---> The expected path from end to start.
         * p1 ---> the index2D start point, (0,0).
         * p2 ---> thr index2D end point, (2,3).
         */
        public void testShortestPath1() {
            int[][] arr = {{-2, -2, -1, -1, -2},
                           {-2, -2, -2, -2, -2},
                           {-1, -2, -1, -2, -2},
                           {-1, -2, -2, -1, -1}};
            int[] exp = {2,3,1,3,1,2,1,1,0,1,0,0};
            Pixel2D p1 = new Index2D(0, 0);
            Pixel2D p2 = new Index2D(2, 3);
            Map2D map = new Map(arr);
            map.setCyclic(false);
            Pixel2D[] ans = map.shortestPath(p1, p2, -1);
            int[] comp = new int[exp.length];
            for (int i = 0; i < exp.length; i += 2) {
                comp[i] = ans[i / 2].getX();
                comp[i + 1] = ans[i / 2].getY();
            }
            assertArrayEquals(exp, comp);
    }

     /**
     * This method tests the shortestPath() method in the Map class.
     * It verifies that the shortest path between two points on the map is calculated correctly, considering obstacles represented by a specified color.
     * The test case constructs a map with given obstacle positions and tests the shortest path between two specified points, ensuring that the returned path matches the expected path.
      0 ---> obstacle.
      1 ---> A place where it is allowed to pass.
     * tests shortestPath, with cyclic maps.
     * exp ---> The expected path from end to start.
      * p1 ---> the index2D start point, (3,2).
      * p2 ---> thr index2D end point, (1,2).
     */

    @Test
    public void testShortestPath2() {
    Pixel2D p1 = new Index2D(3,2);
    Pixel2D p2 = new Index2D(1,2);
    Map2D map = new Map(_m0.getMap());
    map.setCyclic(true);
    Pixel2D[] path = _m0.shortestPath(p1, p2, 0);
    int[] exp = {1,2,0,2,5,2,4,2,3,2};
    int[] comp = new int[exp.length];
    for (int i = 0; i < exp.length; i += 2) {
        comp[i] = path[i / 2].getX();
        comp[i + 1] = path[i / 2].getY();
    }
    assertArrayEquals(exp, comp);

}

    /**
     * The test checks what happens when we go beyond the limits of the map.
     *  p1 ---> start point, (2,5).
     *  p2 ---> end point, (7,3).
     *  -2 ---> represents an invalid or blocked cell.
     *  -1 ---> represents an empty cell on the map.
     * the map is not cyclic.
     */
    @Test
public void testShortestPathLimits(){
    Pixel2D p1 = new Index2D(2,5);
    Pixel2D p2 = new Index2D(7,3);
    int [] [] arr=  {{-2,-1,-1,-2},
                     {-2,-1,-1,-2},
                     {-2,-1,-1,-2},
                     {-2,-1,-1,-2}};
    Map2D map = new Map(arr);
    map.setCyclic(false);
    Pixel2D[] ans = map.shortestPath(p1,p2, -1);
   assertNull(ans);

}

    /**
     * The test checks the case that it is impossible to find the shortest route between two points because there are obstacles blocking access to this route.
     * p1 ---> start point, (2,0).
     * p2 ---> end point, (2,3).
     * -2 ---> represents an invalid or blocked cell.
     * -1 ---> represents an empty cell on the map.
     * The map is not cyclic.
     */
    @Test
    public void testShortestPathNoAccess(){
        Pixel2D p1 = new Index2D(2,0);
        Pixel2D p2 = new Index2D(2,3);
        int [] [] arr=  {{-2,-1,-1,-2},
                         {-2,-1,-1,-2},
                         {-2,-1,-1,-2},
                         {-2,-1,-1,-2}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        Pixel2D[] ans = map.shortestPath(p1,p2, -1);
        assertNull(ans);

    }

    /**
     * This method tests the fill() method in the Map2D class.
     * It verifies whether the fill operation correctly fills the connected area
     * starting from a specified point with a given color.
     *  tests Fill, with not cyclic maps.
     *  p ---> index2D start point, (0,0).
     *  arr --->create array2D with value -1 and -2
      -1 ---> obstacle.
      -2 ---> A place where it is allowed to pass.
     */
    @Test
public void testFill() {
    int[][] arr = {{-2, -2, -2, -2, -1},
                   {-2, -2, -1, -2, -2},
                   {-2, -1, -2, -1, -1},
                   {-2, -2, -1, -2, -2}};
    Pixel2D p = new Index2D(0, 0);
    int[][] exp =  {{6, 6, 6, 6, -1},
                   {6, 6, -1, 6, 6},
                   {6, -1, -2, -1, -1},
                   {6, 6, -1, -2, -2}};
    Map2D map = new Map(arr);
    map.setCyclic(false);
    int n = map.fill(p, 6);
    int[][] ans = map.getMap();
    for (int i = 0; i < exp.length; i++) {
        for (int j = 0; j < exp[0].length; j++) {
            assertEquals(exp[i][j], ans[i][j]);
        }
    }
    assertEquals(11, n);
}
    /**
     * This method tests the fill() method in the Map2D class.
     * It verifies whether the fill operation correctly fills the connected area
     * starting from a specified point with a given color.
     *  tests Fill, with cyclic maps.
     *  p ---> index2D start point, (0,0).
     *  arr --->create array2D with value -1 and -2
     -1 ---> obstacle.
     -2 ---> A place where it is allowed to pass.
     */
    @Test
    public void testFill2() {
        int[][] arr = {{-2, -2, -2, -2, -1},
                       {-2, -2, -1, -2, -2},
                       {-2, -1, -2, -1, -1},
                       {-2, -2, -1, -2, -2}};
        Pixel2D p = new Index2D(0, 0);
        int[][] exp =  {{6, 6, 6, 6, -1},
                {6, 6, -1, 6, 6},
                {6, -1, -2, -1, -1},
                {6, 6, -1, 6, 6}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        int n = map.fill(p, 6);
        int[][] ans = map.getMap();
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < exp[0].length; j++) {
                assertEquals(exp[i][j], ans[i][j]);
            }
        }
        assertEquals(13, n);
    }

    /**
     * This test checks the correctness of the fill function, i.e. it checks what happens if the starting point is between failed and the entire map cannot be painted but only the components connected to that starting point.
     * the map is not cyclic.
     * p ---> the start point, (3,3).
     * n ---> The number of squares painted,(expected==6).
     */

    @Test
    public void testFill3(){
        int[][] arr = {{-2, -2, -2, -1, -1},
                       {-2, -2, -2, -1, -1},
                       {-2, -2, -2, -1, -2},
                       {-2, -2, -1, -2, -2},
                       {-2, -1, -2, -2, -2}};
        Pixel2D p = new Index2D(3, 3);
        int[][] exp =  {{-2,-2, -2, -1, -1},
                        {-2, -2, -2, -1, -1},
                        {-2,-2 ,-2,-1,6},
                        {-2, -2, -1, 6, 6},
                        {-2, -1, 6, 6, 6}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        int n = map.fill(p, 6);
        int[][] ans = map.getMap();
        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < exp[0].length; j++) {
                assertEquals(exp[i][j], ans[i][j]);
            }
        }
        assertEquals(6, n);
    }

    /**
     * Test case to verify the functionality of the numberOfConnectedComponents method in the Map2D class.
     *  The map is not cyclic.
     *  -2 represents an invalid or blocked cell.
     *  -1 represents an empty cell on the map.
     *  This test case checks if the numberOfConnectedComponents method correctly calculates
     *  n ---> the actual number of connected components.
     * the number of connected components in the map with given obstacle color.
     */

    @Test
    public void testNumberOfConnectedComponents(){
        int[][] arr = {{-2, -1, -2, -2, -2},
                       {-2, -1, -1, -2, -2},
                       {-2, -1, -1, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        int n = map.numberOfConnectedComponents(-1);
        assertEquals(2,n);

    }
    /**
     * Test case to verify the functionality of the numberOfConnectedComponents method in the Map2D class.
     *  The map is cyclic.
     *  This test case checks if the numberOfConnectedComponents method correctly calculates
     *  n ---> the actual number of connected components.
     *  -2 represents an invalid or blocked cell.
     *  -1 represents an empty cell on the map.
     * the number of connected components in the map with given obstacle color.
     */
    @Test
    public void testNumberOfConnectedComponents1(){
        int[][] arr = {{-2, -1, -2, -2, -2},
                       {-2, -1, -1, -2, -2},
                       {-2, -1, -1, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        int n = map.numberOfConnectedComponents(-1);
        assertEquals(1,n);
    }
    /**
     * Test case to verify the functionality of the numberOfConnectedComponents method in the Map2D class.
     *  The map is not cyclic.
     *  This test case checks if the numberOfConnectedComponents method correctly calculates
     *  n ---> the actual number of connected components.
     * -2 represents an invalid or blocked cell.
     * -1 represents an empty cell on the map.
     * the number of connected components in the map with given obstacle color.
     */
    @Test
    public void testNumberOfConnectedComponents3(){
        int[][] arr = {{-2, -1, -2, -2, -2},
                       {-1, -1, -1, -2, -2},
                       {-2, -1, -1, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        int n = map.numberOfConnectedComponents(-1);
        assertEquals(3,n);
    }
    /**
     * Test case to verify the functionality of the numberOfConnectedComponents method in the Map2D class.
     *  The map is  cyclic.
     *  This test case checks if the numberOfConnectedComponents method correctly calculates
     *  -2 represents an invalid or blocked cell.
     *  -1 represents an empty cell on the map.
     *  n ---> the actual number of connected components.
     * the number of connected components in the map with given obstacle color.
     */
    @Test
    public void testNumberOfConnectedComponents4(){
        int[][] arr = {{-2, -1, -2, -2, -2},
                {-1, -1, -1, -2, -2},
                {-2, -1, -1, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        int n = map.numberOfConnectedComponents(-1);
        assertEquals(1,n);
    }

    /**
     *This test tests the shortest route between several given points.
     * points --->An array of points that we would like to find the shortest route that passes between all these points, {(0,0),(2,2),(0,4)}.
     * -2 represents an invalid or blocked cell.
     * -1 represents an empty cell on the map.
     * exp ---> The array expected to receive.
     * the map is not cyclic
     */
    @Test
    public void testShortestPath4(){
        int[][] arr =  {{-2, -1, -1, -1, -2},
                       {-2, -1, -1, -2, -2},
                       {-2, -2, -2, -2, -1},
                       {-1, -2, -2, -2, -1}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        Index2D index1 = new Index2D(0, 0);
        Index2D index2 = new Index2D(2, 2);
        Index2D index3 = new Index2D(0, 4);
        Pixel2D[] points = {index1, index2, index3};

        Pixel2D[] ans = map.shortestPath(points, -1);
        Pixel2D[] exp = {
                new Index2D(0, 4), new Index2D(1, 4), new Index2D(1, 3),
                new Index2D(2, 3), new Index2D(2, 2), new Index2D(2, 1),
                new Index2D(2, 0), new Index2D(1, 0), new Index2D(0, 0)};

        assertArrayEquals(exp,ans);

    }

    /**
     *This test tests the shortest route between several given points.
     * points --->An array of points that we would like to find the shortest route that passes between all these points, {(0,0),(2,4),(0,4)}.
     * -2 represents an invalid or blocked cell.
     * -1 represents an empty cell on the map.
     * exp ---> The array expected to receive.
     * the map is cyclic
     */
    @Test
    public void testShortestPath5(){
        int[][] arr = {{-2, -1, -1, -1, -2},
                       {-2, -1, -1, -2, -2},
                       {-2, -2, -2, -2, -1},
                       {-1, -2, -2, -2, -1}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        Index2D index1 = new Index2D(0, 0);
        Index2D index2 = new Index2D(2, 2);
        Index2D index3 = new Index2D(0, 4);
        Pixel2D[] points = {index1, index2, index3};
        Pixel2D[] ans = map.shortestPath(points, -1);
        Pixel2D[] exp = {
                new Index2D(2, 2), new Index2D(2, 3), new Index2D(1, 3),
                new Index2D(1, 4), new Index2D(0, 4), new Index2D(0, 0)};

        assertArrayEquals(exp, ans);

    }

    /**
     *This test tests the shortest route between several given points.
     * points --->An array of points that we would like to find the shortest route that passes between all these points, {(5,0),(1,1),(0,3),(3,5),(5,3)}.
     * -2 represents an invalid or blocked cell.
     * -1 represents an empty cell on the map.
     * exp ---> The array expected to receive.
     * the map is not cyclic
     */
    @Test
    public void testShortestPath6() {
    int[][] arr = {{-2, -2, -2, -2, -2, -2},
                   {-2, -2, -1, -1, -2, -1},
                   {-2, -2, -1, -1, -2, -2},
                   {-2, -1, -1, -1, -1, -2},
                   {-1, -2, -1, -1, -1, -2},
                   {-2, -2, -2, -2, -2, -2}};
    Map2D map = new Map(arr);
    map.setCyclic(false);
    Index2D index1 = new Index2D(5, 0);
    Index2D index2 = new Index2D(1, 1);
    Index2D index3 = new Index2D(0, 3);
    Index2D index4 = new Index2D(3, 5);
    Index2D index5 = new Index2D(5, 3);
    Pixel2D[] points = {index1, index2, index3, index4, index5};
    Pixel2D[] ans = map.shortestPath(points, -1);
    Pixel2D[] exp = {
            new Index2D(1, 1), new Index2D(0, 1), new Index2D(0, 2),
            new Index2D(0, 3), new Index2D(0, 4), new Index2D(1, 4),
            new Index2D(2, 4), new Index2D(2, 5), new Index2D(3, 5),
            new Index2D(4, 5), new Index2D(5, 5), new Index2D(5, 4),
            new Index2D(5, 3), new Index2D(5, 2), new Index2D(5, 1), new Index2D(5, 0)};
        assertArrayEquals(exp, ans);
    }

    /**
     *This test tests the shortest route between several given points.
     * points --->An array of points that we would like to find the shortest route that passes between all these points, {(5,0),(0,1),(0,3),(5,3)}.
     * -2 represents an invalid or blocked cell.
     * -1 represents an empty cell on the map.
     * exp ---> The array expected to receive.
     * the map is cyclic
     */
    @Test
    public void testShortestPath7() {
        int[][] arr = {{-2, -2, -2, -2, -2, -2},
                       {-2, -2, -1, -1, -2, -1},
                       {-2, -2, -1, -1, -2, -2},
                       {-2, -1, -1, -1, -1, -2},
                       {-1, -2, -1, -1, -1, -2},
                       {-2, -2, -2, -2, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        Index2D index1 = new Index2D(5, 0);
        Index2D index2 = new Index2D(0, 1);
        Index2D index3 = new Index2D(0, 3);
        Index2D index4 = new Index2D(5, 3);
        Pixel2D[] points = {index1, index2, index3, index4};
        Pixel2D[] ans = map.shortestPath(points, -1);
        Pixel2D[] exp = {
                new Index2D(5, 3), new Index2D(0, 3), new Index2D(0, 2),
                new Index2D(0, 1), new Index2D(5, 1), new Index2D(5, 0)};
        assertArrayEquals(exp, ans);
    }

    /**
     * The test showed what happens when there is an index in the array that exceeds the boundaries of the map - it should return null.
     * the map is cyclic.
     * points ---> An array of points that we would like to find the shortest route that passes through all of them, (5,0),(0,7),(2,0).
     */
    @Test
    public void testShortestPathLimits1() {
        int[][] arr = {{-2, -2, -2, -2},
                        {-2, -2, -1, -1},
                        {-2, -2, -1, -1},
                        {-2, -2, -2, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(true);
        Index2D index1 = new Index2D(5, 0);
        Index2D index2 = new Index2D(0, 7);
        Index2D index3 = new Index2D(2, 3);
        Pixel2D[] points = {index1, index2, index3};
        Pixel2D[] ans = map.shortestPath(points, -1);
       assertNull(ans);
    }

    /**
     * The test checks what the function holds when there is no shortest route between all the points the function accepts in the array, due to obstacles.
     * points ---> An array of points that we would like to find the shortest route that passes through all of them, (0,0),(3,0),(0,3).
     *the map is not cyclic
     */
    @Test
    public void testShortestPathNoRoute() {
        int[][] arr = {{-2, -1, -2, -2},
                       {-2, -2, -1, -1},
                       {-2, -2, -1, -1},
                       {-2, -2, -1, -2}};
        Map2D map = new Map(arr);
        map.setCyclic(false);
        Index2D index1 = new Index2D(0, 0);
        Index2D index2 = new Index2D(3, 0);
        Index2D index3 = new Index2D(0, 3);
        Pixel2D[] points = {index1, index2, index3};
        Pixel2D[] ans = map.shortestPath(points, -1);
        assertNull(ans);
    }
    /**
     * The test checks if there is the shortest route between two given points, it should be null because the entire map is updated with obstacles -1.
     * -1 ---> is defined as a rule.
     * -2 ---> A place where I can move.
     * NOT CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 2000 milliseconds = 2 second).
     * size ---> The size of the matrix, (500*500).
     * p1 ---> start point, (0,0).
     * p2 ---> end point, (490,490).
     */

    @Test
    @Timeout(value = 2000,unit = MILLISECONDS)
    public void testShortestPathBetweenTwoPoints() {
        int size = 500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = -1;
            }
        }
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(size - 1, size - 1);
        Map2D map = new Map(matrix);
        map.setCyclic(false);
        long startTime = System.currentTimeMillis();
        Pixel2D[] result = map.shortestPath(p1, p2, -1);
        long endTime = System.currentTimeMillis();
        assertNull(result);
        System.out.println((endTime - startTime) + " milliseconds");
    }
    /**
     * The test checks if there is a shortest route between two given points, when there are both failed ones and places where I can move.
     * -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     * -2 ---> A place where I can move.
     * NOT CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 2000 milliseconds = 2 second).
     * size ---> The size of the matrix, (500*500).
     * startPoint ---> start point, (0,0).
     * endPoint ---> end point, (490,490).
     */
    @Test
    @Timeout( value = 2000, unit = MILLISECONDS)
    public void testShortestPathBetweenTwoPointsWithObstaclePerformance() {
        int size = 500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = -2;
                }
            }
        }
        Pixel2D startPoint = new Index2D(0, 0);
        Pixel2D endPoint = new Index2D (size - 1, size - 1);
        Map2D map = new Map(matrix);
        map.setCyclic(false);
        long startTime = System.currentTimeMillis();
        Pixel2D[] result = map.shortestPath(startPoint, endPoint, -1);
        long endTime = System.currentTimeMillis();
        assertNotNull(result);
        System.out.println((endTime - startTime) + " milliseconds");
    }
    /**
     * The test checks whether there is a shortest route between all the given points in the array given obstacles.
     * -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     * -2 ---> A place where I can move.
     * NOT CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 40000 milliseconds = 40 second).
     * size ---> The size of the matrix, (500*500).
     * points --->An array of indexes that we would like to calculate the shortest path among them, {(0,0),(400,400),(450,450),(50,50),(490,490)}.
     */
    @Test
    @Timeout( value = 40000,unit = MILLISECONDS)
    public void testShortestPathBetween5PointsPerformance() {
        int size = 500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = -2;
                }
            }
        }
        Pixel2D index1= new Index2D(0, 0);
        Pixel2D index2 = new Index2D(size-100,size-100);
        Pixel2D index3 = new Index2D(size - 50, size - 50);
        Pixel2D index4 = new Index2D(50, 50);
        Pixel2D index5 = new Index2D(size - 1, size - 1);
        Map2D map = new Map (matrix);
        map.setCyclic(false);
        Pixel2D [] points ={ index1, index2, index3,index4,index5};
        long startTime = System.currentTimeMillis();
        Pixel2D[] result = map.shortestPath(points,-1);
        long endTime = System.currentTimeMillis();
        assertNotNull(result);
        System.out.println((endTime - startTime) + " milliseconds");
    }
    /**
     * This method is a performance test to evaluate the performance of the allDistance() function in the Map2D class.
     * In the test there is a check that the result of the map is not null and that the values in certain places on the map are equal to what we expect.
     * -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     * -2 ---> A place where I can move.
     * NOT CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 1000 milliseconds = 1 second).
     * size ---> The size of the matrix, (500*500).
     * start ---> start point, (50,100).
     */
    @Test
    @Timeout( value = 1000,unit = MILLISECONDS)
    public void testAllDistancePerformance1() {
        int size = 500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > 150 && i < 250 && j > 150 && j < 250) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = -2;
                }
            }
        }
            Pixel2D start = new Index2D(50, 100);
            Map2D map = new Map(matrix);
            map.setCyclic(false);
            long startTime = System.currentTimeMillis();
            Map2D result = map.allDistance(start, -1);
            long endTime = System.currentTimeMillis();
            assertEquals(0,result.getPixel(50,100));
            assertEquals(1,result.getPixel(50,101));
            assertEquals(1,result.getPixel(49,100));
            assertEquals(1,result.getPixel(51,100));
            assertEquals(1,result.getPixel(50,99));
            assertNotNull(result);
            System.out.println((endTime - startTime) + " milliseconds");
        }
    /**
     * This method is a performance test to evaluate the performance of the allDistance() function in the Map2D class.
     * In the test there is a check that the result of the map is not null and that the values in certain places on the map are equal to what we expect.
     * -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     * -2 ---> A place where I can move.
     * CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 1000 milliseconds = 1 second).
     * size ---> The size of the matrix, (500*500).
     * index1 ---> (0,0).
     * index2 ---> (450,450).
     * index3 ---> (50,50).
     */

    @Test
    @Timeout( value = 40000,unit = MILLISECONDS)
    public void testShortestPathBetween5PointsPerformanceCyclic() {
        int size = 500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                }
                else {
                    matrix[i][j] = -2;
                }
            }
        }
        Pixel2D index1= new Index2D(0, 0);
        Pixel2D index2 = new Index2D(size - 50, size - 50);
        Pixel2D index3 = new Index2D(50, 50);
        Map2D map = new Map(matrix);
        map.setCyclic(true);
        Pixel2D [] points ={ index1, index2,index3};
        long startTime = System.currentTimeMillis();
        Pixel2D[] result = map.shortestPath(points,-1);
        long endTime = System.currentTimeMillis();
        assertNotNull(result);
        System.out.println((endTime - startTime) + " milliseconds");
    }
    /**
     * This method is a performance test to evaluate the performance of the allDistance() function in the Map2D class.
     * In the test there is a check that the result of the map is not null and that the values in certain places on the map are equal to what we expect.
     * -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     * -2 ---> A place where I can move.
     * NOT CYCLIC.
     * timeOut ---> Maximum time to perform the test in milliseconds (in the example 10000 milliseconds = 10 second).
     * size ---> The size of the matrix, (300*300).
     * index1 ---> (0,0).
     * index2 ---> (250,250).
     * index3 ---> (50,50).
     * index4 ---> (299,290).
     * index5 ---> (0,50).
     */

    @Test
    @Timeout( value = 10000,unit = MILLISECONDS)
    public void testShortestPathBetween5PointsPerformance200() {
        int size = 300;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                } else {
                    matrix[i][j] = -2;
                }
            }
        }
        Pixel2D index1= new Index2D(0, 0);
        Pixel2D index2 = new Index2D(size - 50, size - 50);
        Pixel2D index3 = new Index2D(50, 50);
        Pixel2D index4 = new Index2D(size-1, size-1);
        Pixel2D index5 = new Index2D(0, 50);
        Map2D map = new Map(matrix);
        map.setCyclic(false);
        Pixel2D [] points ={ index1, index2,index3,index4,index5};
        long startTime = System.currentTimeMillis();
        Pixel2D[] result = map.shortestPath(points,-1);
        long endTime = System.currentTimeMillis();
        assertNotNull(result);
        System.out.println((endTime - startTime) + " milliseconds");
    }

    /**
     * This test checks the execution of the fill function, and checks whether it meets a runtime of 1000 milliseconds.
     * size ---> size of matrix, (500*500).
     *  -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     *  -2 ---> A place where I can move.
     *  start ---> the pixel that we start, (100,100).
     *  startTime ---> starting time.
     *  endTime ---> end time .
     *  num ---> the number of painted squares.
     *  no cyclic
     */
    @Test
    @Timeout(value = 1000,unit =MILLISECONDS)
    public void testFillPerformance() {
        int size =500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                }
                else {
                    matrix[i][j] = -2;
                }

            }
        }
        Pixel2D start = new Index2D(100,100);
        Map2D map = new Map(matrix);
        map.setCyclic(false);
        long startTime = System.currentTimeMillis();
        int  num = map.fill(start,5);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("The number of painted squares" + " " + num);
    }

    /**
     * This test checks the performance of the NumberOfConnectedComponents function.
     * size ---> size of matrix, (500*500).
     *  -1 ---> is defined as a rule, There are obstacles in this area i > 150 && i < 250 && j > 150 && j < 250.
     *  -2 ---> A place where I can move.
     *  startTime ---> starting time.
     *  endTime ---> end time .
     *  num ---> the number of connected components.
     *  no cyclic
     */
    @Test
    @Timeout(value = 1000,unit =MILLISECONDS)
    public void testNumberOfConnectedComponentsPerformance() {
        int size =500;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i>150&&i< 250&&j>150&&j< 250) {
                    matrix[i][j] = -1;
                }
                else {
                    matrix[i][j] = -2;
                }

            }
        }
        Map2D map = new Map(matrix);
        map.setCyclic(false);
        long startTime = System.currentTimeMillis();
        int  num = map.numberOfConnectedComponents(-1);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
        System.out.println("The number of connected components" + " " + num);

    }


}


