public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private final int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  public Integer transform(Object t) {
    Integer result = t.hashCode();
    String str = result.toString();
    str = str.substring(str.length() - this.k);
    return Integer.parseInt(str);
  }
  
}
