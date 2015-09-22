package com.anemortalkid.initiative.tracker;

/**
 * Initiative data holds a label and a value for the initiative. Has to be
 * {@link Comparable} to be sorted
 * 
 * @author JMonterrubio
 *
 */
public class InitiativeData implements Comparable<InitiativeData> {

	private String label;
	private int value;

	/**
	 * Constructs a new InitiativeData
	 * 
	 * @param label
	 *            the text for the initiative data
	 * @param value
	 *            the value of the initiative
	 */
	public InitiativeData(String label, int value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "InitiativeData [label=" + label + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InitiativeData other = (InitiativeData) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(InitiativeData o) {
		return Integer.valueOf(this.value).compareTo(o.value);
	}
}
