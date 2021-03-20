package org.toroder.JavaAnchor.Api;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.util.ajax.JSON;
import org.jetbrains.annotations.NotNull;
import org.toroder.JavaAnchor.DataComponent.FsElem;
import org.toroder.JavaAnchor.DataComponent.ResultPackage;
import org.toroder.JavaAnchor.GlobalState;

import java.util.Arrays;

/**
 * @author autopMateBook
 */
public class GetServerPublicData implements Handler {

    @Override
    public void handle(@NotNull Context context) throws Exception {
        context.req.getContextPath();
        final String reqPath = context.queryParam("path");
        if (reqPath != null) {
            System.out.println(reqPath);
            final FsElem fsElemTree = GlobalState.getOnly().getFsElemTree();
            final FsElem target = fsElemTree.fromPathGetChildElem(reqPath);
            if (target != null) {
                final String base64TypeContent=target.getFileContent(true);
                context.result(ResultPackage.create(StateCode.RES_OK.code,base64TypeContent, ""));
                return;
            }
        }
        System.out.println("req Path is Null");
        context.result(ResultPackage.create(StateCode.RES_ERROR_PATH_FIND_NOT.code, new Object(), "is not file "));
    }
}
