// Bryan HO, 200918380


class WordCount {

  public static void main(String[] args) {
      String textInput = args[0];
      int count = 0;
      
      for (int i = 0; i < textInput.length()-1; i++) {                    
	if ( (Character.isWhitespace(textInput.charAt(i)) == true) &&
           ((Character.isWhitespace(textInput.charAt(i+1))) == false) ) {
              count++;
	   		}
	
      }
      if (Character.isWhitespace(textInput.charAt(0)) == true) {
          System.out.println(count);
      }
      else {
      System.out.println(count +1);  	    
      }  
    }

}