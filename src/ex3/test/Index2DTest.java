/**
 * Name ---> Ron Amsalem.
 * Id ---> 326029600.
 * this class is Junit for index2D class.
 */
package ex3.test;
import ex3.Index2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
public class Index2DTest {
    /**
     * this test cheek the method 'toString()'.
     * p ---> this is index2D, (2,3).
     */
    @Test
    public void toStringTest(){
        Index2D p = new Index2D(2,3);
        String strEx= "2,3";
        assertEquals(strEx,p.toString());
    }
    @Test
    /**
     * this test cheek if the distance between p1 to p2 is equals to distance between p2 to p1 , in other word this is cheek if the symmetric calculation.
     * p1 ---> index2D, (5,8).
     * p2 ---> index2D, (2,7).
     * d1 ---> the distance between p1 to p2.
     * d2 ---> the distance between p2 to p1.
     */
    public void testDistance2DSymmetry(){
        Index2D p1 = new Index2D(5, 8);
        Index2D p2 = new Index2D(2, 7);
        double d1 = p1.distance2D(p2);
        double d2 = p2.distance2D(p1);
        Assertions.assertEquals(d1, d2);
    }
    @Test
    /**
     * tests that the function returns the same distance for different points that are the same distance from p1.
     * p1 ---> index2D, (8,5).
     * p2 ---> index2D, (0,0).
     * p3 ---> index2D, (13,13).
     * d1 ---> the distance between p1 to p2.
     * d2 ---> the distance between p1 to p3.
     */
    public void testDistance2DSameDistance(){
        Index2D p1 = new Index2D(8, 5);
        Index2D p2 = new Index2D(0, 0);
        Index2D p3 = new Index2D(13, 13);
        double d1 = p1.distance2D(p2);
        double d2 = p1.distance2D(p3);
        Assertions.assertEquals(d1, d2);
    }

    /**
     * tests that the function can work with negative values.
     * p1 ---> index2D, (3,2).
     * p2 ---> index2D, (8,10).
     * p3 ---> index2d, (-5,-3).
     * d1 ---> the distance between p1 to p2.
     * d2 ---> the distance between p1 to p3.
     */
    @Test
    public void testDistance2DNegative(){
        Index2D p1 = new Index2D(3, 2);
        Index2D p2 = new Index2D(8, 10);
        Index2D p3 = new Index2D(-5, -3);
        double d1 = p1.distance2D(p2);
        double d2 = p1.distance2D(p3);
        Assertions.assertEquals(d1, d2);
    }
    @Test
    /**
     * tests that a pixel's distance from itself is zero.
     * p1 ---> index2D, (23,19).
     * p2 ---> index2D, (23,19).
     * d1 ---. the distance between p1 to p2.
     */
    public void testDistance2DSame2(){
        Index2D p1 = new Index2D(23, 19);
        Index2D p2 = new Index2D(23, 19);
        double d1 = p1.distance2D(p2);
        Assertions.assertEquals(0, d1);
    }

    /**
     * tests that function returns the correct distance.
     * p1 ---> index2D, (0,0).
     * p2 ---> index2D, (1,1).
     * d1 ---> the distance between p1 to p2.
     *
     */
    @Test
    public void testDistance2DActual(){
        Index2D p1 = new Index2D(0, 0);
        Index2D p2 = new Index2D(1, 1);
        double d1 = p1.distance2D(p2);
        Assertions.assertEquals(Math.sqrt(2), d1);
    }


    /**
     * test that different pixels return false.
     * p1 ---> index2D, (8,13).
     * p2 ---> index2D, (-8,-13).
     * p3 ---> index2D, (-13,5).
     * p4 ---> index2D, (-13,-5).
     * p5 ---> index2D, (8,13).
     * p6 ---> index2D, (-8,13).
     * p7 ---> index2D, (13,-5).
     * p8 ---> index2D, (-13,5).
     */
    @Test
    public void testDifferentEqual(){
        Index2D p1 = new Index2D(8, 13);
        Index2D p2 = new Index2D(-8, -13);
        assertFalse(p1.equals(p2));
        Index2D p3 = new Index2D(-13, 5);
        Index2D p4 = new Index2D(-13, -5);
        assertFalse(p3.equals(p4));
        Index2D p5 = new Index2D(8, 13);
        Index2D p6 = new Index2D(-8, 13);
        assertFalse(p5.equals(p6));
        Index2D p7 = new Index2D(13, -5);
        Index2D p8 = new Index2D(-13, 5);
        assertFalse(p7.equals(p8));
    }


    /**
     * If p1 is equal to p2 it returns true otherwise it returns false
     * In the second test we check if it is equal in terms of the type of the variable, if not it returns false otherwise it returns true.
     * In the last check, it checks if the same index is equal to itself.
     * This test checks whether two Index2D objects are equal.
     *  p1 ---> the first index, (8,13).
     *  p2 ---> the second index, (4,5).
     *  p3 ---> the third index, (0,0).
     *  temp ---> array, (temp[0]=0,temp[1]=0).
     */
    @Test
    public void equalsTest(){
        Index2D p1 = new Index2D(8, 13);
        Index2D p2= new Index2D(4,5);
       assertFalse(p1.equals(p2));
       Index2D p3 =  new Index2D(0,0);
        int[] temp = {0, 0};
        assertFalse(p3.equals(temp));
        assertTrue(p1.equals(p1));
    }
}
