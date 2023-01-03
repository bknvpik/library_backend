package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Invalid credentials")
public class UnauthorizedException extends  Exception {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
