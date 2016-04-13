package org.demo.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robin_2 on 13/04/2016.
 */
public class PiStampTest {

    @Test
    public void testGetLastName() throws Exception {
        //given
        PiStamp test = new PiStamp(true, new User("Kaka", "Bok",new RfidKey("2"),"2" ));
        String given = "Kaka";
        //when
        String got = test.getFirstName();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testGetFirstName() throws Exception {
        //given
        PiStamp test = new PiStamp(true, new User("Kaka", "Bok",new RfidKey("2"),"2" ));
        String given = "Bok";
        //when
        String got = test.getLastName();
        //then
        Assert.assertEquals(given,got);
    }

    @Test
    public void testToString() throws Exception {

    }
}