import com.vlad.spring.Entity.Bank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Bank_Test {
Bank bank;
    @Before
    public void setUp(){
        bank=new Bank(1L,"bank1");
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(bank.getName(),"bank1");
    }
    @Test
    public void testFalse(){
        Assert.assertNotSame(bank.getName(),"bank2");
    }
    @Test
    public void testGetId(){
        Assert.assertEquals(bank.getID().longValue(), 1L);
    }



}
