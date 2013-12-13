


import java.io.*;
import java.util.*;


public class search {
	static ArrayList<String> dictionary = new ArrayList<String>();
	static Map<String,ArrayList<String>> wordMap = new TreeMap<String,ArrayList<String>>();
	

	public static void read() throws IOException{
		String newWord;	
		File file = new File("dictionary.txt");
		Scanner s = new Scanner(file);

		while(s.hasNext())
		{			
			newWord = s.nextLine();
			dictionary.add(newWord);			
		}
		s.close();
	}

	private static <KeyType> void update( Map<KeyType, ArrayList<String>> m, KeyType key, String value )
	{
		//places words into array by String length
		ArrayList<String> lst = m.get(key);
		if(lst == null)
		{
			lst = new ArrayList<>();
			m.put(key, lst);
		}
		if(key != value && !lst.contains(value)){
			lst.add(value);
		}		
	}
    // algorithm found and modified from Data Structures and Algorithm Analysis in Java, 3rd Edition
	// Computes a map in which the keys are words and values are Lists of words
	// that differ in only one character from the corresponding key.
	
	public static Map<String, ArrayList<String>> computeAdjacentWords( ArrayList<String> words ){
		Map<String,ArrayList<String>> adjWords = new TreeMap<>( );
		Map<Integer,ArrayList<String>> wordsByLength = new TreeMap<>( );

		// Group the words by their length
		for( String w : words )
			update( wordsByLength, w.length( ), w );

		ArrayList<String> lastGroupWords = new ArrayList<String>();
		// Work on each group separately
		for(Map.Entry<Integer,ArrayList<String>> entry : wordsByLength.entrySet())
		{
			ArrayList<String> groupsWords = entry.getValue();			
			int groupNum = entry.getKey( );

			// Work on each position in each group
			for( int i = 0; i < groupNum; i++ )
			{
				// Remove one character in specified position, computing
				// representative. Words with same representative are
				// adjacent, so first populate a map ...
				Map<String,ArrayList<String>> repToWord = new TreeMap<>( );

				for( String str : groupsWords ) {
					String rep = str.substring( 0,i)+ str.substring( i + 1 );
					update( repToWord, rep, str );
				}				

				// and then look for map values with more than one string
				for( Map.Entry<String,ArrayList<String>> repToWordEntry : repToWord.entrySet( ) ){
					ArrayList<String> wordClique = repToWordEntry.getValue();
					String mutation = repToWordEntry.getKey();

					if( wordClique.size( ) == 1 ){
						for( String s1 : wordClique ){
							if (lastGroupWords.contains(mutation)){
								update( adjWords, s1, mutation );
								update( adjWords, mutation, s1 );									
							}
						}
					}

					else if( wordClique.size( ) >= 2 ){
						for( String s1 : wordClique ){
							if (lastGroupWords.contains(mutation)){
								update( adjWords, s1, mutation );
								update( adjWords, mutation, s1 );									
							}
							for( String s2 : wordClique ){
								if( s1 != s2 ){
									update( adjWords, s1, s2 );									
								}										
							}
						}					
					}										
				}
			}
			lastGroupWords = groupsWords;
		}
		
		return adjWords;
	}

