import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

final class Vector3Test {

    // should also write a test for the constructor!
    @Test
    public void ConstructorInt() {
        Vector3 test = new Vector3(1,2,3);
        assertThat(test.getX(), is(1.0));
        assertThat(test.getY(), is(2.0));
        assertThat(test.getZ(), is(3.0));
    }

    @Test
    public void ConstructorDouble() {
        Vector3 test = new Vector3(1.0,2.0,3.0);
        assertThat(test.getX(), is(1.0));
        assertThat(test.getY(), is(2.0));
        assertThat(test.getZ(), is(3.0));
    }

    @Test
    public void ConstructorZero() {
        Vector3 test = new Vector3();
        assertThat(test.getY(), is(0.0));
        assertThat(test.getZ(), is(0.0));
        assertThat(test.getX(), is(0.0));
    }

    @Test
    public void IsZeroTrue() {
        Vector3 test = new Vector3();
        assertThat(test.isZero(), is(true));
    }

    @Test
    public void IsZeroFalse() {
        Vector3 test = new Vector3(0,0,1);
        assertThat(test.isZero(), is(false));
    }

    @Test
    public void DotProduct() {
        Vector3 test = new Vector3(1,2,3);
        Vector3 testa = new Vector3(3,2,1);
        assertThat(test.dotProduct(testa), is(10.0));
    }

    @Test
    public void ComputeLength() {
        Vector3 test = new Vector3(4,3,0);
        assertThat(test.computeLength(), is(5.0));
    }

    @Test
    public void Normalize() {
        Vector3 test = new Vector3(4,3,0);
        test.normalize();
        assertThat(test.getX(), is( 4.0 /5.0));
        assertThat(test.getY(), is(3.0 / 5.0));
    }

    @Test
    public void Scale() {
        Vector3 test = new Vector3(4,3,0);
        test.scale(5);
        assertThat(test.getX(), is( 4.0 *5.0));
        assertThat(test.getY(), is(3.0 * 5.0));
    }

    @Test
    public void CrossProduct() {
        Vector3 test = new Vector3(1,0,0);
        Vector3 testa = new Vector3(0,0,1);
        test = test.crossProduct(testa);
        assertThat(test.getX(), is( 0.0));
        assertThat(test.getY(), is(-1.0));
        assertThat(test.getZ(), is(0.0));
    }

    @Test
    public void ToString() {
        Vector3 test = new Vector3(1,0,0);
        assertThat(test.toString(), is("[1.0, 0.0, 0.0]"));
    }

    @Test
    public void Setters() {
        Vector3 test = new Vector3();
        test.setX(1);
        test.setY(1);
        test.setZ(1);
        assertThat(test.getX(), is(1.0));
        assertThat(test.getY(), is(1.0));
        assertThat(test.getZ(), is(1.0));
    }


}
