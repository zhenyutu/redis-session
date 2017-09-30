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

    public static RedisSessionManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public RedisHttpSession getSession(String sessionId){
        String sessionStr = jedisUtil.get(sessionId);
        return JSON.parseObject(sessionStr,RedisHttpSession.class);
    }

    public void deletePhysically(String id) {
         jedisUtil.deletePhysically(id);
    }

    private static final class SingletonHolder {
        private static final RedisSessionManager INSTANCE = init();

        private static RedisSessionManager init() {
            return new RedisSessionManager(RedisSessionManager.configuration);
        }

        private SingletonHolder() {
        }

    }

}
