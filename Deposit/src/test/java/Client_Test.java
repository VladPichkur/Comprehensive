import com.vlad.spring.Entity.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Client_Test {

    Client client;
    @Before
    public void setUp(){
        client = new Client(1L,"Walter White",20000.761);
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(client.getName(),"Walter White");
    }
    @Test
    public void testFalseName(){
        Assert.assertNotSame(client.getName(),"Jessy Pinkman");
    }
    @Test
    public void testGetId(){
        Assert.assertEquals(client.getID().longValue(), 1L);
    }
    @Test
    public void testGetBalance(){
        Assert.assertEquals(client.getBalance(), Double.valueOf(20000.761));
    }
    @Test
    public void testGetFalseBalance(){
        Assert.assertNotEquals(client.getBalance(), Double.valueOf(10000.761));
    }
}
