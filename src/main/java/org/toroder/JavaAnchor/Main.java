package org.toroder.JavaAnchor;


import io.javalin.Javalin;
import org.toroder.JavaAnchor.Api.GetServerPublicData;
import org.toroder.JavaAnchor.Api.ServerPublicDataIndex;


public class Main {
    public static void main(String[] args) {
        Javalin server = Javalin.create().start("0.0.0.0", 9091);
        final String version = "v0";
        final String perfix = "/api/".concat(version).concat("/");
        server.get(perfix.concat("/public/index"), new ServerPublicDataIndex());
        server.get(perfix.concat("/public/file"), new GetServerPublicData());
    }
}
