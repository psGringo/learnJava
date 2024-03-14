package messenger.profiling.mbean;

public class ProfilingController implements ProfilingControllerMBean {

    private boolean enabled = false;

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
