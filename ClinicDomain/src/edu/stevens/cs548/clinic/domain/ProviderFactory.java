package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;

public class ProviderFactory implements IProviderFactory {

	@Override
	public Provider createProvider(long npi, String name, String specialization) throws ProviderExn {
		Provider prov = new Provider();
		prov.setNpi(npi);
		prov.setName(name);
		prov.setSpecialization(specialization);
		return prov;
	}

}
