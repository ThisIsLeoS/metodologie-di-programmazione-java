package it.uniroma1.metodologie2019.hw3;

/**
 * Interfaccia che fornisce i comportamenti che deve avere una classe che rappresenta relazioni tra
 * synset 
 */
public interface WordNetRelation
{
    /**
     * Restituisce il simbolo della relazione usato nei file "data"
     * @return il simbolo della relazione usato nei file "data"
     */
    String getSymbol();
}