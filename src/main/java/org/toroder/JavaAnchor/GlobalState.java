package org.toroder.JavaAnchor;

import org.toroder.JavaAnchor.DataComponent.FsElem;

import java.io.File;

public class GlobalState {
    static private GlobalState onlyElem = null;

    private FsElem FsElemTree = null;


    public FsElem getFsElemTree() {
        if(this.FsElemTree==null){
            String pwdPath = System.getProperty("user.dir");
            FsElemTree= new FsElem(new File(pwdPath + "/src"));
        }
        return FsElemTree;
    }

    private GlobalState() { }

    public static GlobalState getOnly() {
        if (GlobalState.onlyElem == null) {
            GlobalState.onlyElem = new GlobalState();
        }
        return GlobalState.onlyElem;
    }

}
