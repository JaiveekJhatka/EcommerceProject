package whole.model.dto;

public class SignupResponse {
	private long userId;

	public SignupResponse(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
