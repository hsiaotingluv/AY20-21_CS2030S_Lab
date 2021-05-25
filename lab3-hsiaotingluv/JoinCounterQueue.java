/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class JoinCounterQueue extends Event {
  private Customer customer;
  private ServiceCounter counter;

  public JoinCounterQueue(double time, Customer customer, ServiceCounter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = String.format(": %s joined counter queue (at %s)", this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.enqueue(this.customer);
    return new Event[] {};
  }
}
