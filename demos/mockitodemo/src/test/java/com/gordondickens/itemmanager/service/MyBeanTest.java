package com.gordondickens.itemmanager.service;

import com.gordondickens.itemmanager.entity.MyBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class MyBeanTest {

    private MyBean myBean;

    @Before
    public void setUp() {
        myBean = new MyBean();
    }

    @Test
    public void mockStaticMethod() throws Exception {
        // Given
        final long expectedId = 2L;
        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNewId()).thenReturn(expectedId);

        // When
        final long value = myBean.createData();

        // Then
        assertEquals(expectedId, value);
    }

    @Test
    public void mockStaticMethodAndVerify() throws Exception {
        // Given
        final long expectedId = 2L;
        mockStatic(IdGenerator.class);
        when(IdGenerator.generateNewId()).thenReturn(expectedId);

        // When
        final long value = myBean.createData();

        // Then
        assertEquals(expectedId, value);
        verifyStatic();
        IdGenerator.generateNewId();
    }

    @Test
    public void stubStaticMethod() throws Exception {
        // Given
        final long expectedId = 2L;
        stub(method(IdGenerator.class, "generateNewId")).toReturn(expectedId);

        // When
        final long value = myBean.createData();

        // Then
        assertEquals(expectedId, value);
    }

    @Test
    public void suppressStaticMethod() throws Exception {
        // Given
        suppress(method(IdGenerator.class, "generateNewId"));

        // When
        final long value = myBean.createData();

        // Then
        assertEquals(0L, value);
    }
}