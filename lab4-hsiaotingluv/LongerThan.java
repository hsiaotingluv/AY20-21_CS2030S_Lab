public class LongerThan implements BooleanCondition<String> {
	private int limit;

	public LongerThan(int limit) {
		this.limit = limit;
	}
	
	@Override
	public boolean test(String string) {
		return string.length() > this.limit;
	}
}
