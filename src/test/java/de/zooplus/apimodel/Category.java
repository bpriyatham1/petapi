package de.zooplus.apimodel;

/**
 * @author priyatham.bolli
 */
public class Category {

    private Long id;
    private String name;

    private Category() {
    }

    /**
     *
     * @param name
     * @param id
     */
    public Category(Long id, String name) {
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

    /**
     * Creates a Category Mock
     */
    public static Category categoryMock(Long id, String name) {
        return new Category(id,name);
    }

}