package redis;

import javax.security.auth.login.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tuzhenyu on 17-9-28.
 * @author tuzhenyu
 */
public class RedisSessionManager {
    private static Configuration configuration;

    public RedisSessionManager(Configuration configuration){

    }

    public static void config(Configuration configuration){
        RedisSessionManager.configuration = configuration;
    }

    public RedisSessionManager getInstance(){
        return new RedisSessionManager(RedisSessionManager.configuration);
    }

    public Map<String,Object> loadDBSession(String sessionId){
        return  new HashMap<String,Object>();
    }


}
