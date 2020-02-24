package it.uniroma1.metodologie2019.hw3;

/**
 * Classe che rappresenta una coppia (synset sorgente, synset destinazione) con associato uno 
 * score di confidenza sulla somiglianza tra i due synset compreso tra 0 e 1 (a due synset uguali
 * corrisponde uno score di 1)
 */
public class SynsetPairing
{
    /**
     * Synset sorgente della coppia
     */
    Synset src;
    
    /**
     * Synset destinazione della coppia
     */
    Synset target;
    
    /**
     * Score di confidenza sulla somiglianza tra i due synset
     */
    Double score;

    /**
     * Costruttore di SynsetPairing
     * @param src synset sorgente della coppia
     * @param target synset destinazione della coppia
     * @param score score di confidenza sulla somiglianza tra i due synset
     */
    public SynsetPairing(Synset src, Synset target, double score)
    {
        this.src = src;
        this.target = target;
        this.score = score;
    }
    
    /**
     * Restituisce il synset sorgente della coppia
     * @return il synset sorgente della coppia
     */
    public Synset getSource() { return src; }
    
    /**
     * Restituisce il synset destinazione della coppia
     * @return il synset destinazione della coppia
     */
    public Synset getTarget() { return target; }
    
    /**
     * Restituisce uno score di confidenza sulla somiglianza tra i due synset compreso tra 0 e 1 (a
     * due synset uguali corrisponde uno score di 1)
     * @return uno score di confidenza sulla somiglianza tra i due synset compreso tra 0 e 1 (a
     *         due synset uguali corrisponde uno score di 1)
     */
    public double getScore() { return score; }
}