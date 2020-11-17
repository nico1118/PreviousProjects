
public class Token {
	
	private String tok;
	private String var;
	
	Token(String s1){
		String[] string = s1.split(" ");
		this.tok = string[0].toString();
		if (string.length >= 2)
			this.var = string[1].toString();
		else
			this.var = this.tok;
	}
	
	public String getT(){
		return tok;
	}
	public String getV() {
		return var;
	}
	
}
