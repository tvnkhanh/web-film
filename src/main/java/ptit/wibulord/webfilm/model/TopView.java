package ptit.wibulord.webfilm.model;

public class TopView {
    int id;
    String name;
    long view;

    public TopView() {
    }

    public TopView(int id) {
        this.id = id;
    }

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

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }
}
