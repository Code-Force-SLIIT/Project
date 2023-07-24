package Model;
public class Supplier {
    private int id;
    private String name;
    private String telephone;
    private String description;

    // Constructors (you can have multiple constructors if needed)
    public Supplier() {
    }

    public Supplier(int id, String name, String telephone, String description) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.description = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
