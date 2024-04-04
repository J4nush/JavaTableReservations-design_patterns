package pl.pollub.javatablereservations.session;

import java.util.UUID;

public interface Session {

    UUID getId();
    Boolean isActive();
    void expire();
    UUID getUserId();

    Session clone();

}
