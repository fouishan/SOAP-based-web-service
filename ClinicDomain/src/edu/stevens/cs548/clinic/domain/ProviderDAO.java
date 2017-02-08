package edu.stevens.cs548.clinic.domain;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;



public class ProviderDAO implements IProviderDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	public ProviderDAO(EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderDAO.class.getCanonicalName());


	@Override
	public long addProvider(Provider prov) throws ProviderExn {
		long npi = prov.getNpi();
		Query query = em.createNamedQuery("CountProviderByNPI").setParameter("npi", npi);
		Long numExisting = (Long) query.getSingleResult();
		if (numExisting < 1) {
			// TODO add to database (and sync with database to generate primary key)
			// Don't forget to initialize the patient aggregate with a treatment DAO 
			//next two lines added
			em.persist(prov);
			prov.setTreatmentDAO(this.treatmentDAO);
			//throw new IllegalStateException("Unimplemented");
			return prov.getId();
			
		} else {
			throw new ProviderExn("Insertion: Provider with provider id (" + npi + ") already exists.");
		}
		
	}

	@Override
	public Provider getProviderByNPI (long npi) throws ProviderExn {
		
		Provider prov = em.find(Provider.class, npi);
		if(prov==null){
			throw new ProviderExn("Provider not found: provider id = "+ npi);
		}
		else{ 
			prov.setTreatmentDAO(this.treatmentDAO);
			return prov;
		}
		
	}

	@Override
	public Provider getProvider(long proid) throws ProviderExn {
		
		Provider prov = em.find(Provider.class, proid);
		if(prov==null){
			throw new ProviderExn("Provider not found: primary key = "+ proid);
		}
		else{ 
			prov.setTreatmentDAO(this.treatmentDAO);
			return prov;
		}
		
		
	}

	/*@Override
	public void deleteProvider() throws ProviderExn {
		Query update = em.createNamedQuery("RemoveAllProvider");
		em.createQuery("delete from Treatment t").executeUpdate();
		update.executeUpdate();
	}*/

}
