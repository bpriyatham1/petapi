package de.zooplus.apimodel;

/**
 * @author priyatham.bolli
 */
public class PetResponse {

    private Long code;
    private String type;
    private String message;

    public PetResponse() {
    }

    public PetResponse(Long code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
