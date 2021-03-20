package org.toroder.JavaAnchor.DataComponent;


import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

enum FSELEMTYPE {

    FILE(0, "File"),
    DIR(1, "Dir");

    public int code;
    public String message;

    FSELEMTYPE(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

public class FsElem {
    File fileElem;
    FSELEMTYPE type;
    ArrayList<FsElem> childElemList = null;
    HashMap<String, FsElem> childElemMap = null;


    public FsElem(File fileElem) {
        this.fileElem = fileElem;
        this.type = fileElem.isDirectory() ? FSELEMTYPE.DIR : FSELEMTYPE.FILE;
        this.childElemList = new ArrayList<FsElem>() {
        };
        switch (this.type) {
            case DIR:
                final File[] nextListFiles = this.fileElem.listFiles();
                if (nextListFiles != null) {
                    for (File nextFile : nextListFiles) {
                        childElemList.add(new FsElem(nextFile));
                    }
                }
                break;
            case FILE:
            default:
                break;
        }
    }

    public HashMap<String, FsElem> getChildElemMap() {
        if (this.childElemMap == null) {
            this.childElemMap = new HashMap<String, FsElem>();
            for (final FsElem nextChildElem : this.childElemList) {
                childElemMap.put(nextChildElem.fileElem.getName(), nextChildElem);
            }
        }
        return childElemMap;
    }

    public FsElem fromPathGetChildElem(String callPath) {
        String[] callPathSlices = callPath.split("/");
        callPathSlices = Arrays.stream(callPathSlices).filter((v) -> !v.isEmpty()).toArray(String[]::new);
        FsElem nowFsElem = this;
        for (final String nextPathSlice : callPathSlices) {
            if (nowFsElem.getChildElemMap().containsKey(nextPathSlice)) {
                nowFsElem = nowFsElem.getChildElemMap().get(nextPathSlice);
            } else {
                return null;
            }
        }
        return nowFsElem;
    }

    public JSONObject toJson() {
        final JSONObject toJsonObj = new JSONObject();
        toJsonObj.put("type", this.type);
        toJsonObj.put("name", this.fileElem.getName());
        ArrayList<JSONObject> childList = new ArrayList<JSONObject>();
        for (final FsElem nextFsElem : this.childElemList) {
            childList.add(nextFsElem.toJson());
        }
        toJsonObj.put("child", childList);
        return toJsonObj;
    }


    public String getFileContent(boolean useBase64) throws IOException {
        byte[] fileElemContentBytes = Files.readAllBytes(this.fileElem.toPath());
        if (useBase64) {
            return new String(Base64.getEncoder().encode(fileElemContentBytes));
        } else {
            return new String(fileElemContentBytes);
        }

    }
}
