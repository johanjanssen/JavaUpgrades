package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ExampleEnumTest {

    @Mock
    private ExampleEnum exampleEnum;

    @Test
    public void testEnumWithMethods() {
        assertNotNull(exampleEnum);
    }
}
