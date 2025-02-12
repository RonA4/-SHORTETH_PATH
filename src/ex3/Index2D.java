/**
 * name ---> Ron Amsalem
 * ID ---> 326029600
 */
package ex3;
import java.io.Serializable;
public class Index2D implements Pixel2D, Serializable{
    private int _x, _y;
    public Index2D() {
        this(0,0);
    }
    public Index2D(int x, int y) {
        _x=x;_y=y;
    }
    public Index2D(Pixel2D t) {
        this(t.getX(), t.getY());
    }

    /**
     * temp ---> Create a new array whose values will be the string values separated by "," we will use the split method.
     * temp[0]--->The x value.
     * temp[1]---> The y value.
     * @param pos ---> A string that we want to convert to Index2D.
     */
    public Index2D(String pos) {
        String [] temp = pos.split(",");
        this._x= Integer.parseInt(temp[0]);
        this._y= Integer.parseInt(temp[1]);
    }

    @Override
    public int getX() {
        return _x;
    }

    @Override
    public int getY() {
        return _y;
    }


    /**
     *The 'distance2D(Pixel2D t)' function calculates the distance between pixels ,according to the distance formula
     * distanceX ---> The absolute value between the x value differences between two points.
     * distanceY ---> The absolute value between the y value differences between two points.
     * @param t ---> The point from which we want to measure distance.
     * @return ans ---> The distance
     */
    public double distance2D(Pixel2D t) {
        double ans ;
       double distanceX =  Math.abs(t.getX()-this.getX());
       double distanceY =  Math.abs(t.getY()-this.getY());
       ans = Math.sqrt(distanceX*distanceX+distanceY*distanceY);
        return ans;
    }

    /**
     * The purpose of the `toString()` method, which is to provide a string representation of the object's state.
     * @return  ans ---> a string representation of this object in the format "x,y".
     */

    @Override
    public String toString() {
        String ans = this._x+","+this._y;
        return ans;
    }

    /**
     * The `equals(Object t)` method determines whether the current object is equal to another object. The method takes 'Object' as a parameter and returns 'true'
       if the passed object is an instance of 'Index2D' and has the same coordinates as the current object, otherwise 'false'.
     * @param t ---> the compared object.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object t) {
        if(!(t instanceof Index2D temp)){
            return false;
        }
        return temp._x == this._x && temp._y == this._y;
    }
}
