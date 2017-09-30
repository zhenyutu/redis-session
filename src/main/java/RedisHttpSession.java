import javax.servlet.http.*;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class RedisHttpSession implements HttpSession{
    private String id;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private long creationTime;
    private long lastAccessdTime;
    private int maxInactiveInterval;

    private volatile boolean invalid;

    private RedisSessionManager manager;
    private Map<String,Object> attribute;

    public RedisHttpSession(HttpServletRequest request, HttpServletResponse response, String id){
        this.id = id;
        this.request = request;
        this.response = response;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessdTime = this.creationTime;
        this.attribute = new HashMap<String,Object>();

        writeCookie();
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public String getId() {
        return this.id;
    }

    public long getLastAccessedTime() {
        return this.lastAccessdTime;
    }

    public void setMaxInactiveInterval(int i) {
        this.maxInactiveInterval = i;
    }

    public int getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }


    public ServletContext getServletContext() {
        return null;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }


    public Object getAttribute(String s) {
        if (!invalid)
            throw new IllegalStateException("session is invalid");
        return attribute.get(s);
    }

    public Object getValue(String s) {
        return getAttribute(s);
    }

    public Enumeration getAttributeNames() {
        if (!invalid)
            throw new IllegalStateException("session is invalid");
        Set<String> keys = attribute.keySet();
        return Collections.enumeration(keys);
    }

    public String[] getValueNames() {
        if (!invalid)
            throw new IllegalStateException("session is invalid");
        Set<String> keys = attribute.keySet();
        return keys.toArray(new String[0]);
    }

    public void setAttribute(String s, Object o) throws IllegalStateException{
        if (!invalid)
            throw new IllegalStateException("session is invalid");
        attribute.put(s,o);
    }

    public void putValue(String s, Object o) {
        setAttribute(s,o);
    }

    public void removeAttribute(String s) {
        if (!invalid)
            throw new IllegalStateException("session is invalid");
        attribute.remove(s);
    }

    public void removeValue(String s) {
        removeAttribute(s);
    }

    public void invalidate() {
        invalid  =true;
        manager.deletePhysically(id);
    }

    public boolean isNew() {
        return false;
    }


    private void writeCookie(){
        String id = getId();
        Cookie cookie = new Cookie("sessionId",id);
        response.addCookie(cookie);
    }
}
