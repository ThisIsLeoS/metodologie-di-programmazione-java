package it.uniroma1.metodologie2019.hw3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/** 
 * Classe che crea una mappatura tra due versioni di wordnet (una sorgente e una di destinazione) 
 */
public class WordNetMapping
{
    /**
     * Insieme contenente le coppie di synset mappate
     */
    private Set<SynsetPairing> synsetsPairs;
    
    /**
     * Versione di wordnet che fa da codominio per la mappatura
     */
    private WordNet target;
    
    /**
     * Costruttore di WordNetMapping
     * @param target versione di wordnet che fa da codominio per la mappatura
     */
    public WordNetMapping(WordNet target)
    {
        synsetsPairs = new HashSet<>();
        this.target = target;
    }
    
    /**
     * Aggiunge una nuova coppia all'insieme di coppie mappate
     * @param sp elemento da aggiungere all'insieme di coppie mappate
     */
    public void add(SynsetPairing sp) { synsetsPairs.add(sp); }
    
    /**
     * Dato un Synset da una versione sorgente di WordNet, restituisce un Optional contenente il 
     * SynsetPairing che mappa il synset con il miglior synset nella versione di WordNet di 
     * destinazione
     * @param src il synset da mappare
     * @return un Optional contenente il SynsetPairing che mappa il synset con il miglior synset 
     *         nella versione di WordNet di destinazione
     */
    public Optional<SynsetPairing> getMapping(Synset src)
    {   
        SynsetPairing sp = null;
        double score = 0;
        double scoreTmp;
        /* cerca l'insieme dei sinonimi di src nella WordNet target (in particolare nella lista dei 
           synset che hanno la stessa part of speech di src) */
        List<Synset> synsets = target.getSynsets(src.getPOS());
        int index = Collections.binarySearch(synsets, src);
        // se un synset con lo stesso insieme di sinonimi di src è stato trovato
        if (index >= 0)
        {
            // calcola lo score di somiglianza di src col synset trovato
            score = calculateScore(src, synsets.get(index));
            // se il synset trovato è uguale a src
            if (score == 1.0)
                return Optional.ofNullable(new SynsetPairing(src, synsets.get(index), 1.0));
            /* scorri la lista all'indietro finchè non trovi un synset che ha sinonimi diversi
               da quelli di src */
            for (int i = index - 1; i >= 0 && synsets.get(i).getSynonyms().equals(src.getSynonyms()); --i)
            {
                // calcola lo score di somiglianza di src col synset trovato
                scoreTmp = calculateScore(src, synsets.get(i));
                // se si trova un synset uguale a src
                if (scoreTmp == 1.0)
                    return Optional.ofNullable(new SynsetPairing(src, synsets.get(i), 1.0));
                /* altrimenti se lo score calcolato è maggiore di quello calcolato per l'ultimo 
                   elemento visitato */
                if (scoreTmp > score)
                {
                    score = scoreTmp;
                    sp = score > 0.5? new SynsetPairing(src, synsets.get(i), score) : null;
                }
            }
            /* scorri la lista in avanti finchè non trovi un synset che ha sinonimi diversi da 
               quelli di src */
            {
                for (int i = index + 1; i < synsets.size() && synsets.get(i).getSynonyms().equals(src.getSynonyms()); ++i)
                {
                    // calcola lo score di somiglianza di src col synset trovato
                    scoreTmp = calculateScore(src, synsets.get(i));
                    // se si trova un synset uguale a src
                    if (score == 1.0)
                        return Optional.ofNullable(new SynsetPairing(src, synsets.get(i), 1.0));
                    /* altrimenti confronta lo score dell'ultimo elemento visitato, con lo score
                       dell'elemento corrente */
                    if (scoreTmp > score)
                    {
                        score = scoreTmp;
                        sp = score > 0.5? new SynsetPairing(src, synsets.get(i), score) : null;
                    }
                }
            }
        }
        return Optional.ofNullable(sp);
    }
    
    /**
     * Calcola uno score di confidenza sulla somiglianza tra i due synset compreso tra 0 e 1 (a
     * due synset uguali corrisponde uno score di 1)
     * @return uno score di confidenza sulla somiglianza tra i due synset compreso tra 0 e 1 (a
     *         due synset uguali corrisponde uno score di 1)
     */
    private double calculateScore(Synset src, Synset target)
    {
        // se i sinonimi sono uguali
        if (src.getSynonyms().equals(target.getSynonyms()))
        {
            // se la glossa è uguale
            if (src.getGloss().equals(target.getGloss())) return 1.0;
            // se la glossa è diversa
            else
                return (1.0 + division(Arrays.asList(src.getGloss().split(" ")), Arrays.asList(target.getGloss().split(" ")))) / 2.0;
        }
        // se i sinonimi sono diversi
        else
        {
            // se la glossa è uguale
            if (src.getGloss().equals(target.getGloss()))
                return (1.0 + division(src.getSynonyms(), target.getSynonyms())) / 2.0;
            // se la glossa è diversa
            else return 0.0;
        }
    }
    
    /**
     * Prende in input due collezioni di stringhe e restituisce un double che è il risultato della 
     * divisione tra il numero di stringhe uguali nelle due collezioni diviso il numero di stringhe 
     * nella collezione più grande
     * @param c1 una delle due collezioni di stringhe
     * @param c2 una delle due collezioni di stringhe
     * @return un double che è il risultato della divisione tra il numero di stringhe uguali nelle 
     * due collezioni diviso il numero di stringhe nella collezione più grande
     */
    private double division(Collection<String> c1, Collection<String> c2)
    {
        Set<String> intersection = new HashSet<>(c1);
        intersection.retainAll(c2); // intersezione degli insiemi          
        return c1.size() >= c2.size()? 
                   (double)intersection.size() / c1.size() :
                   (double)intersection.size() / c2.size();
    }
}
