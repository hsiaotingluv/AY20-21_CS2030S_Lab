package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author CS2030S Chen Hsiao Ting LAB10F
 * AY20/21 Semester 2
 * 
 * public class InfiniteList that represents an infinite list of elements 
 * of type T.
 */
public class InfiniteList<T> {

  /**
   * private attributes for InfiniteList.
   */
  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  private static InfiniteList<?> EMPTY = new EmptyList(); 

  /** 
   * private constructor to initialize the InfiniteList.
   */
  private InfiniteList() { 
    head = null; 
    tail = null;
  }

  /** 
   * private constructor to initialize the InfiniteList.
   *
   * @param head of type T
   * @param tail producer
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /** 
   * private constructor to initialize the InfiniteList.
   *
   * @param head of type Lazy
   * @param tail of type Lazy
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * static generate method that create an InfiniteList.
   *
   * @param <T> the type of InfiniteList
   * @param producer producer
   * @return InfiniteList
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> generate(producer)));
  }

  /**
   * static iterate method that create an InfiniteList.
   *
   * @param <T> the type of InfiniteList
   * @param seed of type T
   * @param next transformer
   * @return InfiniteList
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () -> iterate(next.transform(seed), next));
  }

  /**
   * head method that produces the head of the infinite list.
   *
   * @return T type of element
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * tail method that produces the tail of the infinite list.
   *
   * @return InfiniteList
   */
  public InfiniteList<T> tail() {
    if (this.head.get().equals(Maybe.none())) {
      return this.tail.get().isEmpty() ? empty() : this.tail.get().tail();
    }
    return this.tail.get();
  }

  /**
   * map method that lazily applies the given transformation to each element
   * in the list and returns the resulting InfiniteList.
   *
   * @param <R> the type of returned InfiniteList
   * @param mapper transformer
   * @return InfiniteList
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))), 
        Lazy.of(() -> this.tail().map(mapper)));
  }

  /**
   * filter method to filter out elements in the list that fail a given BooleanCondition
   * filter should mark any missing elements as Maybe.none() instead of null
   * the resulting lazily filtered InfiniteList is returned.
   *
   * @param predicate BooleanCondition
   * @return InfiniteList
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    if (this.isEmpty()) {
      return this;
    } 
    return new InfiniteList<T>(Lazy.of(() -> predicate.test(this.head())
          ? Maybe.some(this.head())
          : Maybe.none()),
        Lazy.of(() -> this.tail().filter(predicate)));
  }

  /**
   * empty method that returns an empty list.
   *
   * @param <T> type of InfiniteList
   * @return InfiniteList
   */
  public static <T> InfiniteList<T> empty() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) EMPTY;
    return temp;
  }

  /**
   * limit method that takes in a value n and truncate the InfiniteList to a finite
   * list with at most n elements
   * EmptyList is used to handle special cases of the list being empty and to mark
   * the end of the list
   * limit method does not count elements that are filtered our by filter, if any.
   *
   * @param n long
   * @return InfiniteList
   */
  public InfiniteList<T> limit(long n) {
    return n <= 0
      ? empty()
      : new InfiniteList<T>(this.head(), () -> this.tail().limit(n - 1));
  }

  /**
   * method takes in a BooleanCondition, and truncates the list as soon as it
   * finds an element that evaluates the condition to false
   * method should ignore elements that have filtered out by filter.
   *
   * @param predicate BooleanCondition
   * @return InfiniteList
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Boolean> condition = Lazy.of(() -> this.head()).filter(predicate);
    return new InfiniteList<T>(
        Lazy.of(() -> condition.get()
          ? Maybe.some(this.head())
          : Maybe.none()),
        Lazy.of(() -> condition.get() && predicate.test(this.tail().head())
          ? this.tail().helper(predicate)
          : empty())
        );
  }

  /**
   * helper method for takeWhile.
   *
   * @param predicate BooleanCondition
   * @return InfiniteList
   */
  public InfiniteList<T> helper(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(
        this.head(), () -> predicate.test(this.tail().head())
        ? this.tail().helper(predicate)
        : empty());
  }

  /**
   * method that tests if the list is an instance of EmptyList
   * calling isEmpty on an instance of InfiniteList with 0 elements should still
   * return true.
   *
   * @return boolean
   */
  public boolean isEmpty() {
    return false; 
  }

  /**
   * method that takes in an identity and a combiner and returns a type U.
   *
   * @param <U> return type
   * @param identity of type U
   * @param accumulator Combiner
   * @return U
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return accumulator.combine(this.tail().reduce(identity, accumulator), this.head());
  }

  /**
   * count the number of elements in a finite list.
   *
   * @return long
   */
  public long count() {
    long value = this.head.get().equals(Maybe.none()) ? 0L : 1L;
    return this.tail.get().count() + value;
  }

  /**
   * method that collects the elements in the InfiniteList into a java.util.List.
   *
   * @return List
   */
  public List<T> toList() {
    List<T> result = new ArrayList<>();

    if (this instanceof EmptyList) {
      return result;
    } else {
      result.add(this.head());
      result.addAll(this.tail().toList());

      return result;
    }
  }

  /**
   * method that returns the String representation of InfiniteList.
   *
   * @return String
   */
  public String toString() {
    String front = "";
    if (this.head.toString() == "?") {
      front = this.head.toString();
    } else if (this.head.get().equals(Maybe.none())) {
      front = this.head.get().toString();
    } else {
      front = "[" + this.head.toString() + "]";
    }

    return "[" + front + " " + this.tail + "]";
  }

  /** 
   * nested private static class EmptyList that extends InfiniteList.
   */
  private static class EmptyList extends InfiniteList<Object> {
    EmptyList() {
      super();
    }

    @Override
    public Object head() throws NoSuchElementException {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() throws NoSuchElementException {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return empty();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return empty();
    } 

    @Override
    public InfiniteList<Object> limit(long n) {
      return empty();
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return empty();
    }

    @Override
    public InfiniteList<Object> helper(BooleanCondition<? super Object> predicate) {
      return empty();
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0L;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    @Override
    public String toString() {
      return "";
    }
  }
}
