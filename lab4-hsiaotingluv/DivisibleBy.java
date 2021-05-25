public class DivisibleBy implements BooleanCondition<Integer> {
  private int num;

  public DivisibleBy(int num) {
    this.num = num;
  }

  @Override
  public boolean test(Integer integer) {
    return integer % this.num == 0;
  }
}
