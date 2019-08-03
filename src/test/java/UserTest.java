import org.junit.Test;
import pl.sda.springdemo.users.User;

import static org.junit.Assert.assertEquals;

public class UserTest {


    @Test
    public void testGetUserName() {


        //given
        User user=new User("Antek","sasd",
                "asd@gmail.com","Muzykant",null);

        //when

        //then
        assertEquals("Muzykant",user.getUsername());
    }
}
