/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class ServiceCounter implements Comparable<ServiceCounter> {
  private int counterId;
  private boolean available;
  private Queue<Customer> counterQueue;

  public ServiceCounter(int id, int queueSize) {
    this.counterId = id;
    this.available = true;
    this.counterQueue = new Queue<Customer>(queueSize);
  }

  public boolean getState() {
    return this.available;
  }

  public void toggleState() {
    this.available = !this.available;
  }

  public void enqueue(Customer customer) {
    this.counterQueue.enq(customer);
  }

  public Customer dequeue() {
    return (Customer) this.counterQueue.deq();
  }

  public boolean queueFull() {
    return this.counterQueue.isFull();
  }

  public boolean queueEmpty() {
    return this.counterQueue.isEmpty();
  }

  public int queueLength() {
    return this.counterQueue.length();
  }

  @Override
  public String toString() {
    return String.format("S%d %s", this.counterId, this.counterQueue);
  }

  @Override
  public int compareTo(ServiceCounter counter) {
    int currLen = this.queueLength();
    int prevLen = counter.queueLength();
    if (currLen > prevLen) {
      return 1; 
    } else if (currLen < prevLen) {
      return -1;
    } else {
      return 0; 
    } 
  }
}
