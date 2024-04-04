package pl.pollub.javatablereservations.session;

import pl.pollub.javatablereservations.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class OpaqueSession implements Session {

    private final UUID id;
    private Boolean isActive;
    private final Date validUntil;
    private UUID userId;

    public OpaqueSession(UUID userId) {
        this.id = UUID.randomUUID();
        this.isActive = true;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Constants.TOKEN_VALIDITY_MINUTES);
        this.validUntil = calendar.getTime();

        this.userId = userId;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public Boolean isActive() {
        return this.isActive && (new Date()).before(this.validUntil);
    }

    @Override
    public void expire() {
        this.isActive = false;
    }

    @Override
    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public Session clone() {
        OpaqueSession opaqueSession = new OpaqueSession(this.id);
        opaqueSession.setUserId(this.userId);
        return opaqueSession;
    }
}
