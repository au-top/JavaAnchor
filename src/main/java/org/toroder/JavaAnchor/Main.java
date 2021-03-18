package org.toroder.JavaAnchor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.javalin.Javalin;
import org.toroder.JavaAnchor.DataComponent.FsElem;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Javalin server = Javalin.create().start("0.0.0.0", 9091);
        server.get("/api/v0/helloworld", rex -> {
            String pwdPath =  System.getProperty("user.dir");
            final FsElem srcDirElem=new FsElem(new File(pwdPath+"/src"));
            final String transformJsonString = JSON.toJSONString(srcDirElem.toJson());
            rex.result(transformJsonString);
        });
    }
}



