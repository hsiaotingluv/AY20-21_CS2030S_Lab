public class Box<T> {
  private final T content;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    } else {
      return new Box<T>(content);
    }
  }

  public static <T> Box<T> empty() {
    // EMPTY_BOX will always contains null only,
    // so it is type safe to cast it as Box<T>
    @SuppressWarnings("unchecked")
    Box<T> result = (Box<T>) EMPTY_BOX;
    return result;
  }

  public boolean isPresent() {
    return this.content != null;
  }

  public static <T> Box<T> ofNullable(T content) {
    if (content == null) {
      return empty();
    } else {
      return Box.of(content);
    }
  }

  public Box<T> filter(BooleanCondition<? super T> c) {
    if (this.content == null || !c.test(this.content)) {
      return Box.empty();
    } else {
      return this;
    }
  }

  // consumer super producer extends
  public <U> Box<U> map(Transformer<? super T, ? extends U> t) {
    if (this.content == null) {
      return Box.empty();
    } else {
      U result = (U) t.transform(this.content);
      if (result == null) {
        return Box.empty();
      } else {
        return Box.of(result);
      }
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Box) {
      // since object is an instance of Box, 
      // it it safe to cast it as a Box object
      @SuppressWarnings("unchecked")
      Box<?> box = (Box<?>) obj;

      if (this.content == null) {
        return box.content == null;
      } else {
        return this.content.equals(box.content);
      }
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    if (this.content == null) {
      return "[]";
    } else {
      return "[" + this.content + "]";
    }
  }

}
