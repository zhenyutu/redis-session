import sessionIDGenerator.SessionIdGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class RedisHttpRequest extends HttpServletRequestWrapper{
    private final HttpServletRequest request;
    private RedisHttpSession session;
    private SessionIdGenerator generator = new SessionIdGenerator();

    public RedisHttpRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public HttpSession getSession(boolean create) {
        return doGetSession(create);
    }

    @Override
    public HttpSession getSession() {
        return getSession(true);
    }

    private HttpSession doGetSession(boolean create){
        if (session==null){
            RedisHttpSession redisSession = new RedisHttpSession(request,generator.generateID(request));
        }
        return session;
    }
}
