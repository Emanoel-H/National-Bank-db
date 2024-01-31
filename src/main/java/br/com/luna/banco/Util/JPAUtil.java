package br.com.luna.banco.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("nationalbankdb");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
