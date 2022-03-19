package mad.backend.endpoints.datamodel;

public class UserWithoutCredentials {
	private final String cnp;
	private final String numeUtilizator;
	private final String nume;
	private final String prenume;
	private final String email;
	private final String telefon;
	private final String tipUtilizator;

	public UserWithoutCredentials(final String cnp,
								  final String numeUtilizator,
								  final String nume,
								  final String prenume,
								  final String email,
								  final String telefon,
								  final String tipUtilizator) {
		this.cnp = cnp;
		this.numeUtilizator = numeUtilizator;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.telefon = telefon;
		this.tipUtilizator = tipUtilizator;
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

	public String getTipUtilizator() {
		return tipUtilizator;
	}
}
