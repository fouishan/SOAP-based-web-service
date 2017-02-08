package edu.stevens.cs548.clinic.domain;

public interface IProviderDAO {

	public static class ProviderExn extends Exception {
		private static final long serialVersionUID = 1L;
		public ProviderExn (String msg) {
			super(msg);
		}
	}
	
	public long addProvider (Provider prov) throws ProviderExn;
	
	public Provider getProvider (long proid) throws ProviderExn;
	
	public Provider getProviderByNPI(long npi) throws ProviderExn;
	
	//public void deleteProvider() throws ProviderExn;
}
