package dpr.svich.nav4.entity;

import dpr.svich.nav4.enums.RoomType;

public class Room {

    private String label;
    private RoomType type;

    public Room(String label, RoomType type){
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
