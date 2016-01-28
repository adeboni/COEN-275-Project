package knowledge;

import java.util.ArrayList;

public final class KnowledgeSourcesImpl extends ArrayList<KnowledgeSource> implements KnowledgeSources {

	/**
	 * 
	 */
	private static final long serialVersionUID = 719857338445596430L;

	/**
	 * Default constructor
	 */
	public KnowledgeSourcesImpl() {
	}

	/**
	 * Public method to initialize all knowledge sources
	 * 
	 * @throws CollectionLoadingException
	 */
	public void init() {
		loadKnowledgeSources();
		initializeKnowledgeSources();
	}

	/**
	 * Public method to clear and initialize all knowledge sources
	 * 
	 * @throws CollectionLoadingException
	 */
	public void reset() {

		/**
		 * Clear array
		 */

		this.clear();

		/**
		 * Load all Knowledge Sources
		 */

		loadKnowledgeSources();
		initializeKnowledgeSources();

	}


	public void loadKnowledgeSources() {

		RegexWordKnowledgeSource regexKnowledgeSource = new RegexWordKnowledgeSource();
		addKS(regexKnowledgeSource);

		SmallWordKnowledgeSource smallWordKnowledgeSource = new SmallWordKnowledgeSource();
		addKS(smallWordKnowledgeSource);

		WordStructureKnowledgeSource wordStructureKnowledgeSource = new WordStructureKnowledgeSource();
		addKS(wordStructureKnowledgeSource);

		LegalStringKnowledgeSource legalStringKnowledgeSource = new LegalStringKnowledgeSource();
		addKS(legalStringKnowledgeSource);

		DoubleLetterKnowledgeSource doubleLetterKnowledgeSource = new DoubleLetterKnowledgeSource();
		addKS(doubleLetterKnowledgeSource);

	}

	/**
	 * Public method to iterate over all KnowledgeSources, load rules and
	 * context
	 */
	public void initializeKnowledgeSources() {
		for (KnowledgeSource knowledgeSource : this) {
			loadRulesAndContext(knowledgeSource);
		}
	}

	private void loadRulesAndContext(KnowledgeSource ks) {

		// TODO

	}

	private void addKS(KnowledgeSource ks) {
		this.add(ks);
	}

}
