package mad.backend.endpoints.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicationAssignment {
	private final String numeMedicatie;
	private final String dozaMedicatie;
	private final String frecventaAdministrare;

	public MedicationAssignment(@JsonProperty("numeMedicatie") final String numeMedicatie,
								@JsonProperty("dozaMedicatie") final String dozaMedicatie,
								@JsonProperty("frecventaAdministrare") final String frecventaAdministrare) {
		this.numeMedicatie = numeMedicatie;
		this.dozaMedicatie = dozaMedicatie;
		this.frecventaAdministrare = frecventaAdministrare;
	}

	public String getNumeMedicatie() {
		return numeMedicatie;
	}

	public String getDozaMedicatie() {
		return dozaMedicatie;
	}

	public String getFrecventaAdministrare() {
		return frecventaAdministrare;
	}
}
