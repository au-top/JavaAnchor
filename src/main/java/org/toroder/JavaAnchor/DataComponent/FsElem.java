package org.toroder.JavaAnchor.DataComponent;


import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.ArrayList;

enum  FSELEMTYPE{FILE,DIR};

public class FsElem {
    File fileElem;

    FSELEMTYPE type;
    ArrayList<FsElem> childElem;
    public FsElem(File fileElem){
        this.fileElem=fileElem;
        this.type=fileElem.isDirectory()?FSELEMTYPE.DIR:FSELEMTYPE.FILE;
        this.childElem=new ArrayList<FsElem>(){};
        switch (this.type){
            case DIR:
                final File[] nextListFiles = this.fileElem.listFiles();
                if(nextListFiles!=null){
                    for (File nextFile : nextListFiles){
                        childElem.add(new FsElem(nextFile));
                    }
                }
                break;
            case FILE:
            default:
                break;
        }
    }
    public JSONObject toJson(){
        final JSONObject toJsonObj = new JSONObject();
        toJsonObj.put("type",this.type);
        toJsonObj.put("name",this.fileElem.getName());
        ArrayList<JSONObject>  childList=new ArrayList<JSONObject>();
        for (final FsElem nextFsElem: this.childElem) {
            childList.add(nextFsElem.toJson());
        }
        toJsonObj.put("child",childList);
        return toJsonObj;
    }
}
