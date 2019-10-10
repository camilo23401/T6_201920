package model.data_structures;

public class ArbolRojoNegro <K extends Comparable<K>, V> 
{
	private final static boolean RED = true;
	private final static boolean BLACK = false;
	private NodoArbol<K, V> raiz;
	
	public ArbolRojoNegro()
	{
		
	}

	public boolean esRojo(NodoArbol<K,V> pNodo)
	{
		boolean rta = false;
		if(pNodo==null)
		{
			rta = false;
		}
		else 
		{
			if(pNodo.darColor()==RED)
			{
				rta = true;
			}
			else
			{
				rta = false;
			}
		}
		return rta;
	}
	public int size()
	{
		int tamanio = (int)raiz.darTamanio();
		return tamanio;
	}
	public boolean isEmpty()
	{
		boolean rta = false;
		if(raiz==null)
		{
			rta = true;
		}
		return rta;
	}
	public V get(NodoArbol<K,V> pNodo, K pLlave)
	{
		V rta = null;
		while(pNodo!=null)
		{
			int comparacion = pLlave.compareTo(pNodo.darLlave());
			if(comparacion<0)
			{
				pNodo = pNodo.darNodoIzquierda();
			}
			else if(comparacion>0)
			{
				pNodo = pNodo.darNodoDerecha();
			}
			else
			{
				rta = pNodo.darValor();
			}
		}
		return rta;
	}
	public void agregarRaiz(K pLlave, V pValor)
	{
		if(pLlave==null)
		{
			return;
		}
		if(pValor==null)
		{
			borrar(pLlave);
			return;
		}
		raiz = agregar(raiz, pLlave, pValor);
		raiz.cambiarColor(BLACK);
	}
	public NodoArbol<K, V> agregar(NodoArbol<K, V> pNodo, K pLlave, V pValor)
	{
		NodoArbol<K, V> nuevo = null;
		if(pNodo==null)
		{
			nuevo = new NodoArbol<K, V>(pLlave, pValor, RED,(short)1);
		}
		int comparacion = pLlave.compareTo(pNodo.darLlave());
		if(comparacion<0)
		{
			pNodo.cambiarNodoIzquierda(agregar(pNodo, pLlave, pValor));
		}
		else if(comparacion>0)
		{
			pNodo.cambiarNodoDerecha(agregar(pNodo,pLlave, pValor));
		}
		else
		{
			pNodo.ponerValor(pValor);
		}
		return nuevo;
	}
	public void borrar(K pLlave)
	{
		
	}
}
