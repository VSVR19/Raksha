package com.vsvr.raksha.raksha_stage;

public class Note {
    private String appName;
    private String appPasscode;

    public Note(){
        //Public simply sitting constructor
    }

    public Note(String  appName, String appPasscode){
        this.appName = appName;
        this.appPasscode = appPasscode;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppPasscode() {
        return appPasscode;
    }
}