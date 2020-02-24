package toTranspile;

import def.jquery.JQuery;

/**
 * Contiene il metodo buildPage che restituisce l'elemento body a cui sono stati appesi tutti 
 * gli elementi discendenti nell'ordine corretto
 */
public interface BuildablePage
{
    /**
     * Restituisce l'elemento body a cui sono stati appesi tutti gli elementi discendenti
     * nell'ordine corretto
     * @return l'elemento body a cui sono stati appesi tutti gli elementi discendenti nell'ordine
     * corretto
     */
    JQuery buildPage();
}
