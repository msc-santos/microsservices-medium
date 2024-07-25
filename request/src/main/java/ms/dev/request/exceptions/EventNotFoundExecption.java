package ms.dev.request.exceptions;

public class EventNotFoundExecption extends RuntimeException {
    public EventNotFoundExecption() {
        super("Event not found!");
    }

    public EventNotFoundExecption(String message) {
        super(message);
    }
}
