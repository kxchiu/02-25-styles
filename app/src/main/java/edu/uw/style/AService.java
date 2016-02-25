package edu.uw.style;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by joelross on 2/25/16.
 */
public class AService extends AccessibilityService {

    private static final String TAG = "**ACC_SERVICE**";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, "AccessibilityEvent received!");

        final int eventType = event.getEventType();
        switch(eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                //clicked
                Log.i(TAG, "Click detected");
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }
}
