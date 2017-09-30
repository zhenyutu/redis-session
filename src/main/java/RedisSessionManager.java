import com.alibaba.fastjson.JSON;
import redis.JedisUtil;

import javax.security.auth.login.Configuration;

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

    public RedisHttpSession loadDBSession(String sessionId){
        String sessionStr = jedisUtil.get(sessionId);
        RedisHttpSession session = JSON.parseObject(sessionStr,RedisHttpSession.class);
        return session;
    }

    public void deletePhysically(String id) {
         jedisUtil.deletePhysically(id);
    }

}
