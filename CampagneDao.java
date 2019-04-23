package com.dao;

import java.util.List;

import com.beans.Campagne;

public interface CampagneDao {
	void creer(Campagne campagne) throws DAOException;

	Campagne trouver(String id) throws DAOException;
	
	List<Campagne> lister() throws DAOException;
	
	void supprimer(Campagne campaign) throws DAOException;
}
