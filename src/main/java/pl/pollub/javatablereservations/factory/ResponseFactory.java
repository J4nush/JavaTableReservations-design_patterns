package pl.pollub.javatablereservations.factory;

import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    public static<T> ResponseEntity<T> ok(T obj) {
        return ResponseEntity.ok(obj);
    }

    public static<T> ResponseEntity<T> loginFailed() {
        return ResponseEntity.status(400).build();
    }

}
