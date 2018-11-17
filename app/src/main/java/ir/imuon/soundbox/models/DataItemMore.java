package ir.imuon.soundbox.models;

import java.util.UUID;

public class DataItemMore {

    private String id;
    private int title;

    public DataItemMore() {
    }

    public DataItemMore(String id, int title) {

        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DataItemMore{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
