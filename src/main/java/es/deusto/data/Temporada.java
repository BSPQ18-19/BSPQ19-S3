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
	private int anho;
	
	@Element(column="IDTEMP")
	List<Capitulo> caps;

	public Temporada(int num, int anho) {
		this.num = num;
		this.anho = anho;
		this.caps = new ArrayList<Capitulo>();
	}

	public Temporada(int num) {
		this(num, 0);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}

	public List<Capitulo> getCaps() {
		return caps;
	}

	public void setCaps(List<Capitulo> caps) {
		this.caps = caps;
	}

	@Override
	public String toString() {
		return "Temporada [idTemp=" + idTemp + ", num=" + num + ", anho=" + anho + ", caps=" + caps + "]";
	}
	
	
}
