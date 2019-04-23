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
import com.objet.CreationCampagneForm;
import com.dao.DAOFactory;


/**
 * Servlet implementation class Test
 */
public class CreationCampagne extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String ATT_CAMP="maCampagne";
	private static final String ATT_TRAI="traite";
	public static final String SESSION_CAMPAGNE = "mesCampagnes";
	public static final String VUE_SUCCES="/affichecampagne.jsp";
	public static final String VUE_FORM="/creationcampagne.jsp";
	//private static final String VUE="/test.jsp";
	
	private CampagneDao campagneDao;
	
	public void init() throws ServletException{
		this.campagneDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCampagneDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		 
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 CreationCampagneForm traite= new CreationCampagneForm(campagneDao);
		 Campagne maCampagne= traite.uneCampagne(request);
		
		 request.setAttribute(ATT_CAMP, maCampagne);
		 request.setAttribute(ATT_TRAI, traite);
		 
		 if(traite.getErreurs().isEmpty()) {
			 HttpSession session=request.getSession();
			 Map<String, Campagne> mesCampagnes=(HashMap<String, Campagne>) session.getAttribute(SESSION_CAMPAGNE);
			 
			 if (mesCampagnes == null) {
			 mesCampagnes=new HashMap<String,Campagne>();
			 }		 
			 mesCampagnes.put(maCampagne.getId(),maCampagne);
			 session.setAttribute(SESSION_CAMPAGNE, mesCampagnes);
			 this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
			 
		 }else {
			 this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			 
		 }
	}

}
