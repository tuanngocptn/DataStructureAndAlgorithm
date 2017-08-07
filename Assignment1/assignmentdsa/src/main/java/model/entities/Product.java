package model.entities;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this is class define the Product
 */
public class Product {
    private String pcode;
    private String proName;
    private int quantity;
    private int saled;
    private double price;
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
	 * @return the proName
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * @param proName the proName to set
	 */
	public void setProName(String proName) {
		this.proName = proName;
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
	/**
	 * @return the saled
	 */
	public int getSaled() {
		return saled;
	}
	/**
	 * @param saled the saled to set
	 */
	public void setSaled(int saled) {
		this.saled = saled;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
