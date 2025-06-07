package com.exemple;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testAddition(){
        assertEquals(5, App.addition(2,3));
    }
}