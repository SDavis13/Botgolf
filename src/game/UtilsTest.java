package game;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UtilsTest {
    float physX, physY, pixX, pixY, physLength, pixLength;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        
    }
    
    @Parameters
    public static Collection<Object[]> params() {
        Object[][] data = new Object[][] {
                {27, 58, 47, 375, 5, 43},
                {58, 27, 83, 137, 48, 10},
                {-58, 27, 83, 137, 48, 10}
        };
        return Arrays.asList(data);
    }
    
    public UtilsTest(float pxOffset, float pyOffset, float physLoc, float pixLoc, float physLen, float pixLen){
        Consts.pxOffset = pxOffset;
        Consts.pyOffset = pxOffset;
        physX = physLoc;
        physY = physLoc;
        pixX = pixLoc;
        pixY = pixLoc;
        physLength = physLen;
        pixLength = pixLen;
    }

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void testToPixXAndBack() {
        float temp = Utils.toPixX(physX);
        temp = Utils.toPhysX(temp);
        assertEquals(physX, temp, 1);
    }

    @Test
    public void testToPhysXAndBack() {
        float temp = Utils.toPhysX(pixX);
        temp = Utils.toPixX(temp);
        assertEquals(pixX, temp, 5);
    }

    @Test
    public void testToPixYAndBack() {
        float temp = Utils.toPixY(physY);
        temp = Utils.toPhysY(temp);
        assertEquals(physY, temp, 1);
    }

    @Test
    public void testToPhysYAndBack() {
        float temp = Utils.toPhysY(pixY);
        temp = Utils.toPixY(temp);
        assertEquals(pixY, temp, 5);
    }

    @Test
    public void testToPixLengthAndBack() {
        float temp = Utils.toPixLength(physLength);
        temp = Utils.toPhysLength(temp);
        assertEquals(physLength, temp, 1);
    }

    @Test
    public void testToPhysLengthAndBack() {
        float temp = Utils.toPhysLength(pixLength);
        temp = Utils.toPixLength(temp);
        assertEquals(pixLength, temp, 5);
    }

}
