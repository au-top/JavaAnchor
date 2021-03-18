package org.toroder.JavaAnchor;


import io.javalin.Javalin;



public class Main {
    public static void main(String[] args) {
        Javalin server = Javalin.create().start("0.0.0.0", 9091);
        server.get("/api/v0/helloworld", new ShowSrcDir());
    }
}



