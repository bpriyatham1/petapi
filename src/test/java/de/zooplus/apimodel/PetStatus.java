package de.zooplus.apimodel;

/**
 * @author priyatham.bolli
 */
public enum PetStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String petStatus;

    PetStatus(String petStatus) {
        this.petStatus = petStatus;
    }

    public String getParameterName() {
        return petStatus;
    }

}
