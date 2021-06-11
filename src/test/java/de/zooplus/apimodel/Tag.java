package de.zooplus.apimodel;

/**
 * @author priyatham.bolli
 */
public class Tag {

    private Long id;
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public Tag() {
    }

    /**
     * @param name
     * @param id
     */
    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
