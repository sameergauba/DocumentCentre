package com.ttn.geekcombat.dtos;

import com.ttn.geekcombat.enums.PartsOfSpeech;

/**
 * Created by sameer on 25/9/17.
 */
public class ApacheNLPWordClassificationDTO {

    private String word;
    private String partOfSpeech;
    private double probability;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
