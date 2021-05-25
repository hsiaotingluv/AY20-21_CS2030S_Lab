public class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T t) {
    if (t == null) {
      return Box.empty();
    } else {
      return Box.of(t);
    }
  }
}
