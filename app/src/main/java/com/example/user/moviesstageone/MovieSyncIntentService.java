package com.example.user.moviesstageone;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by User on 5/21/2018.
 */

public class MovieSyncIntentService extends IntentService {

    public MovieSyncIntentService() {
        super("MovieSyncIntentService");
        MovieSyncData.syncData(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //MovieSyncData.syncData(this);
    }
}
