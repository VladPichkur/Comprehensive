import com.vlad.spring.Entity.Deposit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Deposit_Test {
Deposit deposit;
@Before
public void setUp(){
    deposit = new Deposit(12L,"deposit1",20000.123,100000.555,0.001,360,0.003);
}
    @Test
    public void testGetName(){
        Assert.assertEquals(deposit.getName(),"deposit1");
    }
    @Test
    public void testFalse(){
        Assert.assertNotSame(deposit.getName(),"deposit2");
    }
    @Test
    public void testGetId(){
        Assert.assertEquals(deposit.getID().longValue(), 12L);
    }

    @Test
    public void testGetSum_min(){Assert.assertEquals(deposit.getSum_min(), Double.valueOf(20000.123));}
    @Test
    public void testGetFalseSum_min(){
        Assert.assertNotEquals(deposit.getSum_min(), Double.valueOf(10000.761));
    }
    @Test
    public void testGetSum_max(){
        Assert.assertEquals(deposit.getSum_max(), Double.valueOf(100000.555));
    }
    @Test
    public void testGetFalseSum_max(){
        Assert.assertNotEquals(deposit.getSum_max(), Double.valueOf(100000.551));
    }
    @Test
    public void testGetNalog(){
        Assert.assertEquals(deposit.getNalog(), Double.valueOf(0.001));
    }
    @Test
    public void testGetFalseNalog(){
        Assert.assertNotEquals(deposit.getSum_max(), Double.valueOf(0.1));
    }
    @Test
    public void testGetTermin(){Assert.assertEquals(deposit.getTermin(), Integer.valueOf(360));
    }
    @Test
    public void testGetFalseTermin(){Assert.assertNotEquals(deposit.getTermin(), Integer.valueOf(359));
    }
    @Test
    public void testGetInterest_rate(){Assert.assertEquals(deposit.getInterest_rate(), Double.valueOf(0.003));
    }
    @Test
    public void testGetFalseInterest_rate(){Assert.assertNotEquals(deposit.getInterest_rate(), Double.valueOf(1));
    }
}
