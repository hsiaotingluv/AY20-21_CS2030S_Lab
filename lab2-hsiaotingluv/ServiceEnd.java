/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceEnd extends Event {
  private int counterId;
  private Customer customer;
  private Shop shop;

  public ServiceEnd(double time, int counterId,
      Customer customer, Shop shop) {
    super(time);
    this.counterId = counterId;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s service done (by S%d)", 
        this.customer, this.counterId);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.shop.toggleCounterState(this.counterId);

    return new Event[] { 
      new ServiceDeparture(this.getTime(), this.counterId, 
          this.customer, this.shop)
    };
  }
}
