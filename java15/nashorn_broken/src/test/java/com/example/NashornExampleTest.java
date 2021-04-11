package com.example;

import org.junit.jupiter.api.Test;

import javax.script.ScriptException;

public class NashornExampleTest {

    @Test
    public void printHelloWorldTest() throws ScriptException {
        NashornExample nashornExample = new NashornExample();
        nashornExample.printHelloWorld();
    }
}
