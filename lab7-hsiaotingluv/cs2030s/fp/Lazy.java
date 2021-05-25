package cs2030s.fp;

/**
 * @author CS2030S Chen Hsiao Ting LAB10F
 * AY20/21 Semester 2
 */

public class Lazy<T> {

  /**
   * Lazy has a Producer that produce content of type T when called
   */
  private Producer<T> producer;

  /**
   * Lazy has value of type Maybe
   */
  private Maybe<T> value;

  /**
   * A private constructor to initialize the Lazy
   *
   * @param v value of Lazy
   */
  private Lazy(Maybe<T> v) {
    this.value = v;
  }

  /**
   * A private constructor to initialize the Lazy
   *
   * @param s producer
   */
  private Lazy(Producer<T> s) {
    this.producer = s;
    this.value = Maybe.none();
  }

  /**
   * factory method that initializes the Lazy object with the given value
   *
   * @param <T> type of Lazy
   * @param v value
   * @return Lazy
   */
  public static <T> Lazy<T> of(T v) {
    Maybe<T> result = Maybe.some(v);
    return new Lazy<>(result);
  }

  /**
   * factory method that takes in a producer that produces the value when needed
   * 
   * @param <T> the type of Lazy
   * @param s producer
   * @return Lazy
   */
  public static <T> Lazy<T> of(Producer<T> s) {
    return new Lazy<>(s);
  }

  /**
   * method is called when the value is needed, if value is already available, 
   * return that value, otherwise, compute the value and return it
   * The computation should only be done once for the same value
   *
   * @return value
   */
  public T get() {
    T content = this.value.orElseGet(this.producer);
    this.value = Maybe.some(content);
    return content;
  }

  /**
   *  map the content of Lazy using the transformer, should not evaluate anything
   *  until get() is called, and should only be evaluated once, once evaluated,
   *  result should be cached (also called memoized), so that function must not
   *  be called again
   *
   *  @param <U> the type of transformed Lazy
   *  @param t transformer
   *  @return a transformed Lazy type
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> t) {
    return new Lazy<>(() -> t.transform(this.get()));
  }

  /**
   *  map the content of Lazy using the transformer, should not evaluate anything
   *  until get() is called, and should only be evaluated once, once evaluated,
   *  result should be cached (also called memoized), so that function must not
   *  be called again
   *
   *  @param <U> the type of transformed Lazy
   *  @param t transformer
   *  @return a transformed Lazy type
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) {
    return new Lazy<>(() -> t.transform(this.get()).get());
  }

  /**
   * filter method that takes in a BooleanCondition and lazily tests if the value
   * passes the test or not, the BooleanCondition must be executed at most once
   *
   * @param c BooleanCondition
   * @return Lazy
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> c) {
    return new Lazy<>(() -> c.test(this.get()));
  }

  /**
   * combine method that combines two value, of type S and T respectively, into a result
   * of type R
   *
   * @param <R> the type of returned Lazy
   * @param <S> the type of input Lazy
   * @param lazy input Lazy
   * @param c Combiner
   * @return Lazy
   */
  public <R,S> Lazy<R> combine(Lazy<S> lazy, Combiner<? super T, ? super S, ? extends R> c) {
    return new Lazy<R>(() -> c.combine(this.get(), lazy.get()));
  }

  /**
   * returns "?" if the value is not yet available, returns the string representation
   * of the value otherwise
   *
   * @return String representation of value
   */
  @Override
  public String toString() {
    // value is either a Some or None, if it is None, return "?", else return the content
    return this.value.map(t -> String.valueOf(t)).orElse("?");
  }

  /**
   * overrides the equal method in the Object class, equals is an eager operation that causes
   * the values to be evaluated (if not already cached), equals should return true only both objects
   * being compared are Lazy and the value contains within are equals according to their equals() method
   *
   * @param obj Object
   * @return boolean
   */
  @Override
  public boolean equals(Object obj) { 
    if (obj instanceof Lazy) {
      @SuppressWarnings("unchecked")
      Lazy<T> lazy = (Lazy<T>) obj;
      return lazy.get().equals(this.get());
    } else {
      return false;
    }
  }

}
