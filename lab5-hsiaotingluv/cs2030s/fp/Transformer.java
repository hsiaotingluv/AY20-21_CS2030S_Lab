/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Chen Hsiao Ting Lab
 */

package cs2030s.fp;

public interface Transformer<T, U> {

	public abstract U transform(T t);

}
