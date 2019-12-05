package dpr.svich.nav4.entity;

import dpr.svich.nav4.enums.RoomType;

public class Room {

    private long id;
    private String label;
    private RoomType type;

    public Room(long id, String label, RoomType type){
        this.id = id;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public RoomType getType() {
        return type;
    }
}
