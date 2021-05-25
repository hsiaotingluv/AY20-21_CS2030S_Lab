package cs2030s.fp;
import java.util.NoSuchElementException;

/**
 * CS2030S Chen Hsiao Ting LAB10F
 * AY20/21 Semester 2
 */

public abstract class Maybe<T> {
  private final static Maybe<?> NONE = new None();

  private Maybe() {}

  private static final class None extends Maybe<Object> {
    
    private None() {}

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof None;
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException("No such element exception!");
    }

    @Override
    public Object orElse(Object t) {
      return t;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> p) {
      return p.produce();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> c) {
      return Maybe.none();

    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> t) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> t) {
      return Maybe.none();
    }
  }

  private static final class Some<T> extends Maybe<T> {
    private T content;

    private Some(T t) {
      this.content = t;
    }

    @Override
    public String toString() {
      return this.content.toString();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Some) {
        @SuppressWarnings("unchecked")
        Some<T> some = (Some<T>) obj;
        if (this.content == null) {
          return some.content == null;
        } else {
          return this.content.equals(some.content);
        } 
      } return false;
    }       


    @Override
    protected T get() {
      return this.content;
    }

    @Override
    public T orElse(T t) {
      return this.content;
    }

    @Override
    public T orElseGet(Producer<? extends T> p) {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> c) {
      if (this.content == null) {
        return this;
      } else if (!c.test(this.content)) {
        return Maybe.none();
      } else {
        return this;
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {
      U result = t.transform(this.content);
      if (result == null) {
        return Maybe.some(result);
      } else {
        return Maybe.of(result);
      }
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
      @SuppressWarnings("unchecked")
      Maybe<U> maybe = (Maybe<U>) t.transform(this.get());
      return maybe;
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> result = (Maybe<T>) NONE;
    return result;
  }

  public static <T> Some<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T content) {
    if (content == null) {
      return Maybe.none();
    } else {
      return new Some<T>(content);
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> c);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t);

  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> p);

}
