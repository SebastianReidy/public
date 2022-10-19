import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

final class Vector3Test {

    // should also write a test for the constructor!
    @Test
    public void IsZeroVectorTrue() {
        Vector3 vec = new Vector3(0,0,0);
        assertThat(vec.isZero(), is(true));
    }

    @Test
    public void IsZeroVectorFalse() {
        Vector3 vec = new Vector3(-8.66,1.2,234.66);
        assertThat(vec.isZero(), is(false));
    }

    @Test
    public void ComputeDotProduct5() {
        Vector3 vec = new Vector3(1,2,3);
        Vector3 vec2 = new Vector3(2,3,-1);
        assertThat(vec.dotProduct(vec2), is(5.0));
    }

    @Test
    public void ComputeLength5() {
        Vector3 vec = new Vector3(0,-4,3);
        assertThat(vec.computeLength(), is(5.0));
    }

    @Test
    public void NormalizeVector5() {
        Vector3 vec = new Vector3(0,4,-3);
        vec.normalize();
        assertThat(vec.toString(), is("[0.0, 0.8, -0.6]"));
    }

    @Test
    public void SclaeByScalar5() {
        Vector3 vec = new Vector3(0,4,-3);
        vec.scale(4);
        assertThat(vec.toString(), is("[0.0, 16.0, -12.0]"));
    }

    @Test
    public void ComputeCrossProduct() {
        Vector3 vec = new Vector3(1,2,3);
        Vector3 vec2 = new Vector3(4,5,6);
        Vector3 result = vec.crossProduct(vec2);
        assertThat(result.toString(), is("[-3.0, 6.0, -3.0]"));
    }

}
