package it.uniroma1.metodologie2019.hw3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe che rappresenta una versione di WordNet
 */
public class WordNet implements Iterable<Synset>
{
    /**
     * Path parziale che verrà usata per fare operazioni su cartelle e file
     */
    private static final String partialPath = "wordnet-releases" + File.separator + "releases";
    
    /**
     * Mappa di coppie (numero della versione di WordNet, versione di WordNet)  
     */
    private static Map<String, WordNet> WNVersions = new HashMap<String, WordNet>(); 

    /**
     * Mappa di coppie (part of speech, synsets di quella part of speech)
     */
    // i synset sono ordinati nelle liste secondo il loro ordine naturale
    private Map<POS, List<Synset>> synsetsMap = new HashMap<POS, List<Synset>>();
    
    /**
     * Numero della versione di WordNet
     */
    private String version;
    
    /**
     * Costruttore di WordNet
     * @param version numero della versione di WordNet
     */
    private WordNet(String version)
    {
        this.version = version;
        parseDataFile(version, POS.NOUN);
        parseDataFile(version, POS.VERB);
        parseDataFile(version, POS.ADJECTIVE);
        parseDataFile(version, POS.ADVERB);          
    }
    
    /**
     * Restituisce un oggetto contenente un'intera versione di WordNet
     * Alla chiamata di getInstance con lo stesso input (la stringa della versione, ad esempio 
     * “1.6” o “3.0”) il metodo restituisce il medesimo oggetto contenente il dizionario caricato 
     * la prima volta per la stessa versione richiesta
     * @param version il numero della versione di WordNet che verrà restituita
     * @return la versione di WordNet indicata nella stringa in input 
     */
    public static WordNet getInstance(String version)
    {
        /* se il numero della versione passata in input non è presente nella cartella releases,
           viene restituito null */
        if (!new File(partialPath + File.separator + "WordNet-" + version).exists()) return null;
        if (WNVersions == null || !WNVersions.containsKey(version)) new WordNet(version);
        return WNVersions.get(version);
    }

    /**
     * Dato in input un synset e una relazione sotto forma di String, restituisce una collezione di 
     * synset correlati (ad esempio, getRelatedSynsets(sourceSynset, “@”) restituisce gli iperonimi 
     * del synset (es. { motor vehicle, automotive vehicle } se il primo synset di car viene dato 
     * in input). Se la relazione non esiste o non ci sono istanze di tale relazione, viene 
     * restituita una collezione vuota
     * @param synset synset connesso dalla relazione ai synset che verranno restituiti
     * @param relationSymbol simbolo della relazione che collega il synset passato in input ai 
     *        synset che verrano restituiti
     * @return una collezione di synset correlati al synset passato in input secondo la relazione 
     *         passata in input. Se la relazione non esiste o non ci sono istanze di tale 
     *         relazione, viene restituita una collezione vuota
     */
    public Collection<Synset> getRelatedSynsets(Synset synset, String relationSymbol)
    {
        Set<Synset> relatedSynsets = new HashSet<>();
        // per ogni relazione nel synset passato in input
        for (Synset.Relation relation : synset.getRelations())
        {
            // se la relazione esiste
            if (relation.getSymbol().equals(relationSymbol))
            {
                // cerca il synset e inseriscilo nella collezione che verrà restituita
                for (Synset syn : synsetsMap.get(relation.getPOS()))
                    if (relation.getID().equals(syn.getID())) relatedSynsets.add(syn);
            }
        }
        return relatedSynsets;     
    }
    
    /**
     * Dato in input un synset e una relazione sotto forma di WordNetRelation, restituisce una 
     * collezione di synset correlati (ad esempio, getRelatedSynsets(sourceSynset, relation) 
     * restituisce gli iperonimi del synset (es. { motor vehicle, automotive vehicle } se il primo 
     * synset di car viene dato in input).
     * @param synset synset connesso dalla relazione ai synset che verranno restituiti
     * @param relation relazione che collega il synset passato in input ai synset che verrano 
     *                 restituiti
     * @return una collezione di synset correlati al synset passato in input secondo la relazione 
     *         passata in input. Se la relazione non esiste o non ci sono istanze di tale 
     *         relazione, viene restituita una collezione vuota
     */
    public Collection<Synset> getRelatedSynsets(Synset synset, WordNetRelation relation)
    {
        Set<Synset> relatedSynsets = new HashSet<>();
        // per ogni relazione nel synset passato in input
        for (Synset.Relation rel : synset.getRelations())
        {
            // se la relazione esiste
            if (rel.getSymbol().equals(relation.getSymbol()))
            {
                // cerca il synset e inseriscilo nella collezione che verrà restituita
                for (Synset syn : synsetsMap.get(rel.getPOS()))
                    if (rel.getID().equals(syn.getID())) relatedSynsets.add(syn);
            }
        }
        return relatedSynsets;  
    }
    
