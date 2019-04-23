package com.objet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.beans.Campagne;
import com.dao.CampagneDao;
import com.dao.DAOException;

public final class CreationCampagneForm {
	private static final String ID = "id";
	private static final String CAMPAIGN_TITLE = "campaign_title";
	private static final String TYPE = "type";
	private static final String EMAIL_SENT = "emails_sent";
	private static final String ABUSE_REPORTS = "abuse_reports";
	private static final String UNSUBSCRIBED = "unsubscribed";
	private static final String SENS_TIME = "send_time";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private CampagneDao campagneDao;

	public CreationCampagneForm(CampagneDao campagneDao) {
		this.campagneDao = campagneDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Campagne uneCampagne(HttpServletRequest request) {

		String C_id = getValeurChamp(request, ID);
		String C_campaign_title = getValeurChamp(request, CAMPAIGN_TITLE);
		String C_type = getValeurChamp(request, TYPE);
		String C_emails_sent =getValeurChamp(request, EMAIL_SENT);
		String C_abuse_reports =getValeurChamp(request, ABUSE_REPORTS);
		String C_unsubscribed = getValeurChamp(request, UNSUBSCRIBED);
		String C_send_time = getValeurChamp(request, SENS_TIME);

		Campagne maCampagne = new Campagne();
		traiterId(C_id,maCampagne);
		traiterC_campaign_title(C_campaign_title,maCampagne);
		traiterC_type(C_type,maCampagne);
		traiterC_emails_sent(C_emails_sent,maCampagne);
		traiterC_abuse_reports(C_abuse_reports,maCampagne);
		traiterC_unsubscribed(C_unsubscribed,maCampagne);
		traiterC_send_time(C_send_time,maCampagne);
		
		try {
			if (erreurs.isEmpty()) {

				campagneDao.creer(maCampagne);
				resultat = "La campagne a été créée avec succès.";
			} else {
				resultat = "Échec de création de la campagne.";
			}
		} catch (DAOException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création de la campagne : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		
		return maCampagne;

	}

	private void traiterC_send_time(String c_send_time, Campagne maCampagne) {
		// TODO Auto-generated method stub

		try {
				validationSentTime(c_send_time);
			} catch (Exception e) {
				setErreur(SENS_TIME, e.getMessage());
			}
			maCampagne.setSend_time(c_send_time);
		
	}

	private void traiterC_unsubscribed(String c_unsubscribed, Campagne maCampagne) {
		// TODO Auto-generated method stub
		Long valeur = null;
		try {
				valeur= validationUnsubscribed(c_unsubscribed);
			} catch (Exception e) {
				setErreur(UNSUBSCRIBED, e.getMessage());
			}
			maCampagne.setUnsubscribed(valeur);
	
	}

	private void traiterC_abuse_reports(String c_abuse_reports, Campagne maCampagne) {
		// TODO Auto-generated method stub
		Long valeur = null;
		try {
			valeur = validationAbuseReports(c_abuse_reports);
		} catch (Exception e) {
			setErreur(ABUSE_REPORTS, e.getMessage());
		}
		maCampagne.setAbuse_reports(valeur);
		
		
	}

	private void traiterC_emails_sent(String c_emails_sent, Campagne maCampagne) {
		// TODO Auto-generated method stub
		Long valeur = null;
		try {
			valeur= validationEmailSent(c_emails_sent);
		} catch (Exception e) {
			setErreur(EMAIL_SENT, e.getMessage());
		}
		maCampagne.setEmails_sent(valeur);
		
	}

	private void traiterC_type(String c_type, Campagne maCampagne) {
		// TODO Auto-generated method stub
		try {
			validationType(c_type);
		} catch (Exception e) {
			setErreur(TYPE, e.getMessage());
		}
		maCampagne.setType(c_type);
		
	}

	private void traiterC_campaign_title(String c_campaign_title, Campagne maCampagne) {
		// TODO Auto-generated method stub
		try {
			validationCampaignTitle(c_campaign_title);
		} catch (Exception e) {
			setErreur(CAMPAIGN_TITLE, e.getMessage());
		}
		maCampagne.setCampaign_title(c_campaign_title);
	}

	private void traiterId(String c_id, Campagne maCampagne) {
		// TODO Auto-generated method stub
		try {
			validationID(c_id);
		} catch (Exception e) {
			setErreur(ID, e.getMessage());
		}
		maCampagne.setId(c_id);
		
	}

	private void validationSentTime(String c_send_time) throws Exception {
		// TODO Auto-generated method stub
		if (c_send_time == null) {
			throw new Exception("champ date est vide.");
		}

	}

	private Long validationUnsubscribed(String c_unsubscribed) throws Exception {
		// TODO Auto-generated method stub
		Long temp;
		if (c_unsubscribed == null) {
			throw new Exception("champ unsubscribed est vide.");
		}else {
			temp=Long.parseLong(c_unsubscribed);
		}
		
		return temp;

	}

	private Long validationAbuseReports(String c_abuse_reports) throws Exception {
		// TODO Auto-generated method stub
		Long temp;
		if (c_abuse_reports == null) {
			throw new Exception("champ abuse_reports est vide.");
		}else {
			temp=Long.parseLong(c_abuse_reports);
		}
		return temp;
	}

	private Long validationEmailSent(String c_emails_sent) throws Exception {
		// TODO Auto-generated method stub
		Long temp;
		if (c_emails_sent == null) {
			throw new Exception("champ emails sent est vide.");
		}else {
			temp=Long.parseLong(c_emails_sent);
		}
		return temp;
	}

	private void validationType(String c_type) throws Exception {
		// TODO Auto-generated method stub
		if (c_type == null) {
			throw new Exception("champ type est vide.");
		}

	}

	private void validationCampaignTitle(String c_campaign_title) throws Exception {
		// TODO Auto-generated method stub
		if (c_campaign_title == null) {
			throw new Exception("champ campaign title est vide.");
		}

	}

	private void setErreur(String champ, String message) {
		// TODO Auto-generated method stub
		erreurs.put(champ, message);

	}

	private void validationID(String c_id) throws Exception {
		// TODO Auto-generated method stub
		if (c_id == null) {
			throw new Exception("champ id est vide.");
		}
	}

	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
	
	

	
}
