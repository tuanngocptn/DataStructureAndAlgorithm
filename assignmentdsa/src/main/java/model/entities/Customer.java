package model.entities;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this is class define Customer
 */
public class Customer {
    private String ccode;
    private String cusName;
    private String phone ;
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
	 * @return the cusName
	 */
	public String getCusName() {
		return cusName;
	}
	/**
	 * @param cusName the cusName to set
	 */
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}
