package model.entities;

/**
 * 
 *  @author : Pham Tuan Ngoc
 *	
 * this class define the order
 */

public class Order {
    private String pcode;
    private String ccode;
    private int quantity;
	/**
	 * @return the pcode
	 */
	public String getPcode() {
		return pcode;
	}
	/**
	 * @param pcode the pcode to set
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	/**
	 * @return the ccode
	 */
	public String getCcode() {
		return ccode;
	}
	/**
	 * @param ccode the ccode to set
	 */
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
