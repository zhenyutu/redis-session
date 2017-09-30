import redis.RedisSessionManager;

import javax.servlet.http.*;
import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
        return null;
    }

    public Object getValue(String s) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public String[] getValueNames() {
        return new String[0];
    }

    public void setAttribute(String s, Object o) {

    }

    public void putValue(String s, Object o) {

    }

    public void removeAttribute(String s) {

    }

    public void removeValue(String s) {

    }

    public void invalidate() {

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
