/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Customer {
  private final int id;
  private final double serviceTime;

  public Customer(int id, double serviceTime) {
    this.id = id;
    this.serviceTime = serviceTime;
  }

  public double getServiceDuration(double arrivalTime) {
    return arrivalTime + this.serviceTime;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }

}
