package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<NodoVertice> nodoVerticeList = new ArrayList<NodoVertice>();
        List<NodoArcoLista> nodoArcoListaList = new ArrayList<NodoArcoLista>();

        nodoVerticeList.add(new NodoVertice("1")); //0
        nodoVerticeList.add(new NodoVertice("2")); //1
        nodoVerticeList.add(new NodoVertice("3")); //2
        nodoVerticeList.add(new NodoVertice("4")); //3
        nodoVerticeList.add(new NodoVertice("5")); //4
        nodoVerticeList.add(new NodoVertice("6")); //5
        nodoVerticeList.add(new NodoVertice("7")); //6

        nodoArcoListaList.add(new NodoArcoLista( //REL DE 1 A 2 CON PESO 200
                nodoVerticeList.get(0), // 1
                nodoVerticeList.get(1), // 2
                200
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 1 A 4 CON PESO 400
                nodoVerticeList.get(0), // 1
                nodoVerticeList.get(3), // 4
                150
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 2 A 3 CON PESO 400
                nodoVerticeList.get(1), // 2
                nodoVerticeList.get(2), // 3
                100
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 4 A 3 CON PESO 500
                nodoVerticeList.get(3), // 4
                nodoVerticeList.get(2), // 3
                500
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 4 A 5 CON PESO 500
                nodoVerticeList.get(3), // 4
                nodoVerticeList.get(4), // 5
                200
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 5 A 6 CON PESO 175
                nodoVerticeList.get(4), // 5
                nodoVerticeList.get(5), // 6
                175
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 5 A 6 CON PESO 25
                nodoVerticeList.get(2), // 3
                nodoVerticeList.get(6), // 7
                25
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 6 A 3 CON PESO 250
                nodoVerticeList.get(5), // 6
                nodoVerticeList.get(2), // 3
                250
        ));

        nodoArcoListaList.add(new NodoArcoLista(// REL DE 5 A 6 CON PESO 25
                nodoVerticeList.get(6), // 7
                nodoVerticeList.get(4), // 5
                300
        ));



        /////////// FINAL DE SAMPLE DATA ///////////

        BuscarRutas(nodoVerticeList.get(0), "5", nodoVerticeList, nodoArcoListaList);

    }

    private static void BuscarRutas(NodoVertice nodoDePartida, String claveDeBusqueda, List<NodoVertice> nodoVerticeList, List<NodoArcoLista> nodoArcoListaList) {

        List<List<NodoArcoLista>> rutas = new ArrayList<>();

        //processo de obtencion de nodos siguientes.
        List<NodoArcoLista> destinos = nodoArcoListaList.stream().filter(x -> x.getOrigen().equals(nodoDePartida)).collect(Collectors.toList());


        for (NodoArcoLista posibleDestino : destinos) {
            if(posibleDestino.getDestino().getLabel().equals(claveDeBusqueda)){
                System.out.println("Eureca!");
            } else {
                //No se encontro lo que se buscaba.
                if( SubBusqueda(posibleDestino, claveDeBusqueda, new ArrayList<>(), nodoVerticeList, nodoArcoListaList, rutas))
                {
                    System.out.println(rutas);
                }

            }
        }
        System.out.println(rutas);

        int i = 0;
        SortedMap<Integer, String> verboseRutas = new TreeMap<Integer, String>();
        for (List<NodoArcoLista> ruta : rutas) {
            i++;
            int peso = 0;
            String currentVerose = "Ruta: " + i + " : " + ruta.get(0).getOrigen().getLabel();
            for ( NodoArcoLista nodo : ruta) {
                currentVerose += " -> " + nodo.getDestino().getLabel() ;
             peso += nodo.getPeso();
            }
            currentVerose += " Peso: " + peso + '\n';
            System.out.print(currentVerose);
            verboseRutas.put(peso, currentVerose);
        }

        System.out.print("La mas rapida: " + verboseRutas.get(verboseRutas.firstKey()));
        System.out.println("La mas lenta: " + verboseRutas.get(verboseRutas.lastKey()));


    }

    //METODO RECURSIVO.
    private static Boolean SubBusqueda(NodoArcoLista nodoDePartida, String claveDeBusqueda,List<NodoArcoLista> ruta, List<NodoVertice> nodoVerticeList, List<NodoArcoLista> nodoArcoListaList, List<List<NodoArcoLista>> rutas) {


        //processo de obtencion de nodos siguientes.
        List<NodoArcoLista> destinos = nodoArcoListaList.stream().filter(x -> x.getOrigen().equals(nodoDePartida.getDestino())).collect(Collectors.toList());
        System.out.println("Nodo actual: " + nodoDePartida.getOrigen().getLabel());
        ruta.add(nodoDePartida);



        for (NodoArcoLista posibleDestino : destinos) {

            //Salva guarda contra retornos infinitos.
            if( posibleDestino.getDestino().equals(ruta.get(0).getOrigen()))
                return  false;


            List<NodoArcoLista> nRuta = new ArrayList<>();
            nRuta.addAll(ruta);
            if(posibleDestino.getDestino().getLabel().equals(claveDeBusqueda)){
                System.out.println("Eureca!");
                nRuta.add(posibleDestino);
                rutas.add(nRuta);
            } else {
                SubBusqueda(posibleDestino, claveDeBusqueda, nRuta, nodoVerticeList, nodoArcoListaList,rutas);
            }
        }
        return false;

    }


}
