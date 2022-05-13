package it.fincons.accademy.webservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import it.fincons.accademy.types.Persona;

//SERVIZI SOAP

@WebService (name = "servizi", serviceName = "serviziService", portName = "serviziPort", targetNamespace = "http://academy.fincons.it/webservices")
public class Servizi {
	
	@WebMethod
	public Persona leggiPersona(@WebParam String codiceFiscale) throws Exception {
		Persona p = null;
		InitialContext ic = new InitialContext();
		Connection conn = null;
		try {
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select nome, cognome, eta, codicefiscale, sesso from Persona where codicefiscale = ?");
			ps.setString(1, codiceFiscale);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				p = new Persona();
				p.setNome(rs.getString(1));
				p.setCognome(rs.getString(2));
				p.setEta(rs.getInt(3));
				p.setCodiceFiscale(rs.getString(4));
				p.setSesso(rs.getString(5));
			}
		} finally {
			try {
				if (conn != null) conn.close();	
			} catch (SQLException e) {}
		}
		
		return p;
	}
}
