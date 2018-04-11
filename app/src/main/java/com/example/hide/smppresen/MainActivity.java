package com.example.hide.smppresen;

import android.app.ActivityOptions;
import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import static android.content.pm.PackageManager.FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaRouter mediaRouter = (MediaRouter)this.getSystemService(Context.MEDIA_ROUTER_SERVICE);
        MediaRouter.RouteInfo route = mediaRouter.getSelectedRoute(MediaRouter.ROUTE_TYPE_LIVE_VIDEO);
        if (route != null) {
            Display presentationDisplay = route.getPresentationDisplay();
            if (presentationDisplay != null) {
                Log.i(TAG, "Display != null");
                Presentation presentation = new MyPresentation(this, presentationDisplay);
                presentation.show();
            } else {
                Log.i(TAG, "Display == null");
            }
        } else {
            Log.i(TAG, "route == null");
        }
        /* DisplayManager displayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE); /**/
        //Display[] presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        /* Display[] presentationDisplays = displayManager.getDisplays(); /**/
        /* Log.i( TAG, "length " + presentationDisplays.length); /**/
    }
}


class MyPresentation extends Presentation {
    public MyPresentation(Context outerContext, Display display){
        super(outerContext,display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presen);
    }
}
