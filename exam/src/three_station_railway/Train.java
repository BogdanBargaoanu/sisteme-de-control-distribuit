package three_station_railway;

public class Train {
    private String trainNumber;
    private String trainName;
    private SchedulerAgent schedulerAgent;

    public Train(String trainNumber, String trainName, SchedulerAgent schedulerAgent) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.schedulerAgent = schedulerAgent;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
