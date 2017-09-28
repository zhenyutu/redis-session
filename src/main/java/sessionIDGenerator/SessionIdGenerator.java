package sessionIDGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class SessionIdGenerator {
    private final String SPLITE = "-";
    private String host;

    public SessionIdGenerator(){
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            host = UUID.randomUUID().toString().substring(0,8);
        }
    }

    public String generateID(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        String remote = request.getRemoteAddr();
        sb.append(remote).append(SPLITE)
                .append(host).append(SPLITE)
                .append(Long.toHexString(System.currentTimeMillis())).append(SPLITE)
                .append(UUID.randomUUID().toString().substring(0,8));
        return sb.toString();
    }
}
