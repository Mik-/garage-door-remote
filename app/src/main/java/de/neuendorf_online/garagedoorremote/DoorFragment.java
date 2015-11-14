package de.neuendorf_online.garagedoorremote;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoorFragment extends android.support.v4.app.Fragment {
    private Door door;
    private TextView textView;
    private Button buttonOpen;
    private Button buttonClose;
    private ImageView imageView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param door Door interface.
     * @return A new instance of fragment DoorFragment.
     */
    public static DoorFragment newInstance(Door door) {
        DoorFragment fragment = new DoorFragment();
        fragment.door = door;
        return fragment;
    }

    public DoorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_door, container, false);

        textView = (TextView) view.findViewById(R.id.door_name);
        textView.setText(this.door.getName());

        imageView = (ImageView) view.findViewById(R.id.doorState_imageView);
        updateStateImage();

        buttonOpen = (Button) view.findViewById(R.id.button_open);
        buttonClose = (Button) view.findViewById(R.id.button_close);

        updateButtonVisibility();

        return view;
    }

    public void setDoorIntent(View view) {
        if (view.getId() == buttonClose.getId()) {
            door.setIntent(DoorIntent.CLOSE);
        } else if (view.getId() == buttonOpen.getId()) {
            door.setIntent(DoorIntent.OPEN);
        }
    }

    private void updateButtonVisibility() {
        buttonOpen.setVisibility(View.GONE);
        buttonClose.setVisibility(View.GONE);

        switch (this.door.getState()) {
            case CLOSED:
            case CLOSING:
                buttonOpen.setVisibility(View.VISIBLE);
                break;
            case INTERMEDIATE:
                buttonOpen.setVisibility(View.VISIBLE);
                buttonClose.setVisibility(View.VISIBLE);
                break;
            case OPEN:
            case OPENING:
                buttonClose.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void updateStateImage() {
        switch (this.door.getState()) {
            case CLOSED:
                imageView.setImageResource(R.drawable.closed_state);
                break;
            case CLOSING:
                imageView.setImageResource(R.drawable.closing_state);
                break;
            case ERROR:
                imageView.setImageResource(R.drawable.error_state);
                break;
            case INIT:
                imageView.setImageResource(R.drawable.init_state);
                break;
            case INTERMEDIATE:
                imageView.setImageResource(R.drawable.intermediate_state);
                break;
            case OPENING:
                imageView.setImageResource(R.drawable.opening_state);
                break;
            case OPEN:
                imageView.setImageResource(R.drawable.open_state);
                break;
        }
    }
}
