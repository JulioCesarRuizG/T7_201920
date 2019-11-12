package model.data_structures;

import java.util.Iterator;

public interface IQueue<T>{
	
	
	/**
	 * Agrega un objeto T a la cola
	 * @param valor a agregar
	 */
	public void enQueue(T valor);
	
	/**
	 * Elimina un objeto de la cola
	 * @return S� lo elimina retorna el objeto, null en caso contrario
	 */
	public T deQueue();
	
	/**
	 * Comprueba que la cola est� vac�a
	 * @return True si est� vac�a, false en caso contrario
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve el tama�o de la cola
	 * @return tama�o de la cola
	 */
	public int size();
	
	/**
	 * Convierte la cola de objetos en un iterator
	 * @return cola de objetos iterables
	 */
	public Iterador<T> iterator();

}
