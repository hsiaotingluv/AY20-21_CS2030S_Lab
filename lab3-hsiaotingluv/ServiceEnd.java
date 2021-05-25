/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceEnd extends Event {
  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceEnd(double time, ServiceCounter counter,
      Customer customer, Shop shop) {
    super(time);
    this.counter = counter;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service done (by %s)", 
        this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.counter.toggleState();

    return new Event[] { 
      new ServiceDeparture(this.getTime(), this.counter, 
          this.customer, this.shop)
    };
  }
}
