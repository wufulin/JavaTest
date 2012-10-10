package com.wufulin.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;

public class AnalyzerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String enGoodsTitle="$25 for 700-900grams Sri Lankan Crab at House of Seafood (Choice of Black Pepper / White Pepper / Chilli / Salted Egg / Golden Butter / Steamed / Stewed / Bee Hoon Crab Soup)";
		Analyzer enAnalyzer=new StandardAnalyzer(Version.LUCENE_36);
		TokenStream tokenStream= enAnalyzer.tokenStream(null, new StringReader(enGoodsTitle));
		
		CharTermAttribute termAtt=(CharTermAttribute)tokenStream.getAttribute(CharTermAttribute.class);
//		TypeAttribute typeAtt=(TypeAttribute)tokenStream.getAttribute(TypeAttribute.class);
		
		try {
			while(tokenStream.incrementToken()){
				System.out.println(new String(termAtt.buffer(),0,termAtt.length()));
//				System.out.println("\t"+typeAtt.type());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
