/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Shop {
  private Array<ServiceCounter> counters;
  private Queue<Customer> shopQueue;
  private int numOfCounters;
  private int sizeOfCounterQueue;

  public Shop(int numOfCounters, int sizeOfCounterQueue, int sizeOfShopQueue) {
    this.numOfCounters = numOfCounters;
    this.sizeOfCounterQueue = sizeOfCounterQueue;
    this.counters = new Array<ServiceCounter>(numOfCounters);
    this.shopQueue = new Queue<Customer>(sizeOfShopQueue);

    for (int i = 0; i < numOfCounters; i++) {
      ServiceCounter counter = new ServiceCounter(i, sizeOfCounterQueue);
      this.counters.set(i, counter);
    }
  }

  public ServiceCounter findAvailableCounter() {
    for (int i = 0; i < numOfCounters; i++) {
      ServiceCounter counter = this.counters.get(i);
      if (counter.getState()) {
        return counter;
      }
    }
    return null;
  }

  public ServiceCounter findMinimumQueue() {
    ServiceCounter counter = this.counters.min();
    if (counter.queueFull()) {
      return null;
    } else {
      return counter;
    }
  }
  
  public void enqueue(Customer customer) {
    this.shopQueue.enq(customer);
  }

  public Customer dequeue() {
    return (Customer) this.shopQueue.deq();
  }

  public boolean queueFull() {
    return this.shopQueue.isFull();
  }

  public boolean queueEmpty() {
    return this.shopQueue.isEmpty();
  }

  public int queueLength() {
    return this.shopQueue.length();
  }

  @Override
  public String toString() {
    return this.shopQueue.toString();
  }
}
