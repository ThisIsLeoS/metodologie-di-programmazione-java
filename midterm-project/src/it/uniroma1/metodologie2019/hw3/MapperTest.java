package it.uniroma1.metodologie2019.hw3;

import java.util.List;
import java.util.Optional;

public class MapperTest
{
	public static void main(String[] args)
	{
		WordNet wn21 = WordNet.getInstance("2.1");
		WordNet wn30 = WordNet.getInstance("3.0");
		List<Synset> synsets1 = wn30.getSynsets("house", POS.VERB);
		System.out.println(synsets1);
		List<Synset> synsets2 = wn30.getSynsets("run");
		System.out.println(synsets2);

		wn21.stream()
		    .filter(s -> s.getPOS() == POS.NOUN)
		    .forEach(s -> System.out.println(s.getSynonyms()+"["+s.getOffset()+"]:"+s.getGloss()));
		WordNetMapping map = Mapper.map(wn21, wn30);
		wn21.stream()
		    .map(map::getMapping)
		    .flatMap(Optional::stream)
		    .forEach(m -> System.out.println(m.getSource()+"->"+m.getTarget()+":"+m.getScore()));
	}
}