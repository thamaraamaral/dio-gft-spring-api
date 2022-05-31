package one.digitalinnovation.com.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CondominiosPostRequest {

	private String razaoSocial;
	private String cnpj;
	private String cep;
	
	
}
