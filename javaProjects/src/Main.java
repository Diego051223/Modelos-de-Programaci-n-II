

/*

Modelos de programación II - Ejercicio 2
Creacuón de un árbol binario de búsqueda y recorrido en orden

-> Diego Andrés Lopez Rodriguez
-> Laura Natalia Orjuela Cardenas

 */

import java.util.Scanner;

public class Main {
    // Definición de la estructura de un nodo
    static class Nodo {
        int valor;
        Nodo subArbolIzquierdo, subArbolDerecho;

        public Nodo(int item) {
            valor = item;
            subArbolIzquierdo = subArbolDerecho = null;
        }
    }

    // Definición de la estructura de un árbol binario de búsqueda
    static class ArbolBinarioBusqueda {
        Nodo raiz;

        ArbolBinarioBusqueda() {
            raiz = null;
        }

        void insertar(int valor) {
            raiz = insertarValores(raiz, valor);
        }

        Nodo insertarValores(Nodo raiz, int valor) {
            if (raiz == null) {
                raiz = new Nodo(valor);
                return raiz;
            }
            if (valor < raiz.valor)
                raiz.subArbolIzquierdo = insertarValores(raiz.subArbolIzquierdo, valor);
            else if (valor > raiz.valor)
                raiz.subArbolDerecho = insertarValores(raiz.subArbolDerecho, valor);
            return raiz;
        }

        void recorridoEnOrden() {
            recorridoEnOrdenRecursivo(raiz);
        }

        void recorridoEnOrdenRecursivo(Nodo raiz) {
            if (raiz != null) {
                recorridoEnOrdenRecursivo(raiz.subArbolIzquierdo);
                int suma = sumaDeHijos(raiz);
                if (raiz.subArbolIzquierdo != null && raiz.subArbolDerecho != null) {
                    System.out.println("Nodo con 2 hijos: " + raiz.valor + ", Suma de hijos: " + suma);
                } else if (raiz.subArbolIzquierdo != null || raiz.subArbolDerecho != null) {
                    System.out.println("Nodo con al menos un hijo: " + raiz.valor + ", Suma de hijos: " + suma);
                }
                recorridoEnOrdenRecursivo(raiz.subArbolDerecho);
            }
        }

        /* Metodo para obtener la suma de los hijos de un nodo */
        int sumaDeHijos(Nodo nodo) {
            int suma = 0;
            if (nodo.subArbolIzquierdo != null) suma += nodo.subArbolIzquierdo.valor;
            if (nodo.subArbolDerecho != null) suma += nodo.subArbolDerecho.valor;
            return suma;
        }

        // Metodo para buscar un nodo en el árbol
        void buscarNodo(int valor) {
            Nodo resultado = buscarNodoRecursivo(raiz, valor);
            if (resultado == null) {
                System.out.println("El nodo no existe");
            } else {
                System.out.println("Nodo encontrado: " + resultado.valor);
            }
        }

        Nodo buscarNodoRecursivo(Nodo raiz, int valor) {
            if (raiz == null || raiz.valor == valor)
                return raiz;
            if (raiz.valor > valor)
                return buscarNodoRecursivo(raiz.subArbolIzquierdo, valor);
            return buscarNodoRecursivo(raiz.subArbolDerecho, valor);
        }
    }

    // Estructura para obtener la suma de los hijos de un nodo
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        System.out.println("Ingrese la cantidad de valores a insertar:");
        int cantidad = scanner.nextInt();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingrese el valor " + (i + 1) + ":");
            int valor = scanner.nextInt();
            arbol.insertar(valor);
        }

        System.out.println("Recorrido en orden:");
        arbol.recorridoEnOrden();

        System.out.println("\nIngrese el valor del nodo a buscar:");
        int valorBusqueda = scanner.nextInt();
        arbol.buscarNodo(valorBusqueda);

        scanner.close();
    }
}