package com.stanley;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "myprefix")
@PropertySource("myapplication.properties")
public class AppProps {
    private String myProp;
}
