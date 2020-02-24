package it.uniroma1.metodologie2019.hw3;

/**
 * Rappresenta le quattro parti del discorso a cui un synset può appartenere
 */
public enum POS 
{ 
    NOUN("noun", 'n'), VERB("verb", 'v'), ADJECTIVE("adj", 'a'), ADVERB("adv", 'r');
    
    /**
     * Abbreviazione della POS che è usata come estensione per i nomi dei file "data"
     */
    private String POSAbbreviation;
    
    /**
     * Carattere che viene usato per rappresentare la POS all'interno dei file "data"
     */
    private char POSChar;
    
    /**
     * Costruttore di POS
     * @param POSAbbreviation abbreviazione della POS che è usata come estensione per i nomi dei 
     *        file "data"
     * @param POSChar carattere che viene usato per rappresentare la POS all'interno dei file "data"
     */
    POS(String POSAbbreviation, char POSChar) 
    { 
        this.POSAbbreviation = POSAbbreviation; 
        this.POSChar = POSChar;
    }
    
    /**
     * Restituisce l'abbreviazione della POS che è usata come estensione per i nomi dei file "data"
     * @return l'abbreviazione della POS che è usata come estensione per i nomi dei file "data"
     */
    public String getPOSAbbreviation() { return POSAbbreviation; }
    
    /**
     * Restituisce il carattere che viene usato per rappresentare la POS all'interno dei file "data"
     * @return il carattere che viene usato per rappresentare la POS all'interno dei file "data"
     */
    public char getPOSChar() { return POSChar; }
    
    /**
     * Restituisce, se presente, la costante enumerativa corrispondente al carattere passato in 
     * input, altrimenti restituisce null
     * @param POSChar carattere che viene usato per rappresentare la POS all'interno dei file "data"
     * @return se presente, la costante enumerativa corrispondente al carattere passato in input,
     *         altrimenti null
     */
    public static POS getPOSFromChar(char POSChar)
    {
        switch(POSChar)
        {
            case 'n': return POS.NOUN;
            case 'v': return POS.VERB;
            case 'a': return POS.ADJECTIVE;
            case 'r': return POS.ADVERB;
        }
        return null;
    }
}