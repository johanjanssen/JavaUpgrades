package com.example.annotation;

import jakarta.activation.URLDataSource;
import java.net.URL;

public class ActivationExample {
    public void retrieveFromURL(final URL url) {
        final URLDataSource dataSource = new URLDataSource(url);
        // more code
    }
}
