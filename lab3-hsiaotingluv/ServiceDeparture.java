/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceDeparture extends Event {
  private ServiceCounter counter;
  private Customer customer;
  private Shop shop;

  public ServiceDeparture(double time, ServiceCounter counter, 
      Customer customer, Shop shop) {
    super(time);
    this.counter = counter;
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
    if (this.counter.queueEmpty()) {
      if (this.shop.queueEmpty()) {
        return new Event[] {};
      } else {
        Customer nextCust = this.shop.dequeue();
        return new Event[] {
          new ServiceBegin(this.getTime(), this.counter, 
              nextCust, this.shop)
        };
      }
    } else {
      Customer nextCust = this.counter.dequeue();
      if (this.shop.queueEmpty()) {
        return new Event[] {
          new ServiceBegin(this.getTime(), this.counter, 
              nextCust, this.shop)
        };
      } else {
        Customer nextShopQueueCust = this.shop.dequeue();
        return new Event[] {
          new JoinCounterQueue(this.getTime(), 
              nextShopQueueCust, this.counter),
          new ServiceBegin(this.getTime(), this.counter, 
              nextCust, this.shop)
        };
      }
    }
  }
}
