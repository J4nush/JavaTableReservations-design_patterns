package pl.pollub.javatablereservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.pollub.javatablereservations.component.DataLoader;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final DataLoader dataLoader;

    @Autowired
    public ApplicationReadyEventListener(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        this.dataLoader.seed();
    }

}
