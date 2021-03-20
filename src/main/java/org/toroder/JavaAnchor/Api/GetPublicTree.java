package org.toroder.JavaAnchor.Api;

import com.alibaba.fastjson.JSON;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.toroder.JavaAnchor.DataComponent.FsElem;
import org.toroder.JavaAnchor.DataComponent.ResultPackage;
import org.toroder.JavaAnchor.GlobalState;

import java.io.File;

public class GetPublicTree implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        final String transformJsonString = ResultPackage.create(STATECODE.RES_OK.code, GlobalState.getOnly().getFsElemTree().toJson(), "");

        context.result(transformJsonString);
    }
}