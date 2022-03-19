package mad.backend.database.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medication {
	private final String numeMedicatie;
	private final String dozaMedicatie;

	public Medication(@JsonProperty("numeMedicatie") final String numeMedicatie,
					  @JsonProperty("dozaMedicatie") final String dozaMedicatie) {
		this.numeMedicatie = numeMedicatie;
		this.dozaMedicatie = dozaMedicatie;
	}

	public String getNumeMedicatie() {
		return numeMedicatie;
	}

	public String getDozaMedicatie() {
		return dozaMedicatie;
	}
}
