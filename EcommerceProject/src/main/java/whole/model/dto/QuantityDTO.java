package whole.model.dto;

public class QuantityDTO {
	private int quantity;

	public QuantityDTO() {
		quantity = 0;
	}
	
	public QuantityDTO(int quantity) throws IllegalArgumentException{
		if(quantity < 0) {
			throw new IllegalArgumentException("Cant set quantity to negative. Please try again with valid input.");
		}
		else {
			this.quantity = quantity;
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws IllegalArgumentException{
		if(quantity < 0) {
			throw new IllegalArgumentException("Cant set quantity to negative. Please try again with valid input.");
		}
		else {
			this.quantity = quantity;
		}
	}
	
	
}
