package com.capgemini.advertisement.exception;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

 

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

 

import com.capgemini.advertisement.advertisementmanagementsystemrest.AdvertisementManagementSystemRestApplication;
import com.capgemini.advertisement.dao.StaffSpringDataDAO;
import com.capgemini.advertisement.entity.Staff;

 


/**
 * The Class CustomExceptionTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AdvertisementManagementSystemRestApplication.class)
public class CustomExceptionTest {

 

    @Autowired
    StaffSpringDataDAO staffSpringDataDAO;

 

    /**
     * When exception thrown then assertion succeeds.
     */
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds()
    {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

 

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

 

        assertTrue(actualMessage.contains(expectedMessage));
    }

 

    /**
     * When derived exception thrown then assertion succeds.
     */
    @Test
    public void whenDerivedExceptionThrown_thenAssertionSucceds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Integer.parseInt("1a");
        });

 

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

 

        assertTrue(actualMessage.contains(expectedMessage));
    }
    /**
     * When invalid exception thrown then assertion succeeds.
     */
    @Test
    void whenInvalidExceptionThrown_thenAssertionSucceeds() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            String name = null;
            name.length();
        });
    }
    /**
     * When staff exception thrown then assertion succeeds.
     */
    @Test
    void whenStaffExceptionThrown_thenAssertionSucceeds() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            Staff staff = new Staff();
            staff.setStaffId(null);
            Integer id = staff.getStaffId();
            staff = staffSpringDataDAO.getOne(id);
        });
    }
}
 