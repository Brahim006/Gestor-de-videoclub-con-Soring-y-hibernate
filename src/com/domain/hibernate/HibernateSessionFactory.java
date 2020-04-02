/*
 * SessionFactory para el mappeo con Hibernate
 */
package com.domain.hibernate;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Brahim
 */
public class HibernateSessionFactory {
    
    public static final String CONFIGURATION_FILE = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    
    /**
     * Devuelve la sesión de Hibernate única en tiempo de ejecución, que de no
     * haber sido abierta la configura desde el archivo hibernate.cfg.xml
     * @return Un objeto Session conteniendo la instancia única de la sesión.
     */
    public static Session getSession(){
        
        if(sessionFactory == null || !session.isOpen()){
            File f = new File(CONFIGURATION_FILE);
            sessionFactory = new AnnotationConfiguration().configure(f)
                                                        .buildSessionFactory();
            session = sessionFactory.openSession();
        }
        
        return session;
        
    } // fin getSession
    
}
