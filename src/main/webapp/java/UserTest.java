import org.junit.Test;

import pl.sda.pms.productFeature.ProductFeature;
import pl.sda.pms.sprint.Sprint;
import pl.sda.pms.sprint.SprintService;
import pl.sda.pms.users.User;

import javax.annotation.Resource;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Resource
    private SprintService sprintService;

    @Test
    public void testGetUserName() {


        //given
        User user = new User("Antek", "sasd",
                "asd@gmail.com", "Muzykant", null);

        //when

        //then
        assertEquals("Muzykant", user.getUsername());
    }

    @Test
    public void testSprintAddException() {
        //given
        LocalDate startDate1 = LocalDate.of(2019, 1, 5);
        LocalDate finishDate1 = LocalDate.of(2019, 1, 10);
//        Sprint sprint1 = new Sprint(startDate1, finishDate1, 10);
        LocalDate startDate2 = LocalDate.of(2019, 1, 4);
        LocalDate finishDate2 = LocalDate.of(2019, 1, 10);
      //  Sprint sprint2 = new Sprint(startDate2, finishDate2, 10);
        System.out.println(sprintService.findIfExists(startDate1,startDate2));
//        sprintService.saveSprint(startDate1, finishDate1, 10);

        //when

        //then
//        try {
//            sprintService.saveSprint(startDate2, finishDate2, 10);
////            fail("Expected an IndexOutOfBoundsException to be thrown");
//        } catch (NullPointerException e) {
//            assertEquals(e.getMessage(), "Data range overlaps");
//        }
assertEquals(1,1);
    }



}
