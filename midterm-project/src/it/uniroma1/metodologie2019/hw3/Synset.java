package it.uniroma1.metodologie2019.hw3;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe che rappresenta un synset
 */
public class Synset implements Comparable<Synset>
{
   /**
    * Offset del synset rappresentato con un intero decimale di 8 cifre
    */
   private String synset_offset;
   
   /**
    * Part of speech del synset
    */
   private POS ss_type;
   
   /**
    * Numero di sinonimi nel synset
    */
   private String w_cnt;
   
   /**
    * Insieme dei sinonimi del synset
    */
   /* viene usata una struttura dati che mantiene l'ordine naturale sui sinonimi, perchè l'ordine
      di quest'ultimi è usato per comparare i synset fra di loro */
   private Set<String> synonymsSet = new TreeSet<>();
   
   /**
    * Numero di relazioni del synset verso altri synset rappresentato con un intero decimale di 8 
    * cifre 
    */
   private String p_cnt;
   
   /**
    * Insieme di relazioni del synset verso altri synset
    */
   private Set<Relation> relationsSet = new HashSet<>();
   
   /**
    * Glossa del synset
    */
   private String gloss;
   
   /**
    * Insieme di esempi del synset
    */
   private Set<String> examplesSet = new HashSet<>();
   
   /**
    * Classe che rappresenta una relazione fra synset
    */
   public class Relation
   {
       /**
        * Simbolo della relazione
        */
       private String pointer_symbol;
       
       /**
        * Offset del synset target
        */
       private String synset_offset;
       
       /**
        * Part of speech del synset target
        */
       private POS pos;
       
       /**
        * Costruttore di Relation
        * @param pointer_symbol simbolo della relazione
        * @param synset_offset offset del synset target
        * @param pos part of speech del synset target
        */
       private Relation(String pointer_symbol, String synset_offset, POS pos)
       {
           this.pointer_symbol = pointer_symbol;
           this.synset_offset = synset_offset;
           this.pos = pos;
       }
       
       /**
        * Restituisce l'ID (offset + part of speech) del synset target
        * @return l'ID (offset + part of speech) del synset target
        */
       public String getID() { return synset_offset + pos.getPOSChar();}
       
       /**
        * Restituisce la part of speech del synset target
        * @return la part of speech del synset target
        */
       public POS getPOS() { return pos; }

       /**
        * Restituisce il simbolo della relazione
        * @return il simbolo della relazione
        */
       public String getSymbol() { return pointer_symbol; }
   }
   
