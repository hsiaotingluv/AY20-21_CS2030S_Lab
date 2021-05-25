/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceBegin extends Event {
  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceBegin(double time, ServiceCounter counter, 
      Customer customer, Shop shop) {
    super(time);
    this.counter = counter;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service begin (by %s)", 
        this.customer, this.counter);
    return super.toString() + str; 
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.counter.toggleState();

    double endTime = this.customer.getServiceDuration(this.getTime());
    return new Event[] { 
      new ServiceEnd(endTime, this.counter,
          this.customer, this.shop)
    };
  }
}
