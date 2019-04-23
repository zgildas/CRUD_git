package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Campagne;
import com.dao.CampagneDao;
import com.dao.DAOException;
import com.dao.DAOFactory;

/**
 * Servlet implementation class SuppressionCampagne
 */
public class SuppressionCampagne extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PARAM_ID_CAMPAGNE  = "ID";
	public static final String SESSION_CAMPAGNE = "mesCampagnes";
	public static final String VUE="/affichecampagne.jsp";
	//private static final String VUE="/test.jsp";
	private CampagneDao campagneDao;
	
	public void init() throws ServletException{
		this.campagneDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCampagneDao();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idCampagne=getValeurParmetres(request,PARAM_ID_CAMPAGNE);
		HttpSession session=request.getSession();
		Map<String,Campagne> mesCampagnes=(HashMap<String,Campagne>)session.getAttribute(SESSION_CAMPAGNE);
		
		if(idCampagne!=null && mesCampagnes !=null) {
			try {
				campagneDao.supprimer(mesCampagnes.get(idCampagne));
				mesCampagnes.remove(idCampagne);
			}catch(DAOException e){
				e.printStackTrace();
			}
			session.setAttribute(SESSION_CAMPAGNE, mesCampagnes);
		}
		
		response.sendRedirect(request.getContextPath()+ VUE);
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static String getValeurParmetres( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur;
	    }
	}

}
