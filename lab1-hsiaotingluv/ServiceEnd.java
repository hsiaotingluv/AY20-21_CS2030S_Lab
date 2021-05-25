class ServiceEnd extends Event {
	int counterId;
	private Customer customer;
	private Counter available;

	public ServiceEnd(double time, int counterId,
			Customer customer, Counter available) {
		super(time);
		this.counterId = counterId;
		this.customer = customer;
		this.available = available;
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format("%s (by Counter %d)", 
				this.customer.getIdString("service done"), this.counterId);
		return super.toString() + str;
	}

	@Override
	public Event[] simulate() {
		// Mark the counter is available, then schedule
		// a departure event at the current time.
		this.available.updateServiceEnd(counterId);
		return new Event[] { 
			new Departure(this.getTime(), this.customer),
		};
	}
}
