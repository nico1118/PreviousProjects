import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.regex;



public class LexicalAnalyzer {
	File input = null;
	int size;
	Token tokenArray[];
	boolean comment;
	String commentString = null;
	int i;
	Token[] token;

	LexicalAnalyzer(File filename) {
		this.input = filename; // Create a File Object using filename given by main Compiler
		this.size = (int) (input.length() * 9 / 10);
		this.tokenArray = new Token[size];
	}
	Token[] getTokens() {
		token = new Token[i+1];
		for (int j = 0;j<=i;j++) {
			token[j] = tokenArray[j];
			//System.out.println(token[j] + " = " + tokenArray[j]);
		}
		return token;
	}
	int getSize() {
		return i+1;
	}
	void output() {
		try (PrintWriter printer = new PrintWriter("token.parse");) {
			if (tokenArray[0] == null) {
				System.out.println("The token Array is Empty");
			} // end if
			else {
				// System.out.println("Beginning of the Token File");
				// System.out.println(
				// "-------------------------------------------------------------------------------------------------");
				for (int j = 0; j < tokenArray.length; j++) {
					if (tokenArray[j] == null) {
					} else {
						printer.println(tokenArray[j]);
						// System.out.println(tokenArray[j]);
					}
				}
				// System.out.println(
				// "-------------------------------------------------------------------------------------------------");
				// System.out.println("End of the Token File");
				// System.out.println(
				// "The actual file is named token.parse and will not be used as an external
				// input for the parser.\n"
				// + "The program puts all the tokens into an array that will be passed to
				// project 2 instead.\n"
				// + "The External File is just for testing purposes as with the messages
				// printed to the screen.\n"
				// + "Any Comments and errors will not show up in the token.parse document, but
				// will be printed to the screen.\n");
				// System.out.println(
				// "If there are any errors or comments, they will also be included in the token
				// file for now, but in the future these \" tokens \" will be removed");
			}
		} catch (FileNotFoundException ef) {
			ef.printStackTrace();
		}

	}

