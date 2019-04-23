package com.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.DAOFactory;

/**
 * Application Lifecycle Listener implementation class InitialisationDaoFactory
 *
 */
public class InitialisationDaoFactory implements ServletContextListener {
	private static final String ATT_DAO_FACTORY = "daofactory";
	private DAOFactory daoFactory;



	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		/* Récupération du ServletContext lors du chargement de l'application */
        ServletContext servletContext = sce.getServletContext();
        /* Instanciation de notre DAOFactory */
        this.daoFactory = DAOFactory.getInstance();
        /* Enregistrement dans un attribut ayant pour portée toute l'application */
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
	}

}
