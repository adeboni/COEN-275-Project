package knowledge;

import java.util.ArrayList;

import knowledge.sources.CommonPrefixKnowledgeSource;
import knowledge.sources.CommonSuffixKnowledgeSource;
import knowledge.sources.ConsonantKnowledgeSource;
import knowledge.sources.DirectSubstitutionKnowledgeSource;
import knowledge.sources.DoubleLetterKnowledgeSource;
import knowledge.sources.LegalStringKnowledgeSource;
import knowledge.sources.LetterFrequencyKnowledgeSource;
import knowledge.sources.PatternMatchingKnowledgeSource;
import knowledge.sources.SentenceStructureKnowledgeSource;
import knowledge.sources.SmallWordKnowledgeSource;
import knowledge.sources.SolvedKnowledgeSource;
import knowledge.sources.VowelKnowledgeSource;
import knowledge.sources.WordStructureKnowledgeSource;

public final class KnowledgeSourcesImpl extends ArrayList<KnowledgeSource> implements KnowledgeSources {

    /**
     * unique serial identifier
     */
    private static final long serialVersionUID = -7058137814441379445L;


    /**
     * Public method to initialize all knowledge sources
     * 
     * @throws CollectionLoadingException
     */
    public void init() {
        loadKnowledgeSources();
    }

    /**
     * Public method to clear and initialize all knowledge sources
     * 
     * @throws CollectionLoadingException
     */
    public void reset()  {
        this.clear();
        loadKnowledgeSources();
    }

    /**
     * Private method to load this data structure with unique knowledge sources
     */
    public void loadKnowledgeSources() {

    	//TODO: put this in order of priority
    	
    	addKS(new DirectSubstitutionKnowledgeSource());
        addKS(new SentenceStructureKnowledgeSource());
        addKS(new PatternMatchingKnowledgeSource());
        addKS(new SmallWordKnowledgeSource());
        addKS(new WordStructureKnowledgeSource());  
        addKS(new LegalStringKnowledgeSource());
        addKS(new DoubleLetterKnowledgeSource());
        addKS(new CommonSuffixKnowledgeSource());
        addKS(new CommonPrefixKnowledgeSource());
        addKS(new LetterFrequencyKnowledgeSource());
        addKS(new ConsonantKnowledgeSource());
        addKS(new VowelKnowledgeSource());
        addKS(new SolvedKnowledgeSource());
    }


    /**
     * Private method to add KnowledgeSources to itself
     * 
     * @param ks
     *            the {@link knowledge.KnowledgeSource}
     *            reference
     * @throws CollectionLoadingException
     */
    private void addKS(KnowledgeSource ks) {
    	this.add(ks);
    }

}
