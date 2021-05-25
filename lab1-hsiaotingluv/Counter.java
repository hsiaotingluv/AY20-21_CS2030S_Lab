/**
 * @author Chen Hsiao Ting
 * @version CS2030S AY20/21 Semester 2
 */

class Counter {
	private boolean[] available; 

	public Counter(int numOfCounters) {
		this.available = new boolean[numOfCounters];

		for (int i = 0; i < numOfCounters; i++) {
			available[i] = true;
		}
	}

	public int findAvailableCounter() {
		int counter = -1;
		for (int i = 0; i < this.available.length; i++) {
			if (this.available[i]) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	public void updateServiceBegin(int counterId) {
		this.available[counterId] = false;
	}

	public void updateServiceEnd(int counterId) {
		this.available[counterId] = true;
	}
}
