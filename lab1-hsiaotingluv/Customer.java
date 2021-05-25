/**
 * @author Chen Hsiao Ting
 * @version CS2030S AY20/21 Semester 2
 */

class Customer {
	private final int id;
	private final double serviceTime;

	public Customer(int id, double serviceTime) {
		this.id = id;
		this.serviceTime = serviceTime;
	}
	
	public double getServiceDuration(double arrivalTime) {
		return arrivalTime + this.serviceTime;
	}

	public String getIdString(String action) {
		return String.format(": Customer %d %s", this.id, action);
	}

}
