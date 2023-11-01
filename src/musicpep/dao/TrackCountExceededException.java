package musicpep.dao;

public class TrackCountExceededException extends Exception {
    public TrackCountExceededException(String message) {
        super(message);
    }
}
