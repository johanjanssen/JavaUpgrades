package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class ExampleEnumTest {

    @Mock
    private ExampleEnum exampleEnum;

    @Test
    public void testEnumWithMethods() {
        Container container = new Container(exampleEnum);
        Mockito.when(exampleEnum.getValue()).thenReturn("42");
        assertEquals("42", container.retrieveValue());
    }
}
