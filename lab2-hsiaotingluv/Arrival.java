/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Arrival extends Event {
  private Customer customer;
  private Shop shop;

  public Arrival(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s arrived %s", this.customer, this.shop);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // Find the first available counter.
    int counterId = this.shop.findAvailableCounter();

    if (counterId == -1) {
      // If no such counter can be found, check space in queue
      if (this.shop.queueFull()) {
        return new Event[] { 
          new Departure(this.getTime(), this.customer)
        };
      } else {
        return new Event[] {
          new JoinQueue(this.getTime(), this.customer, this.shop)
        };
      }

    } else {
      // Else, the customer should go the the first 
      // available counter and get served.
      return new Event[] { 
        new ServiceBegin(this.getTime(), counterId, 
            this.customer, this.shop)
      };
    }
  }
}
