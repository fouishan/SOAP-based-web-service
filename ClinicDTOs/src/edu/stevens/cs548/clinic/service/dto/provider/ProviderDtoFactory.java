package edu.stevens.cs548.clinic.service.dto.provider;


import edu.stevens.cs548.clinic.domain.Provider;

public class ProviderDtoFactory {

	ObjectFactory factory;
	 public ProviderDtoFactory(){
		 factory = new ObjectFactory();
	 }
	 
	 
	 
	 public ProviderDto createProviderDto(){
		 return factory.createProviderDto();
	 }
	  public ProviderDto createProviderDto(Provider p){
		  
		  ProviderDto d= factory.createProviderDto();
		  d.setId(p.getId());
		  d.setName(p.getName());
		  d.setProviderId(p.getNpi());
		  d.setSpecialization(p.getSpecialization());
		  return d;
		  
		  
	  }
}
