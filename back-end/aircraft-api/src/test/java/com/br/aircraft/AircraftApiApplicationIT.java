package com.br.aircraft;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.br.aircraft.api.domain.dto.input.AeronaveIdInput;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.service.AeronaveService;
import com.br.aircraft.utils.DatabaseCleaner;
import com.br.aircraft.utils.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties") // caso n funcione, usar as confgurações do h2 desse arquivo no application.properties
class AircraftApiApplicationIT {

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private AeronaveService aeronaveService;

	@LocalServerPort
	private int port;

	private static final int AERONAVE_ID_INEXISTENTE = 999;
	private String jsonCorretoAeronavec390;
	private String jsonAeronaveAnoIncorreto;
	private String jsonAeronaveMarcaIncorreto;
	private String jsonAeronaveNomeEmBranco;
	private String jsonAeronaveVendidoNull;
	private AeronaveIdInput aeronaveBoeing;
	private AeronaveIdInput aeronaveBoeing2;

	

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "aircraft/aeronaves";
		jsonCorretoAeronavec390 = ResourceUtils.getContentFromResource("/json/correto/aeronave-c390.json");

		jsonAeronaveAnoIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/aeronave-ano-incorreto.json");

		jsonAeronaveMarcaIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/aeronave-marca-errada.json");

		jsonAeronaveNomeEmBranco = ResourceUtils.getContentFromResource("/json/incorreto/aeronave-nome-branco.json");

		jsonAeronaveVendidoNull = ResourceUtils.getContentFromResource("/json/incorreto/aeronave-vendido-null.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarAeronaves() {
		given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test  // testado unitariamente
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarAeronaveExistente() {
		given().pathParam("id", aeronaveBoeing.getId()).accept(ContentType.JSON).when().get("/{id}").then()
				.statusCode(HttpStatus.OK.value());

	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarAeronaveInexistente() {
		given().pathParam("id", AERONAVE_ID_INEXISTENTE) // indicar o parametro e o id
				.accept(ContentType.JSON).when().get("/{id}").then().statusCode(HttpStatus.NOT_FOUND.value());

	}

	// OS testes de cadastrar aeronave tem as mesmas regras que a alteração, o que
	// muda é que no alterar tem que passar o id.

	@Test
	public void deveRetornar201_QuandoCadastrarAeronave() {
		given().body(jsonCorretoAeronavec390).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarAeronaveAnoIncorreto() {
		given().body(jsonAeronaveAnoIncorreto).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarAeronaveMarcaIncorreta() {
		given().body(jsonAeronaveMarcaIncorreto).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarAeronaveNomeEmBranco() {
		given().body(jsonAeronaveNomeEmBranco).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void deveRetornarStatus400_QuandoCadastrarAeronaveVendidoComNull() {
		given().body(jsonAeronaveVendidoNull).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test // testado unitariamente
	public void deveRetornarStatus204_QuandoRemoverAeronaveExistente() {
		given().pathParam("id", aeronaveBoeing2.getId()).accept(ContentType.JSON).when().delete("/{id}").then()
				.statusCode(HttpStatus.NO_CONTENT.value());
	}

	@Test
	public void deveRetornarStatus404_QuandoRemoverAeronaveInexistente() {
		given().pathParam("id", AERONAVE_ID_INEXISTENTE) //
				.accept(ContentType.JSON).when().delete("/{id}").then().statusCode(HttpStatus.NOT_FOUND.value());

	}
	
	private void prepararDados() {

		AeronaveInput novaAeronave1 = new AeronaveInput();
		novaAeronave1.setMarca("Embraer");
		novaAeronave1.setNome("Legacy 600");
		novaAeronave1.setAno(2019);
		novaAeronave1.setVendido(true);
		aeronaveService.save(novaAeronave1);

		AeronaveInput novaAeronave2 = new AeronaveInput();
		novaAeronave2.setMarca("Embraer");
		novaAeronave2.setNome("EMB-312 \"Tucano");
		novaAeronave2.setAno(2019);
		novaAeronave2.setVendido(true);
		aeronaveService.save(novaAeronave2);

		aeronaveBoeing = new AeronaveIdInput();
		aeronaveBoeing.setId(3L);
		aeronaveBoeing.setMarca("Boeing");
		aeronaveBoeing.setNome("EMB-314 \"Tucano");
		aeronaveBoeing.setAno(2019);
		aeronaveBoeing.setVendido(true);
		aeronaveService.save(aeronaveBoeing);
		
		aeronaveBoeing2 = new AeronaveIdInput();
		aeronaveBoeing2.setId(4L);
		aeronaveBoeing2.setMarca("Boeing");
		aeronaveBoeing2.setNome("EMB-314 \"Tucano");
		aeronaveBoeing2.setAno(2019);
		aeronaveBoeing2.setVendido(true);
		aeronaveService.save(aeronaveBoeing2);

		System.out.println("Aeronaves Cadastradas");
	}

}
