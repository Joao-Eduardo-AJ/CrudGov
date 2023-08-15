package br.com.syonet.gov.persistence;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;

import br.com.syonet.gov.entity.City;
import br.com.syonet.gov.entity.Region;
import br.com.syonet.gov.entity.State;
import br.com.syonet.gov.service.EntityManagerProvider;

public class Persistence {
	private static EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();

	public void saveState(State entity) {
		entityManagerProvider.executeTransaction(manager -> {
			manager.persist(entity);
			return null;
		});
	}
	
	public void saveCity(City entity) {
		entityManagerProvider.executeTransaction(manager -> {
			manager.persist(entity);
			return null;
		});
	}
	public void saveRegion(Region entity) {
		entityManagerProvider.executeTransaction(manager -> {
			manager.persist(entity);
			return null;
		});
	}
	
	public void updateState(State entity) {
		entityManagerProvider.executeTransaction(manager -> {
			State existingState = manager.find(State.class, entity.getId());
			if (existingState != null) {
				manager.merge(entity);
			} else {
				saveState(entity);
			}
			return null;
		});
	}
	
	public void updateCity(City entity) {
		entityManagerProvider.executeTransaction(manager -> {
			City existingState = manager.find(City.class, entity.getId());
			if (existingState != null) {
				manager.merge(entity);
			} else {
				saveCity(entity);
			}
			return null;
		});
	}
	
	public void updateRegion(Region entity) {
		entityManagerProvider.executeTransaction(manager -> {
			Region existingState = manager.find(Region.class, entity.getId());
			if (existingState != null) {
				manager.merge(entity);
			} else {
				saveRegion(entity);
			}
			return null;
		});
	}

	public List<State> getStates() {
		TypedQuery<State> query = entityManagerProvider
				.executeTransaction(manager -> manager.createQuery("select s from State s", State.class));
		return query.getResultList();
	}

	public List<City> getCitiesByState(@PathParam("idState") int idState) {
		TypedQuery<City> query = entityManagerProvider
				.executeTransaction(manager -> manager.createQuery("select c from City where c.id_state = :idState", City.class));
		return query.getResultList();
	}

	public List<Region> getRegionsByCity(@PathParam("idCity") int idCity) {
		TypedQuery<Region> query = entityManagerProvider.executeTransaction(manager -> manager.createQuery("select r from Region where r.id_city = :idCity", Region.class));
		return query.getResultList();
	}
	
	public void deleteStateById(@PathParam("idState") int idState) {
		TypedQuery<State> query = entityManagerProvider.executeTransaction(manager -> manager.createQuery("delete from State s where s.id = :idState", State.class));
		query.executeUpdate();
	}
	
	public void deleteCityById(@PathParam("idCity") int idCity) {
		TypedQuery<City> query = entityManagerProvider.executeTransaction(manager -> manager.createQuery("delete from City c where c.id = :idCity", City.class));
		query.executeUpdate();
	}
	
	public void deleteRegionById(@PathParam("idRegion") int idRegion) {
		TypedQuery<Region> query = entityManagerProvider.executeTransaction(manager -> manager.createQuery("delete from Region r where r.id = :idRegion", Region.class));
		query.executeUpdate();
	}
}
