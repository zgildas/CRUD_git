package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Campagne;
import static com.dao.DAOUtilitaire.*;

public class CampagneDaoImpl implements CampagneDao {

	private DAOFactory daoFactory;
	private static final String SQL_SELECT = "SELECT id, campaign_title,type,emails_sent,abuse_reports, unsubscribed, send_time FROM  campaign";
	private static final String SQL_SELECT_PAR_ID = "SELECT id, campaign_title,type,emails_sent,abuse_reports, unsubscribed, send_time FROM  campaign WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO campaign (id, campaign_title,type,emails_sent,abuse_reports, unsubscribed, send_time) VALUES (?, ?, ?, ?, ?, ?,?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM campaign WHERE id = ?";

	CampagneDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Campagne trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return trouver( SQL_SELECT_PAR_ID, id );
	}

	@Override
	public List<Campagne> lister() throws DAOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Campagne> mescampagnes = new ArrayList<Campagne>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mescampagnes.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return mescampagnes;
	}

	@Override
	public void supprimer(Campagne campaign) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, true, campaign.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la suppression du client, aucune ligne supprimée de la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	@Override
	public void creer(Campagne campagne) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, false, campagne.getId(),
					campagne.getCampaign_title(), campagne.getType(), campagne.getEmails_sent(),
					campagne.getAbuse_reports(), campagne.getUnsubscribed(), campagne.getSend_time());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if (statut == 0) {
				throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	private Campagne trouver(String sql, Object... objects) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Campagne campaign = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments (ici,
			 * uniquement un id) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet.next()) {
				campaign = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return campaign;
	}

	static Campagne map(ResultSet resultSet) throws SQLException {
		Campagne campaign = new Campagne();
		campaign.setId(resultSet.getString("id"));
		campaign.setCampaign_title(resultSet.getString("campaign_title"));
		campaign.setType(resultSet.getString("type"));
		campaign.setEmails_sent(resultSet.getLong("emails_sent"));
		campaign.setUnsubscribed(resultSet.getLong("unsubscribed"));
		campaign.setAbuse_reports(resultSet.getLong("abuse_reports"));
		campaign.setSend_time(resultSet.getString("send_time"));
		return campaign;
	}

}
