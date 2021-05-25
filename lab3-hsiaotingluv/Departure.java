/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Departure extends Event {
  private Customer customer;

  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // do nothing
    return new Event[] {};
  }
}
