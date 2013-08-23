/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kic.engine.plot.svg.render;

import java.io.InputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class HeadlessWebEngine extends Application {
    private WebEngine engine;
    
    @Override public void start(Stage stage) {    
        this.engine = new WebEngine();
        // this.engine = engine;
        
        JSObject win = (JSObject) engine.executeScript("window");
        win.setMember("console", new Print());
        
        loadScript("js/d3/d3.v3.js");
        loadScript("js/axistest.js"); 
        engine.executeScript("var a= 12*2;");
        engine.executeScript("console.log('console log test!');");
        System.out.println(engine.executeScript("3+a"));
        System.out.println(engine.executeScript("d3.scale.linear().domain([100,1000]).range([0,100])(200)"));
        System.out.println((String) engine.executeScript("document.documentElement.innerHTML"));
        
        Platform.exit();        
    }

    private void loadScript(String name) {   
        InputStream resourceAsStream = Renderer.class.getResourceAsStream(name);
        Scanner s = new java.util.Scanner(resourceAsStream).useDelimiter("\\A");
        String script = s.hasNext() ? s.next() : "";
        // System.out.println(script.substring(0,100));
        engine.executeScript(script);
    }
    
    public String toString() {
        return "das bin ich!";
    }
    
    public static void main(String[] args) { 
        System.out.println(launch(args)); 
    }
}
