package es.deusto.data;

import java.io.Serializable;
import java.util.*;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Temporada implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6357007824129485152L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int idTemp;
	private int num;
	
	@Element(column="IDTEMP")
	List<Capitulo> caps;

	public Temporada(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Capitulo> getCaps() {
		return caps;
	}

	public void setCaps(List<Capitulo> caps) {
		this.caps = caps;
	}
	
	
}
