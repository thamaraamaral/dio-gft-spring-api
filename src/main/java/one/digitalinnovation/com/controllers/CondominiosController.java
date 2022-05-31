package one.digitalinnovation.com.controllers;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.com.entities.Condominio;
import one.digitalinnovation.com.repositories.ICondominioRepository;
import one.digitalinnovation.com.requests.CondominiosPostRequest;
import one.digitalinnovation.com.requests.CondominiosPutRequest;

@Transactional
@Controller
public class CondominiosController {

	private static final String ENDPOINT = "/api/condominios";

	@Autowired // A interface será inicializada automaticamente
	private ICondominioRepository condominioRepository;

	@ApiOperation("Método para realizar o cadastro de um condomínio.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody CondominiosPostRequest request) {

		try {

			if (condominioRepository.findyByCnpj(request.getCnpj()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("O cnpj " + request.getCnpj() + " já está cadastrado no sistema, tente outro.");
			}

			if (condominioRepository.findoByRazaoSocial(request.getRazaoSocial()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A razão social " + request.getRazaoSocial()
						+ " já está cadastrada no sistema, tente outra.");
			}

			Condominio condominio = new Condominio();

			condominio.setCep(request.getCep());
			condominio.setRazaoSocial(request.getRazaoSocial());
			condominio.setCnpj(request.getCnpj());

			condominioRepository.save(condominio);

			return ResponseEntity.status(HttpStatus.CREATED) // HTTP 201
					.body("Condomínio cadastrado com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@ApiOperation("Método para realizar a atualização dos dados de um condomínio.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody CondominiosPutRequest request) {

		try {

			Optional<Condominio> consulta = condominioRepository.findById(request.getIdCondominio());

			// verificar se a empresa não foi encontrada
			if (consulta.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Nenhum condomínio foi encontrado, por favor verifique o id informado.");
			}

			Condominio condominio = consulta.get();

			// modificando os dados do condominio
			condominio.setRazaoSocial(request.getRazaoSocial());
			condominioRepository.save(condominio);

			return ResponseEntity.status(HttpStatus.CREATED) // HTTP 201
					.body("Condomínio atualizado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@ApiOperation("Método para realizar a exclusão de um condomínio.")
	@RequestMapping(value = ENDPOINT + "/{idCondominio}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idCondominio") Integer idCondominio) {

		try {

			// pesquisar o condominio pelo id
			Optional<Condominio> consulta = condominioRepository.findById(idCondominio);

			// verificar se o condominio não foi encontrado
			if (consulta.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Nenhum condomínio foi encontrado, por favor verifique o id informado.");
			}

			// capturar o condominio
			Condominio condominio = consulta.get();

			// excluindo o condominio
			condominioRepository.delete(condominio);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Condomínio excluído com sucesso.");// HTTP 201
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@ApiOperation("Método para consultar todas os condomínios cadastrados.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<Condominio>> getAll() {

		try {

			List<Condominio> condominios = (List<Condominio>) condominioRepository.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(condominios);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation("Método para consultar um condomínio baseado em seu ID (identificador do condomínio).")
	@RequestMapping(value = ENDPOINT + "/{idCondominio}", method = RequestMethod.GET)
	public ResponseEntity<Condominio> getById(@PathVariable("idCondominio") Integer idCondominio) {

		try {

			Optional<Condominio> consulta = condominioRepository.findById(idCondominio);

			// verificando se umcondomínio foi encontrado
			if (consulta.isPresent()) {
				Condominio condominio = consulta.get();
				return ResponseEntity.status(HttpStatus.OK).body(condominio);
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
