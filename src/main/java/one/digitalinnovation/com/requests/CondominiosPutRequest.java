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

public class CondominiosPutRequest {

	private Integer idCondominio;
	private String razaoSocial;

}
