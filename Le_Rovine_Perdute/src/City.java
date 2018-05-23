import java.util.LinkedList;

public class City 
{
	/**
	 * attributi fisici della citta,
	 * abbiamo utilizzato un array di interi per salvare momentaneamente
	 * gli id delle cit� adiacenti alla citta stessa
	 */
	protected int x=0;
	protected int y=0;
	protected int h=0;
	protected String cityName=null;
	protected int cityId=0;
	protected Map adjacents;
	protected int[] citiesId;
	/**
	 * attributi che contengono la distanza della citt� dalla sorgente
	 * un attributo per ogni veicolo
	 */
	protected int distanceFromSourceForMetztli=Integer.MAX_VALUE;
	protected int distanceFromSourceForTonatiuh=Integer.MAX_VALUE;
	/**
	 * attributo per sapere qual � la citt� precedente
	 */
	protected City prev=null;
	/**
	 * LinkedList che contengono le citt� del percorso minimo
	 * per i due veicoli
	 */
	protected LinkedList<City> shortestPathMetztli=new LinkedList<City>();
	protected LinkedList<City> shortestPathTonatiuh=new LinkedList<City>();
	
    public final static int MAX_CONNECTION=200;
    /**
     * costruttore della Citt� (l'altezza non pu� essere negativa)
     * @param x
     * @param y
     * @param h
     * @param cityName
     * @param cityId
     */
public City (int x,int y,int h,String cityName,int cityId)
{
	if ( h<0)
	{
	 throw new IllegalArgumentException();
	}
	else
	{
		this.x=x;
		this.y=y;
		this.h=h;
		this.cityName=cityName;
		this.cityId=cityId;
		adjacents= new Map();//LinkList<City>
		
		citiesId=new int[MAX_CONNECTION];
	}
	
	
}
/**
 * metodo toString della citt� che stampa i dati principali della stessa
 */
public String toString()
{
	String c = "\n";
    String nome = "Nome citta: "+ cityName  + "  ID Citta: "+ cityId+c;
    String posizione= "Posizione: " +c+ "x: "+x+" y: " +y +" h: "+h+c;
	return nome + posizione;
}
/**
 * metodo che ritorna una citt� all'interno di una mappa
 * @param id
 * @param map
 * @return citt� con l'ID voluto
 */
public City findAdjacentsById(int id,Map map)
{
	return  map.findCityById(id);
}

/**
 * metodo che passata una mappa,setta le citt� vicine.
 * La citt� viene aggiunta tramite il metodo findAdjacentsById
 * e si utilizza il vettore di int per avere gli id delle citt� limitrofe
 * @param map
 */
public void setAdjacentsCity(Map map)
{
	boolean k=true;
  	
	for(int i=0; citiesId[i]!=0 || k!=false ; i++)
	{
		adjacents.cities.add(findAdjacentsById(citiesId[i], map));
		
		k=false;
	}
}
/**
 * funzioni ricorsive che settano il percorso migliore 
 * da una citt� sorgente ad una citt� target per entrambi i veicoli 
 * 
 * @param target
 */
public void calculateBestPathForTonatiuhTo(City target)
{
	if (target.equals(this)) 
	{
		shortestPathTonatiuh.add(target);
		
		return;
	}
		else 
		{
			shortestPathTonatiuh.add(target);
			calculateBestPathForTonatiuhTo(target.prev);
		}
		
}
public void calculateBestPathForMetztliTo(City target)
{
	if (target.equals(this)) 
	{
		shortestPathMetztli.add(target);
		
		return;
	}
		else 
		{
			shortestPathMetztli.add(target);
			calculateBestPathForMetztliTo(target.prev);
		}
		
}
/**
 * a seguire metodi di hash() e equals()
 */

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + cityId;
	result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
	result = prime * result + h;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	City other = (City) obj;
	if (cityId != other.cityId)
		return false;
	if (cityName == null) {
		if (other.cityName != null)
			return false;
	} else if (!cityName.equals(other.cityName))
		return false;
	if (h != other.h)
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}

	


}

