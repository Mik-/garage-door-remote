package de.neuendorf_online.garagedoorremote;

/**
 * Created by michael on 14.11.15.
 */

public interface Door {
    public String getName();
    public DoorState getState();
    public DoorIntent getIntent();
    public void setIntent(DoorIntent newIntent);
}
