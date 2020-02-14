package model;

public enum DocumentType {

	CITIZENSHIP_CARD("CC"), IDENTITY_CARD("ID"), CIVIL_REGISTRATION("CR"), PASSPORT("P"), FOREIGNER_ID("FID");

	private DocumentType(String abbreviation) {

		this.abbreviation= abbreviation;
	}

	private String abbreviation;
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation= abbreviation;
	}

}
