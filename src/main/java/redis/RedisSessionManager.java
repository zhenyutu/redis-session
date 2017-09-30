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
    private JedisUtil jedisUtil;

    public RedisSessionManager(Configuration configuration){
        jedisUtil = new JedisUtil();
    }

    public static void config(Configuration configuration){
        RedisSessionManager.configuration = configuration;
    }

    public RedisSessionManager getInstance(){
        return new RedisSessionManager(RedisSessionManager.configuration);
    }

    public Map<String,Object> loadDBSession(String sessionId){
        String session = jedisUtil.get(sessionId);
        return  new HashMap<String,Object>();
    }


}
