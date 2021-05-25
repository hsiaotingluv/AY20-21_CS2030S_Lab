/**
 * @author Chen Hsiao Ting, LAB10F
 * @version CS2030S AY20/21 Semester 2
 */

// Array<T> takes in only a subtype of Comparable<T> as its type argument
public class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast `Object[]` to `T[]`.
    @SuppressWarnings("unchecked")
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {

    if (array.length == 0) {
      return null;
    } else {
      T result = this.array[0];

      for (T item : this.array) {
        if (item.compareTo(result) < 0) {
          result = item;
        }
      }

      return result;
    }
  }
}

