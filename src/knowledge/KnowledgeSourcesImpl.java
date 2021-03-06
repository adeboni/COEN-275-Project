package knowledge;

import java.util.ArrayList;

import knowledge.sources.*;

public final class KnowledgeSourcesImpl extends ArrayList<KnowledgeSource> implements KnowledgeSources {

    /**
     * unique serial identifier
     */
    private static final long serialVersionUID = -7058137814441379445L;


    /**
     * Public method to initialize all knowledge sources
     */
    public void init() {
        loadKnowledgeSources();
    }

    /**
     * Public method to clear and initialize all knowledge sources
     */
    public void reset()  {
        this.clear();
        loadKnowledgeSources();
    }

    /**
     * Private method to load this data structure with unique knowledge sources
     */
    public void loadKnowledgeSources() {

    	//Put this in order of priority
    	
    	addKS(new DirectSubstitutionKnowledgeSource());
    	addKS(new DoubleLetterKnowledgeSource());
    	addKS(new SmallWordKnowledgeSource());
     	addKS(new PatternMatchingKnowledgeSource());
    	
    	addKS(new LetterFrequencyKnowledgeSource());
        addKS(new ConsonantKnowledgeSource());
        addKS(new VowelKnowledgeSource());
    	
        addKS(new LegalStringKnowledgeSource());
        addKS(new CommonSuffixKnowledgeSource());
        addKS(new CommonPrefixKnowledgeSource());
        addKS(new WordStructureKnowledgeSource());
    	
    	addKS(new SentenceStructureKnowledgeSource());
        addKS(new SolvedKnowledgeSource());
    }


    /**
     * Private method to add KnowledgeSources to itself
     * 
     * @param ks
     *            the {@link knowledge.KnowledgeSource}
     *            reference
     */
    private void addKS(KnowledgeSource ks) {
    	this.add(ks);
    }

}
