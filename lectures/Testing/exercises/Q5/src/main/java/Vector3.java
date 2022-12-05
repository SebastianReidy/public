import java.lang.Math;
/**
 * Class implementing 3D vectors
 * @author sebastian
 * @version 1.0
 * @param x: x coordinate
 * @param y: y coordinate
 * @param z: z coordinate
 */
public final class Vector3 {

    private double x;
    private double y;
    private double z;

    public Vector3(int x, int y, int z) {
        this.x = (double) x;
        this.y = (double) y;
        this.z = (double) z;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public boolean isZero() {
        if(this.x == 0.0 && this.y == 0.0 && this.z == 0.0)
            return true;
        else
            return false;
    }

    public double dotProduct(Vector3 vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public double computeLength() {
        return Math.sqrt((double) (Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2)));
    }

    public void normalize(){
        double length = this.computeLength();
        this.x = this.x / length;
        this.y = this.y / length;
        this.z = this.z / length;
    }

    public void scale(double num){
        this.x = this.x * num;
        this.y = this.y * num;
        this.z = this.z * num;
    }

    public void scale(int num) {
        scale((double) num);
    }

    public Vector3 crossProduct(Vector3 vec) {
        Vector3 result = new Vector3();

        result.x = this.y*vec.z - this.z*vec.y;
        result.y = this.z*vec.x - this.x*vec.z;
        result.z = this.x*vec.y - this.y*vec.x;

        return result;
    }

    public String toString(){
        String result = "[";
        result += this.x + ", " + this.y + ", " + this.z + "]";
        return result;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
