package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="distrito")
public class Distrito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_Distrito")
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	
	

	public Distrito() {
		super();
	}




	public Distrito(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}




	public int getCodigo() {
		return codigo;
	}




	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	

	


	

	

	

	
	
}

