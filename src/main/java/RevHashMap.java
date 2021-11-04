public class RevHashMap<E, T> {

	private E[] keys;
	private T[] values;
	private int hashMapCapacity;
	private int hashMapSize;
	private double loadFactor;

	//Default constructor
	public RevHashMap () {
		this.hashMapCapacity = 16;
		this.keys = (E[]) new Object[16];
		this.values = (T[]) new Object[16];
		this.hashMapSize = 0;
		this.loadFactor = 0.75;
	}

	//Make a new hashmap with a given size
	public RevHashMap (int initialCapacity) {
		this.hashMapCapacity = initialCapacity;
		this.keys = (E[]) new Object[initialCapacity];
		this.values = (T[]) new Object[initialCapacity];
		this.hashMapSize = 0;
		this.loadFactor = 0.75;
	}

	public RevHashMap (int initialCapacity, double initialFactor) {
		this.hashMapCapacity = initialCapacity;
		this.keys = (E[]) new Object[initialCapacity];
		this.values = (T[]) new Object[initialCapacity];
		this.hashMapSize = 0;
		this.loadFactor = initialFactor;
	}

	public int getHashMapSize() {
		return this.hashMapSize;
	}

	public int getHashMapCapacity() {
		return this.hashMapCapacity;
	}

	public double getLoadFactor() {
		return this.loadFactor;
	}

	public void setLoadFactor(double factor) {
		this.loadFactor = factor;
	}

	private void increaseCapacity(int capacityFactor) {
		int oldCapacity = this.hashMapCapacity;
		this.hashMapCapacity *= capacityFactor;
		E[] newKeys = (E[]) new Object[this.hashMapCapacity];
		T[] newValues = (T[]) new Object[this.hashMapCapacity];

		//Re-hash all of our old key-value pairs into our new hashmap
		for (int i=0; i<oldCapacity; i++) {
			if (this.keys[i] != null) {
				Boolean hasPut = false;
				int keyPosition = Math.floorMod(this.keys[i].hashCode(), this.getHashMapCapacity());
				while (!hasPut) {
					if (newKeys[keyPosition] == null) {
						newKeys[keyPosition] = this.keys[i];
						newValues[keyPosition] = this.values[i];
						hasPut = true;
					} else {
						keyPosition = Math.floorMod((keyPosition + 1), this.getHashMapCapacity());
					}
				}
			}
		}

		//Set the hashmap to the new key-value pairs
		this.keys = newKeys;
		this.values = newValues;
	}

	public void put(E key, T value) {
		//If key already exists, don't make a new entry
		if (this.keyExists(key)) {
			return;
		}

		//If we're over our load factor, reallocate memory
		double currentLoad = ((double) hashMapSize) / ((double) hashMapCapacity);
		if (currentLoad > this.loadFactor) {
			this.increaseCapacity(2);
		}

		//Place it in our hash map
		Boolean hasPut = false;
		int keyPosition = Math.floorMod(key.hashCode(), this.getHashMapCapacity());
		while (!hasPut) {
			if (keys[keyPosition] == null) {
				//If hash function sends us to an empty key index, fill it
				this.keys[keyPosition] = key;
				this.values[keyPosition] = value;
				hasPut = true;
				this.hashMapSize++;
			} else {
				//Otherwise, attempt to fill the next index
				keyPosition = Math.floorMod((keyPosition + 1), this.getHashMapCapacity());
			}
		}
	}

	public T getValueFromKey(E key) {
		//Use the hash function to get which index the key object would be stored at
		int keyPosition = Math.floorMod(key.hashCode(), this.getHashMapCapacity());
		for (int i=0; i<hashMapCapacity; i++) {
			if (keys[keyPosition].equals(key)) {
				return values[keyPosition];
			}
			//If there were multiple keys attempting to hash to the same location, we might find our key later
			keyPosition = Math.floorMod((keyPosition + 1), this.getHashMapCapacity());
		}
		//This will occur if the key doesn't exist in our hash map
		return null;
	}

	public void setValueAtKey(E key, T value) {
		//Find the key position and change its value
		int keyPosition = Math.floorMod(key.hashCode(), this.getHashMapCapacity());
		for (int i=0; i<hashMapCapacity; i++) {
			if (keys[keyPosition].equals(key)) {
				values[keyPosition] = value;
				return;
			}
			keyPosition = Math.floorMod((keyPosition + 1), this.getHashMapCapacity());
		}
		return;
	}

	public Boolean keyExists(E key) {
		//Check if the given key is included in the hashmap
		int keyPosition = Math.floorMod(key.hashCode(), this.getHashMapCapacity());
		for (int i=0; i<hashMapCapacity; i++) {
			if (this.keys[keyPosition] != null) {
				if (this.keys[i].equals(key)) {
					return true;
				}
			}
			keyPosition = Math.floorMod((keyPosition + 1), this.getHashMapCapacity());
		}
		return false;
/*		for (int i=0; i<hashMapCapacity; i++) {
			if (this.keys[i] != null) {
				if (this.keys[i].equals(key)) {
					return true;
				}
			}
		}
		return false;*/
	}
}