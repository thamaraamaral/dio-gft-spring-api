package one.digitalinnovation.com.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import one.digitalinnovation.com.entities.Condominio;

public interface ICondominioRepository extends CrudRepository<Condominio, Integer>{

	@Query("select condo from Condominio condo where condo.cnpj = :param1")
	Condominio findyByCnpj(@Param("param1") String cnpj)throws Exception;
	
	@Query("select condo from Condominio condo where condo.razaoSocial = :param1")
	Condominio findoByRazaoSocial(@Param("param1") String razaoSocial) throws Exception;
	
}
