package com.manage.system.shiro.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.crazycake.shiro.RedisSessionDAO;

import java.io.Serializable;

public class LocalRedisSessionDAO extends CachingSessionDAO {
    private RedisSessionDAO redisSessionDAO;

    public void setRedisSessionDAO(RedisSessionDAO redisSessionDAO) {
        this.redisSessionDAO = redisSessionDAO;
    }

    @Override
    protected void doUpdate(Session session) {
        System.out.println("doUpdate----------");
        redisSessionDAO.update(session);
    }

    @Override
    protected void doDelete(Session session) {
        System.out.println("doDelete----------");
        redisSessionDAO.delete(session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("doCreate----------");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("doReadSession----------");
        return redisSessionDAO.readSession(sessionId);
    }
}
