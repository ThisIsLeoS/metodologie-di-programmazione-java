package it.uniroma1.metodologie2019.hw3;

import java.util.Optional;

/**
 * Classe che fornisce una mappatura tra versioni di WordNet
 */
public class Mapper
{
    /**
     * Presi in input due WordNet (la prima sorgente, la seconda destinazione) restituisce un 
     * oggetto di tipo WordNetMapping che fornisce le mappature dalla wordnet di partenza a quella 
     * di destinazione
     * @param src versione di WordNet che è il dominio della mappatura
     * @param target versione di WordNet che è il codominio della mappatura
     * @return un WordNetMapping che fornisce le mappature dalla wordnet di partenza a quella di 
     *         destinazione
     */
    public static WordNetMapping map(WordNet src, WordNet target)
    {
        WordNetMapping wnp = new WordNetMapping(target);
        if (src == target)
            for (Synset syn : src) wnp.add(new SynsetPairing(syn, syn, 1.0));
        else
        {
            Optional<SynsetPairing> optional;
            for (Synset syn : src)
            {
                /* se l'Optional restituito da getMapping contiene un oggetto non nullo, 
                   quest'ultimo viene inserito nella WordNetMapping */
                optional = wnp.getMapping(syn);
                if (wnp.getMapping(syn).isPresent()) wnp.add(optional.get());
            }
        }
        return wnp;       
    }
}