   /**
    * Costruttore di synset
    * @param line una riga di un file data.POS
    */
   public Synset(String line)
   {
       String[] ssArray;
       /* si dividono le parole della riga separate da uno o più spazi (questo perchè le parole di 
          una riga possono essere separate da più di uno spazio, come per esempio in WorNet 2.1 
          nella glossa del synset { European cuckoo, Cuculus canorus } (riga 9323 di data.noun) */
       ssArray = line.split(" +");
       // si salvano le informazioni 
       int i = 0;
       synset_offset = ssArray[i];
       ++i; // viene saltato il lex_filenum
       /* gli adjective satellite (che hanno s come ss_type) vengono considerati adjective (a come 
          ss_type) */
       if (ssArray[++i].equals("s")) ssArray[i] = "a";
       ss_type = POS.getPOSFromChar(ssArray[i].charAt(0));
       w_cnt = ssArray[++i];                   
       for (int j = 0; j < Integer.parseInt(w_cnt, 16); ++j)
       {
           ++i; // si passa al sinonimo successivo
           // viene eliminato il possibile marcatore sintattico alla fine di un aggettivo 
           if (ss_type.equals(POS.ADJECTIVE) && ssArray[i].charAt(ssArray[i].length() - 1) == ')')
           {
               switch(ssArray[i].substring(ssArray[i].length() - 3))
               {
                   case "(p)": synonymsSet.add(ssArray[i].substring(0, ssArray[i].length() - 3));
                               break;
                   case "(a)": synonymsSet.add(ssArray[i].substring(0, ssArray[i].length() - 3));
                               break;
                   case "ip)": synonymsSet.add(ssArray[i].substring(0, ssArray[i].length() - 4));
                               break;
               }
           }
           else synonymsSet.add(ssArray[i]);
           ++i; // viene saltato il lex_id
       }
       p_cnt = ssArray[++i];
       for (int j = 0; j < Integer.parseInt(p_cnt); ++j)
       {
           Synset.Relation r = new Relation(ssArray[++i], ssArray[++i], POS.getPOSFromChar(ssArray[++i].charAt(0)));
           ++i; // viene saltato source_target
           relationsSet.add(r);
       }
       /* se si tratta di un synset di verbi, vengono saltate le informazioni riguardanti i frame */
       if (ss_type.equals(POS.VERB))
       {
           String f_cnt = ssArray[++i];
           for (int j = 0; j < Integer.parseInt(f_cnt); ++j) i += 3;
       }
       // salvataggio della glossa
       /* nota: casi gestiti con questa implementazione:
                    - glossa separata dagli esempi con ':' invece che con ';'
                    - ';' all'interno della glossa, oltre che alla fine
                casi non gestiti:
                    - virgolette all'interno della glossa */
       i += 2; // viene saltato il carattere "|" (carattere iniziale di ogni glossa)
       StringBuilder sb = new StringBuilder();
       for (; i < ssArray.length; ++i)
       {
           sb.append(ssArray[i]).append(" ");
           if (ssArray[i].charAt(ssArray[i].length() - 1) == ';' || ssArray[i].charAt(ssArray[i].length() - 1) == ':')
           {
               if (i != ssArray.length - 1)
                   // se non si è alla fine della glossa bisogna continuare a costruire la glossa
                   if (ssArray[i + 1].charAt(0) != '\"') continue;
               sb.deleteCharAt(sb.length() - 2); // viene eliminato il ';'
               break;
           }
       }
       sb.deleteCharAt(sb.length() - 1); // viene eliminato lo spazio finale
       gloss = sb.toString();
       ++i; // si passa alla prima stringa del primo esempio
       // salvataggio degli esempi
       /* nota: casi gestiti con questa implementazione:
                1) ';' all'interno di un esempio, oltre che alla fine
                2) esempio che finisce con " ;" invece che con ';'
                3) esempio "vuoto" (solo ';' e nient'altro)  */
       sb.setLength(0);
       for (; i < ssArray.length; ++i)
       {   
           // se si è arrivati alla fine dell'ultimo esempio
           if (i == ssArray.length - 1)
           {
               sb.append(ssArray[i]);
               examplesSet.add(sb.toString());
           }
           if (ssArray[i].length() >= 2)
           {
               /* se i due caratteri finali sono "";" siamo alla fine di un esempio che non è
                  l'ultimo */
               if(ssArray[i].substring(ssArray[i].length() - 2).equals("\";"))
               {
                   sb.append(ssArray[i]);
                   sb.deleteCharAt(sb.length() -1); // viene eliminato il ';'
                   examplesSet.add(sb.toString());
                   sb.setLength(0);
                   continue;
               }
               /* se i tre caratteri finali sono "" ;"" siamo alla fine di un esempio "vuoto" (solo 
                  ';' e nient'altro) */
               else if (ssArray[i].length() >= 3 && ssArray[i].substring(ssArray[i].length() - 3).equals("\" ;"))
               {
                   sb.append(ssArray[i]);
                   sb.deleteCharAt(sb.length() -1); // viene eliminato il ';'
                   sb.deleteCharAt(sb.length() -1); // viene eliminato lo spazio
                   examplesSet.add(sb.toString());
                   sb.setLength(0);
                   continue;
               }
           }
           sb.append(ssArray[i]).append(" ");  
       }
   }
   
   /**
    * Compara due synset secondo l'ordine lessicografico delle stringhe restituite dal 
    * {@link #toString}
    */
   @Override
   public int compareTo(Synset syn) { return syn.toString().compareTo(this.toString()); }
   
   /**
    * Dato in input un lemma, restituisce true se il synset contiene quel lemma, false altrimenti
    * @param lemma lemma per cui si vuole sapere se è contenuto o meno nel synset
    * @return true se il synset contiene il lemma passato in input, false altrimenti
    */
   public boolean contains(String lemma) { return synonymsSet.contains(lemma); }
   
   /**
    * Restituisce linsieme degli esempi del synset (vuoto se non ci sono esempi)
    * @return linsieme degli esempi del synset (vuoto se non ci sono esempi)
    */
   public Set<String> getExamples() { return examplesSet; }
   
   /**
    * Restituisce la glossa del synset
    * @return la glossa del synset
    */
   public String getGloss() { return gloss; } 
   
   /**
    * Restituisce lID (offset + pos) del Synset; per pos si intende uno dei caratteri n, v,
    *  a, r usati nei file "data" per le POS
    * @return lID (offset + pos) del Synset; per pos si intende uno dei caratteri n, v, a, 
    *         r usati nei file "data" per le POS
    */
   public String getID() { return synset_offset + ss_type.getPOSChar(); }
   
   /**
    * Restituisce loffset (01234567) del Synset
    * @return loffset (01234567) del Synset
    */
   public String getOffset() { return synset_offset; }
   
   /**
    * Restituisce la part of speech
    * @return la part of speech
    */
   public POS getPOS() { return ss_type; }
   
   /**
    * Restituisce l'insieme delle relazioni del synset
    * @return l'insieme delle relazioni del synset
    */
   public Set<Relation> getRelations() { return relationsSet; }
   
    /**
     * Restituisce linsieme dei sinonimi del synset
     * @return l'insieme dei sinonimi del synset
     */
    public Set<String> getSynonyms() { return synonymsSet; }
    
    /**
     * Restituisce una stringa che rappresenta il synset con la forma {synonym, ..., synonym}
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{");
        for (String s : getSynonyms()) sb.append(s + ", ");
        sb.delete(sb.length() - 2, sb.length()); // si elimina l'ultimo ", " inserito
        return sb.append("}").toString();           
    }
}
