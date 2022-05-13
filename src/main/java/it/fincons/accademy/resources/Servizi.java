package it.fincons.accademy.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.fincons.accademy.types.Persona;

//SERVIZI REST

@Path ("/servizi")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class Servizi {
	
	// Con @PathParam => Risponde alla richiesta Services/api/servizi/persona/esempiocf
	// Con @QueryParam => Services/api/servizi/persona?codiceFiscale=esempiocf
	
	@GET
	@Path ("/persona/{cf}")
	public Response getPersona(@PathParam ("cf") String codiceFiscale) throws Exception {
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
		
		
		return p != null ? Response.ok().entity(p).build() : Response.status(404).build();
	}
	
	
	//Prova con curl
	//curl -v -H "Content-Type: application/json" 
	//-d "{\"codiceFiscale\":\"provaprova\",\"cognome\":\"Rossi\",\"eta\":30,\"nome\":\"Mario\",\"sesso\":\"M\"}" 
	//-X POST http://127.0.0.1:8080/Services/api/servizi/persona/provaprova
	
	//POST per la creazione
	@POST
	@Path ("/persona/{cf}")
	public Response postPersona(@PathParam ("cf") String codiceFiscale, Persona persona) throws Exception {
		int count = 0;
		InitialContext ic = new InitialContext();
		Connection conn = null;
		try {
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into Persona(nome, cognome, eta, sesso, codicefiscale) values (?, ?, ?, ?, ?)");
			ps.setString(1, persona.getNome());
			ps.setString(2, persona.getCognome());
			ps.setInt(3, persona.getEta());
			ps.setString(4, persona.getSesso());
			ps.setString(5, codiceFiscale);
			count = ps.executeUpdate();
			
		} finally {
			try {
				if (conn != null) conn.close();	
			} catch (SQLException e) {}
		}
		
		return count != 0 ? Response.ok().entity(persona).build() : Response.status(404).build();
	}
	
	//Prova con curl
	//curl -v -H "Content-Type: application/json" -d "{\"codiceFiscale\":\"provaprova\",\"cognome\":\"Rossi\",\"eta\":30,\"nome\":\"Mario\",\"sesso\":\"M\"}" 
	//-X PUT http://127.0.0.1:8080/Services/api/servizi/persona/provaprova
	
	//PUT per la modifica
	@PUT
	@Path ("/persona/{cf}")
	public Response putPersona(@PathParam ("cf") String codiceFiscale, Persona persona) throws Exception {
		int count = 0;
		InitialContext ic = new InitialContext();
		Connection conn = null;
		try {
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("update Persona set nome = ?, cognome = ?, eta = ?, sesso = ? where codicefiscale = ?");
			ps.setString(1, persona.getNome());
			ps.setString(2, persona.getCognome());
			ps.setInt(3, persona.getEta());
			ps.setString(4, persona.getSesso());
			ps.setString(5, codiceFiscale);
			count = ps.executeUpdate();
			
		} finally {
			try {
				if (conn != null) conn.close();	
			} catch (SQLException e) {}
		}
		
		return count != 0 ? Response.ok().entity(persona).build() : Response.status(404).build();
	}
	
	//Prova con curl
	//curl -v -H "Content-Type: application/json" 
	//-X DELETE http://127.0.0.1:8080/Services/api/servizi/persona/provaprova
	
	@DELETE
	@Path ("/persona/{cf}")
	public Response deletePersona(@PathParam ("cf") String codiceFiscale) throws Exception {
		int count = 0;
		InitialContext ic = new InitialContext();
		Connection conn = null;
		try {
			DataSource ds = (DataSource)ic.lookup("java:/PostgresDS");
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from Persona where codicefiscale = ?");
			ps.setString(1, codiceFiscale);
			count = ps.executeUpdate();
			
		} finally {
			try {
				if (conn != null) conn.close();	
			} catch (SQLException e) {}
		}
		
		return count != 0 ? Response.ok().build() : Response.status(404).build();
	}
	
}
