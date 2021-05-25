import java.util.Scanner;

class Shop extends Simulation {
	private Event[] initEvents;
	private int id = 0;

	public Shop(Scanner sc) {
		this.initEvents = new Event[sc.nextInt()];
		Counter available = new Counter(sc.nextInt());

		while (sc.hasNextDouble()) {
			double arrivalTime = sc.nextDouble();
			double serviceTime = sc.nextDouble();

			Customer newCust = new Customer(id, serviceTime);
			this.initEvents[id] = new Arrival(arrivalTime, newCust, available);

			id += 1;
		}

	}

	@Override
	public Event[] getInitialEvents() {
		return initEvents;
	}
}
