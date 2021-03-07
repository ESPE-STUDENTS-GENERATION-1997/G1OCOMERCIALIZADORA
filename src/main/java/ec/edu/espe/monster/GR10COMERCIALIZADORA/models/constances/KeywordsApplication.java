package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances;

public enum KeywordsApplication {
	ENTITY_STATE_USER_REGISTER("E001"),
	ENTITY_STATE_USER_ACTIVE("E002"),
	ENTITY_STATE_USER_BLOQUED("E003"),
	ENTITY_STATE_USER_FORGOT_PASS("E004"),
	ENTITY_STATE_USER_PASS_TEMPORAL("E005");
	
	private String code;

	private KeywordsApplication(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	
}
