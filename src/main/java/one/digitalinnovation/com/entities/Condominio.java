package one.digitalinnovation.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CONDOMINIO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Condominio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "IDCONDOMINIO")
	private Integer idCondominio;
	
	@Column(name = "RAZAOSOCIAL", length = 150, nullable = false, unique = true)
	private String razaoSocial;
	
	@Column(name = "CEP", length = 12, nullable = false)
	private String cep;
	
	@Column(name = "CNPJ", length = 20, nullable = false, unique = true)
	private String cnpj;

}