	// Analyzes Data, Creates Tokens, and Stores tokens in a file called
	// tokens.parse
	void analyze() {
		// System.out.println(size);
		try (FileReader f = new FileReader(input); BufferedReader br = new BufferedReader(f);) {

			String line = null;
			while ((line = br.readLine()) != null) {
				// Input from file will be line by line

				line = line.trim(); // trims line of extra spaces at beginning and end of line
				if (!line.equals(""))
					//System.out.println("INPUT: " + line);
				line = parseLine(line);

			}
			tokenArray[i] = new Token("$");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// end analyze

	private String parseLine(String line) {
		char[] arrayC = line.toCharArray();
		String string = "";
		boolean lineComment = false;
		for (int j = 0; j < arrayC.length; j++) {
			switch (arrayC[j]) {
			case ('/'): {
				if (j == 0) {
					if (arrayC[j + 1] == '*') {

					} else if (arrayC[j + 1] == '/') {
						comment = true;
						lineComment = true;
					} else
						addToTokenArray("/");
				} else {
					if (arrayC[j - 1] == '*') { // if previous was *
						if (comment) { // if previous was * and comment is true
							comment = false;
							// addToCommentString('/');
							// addToTokenArray("Comment", commentString);
							commentString = null;
						} else
							addToTokenArray("/");
					} else if (arrayC[j - 1] == '/') { // if there was a / before

					} else if (j != arrayC.length - 1) { // if not at end
						if (!comment) {
							if (arrayC[j + 1] == '/') { // if next is /
								lineComment = true;
								comment = true;
								lineComment = true;
								// addToCommentString('/');
							} else {
								if (!comment) {
									if (string == "") {
									} else if (checkKeyword(string)) {

									} else if (checkAlpha(string)) {
										addToTokenArray("id", string);
									} else {
										addToTokenArray("Error", string);

									}
									string = "";
									if (j != arrayC.length) {
										if (arrayC[j + 1] == '*') {
										} else
											addToTokenArray("/");
									} else if (arrayC[j - 1] == '*') {
									} else
										addToTokenArray("/");
								}

							}
						} else {
							// addToCommentString('/');
						}
					}
				}

				break;
			} // end case /
			case ('*'): {
				if (j == 0) {
					if (arrayC[j + 1] == '/') {
						/// addToCommentString('*');
					}
				} else {
					if (arrayC[j - 1] == '/') {
						if (!comment) {
							comment = true;
							// addToCommentString('/');
							// addToCommentString('*');
						}
					} else {
						if (comment) {
							// addToCommentString('*');
						} else {
							if (string == "") {
							} else if (checkKeyword(string)) {
								addToTokenArray("KW", string);

							} else if (checkAlpha(string)) {
								addToTokenArray("id", string);
							} else
								addToTokenArray("Error", string);
							string = "";
							addToTokenArray("*");
						}

					}
				}
				break;
			} // end case *
			case ('('): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else if (checkAlpha(string))
							addToTokenArray("id", string);
						else
							addToTokenArray("Error", string);
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;
			} // end case (
			case (')'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkInteger(string))
								addToTokenArray("NUM", string);
							else if (checkAlpha(string))
								addToTokenArray("id", string);

						}
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;

			} // end case )
			case ('{'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
						}
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;
			}
			case ('}'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
						}
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					break;
				}
			}
			case ('['): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
						}
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;
			}
			case (']'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
						}
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;
			}
			case ('-'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else if (checkAlpha(string))
							addToTokenArray("id", string);
						else
							addToTokenArray("Error", string);
						string = "";
					}
					addToTokenArray("" + arrayC[j]);
					
				}break;
			} // end case (
			case (';'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
					}
					

				}
				addToTokenArray("" + arrayC[j]);
				break;
			}
			case (','): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
						
					}
					addToTokenArray(",");
					
				}break;
			} // case ,
			case ('>'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
					}
					if (j != arrayC.length - 1) {
						if (arrayC[j + 1] == '=') {
						} else
							addToTokenArray("" + arrayC[j]);
					}
					
				}break;
			} // end case >
			case ('<'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
					}
					if (j != arrayC.length - 1) {
						if (arrayC[j + 1] == '=') {
						} else
							addToTokenArray("" + arrayC[j]);
					}
					
				}break;
			} // end case <
			case ('+'): {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else {
					if (string != "") {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ",string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else if (checkInteger(string))
								addToTokenArray("NUM", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
					}
					if (j != arrayC.length - 1) {
						if (arrayC[j + 1] == '=') {
						} else
							addToTokenArray("" + arrayC[j]);
					}
					
				}break;
			} // end case +
			case ('='): {
				if (!comment) {
					if (j == 0) {

					} else {
						char next;
						char prev = arrayC[j - 1];
						if (prev == '!') {
							addToTokenArray("!=");
							string = "";
						} else if (prev == '>') {
							addToTokenArray(">=");
						} else if (prev == '<') {
							addToTokenArray("<=");
						} else if (prev == '=') {
							addToTokenArray("==");
						} else {
							if (j == arrayC.length - 1) {
								addToTokenArray("=");
							} else {
								next = arrayC[j + 1];
								if (next == '=') {

								} else {
									if (string != "") {
										if (checkKeyword(string)) {
											// addToTokenArray("KW: ",string);
										} else {
											if (checkAlpha(string))
												addToTokenArray("id", string);
											else if (checkInteger(string))
												addToTokenArray("NUM", string);
											else
												addToTokenArray("Error", string);
										}
										
									}
									string = "";
									addToTokenArray("=");
								}
							}
							if (string != "") {
								if (checkKeyword(string)) {
									// addToTokenArray("KW: ",string);
								} else {
									if (checkAlpha(string))
										addToTokenArray("id", string);
									else if (checkInteger(string))
										addToTokenArray("NUM", string);
									else
										addToTokenArray("Error", string);
								}
								string = "";
							}
						}
					}
				}
				if (comment) {
					// addToCommentString(arrayC[j]);
				}
				break;
			} // case =
			default: {
				if (comment) {
					// addToCommentString(arrayC[j]);
				} else if (arrayC[j] == ' ') {
					if (string == "") {

					} else {
						if (checkKeyword(string)) {
							// addToTokenArray("KW: ", string);
						} else if (checkInteger(string)) {
							addToTokenArray("NUM", string);
						} else {
							if (checkAlpha(string))
								addToTokenArray("id", string);
							else
								addToTokenArray("Error", string);
						}
						string = "";
					}
				} else {
					string += arrayC[j];
				}
			}
			}// end switch
		}
		if (string != "") {
			if (checkInteger(string)) {
				addToTokenArray("NUM", string);
				string = "";
			} else if (checkAlpha(string)) {
				if (checkKeyword(string)) {
				}
				// addToTokenArray("KW", string);
				else {
					addToTokenArray("id", string);
				}
				string = "";
			} else
				addToTokenArray("Error", string);
		}
		if (lineComment) {
			comment = false;
		}
		return string;
	}

	private boolean checkAlpha(String string) {
		boolean test = string.matches("^[a-zA-Z]*$");

		return test;
	}

	private boolean checkInteger(String testString) {
		boolean isInteger = false;
		testString.trim();

		try {
			Integer.parseInt(testString);
			isInteger = true;
		} catch (NumberFormatException ex) {

		}
		return isInteger;
	}

	// Checks String for keyword and adds token to tokenArray if Keyword is found
	private boolean checkKeyword(String string) {
		switch (string) {

		case ("else"):
		case ("if"):
		case ("int"):
		case ("return"):
		case ("void"):
		case ("while"):
			addToTokenArray("KW", string);
			return true;
		default:
			return false;
		}// end switch

	}// end checkKeyword
		// Adds the token type and the token string to the token array

	private void addToTokenArray(String string, String string2) {
		if (string.equals("Error")) {
			//System.out.println("Error: " + string2);
		} else {
			if (string == "KW") {
				this.tokenArray[this.i] = new Token(string + " " + string2);
			}
			else if (string == "id" || string == "NUM") {
				this.tokenArray[this.i] = new Token(string + " " + string2);
			}
			
			//this.tokenArray[this.i] = string + ": " + string2;
			// System.out.println("Added \"" + tokenArray[this.i] + "\" to the array of
			// tokens.");
			//System.out.println(tokenArray[i]);
			++i;
		}
	}// end addToTokenArray
		// This method is used when the token type and token string are the same

	private void addToTokenArray(String string) {
		this.tokenArray[this.i] = new Token(string);
		// System.out.println("Added \"" + tokenArray[this.i] + "\" to the array of
		// tokens.");
		//System.out.println(tokenArray[i]);
		++i;
	}// end addToTokenArray

}
