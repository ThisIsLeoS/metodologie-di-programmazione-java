package it.uniroma1.metodologie2019.hw3;

/**
 * Rappresenta le relazioni che possono collegare due synset
 */
public enum Relation implements WordNetRelation
{
    ANTONYM("!"), 
    HYPERNYM("@"),
    INSTANCEHYPERNYM("@i"),
    HYPONYM("~"),
    INSTANCEHYPONYM("~i"),
    MEMBERHOLONYM("#m"),
    SUBSTANCEHOLONYM("#s"),
    PARTHOLONYM("#p"),
    MEMBERMERONYM("%m"),
    SUBSTANCEMERONYM("%s"),
    PARTMERONYM("%p"),
    ATTRIBUTE("="),
    DERIVATIONALLYRELATEDFORM("+"),      
    DOMAINOSYNSETTOPIC(";c"),
    MEMBEROFTHISDOMAINTOPIC("-c"),
    DOMAINOFSYNSETREGION(";r"),
    MEMBEROFTHISDOMAINREGION("-r"),
    DOMAINOFSYNSETUSAGE(";u"),
    MEMBEROFTHISDOMAINUSAGE("-u"),
    CAUSE(">"),
    ALSOSEE("^"),
    VERBGROUP("$"),
    SIMILARTO("&"),
    PARTICIPLEOFVERB("<"),
    PERTAINYM("\\"); 
    
    /**
     * Simbolo della relazione usato nei file "data"
     */
    private String symbol;
    
    /**
     * Costruttore di relazione
     * @param symbol simbolo della relazione usato nei file "data"
     */
    Relation(String symbol) { this.symbol = symbol; }
    
    @Override
    public String getSymbol() { return symbol; }
}
