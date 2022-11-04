package les.donations.backendspring.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import les.donations.backendspring.dto.ModelDTO;

public class ApiReturnMessage {

    @JsonProperty
    private final Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    private ModelDTO message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    private String errorMessage;

    public ApiReturnMessage(final Integer code, final ModelDTO message) {
        this.code = code;
        this.message = message;
    }

    public ApiReturnMessage(final Integer code, final String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Integer getCode() {
        return code;
    }

    public ModelDTO getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
