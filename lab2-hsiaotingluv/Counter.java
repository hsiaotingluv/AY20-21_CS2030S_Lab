/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

class Counter {
  private int counterId;
  private boolean available;

  public Counter(int id) {
    this.counterId = id;
    this.available = true;
  }

  public boolean getState() {
    return this.available;
  }

  public void toggleState() {
    this.available = !this.available;
  }

  @Override
  public String toString() {
    return String.format("S%d", this.counterId);
  }
}
