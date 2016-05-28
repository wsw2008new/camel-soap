
package uy.com.antel.tallercamel.soap.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Hincha {
    private String nombre;
    private String tipo;
    private int id; 


    public Hincha(int id, String nombre, String tipo) {
        this.setNombre(nombre);
        this.setTipo(tipo);
        this.id = id;
    }

    public Hincha() {
    }
    
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hincha h = (Hincha) o;

        if (id != h.id) return false;

        return true;
    }	
}
