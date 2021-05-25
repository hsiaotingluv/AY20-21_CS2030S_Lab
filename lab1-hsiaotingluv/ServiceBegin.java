class ServiceBegin extends Event {
	private int counterId;
	private Customer customer;
        private Counter available;

	public ServiceBegin(double time, int counterId, 
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
				this.customer.getIdString("service begin"), this.counterId);
		return super.toString() + str; 
	}

	@Override
	public Event[] simulate() {
		// Mark the counter is unavailable, then schedule
		// a service-end event at the current time + service time.
		this.available.updateServiceBegin(counterId);
		double endTime = customer.getServiceDuration(this.getTime());
		return new Event[] { 
			new ServiceEnd(endTime, this.counterId,
				       this.customer, this.available)
		};
	}
}
