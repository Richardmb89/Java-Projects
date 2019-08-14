
public class Pandigm {
	//Roy wanted to increase his typing speed for programming contests. His friend
	//suggested that he type the sentence "The quick brown fox jumps over the lazy dog" repeatedly. This
	//sentence is known as a pangram because it contains every letter of the alphabet.

	//After typing the sentence several times, Roy became bored with it so he started to look for other pangrams.

	//Given a sentence, determine whether it is a pangram. Ignore case.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Test = "We promptly judged antique ivory buckles for the next prize";
		System.out.print(pangram(Test));
	}

	private static String pangram(String s) {
		// TODO Auto-generated method stub
		String Not,Yes;
		Not="Not Pangram";
		Yes = "Pangram!";
		String S = s.toLowerCase();
		
		if(s.length()<26)
		{
			return Not;
		}
		else
		{
			for(char c = 'a'; c <= 'z';c++ )
				if(S.indexOf(c)<0)
				{
					return Not;
				}
		}
		return Yes;
	}

}
