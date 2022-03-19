package mad.backend.database.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final String cnp;
	private final String numeUtilizator;
	private final String nume;
	private final String prenume;
	private final String email;
	private final String telefon;
	private final String tipUtilizator;
	private final String parola;
	private final String salt;

	public User(@JsonProperty("cnp") final String cnp,
				@JsonProperty("numeUtilizator") final String numeUtilizator,
				@JsonProperty("nume") final String nume,
				@JsonProperty("prenume") final String prenume,
				@JsonProperty("email") final String email,
				@JsonProperty("telefon") final String telefon,
				@JsonProperty("tipUtilizator") final String tipUtilizator,
				@JsonProperty("parola") final String parola,
				@JsonProperty("salt") final String salt) {
		this.cnp = cnp;
		this.numeUtilizator = numeUtilizator;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.telefon = telefon;
		this.tipUtilizator = tipUtilizator;
		this.parola = parola;
		this.salt = salt;
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

	public String getParola() {
		return parola;
	}

	public String getSalt() {
		return salt;
	}

	@Override
	public String toString() {
		return "User{" +
				"cnp='" + cnp + '\'' +
				", numeUtilizator='" + numeUtilizator + '\'' +
				", nume='" + nume + '\'' +
				", prenume='" + prenume + '\'' +
				", email='" + email + '\'' +
				", telefon='" + telefon + '\'' +
				", tipUtilizator='" + tipUtilizator + '\'' +
				", parola='" + parola + '\'' +
				", salt='" + salt + '\'' +
				'}';
	}
}