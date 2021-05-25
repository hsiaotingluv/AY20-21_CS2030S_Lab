/**
 * @author Chen Hsiao Ting
 * @version CS2030S AY20/21 Semester 2
 */

class Departure extends Event {
	Customer customer;

	public Departure(double time, Customer customer) {
		super(time);
		this.customer = customer;
	}

	@Override
	public String toString() {
		return super.toString() + this.customer.getIdString("departed");
	}

	@Override
	public Event[] simulate() {
		// do nothing
		return new Event[] {};
	}
}
