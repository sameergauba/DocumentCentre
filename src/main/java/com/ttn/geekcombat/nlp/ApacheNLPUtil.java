package com.ttn.geekcombat.nlp;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ttn.geekcombat.dtos.ApacheNLPWordClassificationDTO;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
/**
 * Created by sameer on 25/9/17.
 */
public class ApacheNLPUtil {


    public static List<ApacheNLPWordClassificationDTO> getPartsOfSpeechFromString(String document) throws IOException{
        List<ApacheNLPWordClassificationDTO> apacheNLPWordClassificationDTOS = new ArrayList<>();
        InputStream inputStream = new FileInputStream("src/main/resources/apache/opennlp/en-pos-maxent.bin");
        POSModel model = new POSModel(inputStream);

        // Instantiating POSTaggerME class
        POSTaggerME tagger = new POSTaggerME(model);

        // Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(document);

        // Generating tags
        String[] tags = tagger.tag(tokens);

        // Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);
        String[] words = sample.getSentence();
        String[] partsOfSpeech = sample.getTags();

        double [] probs = tagger.probs();

        apacheNLPWordClassificationDTOS = getApacheNLPDTOs(words, partsOfSpeech, probs, apacheNLPWordClassificationDTOS);

        return apacheNLPWordClassificationDTOS;
    }

    private static List<ApacheNLPWordClassificationDTO> getApacheNLPDTOs(String[] words, String[] partsOfSpeech, double[] probs, List<ApacheNLPWordClassificationDTO> apacheNLPWordClassificationDTOS) {
        if(words != null && words.length>0){
            for(int i=0; i<words.length; i++){
                ApacheNLPWordClassificationDTO dto = new ApacheNLPWordClassificationDTO();
                dto.setWord(words[i]);
                dto.setPartOfSpeech(partsOfSpeech[i]);
                dto.setProbability(probs[i]);
            }
        }
        return apacheNLPWordClassificationDTOS;
    }


}


