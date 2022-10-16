package les.donations.backendspring.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiReturnMessage {

    @JsonProperty
    private final Integer code;
    @JsonProperty
    private final Object message;

    public ApiReturnMessage(final Integer code, final Object message) {
        this.code = code;
        this.message = message;
    }
}
