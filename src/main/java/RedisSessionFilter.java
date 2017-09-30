import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class RedisSessionFilter implements Filter{

    public RedisSessionFilter(){
        RedisSessionManager.config(Configuration.getConfiguration());
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof RedisHttpRequest)
            filterChain.doFilter(servletRequest,servletResponse);
        else {
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
            RedisHttpRequest request = new RedisHttpRequest(httpServletRequest,httpServletResponse);
            filterChain.doFilter(request,httpServletResponse);
        }
    }

    public void destroy() {

    }
}
