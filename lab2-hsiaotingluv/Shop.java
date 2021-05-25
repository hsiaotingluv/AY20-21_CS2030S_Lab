/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Shop {
  private Counter[] counters;
  private Queue queue;

  public Shop(int numOfCounters, int sizeOfQueue) {
    this.counters = new Counter[numOfCounters];
    this.queue = new Queue(sizeOfQueue);

    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }
  }

  public int findAvailableCounter() {
    int result = -1;

    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].getState()) {
        result = i;
        break;
      }
    }

    return result;
  }

  public void toggleCounterState(int id) {
    this.counters[id].toggleState();
  }

  public void enqueue(Customer customer) {
    this.queue.enq(customer);
  }

  public Customer dequeue() {
    return (Customer) this.queue.deq();
  }

  public boolean queueFull() {
    return this.queue.isFull();
  }

  public boolean queueEmpty() {
    return this.queue.isEmpty();
  }

  public String toString() {
    return this.queue.toString();
  }
}
