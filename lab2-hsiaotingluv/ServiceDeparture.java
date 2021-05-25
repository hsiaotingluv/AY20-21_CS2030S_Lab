/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceDeparture extends Event {
  private int counterId;
  private Customer customer;
  private Shop shop;

  public ServiceDeparture(double time, int counterId, 
      Customer customer, Shop shop) {
    super(time);
    this.counterId = counterId;
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    if (this.shop.queueEmpty()) {
      return new Event[] {};
    } else {
      Customer nextCust = this.shop.dequeue();
      return new Event[] {
        new ServiceBegin(this.getTime(), this.counterId, 
            nextCust, this.shop)
      };
    }
  }
}
