/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class JoinShopQueue extends Event {
  private Customer customer;
  private Shop shop;

  public JoinShopQueue(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = String.format(": %s joined shop queue %s", this.customer, this.shop);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.shop.enqueue(this.customer);
    return new Event[] {};
  }
}
