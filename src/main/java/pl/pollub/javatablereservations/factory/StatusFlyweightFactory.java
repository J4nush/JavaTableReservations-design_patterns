package pl.pollub.javatablereservations.factory;

import org.springframework.stereotype.Component;
import pl.pollub.javatablereservations.entity.Status;

import java.util.HashMap;
import java.util.Map;

@Component
public class StatusFlyweightFactory {

    private final Map<String, Status> statusCache = new HashMap<>();

    public Status getStatus(String apiName) {
        Status status = statusCache.get(apiName);
        if (status == null) {
            status = new Status();
            statusCache.put(apiName, status);
        }
        return status;
    }
}
