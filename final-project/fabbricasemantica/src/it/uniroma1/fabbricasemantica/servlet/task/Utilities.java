package it.uniroma1.fabbricasemantica.servlet.task;

/**
 * Contiene utilities per la parte back end del progetto
 */
public class Utilities
{
    /**
     * Array contenente i file name di tutte le pagine html del progetto
     */
    public static final String[] HTMLFilesNames = {
        "translationAnnotation.html",
        "wordAnnotation.html",
        "definitionAnnotation.html",
        "senseAnnotation.html",
        "translationValidation.html",
        "senseValidation.html",
        "myAnnotation.html"     
    };
    
    /**
     * Restituisce un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al
     * file name passato in input
     * @param HTMLFileNameToDiscard file name presente in HTMLFilesNames che non deve essere
     * restituito
     * @return un file name a caso tra quelli presenti in HTMLFilesNames che non è uguale al file
     * name passato in input
     */
    public static String getRandomHTMLFilename(String HTMLFileNameToDiscard)
    {
        String HTMLFileName;
        int randomInt;
        do
        {
            // viene generato un intero random compreso tra 0 e HTMLFilesNames.length (escluso)
            randomInt = (int)(Math.random() * Utilities.HTMLFilesNames.length);
            HTMLFileName = Utilities.HTMLFilesNames[randomInt];
        }
        while(HTMLFileName.equals(HTMLFileNameToDiscard));
        return HTMLFileName;
    }
}
