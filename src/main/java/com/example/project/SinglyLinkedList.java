package com.example.project;

public class SinglyLinkedList<T extends Comparable<T>> {
    private Node<T> first; // Primero nodo de la lista
    private int size; // Tamano de la lista

    // Constructor (crea lista vacia)
    SinglyLinkedList() {
        first = null;
        size = 0;
    }

    // Retorna el tamano de la lista
    public int size() {
        return size;
    }

    // Devuelve true si la lista esta vazia o false caso contrario
    public boolean isEmpty() {
        return (size == 0);
    }

    // Adiciona v al inicio de la lista
    public void addFirst(T v) {
        Node<T> newNode = new Node<T>(v, first);
        first = newNode;
        size++;
    }

    // Adiciona v al final de la lista
    public void addLast(T v) {
        Node<T> newNode = new Node<T>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> cur = first;
            while (cur.getNext() != null)
                cur = cur.getNext();
            cur.setNext(newNode);
        }
        size++;
    }

    // Retorna el primer valor de la lista (o null si la lista esta vacia)
    public T getFirst() {
        if (isEmpty())
            return null;
        return first.getValue();
    }

    // Retorna el ultimo valor de la lista (o null si la lista esta vazia)
    public T getLast() {
        if (isEmpty())
            return null;
        Node<T> cur = first;
        while (cur.getNext() != null)
            cur = cur.getNext();
        return cur.getValue();
    }

    // Elimina el primer elemento de la lista (si esta vacia no hara nada)
    public void removeFirst() {
        if (isEmpty())
            return;
        first = first.getNext();
        size--;
    }

    // Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
    public void removeLast() {
        if (isEmpty())
            return;
        if (size == 1) {
            first = null;
        } else {
            // Ciclo con for y uso de size para mostrar alternativa al while
            Node<T> cur = first;
            for (int i = 0; i < size - 2; i++)
                cur = cur.getNext();
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    // Convierte la lista para um String
    public String toString() {
        String str = "{";
        Node<T> cur = first;
        while (cur != null) {
            str += cur.getValue();
            cur = cur.getNext();
            if (cur != null)
                str += ",";
        }
        str += "}";
        return str;
    }

    // NUEVOS METODOS

    // Elimina aquellos nodos de la lista que esten duplicados
    public void deleteDuplicates() {
	//Lista principal que servira para comparar, esta lista recorrera al finalizar cada pasada
    	Node <T> principal = first;
    	
    	//Misma lista pero auxiliar que recorrera mientras se compara
    	for(int i = 0; i < size; i++) {
    		//Si el nodo es nulo el bucle se detiene.
    		if(principal == null) {
    			break;
    		} else {
        		int j = 0;
        		//El comparador se reiniciara desde la cabeza cuando acabe cada pasado del ciclo while
            	Node <T> comparador = first;
        		while(comparador != null) {
        			if(j != i) {
        				if(principal.getValue().compareTo(comparador.getValue()) == 0){
        					deleteNth(j);
        				}
        			}
        			comparador = comparador.getNext();
        			j++;
        		}
    		}
    		principal = principal.getNext();
    	}
    }

    // Inserta un nuevo nodo en una posicion especifica de la lista
    public void insertNth(T data, int position) {
	Node<T> temporal = first;
	
	//Si la posicion no es valida no se hace nada
	if (position > size){
		return;
	}
	    
	//Si la posicion es el inicio de la lista, se ejecuta el metodo addFirst()
    	else if(position == 0) {
    		addFirst(data);
    	} 
    	//Si el dato a insertar es en la posicion ubicada despues del final de la lista se ejecuta el metodo addLast()
    	else if(position == size) {
    		addLast(data);
	} 
    	//Si el dato a insertar es mayor a 0 y no es despues del ultimo de la lista, se realiza un bucle
    	else if (position > 0 && position != size){
    		//El bucle recorrera hasta la posicion - 1, para que se detenga un nodo antes del nodo a insertar
	    	for(int i = 0; i < position - 1 ; i++){
			//El nodo temporal se actualizara con su siguiente nodo mientras el bucle siga ejecutandose
			temporal = temporal.getNext();
	    	}
	    	//El nuevo nodo apuntara al nodo temporal siguiente.
	    	Node<T> nuevo = new Node<T>(data,temporal.getNext());
	    	//El nodo temporal que esta detras del nuevo, apuntara al nuevo nodo
	    	temporal.setNext(nuevo);
		
		size++;
	}
    }

    // Elimina el nodo de una posicion especifica de la lista
    public void deleteNth(int position) {
        Node<T> temporal = first;
    	
	//Si la posicion esta fuera del rango no se hace nada
    	if(position >= size) {
    		return;
    	} 
    	//Si la posicion a eliminar es la primera llamara al metodo que elimina al primero de la lista
    	else if(position == 0) {
	    	removeFirst();
	} 
    	//Si la posicion a eliminar es mayor a 0, se buscara el nodo a eliminar
    	else if (position > 0){
    		//El bucle recorrera hasta la posicion - 1, para que se detenga un nodo antes del nodo a eliminarse
	    	for(int i = 0; i < position - 1 ; i++){
	    		//El nodo temporal se actualizara con su siguiente nodo mientras el bucle siga ejecutandose
	    		temporal = temporal.getNext();
	    	}
	    	//Se enlaza el nodo anterior al nodo que se eliminara, con el nodo posterior al nodo que se esta eliminando
	      	temporal.setNext(temporal.getNext().getNext());
		
		size--;
	    }
    }

    public static void main(final String[] args) {

        // testExercicio1();
        // testExercicio2();
        // testExercicio3();       

    }

    public static void testExercicio1(){

        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        list.addLast(47);
        list.addLast(89);
        list.addLast(56);
        list.addLast(89);
        list.addLast(56);

        System.out.println(list);

        list.deleteDuplicates();

        System.out.println(list);
    }

    public static void testExercicio2(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.insertNth('c', 2);

        System.out.println(list);
    }

    public static void testExercicio3(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.deleteNth(2);

        System.out.println(list);
    }

}
