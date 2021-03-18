package org.toroder.JavaAnchor;

import com.alibaba.fastjson.JSON;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.toroder.JavaAnchor.DataComponent.FsElem;

import java.io.File;

class ShowSrcDir implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        String pwdPath = System.getProperty("user.dir");
        final FsElem srcDirElem = new FsElem(new File(pwdPath + "/src"));
        final String transformJsonString = JSON.toJSONString(srcDirElem.toJson());
        context.result(transformJsonString);
    }
}