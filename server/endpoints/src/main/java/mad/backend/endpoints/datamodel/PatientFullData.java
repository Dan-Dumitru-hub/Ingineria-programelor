package mad.backend.endpoints.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PatientFullData {
	private final String cnp;
	private final String numeUtilizator;
	private final String nume;
	private final String prenume;
	private final String email;
	private final String telefon;
	private final List<MedicationAssignment> medicatii;
	private final String alimentatie;

	public PatientFullData(@JsonProperty("cnp") final String cnp,
						   @JsonProperty("numeUtilizator") final String numeUtilizator,
						   @JsonProperty("nume") final String nume,
						   @JsonProperty("prenume") final String prenume,
						   @JsonProperty("email") final String email,
						   @JsonProperty("telefon") final String telefon,
						   @JsonProperty("medicatii") final List<MedicationAssignment> medicatii,
						   @JsonProperty("alimentatie") final String alimentatie) {
		this.cnp = cnp;
		this.numeUtilizator = numeUtilizator;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.telefon = telefon;
		this.medicatii = medicatii;
		this.alimentatie = alimentatie;
	}

	public String getCnp() {
		return cnp;
	}

	public String getNumeUtilizator() {
		return numeUtilizator;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefon() {
		return telefon;
	}

	public List<MedicationAssignment> getMedicatii() {
		return medicatii;
	}

	public String getAlimentatie() {
		return alimentatie;
	}
}
