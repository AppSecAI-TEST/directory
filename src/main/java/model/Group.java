package model;

/**
 * Created by neste_000 on 11.07.2017.
 */
public class Group {

    //<editor-fold desc="����">

    private int id;
    private String name;
    private String description;

    //</editor-fold>

    //<editor-fold desc="������������">

    public Group() {
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    //</editor-fold>

    //<editor-fold desc="������ ��������� � ���������">

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //</editor-fold>
}
