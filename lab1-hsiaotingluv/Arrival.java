/**
 * @author Chen Hsiao Ting
 * @version CS2030S AY20/21 Semester 2
 */

class Arrival extends Event {
	private Customer customer;
	private Counter available;

	public Arrival(double time, Customer customer, Counter available) {
		super(time);
		this.customer = customer;
		this.available = available;
	}

	@Override
	public String toString() {
		return super.toString() + this.customer.getIdString("arrives");
	}

	@Override
	public Event[] simulate() {
		// Find the first available counter.
		int counter = this.available.findAvailableCounter();

		if (counter == -1) {
			// If no such counter can be found, the customer
			// should depart.
			return new Event[] { 
				new Departure(this.getTime(), this.customer)
			};
		} else {
			// Else, the customer should go the the first 
			// available counter and get served.
			return new Event[] { 
				new ServiceBegin(this.getTime(), counter, 
						this.customer, this.available)
			};
		}
	}
}
