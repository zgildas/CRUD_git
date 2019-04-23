package com.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.beans.Campagne;
import com.dao.CampagneDao;
import com.dao.DAOFactory;

/**
 * Servlet Filter implementation class PrechargementFilter1
 */
public class PrechargementFilter implements Filter {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PARAM_ID_CAMPAGNE  = "id";
	public static final String SESSION_CAMPAGNE = "mesCampagnes";
	public static final String VUE="/affichecampagne.jsp";
	//private static final String VUE="/test.jsp";
	private CampagneDao campagneDao;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.campagneDao=((DAOFactory)filterConfig.getServletContext().getAttribute(CONF_DAO_FACTORY)).getCampagneDao();		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
			HttpServletRequest req=(HttpServletRequest) request;
			HttpSession session=req.getSession();
			
			if(session.getAttribute(SESSION_CAMPAGNE)==null) {
				List<Campagne> listeCampagnes=campagneDao.lister();
				Map<String,Campagne> mapCampagne=new HashMap<String,Campagne>();
				for(Campagne campaign:listeCampagnes) {
					mapCampagne.put(campaign.getId(), campaign);
				}
				session.setAttribute(SESSION_CAMPAGNE, mapCampagne);
			}
			chain.doFilter(req, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
