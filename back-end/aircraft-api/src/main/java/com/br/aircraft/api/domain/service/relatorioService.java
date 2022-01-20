package com.br.aircraft.api.domain.service;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.br.aircraft.api.domain.enums.EnumRelatorio;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class relatorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * Método que dá uma rebosta de byte array , mostra o pdf direto , e vai receber
	 * parametros no relátorio
	 */
	public byte[] gerarRelatorio(String nomeRelatorio, Map<String, Object> params, ServletContext servletContext)
			throws Exception {

		/* Obter conexão com banco de dados */
		Connection connection = jdbcTemplate.getDataSource().getConnection();

		/*
		 * Carregar o caminho do arquivo Jasper nome por parametro e extenção do arquivo
		 * que é o jasper
		 */
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		/* Gerar o Relatorio com os dados e conexão */
		JasperPrint print = JasperFillManager.fillReport(caminhoJasper, params, connection);

		/* Exporta para byte Array o Pdf para fazer o dowload */
		byte[] retorno = JasperExportManager.exportReportToPdf(print);

		connection.close(); // fecha a conexão para evitar algum problema com a conexão

		return retorno; // ai pega e retorna certinho o nosso pdf e fecha a conexão q foi usada pelo
						// relatorio
	}

	public String pdfAeronaves(HttpServletRequest request, EnumRelatorio relatorio) throws Exception {
		byte[] pdf = gerarRelatorio(relatorio.getRelatorio(), new HashMap<String, Object>(),
				request.getServletContext());
		String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);
		return base64Pdf;
	}

}
