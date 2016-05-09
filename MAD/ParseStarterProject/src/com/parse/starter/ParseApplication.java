package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "AI7wRhtJs72eNLj05lQsf8ellN5Bt1CAI49nBpEJ", "vjDXmE7rxn4iOFRx1QUlnINDTYHxTg2KwmeySwxK");



   // ParseUser.enableAutomaticUser();
   // ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
   // ParseACL.setDefaultACL(defaultACL, true);
  }
}