	public static void  printPathOne(){
		try{
			PrintWriter writer = new PrintWriter("output1.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();
				Collections.sort(adjWords);
				for(String nextWord: adjWords){
					writer.println(word + ","+ nextWord);
				}
			}
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void  printPathTwo(){
		try{
			PrintWriter writer = new PrintWriter("output2.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();
				Collections.sort(adjWords);
				for(String nextWord: adjWords){
					if (!word.equals(nextWord)){
						ArrayList<String>nextAdjWords =wordMap.get(nextWord);
						Collections.sort(nextAdjWords);
						for(String endWord: nextAdjWords){
							if  (!endWord.equals(nextWord) && !endWord.equals(word)){
								writer.println(word + ","+ nextWord + "," + endWord);
								
							}
						}
					}
				}
			}
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void  printPathThree(){
		try{
			PrintWriter writer = new PrintWriter("output3.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();
				Collections.sort(adjWords);
				for(String nextWord: adjWords){
					if (!word.equals(nextWord)){
						ArrayList<String>nextAdjWords =wordMap.get(nextWord);
						Collections.sort(nextAdjWords);
						for(String anothernextWord: nextAdjWords){
							if  (!anothernextWord.equals(nextWord) && !anothernextWord.equals(word)){
								ArrayList<String>finalWords =wordMap.get(anothernextWord);
								Collections.sort(finalWords);
								for(String endWord: finalWords){
									if (!endWord.equals(anothernextWord) && !endWord.equals(nextWord) 
											&& !endWord.equals(word))
										writer.println(word + ","+ nextWord + "," + anothernextWord +"," + endWord);
								}
							}
						}
					}
				}
			}
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void  printPathFour(){
		try{
			long counter = 0;
			PrintWriter writer = new PrintWriter("output4.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();//String 1
				Collections.sort(adjWords);
				for(String nextWord: adjWords){//String 2
					if (!word.equals(nextWord)){
						ArrayList<String>nextAdjWords =wordMap.get(nextWord);
						Collections.sort(nextAdjWords);
						for(String anothernextWord: nextAdjWords){//String 3
							if  (!anothernextWord.equals(nextWord) && !anothernextWord.equals(word)){
								ArrayList<String>finalWords =wordMap.get(anothernextWord);
								Collections.sort(finalWords);
								for(String endWord: finalWords){//String 4
									if (!endWord.equals(anothernextWord) && !endWord.equals(nextWord) 
											&& !endWord.equals(word)){
										ArrayList<String> thefinalWords =wordMap.get(endWord);
										Collections.sort(thefinalWords);
										for (String finalendword : thefinalWords){//String 5
											if(!finalendword.equals(word) && !finalendword.equals(nextWord) &&
											    !finalendword.equals(anothernextWord) && !finalendword.equals(endWord)){
												//writer.println(word + ","+ nextWord + "," + anothernextWord +"," + endWord + "," + finalendword);
												counter++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			writer.println(counter + " matches found");
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void  printPathFive(){
		long counter = 0;
		try{
			PrintWriter writer = new PrintWriter("output5.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();
				Collections.sort(adjWords);
				for(String nextWord: adjWords){
					if (!word.equals(nextWord)){
						ArrayList<String>nextAdjWords = wordMap.get(nextWord);
						for(String anothernextWord: nextAdjWords){
							if  (!anothernextWord.equals(nextWord) && !anothernextWord.equals(word)){
								ArrayList<String> nextToLast = wordMap.get(anothernextWord);
								for(String almostEndWord: nextToLast){
									if(!almostEndWord.equals(word) && !almostEndWord.equals(nextWord) &&
											!almostEndWord.equals(anothernextWord)){
											ArrayList<String>finalWords = wordMap.get(almostEndWord);
											for(String endWord: finalWords){
												if (!endWord.equals(anothernextWord) && !endWord.equals(almostEndWord) 
														&& !endWord.equals(word)){
													ArrayList<String>extraFinalWords = wordMap.get(endWord);
													for(String extraEndWord: extraFinalWords){
														if (!extraEndWord.equals(word) && !extraEndWord.equals(nextWord)&& 
															!extraEndWord.equals(anothernextWord) && !extraEndWord.equals(endWord)){
															counter++;
														}
													}
												}		
											}	
									}
								}
							}
						}
					}
				}
			}
			writer.println(counter);
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void  printPathSix(){
		long counter = 0;
		try{
			PrintWriter writer = new PrintWriter("output6.txt");
			for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
				ArrayList<String> adjWords = wordMapEntry.getValue();
				String word = wordMapEntry.getKey();
				Collections.sort(adjWords);
				for(String nextWord: adjWords){
					if (!word.equals(nextWord)){
						ArrayList<String>nextAdjWords = wordMap.get(nextWord);
						for(String anothernextWord: nextAdjWords){
							if  (!anothernextWord.equals(nextWord) && !anothernextWord.equals(word)){
								ArrayList<String> nextToLast = wordMap.get(anothernextWord);
								for(String almostEndWord: nextToLast){
									if(!almostEndWord.equals(word) && !almostEndWord.equals(nextWord) &&
											!almostEndWord.equals(anothernextWord)){
											ArrayList<String>finalWords = wordMap.get(almostEndWord);
											for(String endWord: finalWords){
												if (!endWord.equals(anothernextWord) && !endWord.equals(nextWord) 
														&& !endWord.equals(word)){
													ArrayList<String>extraFinalWords = wordMap.get(endWord);
													for(String extraEndWord: extraFinalWords){
														if (!extraEndWord.equals(word) && !extraEndWord.equals(nextWord)&& 
															!extraEndWord.equals(anothernextWord) && !extraEndWord.equals(endWord)){
															ArrayList<String>absoluteFinalWords = wordMap.get(extraEndWord);
															if (!absoluteFinalWords.equals(word) && !absoluteFinalWords.equals(nextWord)&& 
																!absoluteFinalWords.equals(anothernextWord) && !absoluteFinalWords.equals(endWord)&&
																!absoluteFinalWords.equals(extraEndWord) ){
																counter++;
															}	
														}
													}
												}		
											}	
									}
								}
							}
						}
					}
				}
			}
			writer.println(counter);
			writer.close();
		}
		catch (IOException e){}
	}
	
	public static void main(String[]args){
		long startTime = System.nanoTime();
		int numToFind = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a number 1-6 to find entries");
		numToFind = s.nextInt();
		
			
		if (numToFind > 6 || numToFind < 1){
			System.out.println("Invalid input");	
		}
		else{
			try{
				read();
			}
			catch(IOException e){
				System.out.println(e);
			}
			wordMap = computeAdjacentWords(dictionary);
			if (numToFind == 1)
				printPathOne();	
			else if (numToFind == 2)
				printPathTwo();
			else if (numToFind == 3)
				printPathThree();
			else if (numToFind == 4)
				printPathFour();
			else if (numToFind == 5)
				printPathFive();
			else if (numToFind == 6)
				printPathSix();			
		}
			

		s.close();	
		long endTime = System.nanoTime();
		System.out.println("Time to complete " +( endTime - startTime)/1000000000 + " seconds!");		
	}
}

//for( Map.Entry<String,ArrayList<String>> wordMapEntry : wordMap.entrySet( ) ){
//			ArrayList<String> adjWords = wordMapEntry.getValue();
//			Collections.sort(adjWords);
//			String word = wordMapEntry.getKey();
//			for(String W: adjWords){
//				System.out.println(word + ","+ W);
//			}
