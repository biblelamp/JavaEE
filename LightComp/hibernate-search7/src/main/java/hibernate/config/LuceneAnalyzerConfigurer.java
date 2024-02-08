package hibernate.config;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

public class LuceneAnalyzerConfigurer implements LuceneAnalysisConfigurer {

	@Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        context.analyzer("cz")
				.custom()
        		.tokenizer(KeywordTokenizerFactory.class)
        		.tokenFilter(LowerCaseFilterFactory.class)
        		.tokenFilter(ASCIIFoldingFilterFactory.class);
    }
}
