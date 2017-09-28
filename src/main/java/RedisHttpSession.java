import redis.RedisSessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class RedisHttpSession implements HttpSession{
    private String id;
    private HttpServletRequest request;
    private long creationTime;
    private long lastAccessdTime;
    private int maxInactiveInterval;

    private RedisSessionManager manager;
    private Map<String,Object> dbSession;

    public RedisHttpSession(HttpServletRequest request, String id){
        this.id = id;
        this.request = request;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessdTime = this.creationTime;

        dbSession = manager.loadDBSession(id);
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
}
