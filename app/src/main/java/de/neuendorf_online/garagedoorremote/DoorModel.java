package de.neuendorf_online.garagedoorremote;

/**
 * Created by michael on 14.11.15.
 */
public class DoorModel implements Door {
    private String name;
    private DoorState state;
    private DoorIntent intent;
    private int id;

    public DoorModel(String name, DoorState state, DoorIntent intent, int id) {
        this.name = name;
        this.state = state;
        this.intent = intent;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DoorState getState() {
        return this.state;
    }

    @Override
    public DoorIntent getIntent() {
        return this.intent;
    }

    @Override
    public void setIntent(DoorIntent newIntent) {
        // TODO: implement sending new intent
    }
}
