public interface PersonIF {

	int hashCode();

	boolean equals(Object obj);

	String toString();
	String toString(int indent);

	void neuesKind(PersonIF p);
}