    /**
     * Restituisce la lista dei synset della part of speech passata in input
     * @param pos la part of speech per la quale si vogliono i synset 
     * @return una lista dei synset della part of speech passata in input
     */
    public List<Synset> getSynsets(POS pos) { return synsetsMap.get(pos); }
       
    /**
     * Restituisce la lista dei synset contenenti il lemma passato in input. Se il lemma è composto 
     * da più parole, si assume che le parole siano separate da _
     * @param lemma il lemma che dev'essere contenuto nei synset restituiti. Se il lemma è composto 
     *              da più parole, si assume che le parole siano separate da _
     * @return una lista dei synset contenenti il lemma passato in input
     */
    public List<Synset> getSynsets(String lemma)
    {
        return Stream.of(getSynsets(lemma, POS.NOUN), getSynsets(lemma, POS.VERB), getSynsets(lemma, POS.ADJECTIVE), getSynsets(lemma, POS.ADVERB))
                     .flatMap(Collection::parallelStream)
                     .collect(Collectors.toList());
    }
    
    /**
     * Restituisce la lista dei synsets di una data part of speech contenenti un dato lemma. Se il 
     * lemma è composto da più parole, si assume che le parole siano separate da _
     * @param lemma il lemma che dev'essere contenuto nei synset restituiti. Se il lemma è composto 
     *              da più parole, si assume che le parole siano separate da _
     * @param pos la part of speech dei synset restituiti
     * @return una lista di synset della part of speech passata in input contenenti il lemma 
     *         passato in input
     */
    public List<Synset> getSynsets(String lemma, POS pos)
    {
        List<Synset> l = new ArrayList<>();
        // per ogni synset nella lista dei synset della part of speech passata in input
        for (Synset synset : synsetsMap.get(pos))
        {
            // per ogni sinonimo del synset
            for (String synonym : synset.getSynonyms())
                /* se il sinonimo è uguale al lemma passato in input, aggiungi il synset alla lista
                   che verrà restituita */ 
                if (synonym.equals(lemma)) l.add(synset);
        }
        return l;
    }
    
    /**
     * Dato in input l’ID sotto forma di stringa (offset+pos, es. 00001740n), restituisce il synset 
     * corrispondente, null se non presente.
     * @param ID l'ID del synset che verrà restituito, se presente 
     * @return il synset identificato dall'ID fornito in input. null se il synset non è presente
     */
    public Synset getSynsetFromID(String ID)
    {
        /* per ogni synset nella lista dei synset della part of speech indicata nell'ID passato in
           input */
        for(Synset synset : synsetsMap.get(POS.getPOSFromChar(ID.charAt(ID.length() - 1))))
            if (synset.getID().equals(ID)) return synset;
        return null;
    }
    
    /**
     * Restituisce una stringa contenente la versione di WordNet
     * @return una stringa contenente la versione di WordNet
     */
    public String getVersion() { return version; }
    
    @Override
    public Iterator<Synset> iterator()
    {
        return Stream.of(getSynsets(POS.NOUN), getSynsets(POS.VERB), getSynsets(POS.ADJECTIVE), getSynsets(POS.ADVERB))
                     .flatMap(Collection::parallelStream)
                     .collect(Collectors.toList())
                     .iterator();
    }
    
    /**
     * Metodo che fa il parsing di un file data.POS di una versione di WordNet, basandosi sulla
     * part of speech e il numero della versione passati in input
     * @param version numero della versione di WordNet per cui verrà fatto il parsing del file data.POS
     * @param pos part of speech del cui relativo file verrà fatto il parsing
     */
    private void parseDataFile(String version, POS pos)
    {
        try(Stream<String> lines = Files.lines(Paths.get(partialPath, "WordNet-" + version, "dict", "data." + pos.getPOSAbbreviation())).parallel())
        {
            List<String> l = lines.collect(Collectors.toList());
            int lineNbr = 0;
            // viene saltato l'avviso di copyright (righe che iniziano con spazio)          
            while (l.get(lineNbr).charAt(0) == ' ') ++lineNbr;
            /* per ognuna delle restanti righe viene creato un oggetto di tipo Synset che viene
               aggiunto ad una lista */
            List<Synset> synsets = new ArrayList<>();
            for (; lineNbr < l.size(); ++lineNbr) synsets.add(new Synset(l.get(lineNbr)));
            Collections.sort(synsets); // viene fatto il sorting della lista di synset
            synsetsMap.put(pos, synsets);
            WNVersions.put(version, this);
        }
        catch(IOException e) { e.printStackTrace(); }       
    }
    
    /**
     * Restituisce lo stream di tutti i synset
     * @return lo stream di tutti  isynset
     */
    public Stream<Synset> stream()
    {
        return Stream.concat(Stream.concat(synsetsMap.get(POS.NOUN).parallelStream(), synsetsMap.get(POS.VERB).parallelStream()), Stream.concat(synsetsMap.get(POS.ADJECTIVE).parallelStream(), synsetsMap.get(POS.ADVERB).parallelStream()));     
    }
}
