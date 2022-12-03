import com.vlad.spring.Entity.Contract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class Contract_Test {
    Contract contract;
    @Before
    public void setUp(){
        contract = new Contract(2L,50666.0,LocalDate.of(2021,11,17));
    }
    @Test
    public void testLocalDate(){
        LocalDate time = LocalDate.now();
        Contract contract1 = new Contract(time);
        contract1.getTermin_pochatok();
        Assert.assertTrue(time.isEqual(contract1.getTermin_pochatok()));
    }
    @Test
    public void testGetID(){Assert.assertEquals(contract.getContractID().longValue(), 2L);}
    @Test
    public void testGetFalseID(){Assert.assertNotSame(contract.getContractID().longValue(), 1L);}
    @Test
    public void testGetSum(){Assert.assertEquals(contract.getSum_contract(), Double.valueOf(50666.0));}
    @Test
    public void testGetFalseSum(){Assert.assertNotEquals(contract.getSum_contract(), Double.valueOf(20000.123));}

}
