package com.example.demo.jedisCluster;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Data
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

    private String                  redisNodes;                            // redis节点地址
    private JedisCluster            jedisCluster;
    private Integer                 timeout;
    private Integer                 maxRedirections;
    private String                  redisAuth;                             // 认证密码
    private Integer                 soTimeout;                             // 间隔超时时间
    private Integer                 isAuth;                                // 是否加密
    private GenericObjectPoolConfig genericObjectPoolConfig;
    protected final Log logger = LogFactory.getLog(getClass());
    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public  Class<? extends JedisCluster> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    private Set<HostAndPort> parseHostAndPort() throws Exception {
        try {

            if (StringUtils.isBlank(redisNodes)) {
                throw new Exception("redis初始化异常，redisNodes不能为空！");
            }
            Set<HostAndPort> haps = new HashSet<HostAndPort>();

            String[] nodes = redisNodes.split(";");

            for (String node : nodes) {
                String[] ipAndPort = node.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                haps.add(hap);
            }
            return haps;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception("解析 jedis 配置文件失败", ex);
        }
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        Set<HostAndPort> haps = this.parseHostAndPort();
        logger.debug("初始化所有redis===================================");
        if (  isAuth==null || isAuth == 1 ){
            // 带认证的reids集群
            jedisCluster = new JedisCluster(haps, timeout,soTimeout, maxRedirections,redisAuth, genericObjectPoolConfig);
        }else if(isAuth!=null || isAuth == 0) {
            jedisCluster = new JedisCluster(haps, timeout, maxRedirections, genericObjectPoolConfig);
        }

    }
}
