package de.neuendorf_online.garagedoorremote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DoorControllerService extends Service {
    public DoorControllerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
