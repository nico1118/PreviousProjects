import java.io.File;


public class mainCompiler {

	public static void main(String[] args) {
		File inputfile = new File(args[0]);
		
		//System.out.println("Can the file be read? "+ inputfile.canRead());
		Token[] token;

		if (inputfile.canRead()) {
				LexicalAnalyzer la = new LexicalAnalyzer(inputfile);
				la.analyze();
				token = new Token[la.getSize()];
				token = la.getTokens();
				Parser parse = new Parser(token);
				parse.Start();
		}
		else
			System.out.println("File not Found");

	}
}