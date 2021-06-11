package de.zooplus.staticdata;

/**
 * @author priyatham.bolli
 */
public enum RestParameters {

    NAME("name"),
    PET_ID("petId"),
    STATUS("status");

    private final String parameterName;

    RestParameters(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

}
