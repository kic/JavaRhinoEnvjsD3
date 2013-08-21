package kic.engine.plot.svg.render;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 *
 * @author Kic
 *   later (javafx8) headless javafx 
 *      with Xvfb http://docs.codehaus.org/display/FEST/Running+FEST+under+Xvfb
 *      and fake jfxpanel https://javafx-jira.kenai.com/browse/RT-32335?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
 *
 *   share objects http://stackoverflow.com/questions/16633660/passing-custom-java-objects-to-webengine-executescript    
 */

public class Renderer {
    private final Context cx = ContextFactory.getGlobal().enterContext();
    private Scriptable scope = cx.initStandardObjects();
    
    public static void main(String[] args) throws Exception {
        Renderer r = new Renderer();
        r.test();
        r.loadScript("js/axistest.js");
    }
    
    public Renderer() {
        // load missing functions 
        loadScript("js/missingfunctions.js");
        
        // load envjs core
        loadScript("js/envjs/dist12/core.js");
        loadScript("js/envjs/dist12/rhino.js");
        
        // load envjs dom.js |→event.js |→html.js |→timer.js |→parser.js |→xhr.js |→window.js 
        loadScript("js/envjs/dist12/console.js");
        loadScript("js/envjs/dist12/dom.js");
        loadScript("js/envjs/dist12/event.js");
        loadScript("js/envjs/dist12/html.jsr");
        loadScript("js/envjs/dist12/css.js");
        loadScript("js/envjs/dist12/parser.js");
        loadScript("js/envjs/dist12/xhr.js");
        loadScript("js/envjs/dist12/timer.js");
        loadScript("js/envjs/dist12/window.js");
        
        // load css selector fix
        loadScript("js/envjs/dist12/sizzle.js");
        
        // load d3
        //loadScript("js/d3/d3.v3.min.js"); // better to load
        loadScript("js/d3/d3.v3.js"); // better to debug
    }
    
    public final void loadScript(String name) {
        InputStreamReader readerJQ = null;
        try {
            InputStream resourceAsStream = Renderer.class.getResourceAsStream(name);
            readerJQ = new InputStreamReader(resourceAsStream);
            cx.evaluateReader(scope, readerJQ, name, 1, null);
            readerJQ.close();
        } catch (IOException e) {               
            throw new RuntimeException(e);
        }
    }
    
    public final void setObject(String name, Object o) {
        ScriptableObject.putProperty(scope, name, Context.javaToJS(o, scope));
    }
    
    public String getBody() {
        cx.evaluateString(scope, "var svgSource = document.getElementsByTagName('body')[0].innerHTML;", "getsvg", 1, null);
        return scope.get("svgSource", scope).toString();
    }
    
    public void eval(String script) {
        cx.evaluateString(scope, script, ""+script.hashCode(), 1, null);
    }
    
    public void test() {
        setObject("test", new java.util.Date());
        eval("d3.select('body').selectAll('p').data([1,2,3]).enter().append('p').text('hello');"
           + "print(document.innerHTML);");
        
        System.out.println(getBody());
    }
}
