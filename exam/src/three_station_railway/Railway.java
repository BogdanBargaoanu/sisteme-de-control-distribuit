package three_station_railway;

import java.util.List;

public class Railway {
    private Train train;
    private boolean semaphoreOn;
    private List<Railway> bifurcations;

    public Railway(Train train, boolean semaphoreOn, List<Railway> bifurcations) {
        this.train = train;
        this.semaphoreOn = semaphoreOn;
        this.bifurcations = bifurcations;
    }

    public boolean isSemaphoreOn() {
        return semaphoreOn;
    }

    public void setSemaphoreOn(boolean semaphoreOn) {
        this.semaphoreOn = semaphoreOn;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
