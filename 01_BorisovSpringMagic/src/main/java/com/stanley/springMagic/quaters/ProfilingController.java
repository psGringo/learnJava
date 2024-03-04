package com.stanley.springMagic.quaters;

public class ProfilingController implements ProfilingControllerMBean{
    private boolean enabled = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
