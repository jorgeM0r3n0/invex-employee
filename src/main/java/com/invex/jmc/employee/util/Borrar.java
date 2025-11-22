package com.invex.jmc.employee.util;

import java.util.List;

public class Borrar {
  public static void main(String[] args) {
    List<Integer> lista = List.of(4,11,9,8);
    Borrar b = new Borrar();
    //b.eresPar(lista);
    b.eresImpar(lista);
  }

  private void eresImpar(List<Integer> lista) {
    int sumatoria = 0;
    int cont = 0;
    for(int i=0 ; i<lista.size() ; i++){
      int dividendo = lista.get(i);
      int divisor = 2;
      if(dividendo % 2 != 0){
        System.out.println(lista.get(i));
        sumatoria += lista.get(i);
        cont++;
      }
    }
    System.out.println("sumatoria = " + sumatoria);
    System.out.println("cont = " + cont);
  }

  void eresPar(List<Integer> lista){
    int sumatoria = 0;
    for(int i=0 ; i<lista.size() ; i++){
      int dividendo = lista.get(i);
      int divisor = 2;
      if(dividendo % 2 == 0){
        System.out.println(lista.get(i));
        sumatoria += lista.get(i);
      }
      System.out.println("sumatoria = " + sumatoria);

    }
  }
}
