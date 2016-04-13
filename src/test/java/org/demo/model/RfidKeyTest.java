package org.demo.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robin_2 on 13/04/2016.
 */
public class RfidKeyTest {
    @Test
    public void testGetId() throws Exception {
        //given
        RfidKey expected = new RfidKey("1");
        //when
        String got = expected.getId();
        //then
        Assert.assertEquals("1", got);
    }

    @Test
    public void testSetId() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }
}