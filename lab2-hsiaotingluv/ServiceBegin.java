/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceBegin extends Event {
  private int counterId;
  private Customer customer;
  private Shop shop;

  public ServiceBegin(double time, int counterId, 
      Customer customer, Shop shop) {
    super(time);
    this.counterId = counterId;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service begin (by S%d)", 
        this.customer, this.counterId);
    return super.toString() + str; 
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.shop.toggleCounterState(this.counterId);

    double endTime = this.customer.getServiceDuration(this.getTime());
    return new Event[] { 
      new ServiceEnd(endTime, this.counterId,
          this.customer, this.shop)
    };
  }
}
