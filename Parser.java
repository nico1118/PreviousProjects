
public class Parser {
	private Token[] tokens;
	private int index = 0;
	private String NT;
	private String NNT;

	Parser(Token[] array) {
		this.tokens = array;
		if (tokens[index].getT().equals("id") || tokens[index].getT().equals("NUM"))
		this.NT = tokens[0].getT();
		else
			this.NT = tokens[0].getV();
	}

	public void accept(String token) {
		if (token.equals(NT)) {
			index++;

			if (index == tokens.length) {
			} else {
				if (tokens[index].getT().equals("id") || tokens[index].getT().equals("NUM"))
					this.NT = tokens[index].getT();
					else
						this.NT = tokens[index].getV();
				
				if (index + 1 == tokens.length) {
				} else
					if (tokens[index].getT().equals("id") || tokens[index].getT().equals("NUM"))
						this.NNT = tokens[index].getT();
						else
							this.NNT = tokens[index].getV();
			}
		} else
			reject();
	}

	public void reject() {
		System.out.println("REJECT");
		System.exit(1);
	}

	public void Start() {
		A();
		if (NT.equals("$")) {
			System.out.println("ACCEPT");
			System.exit(0);
		} else
			reject();
	}

	private void A() {
		B();
		Aprime();
	}

	private void Aprime() {
		if (NT.equals("$")) {
		} else {
			B();
			Aprime();
		}

	}

	private void B() {

		D();
		accept("id");
		Bprime();

	}

	private void Bprime() {
		if (NT.equals(";")) {
			accept(";");
		}
		if (NT.equals("[")) {
			accept("[");
			accept("NUM");
			accept("]");
			accept(";");
		}
		if (NT.equals("(")) {
			accept("(");
			F();
			accept(")");
			I();
		}
	}

	private void C() {
		D();
		accept("id");
		Cprime();
	}

	private void Cprime() {
		if (NT.equals(";")) {
			accept(";");
		}
		if (NT.equals("[")) {
			accept("[");
			accept("NUM");
			accept("]");
			accept(";");
		}
	}

	private void D() {
		if (NT.equals("int")) {
			accept("int");
		}
		if (NT.equals("void")) {
			accept("void");
		}
	}

	private void F() {
		if (NT.equals("void") && NNT.equals(")")) {
			accept("void");
		}
		if (NT.equals("int") || NT.equals("void")) {
			G();
		}
	}

	private void G() {
		H();
		Gprime();
	}

	private void Gprime() {
		if (NT.equals(",")) {
			accept(",");
			H();
			Gprime();
		}
		else if (NT.equals(")")) { return;
		}
		else
			reject();
	}

	private void H() {
		D();
		if (!tokens[index-1].getV().equals("void"))
		accept("id");
		Hprime();
	}

	private void Hprime() {
		if (NT.equals("[")) {
			accept("[");
			accept("]");
		}
		else if (NT.equals(",") || NT.equals(")")) { return;
		}
		else
			reject();
	}

	private void I() {
		accept("{");
		J();
		K();
		accept("}");
	}

	private void J() {
		Jprime();
	}

	private void Jprime() {
		if (NT.equals("id") || NT.equals("(") || NT.equals("NUM") || NT.equals(";") || NT.equals("{") || NT.equals("if")
				|| NT.equals("while") || NT.equals("return")) { return;

		} else if (NT.equals("int") || NT.equals("void")) {
			C();
			Jprime();
		}
		else
			reject();
	}

	private void K() {
		Kprime();
	}

	private void Kprime() {
		if (NT.equals("}")) { return;
		} else if (NT.equals("id") || NT.equals("(") || NT.equals("NUM") || NT.equals(";") || NT.equals("{")
				|| NT.equals("if") || NT.equals("while") || NT.equals("return")) {
			L();
			Kprime();
		}
		else
			reject();
	}

	private void L() {
		if (NT.equals("id") || NT.equals("(") || NT.equals("NUM") || NT.equals(";")) {
			M();
		}
		if (NT.equals("{")) {
			I();
		}
		if (NT.equals("if")) {
			N();
		}
		if (NT.equals("while")) {
			O();
		}
		if (NT.equals("return")) {
			P();
		}
	}

	private void M() {
		if (NT.equals(";")) {
			accept(";");
		}
		if (NT.equals("id") || NT.equals("(") || NT.equals("NUM")) {
			Q();
			accept(";");
		}
	}

	private void N() {
		accept("if");
		accept("(");
		Q();
		accept(")");
		L();
		Nprime();
	}

	private void Nprime() {
		if (NT.equals("else")) {
			accept("else");
			L();
		}
		else if (NT.equals("id") || NT.equals("(") || NT.equals("NUM") || NT.equals(";") || NT.equals("{") || NT.equals("if")
				|| NT.equals("while") || NT.equals("return") || NT.equals("else") || NT.equals("}")) { return;
		}
		else
			reject();
	}

	private void O() {
		accept("while");
		accept("(");
		Q();
		accept(")");
		L();
	}

	private void P() {
		accept("return");
		Pprime();
	}

	private void Pprime() {
		if (NT.equals(";")) {
			accept(";");
		} else {
			Q();
			accept(";");
		}
	}

	private void Q() {
		if (NT.equals("id")) {
			accept("id");
			Rprime();
			Qprime();
		}
		if (NT.equals("(")) {
			accept("(");
			Q();
			accept(")");
			Xprime();
			Vprime();
			Tprime();
		}
		if (NT.equals("NUM")) {
			accept("NUM");
			Xprime();
			Vprime();
			Tprime();
		}
	}

	private void Qprime() {
		if (NT.equals("=")) {
			accept("=");
			Q();
		}
		if (NT.equals("*") || NT.equals("/") || NT.equals("+") || NT.equals("-") || NT.equals("<=") || NT.equals("<")
				|| NT.equals(">") || NT.equals(">=") || NT.equals("==") || NT.equals("!=")) {
			Xprime();
			Vprime();
			Tprime();
		}
	}

	private void R() {
		accept("id");
		Rprime();
	}

	private void Rprime() {
		if (NT.equals("[")) {
			accept("[");
			Q();
			accept("]");
		}
		else if (NT.equals("(")) {
			accept("(");
			Beta();
			accept(")");
		}
		else if (NT.equals("=") || NT.equals("*") || NT.equals("/") || NT.equals("+") || NT.equals("-") || NT.equals("<=")
				|| NT.equals("<") || NT.equals(">") || NT.equals(">=") || NT.equals("==") || NT.equals("!=")
				|| NT.equals(";") || NT.equals(")") || NT.equals("]")) { return;
		}
		else
			reject();
	}

	private void T() {
		V();
		Tprime();
	}

	private void Tprime() {
		if (NT.equals(";") || NT.equals(")") || NT.equals("]")) { return;
		}
		else if (NT.equals("<=") || NT.equals("<") || NT.equals(">") || NT.equals(">=") || NT.equals("==")
				|| NT.equals("!=")) {
			U();
			V();
		}
		else
			reject();

	}

	private void U() {
		if (NT.equals("<=")) {
			accept("<=");
		}
		if (NT.equals("<")) {
			accept("<");
		}
		if (NT.equals(">")) {
			accept(">");
		}
		if (NT.equals(">=")) {
			accept(">=");
		}
		if (NT.equals("==")) {
			accept("==");
		}
		if (NT.equals("!=")) {
			accept("!=");
		}
	}

	private void V() {
		X();
		Vprime();
	}

	private void Vprime() {
		if (NT.equals("<=") || NT.equals("<") || NT.equals(">") || NT.equals(">=") || NT.equals("==") || NT.equals("!=")
				|| NT.equals(";") || NT.equals(")") || NT.equals("]")) { return;
		}
		else if (NT.equals("+") || NT.equals("-")) {
			W();
			X();
			Vprime();
		}
		else
			reject();
	}

	private void W() {
		if (NT.equals("+"))
			accept("+");

		if (NT.equals("-"))
			accept("-");
	}

	private void X() {
		Z();
		Xprime();
	}

	private void Xprime() {
		if (NT.equals("+") || NT.equals("-") || NT.equals("<=") || NT.equals("<") || NT.equals(">") || NT.equals(">=")
				|| NT.equals("==") || NT.equals("!=") || NT.equals(";") || NT.equals(")") || NT.equals("]")) { return;
		}
		else if (NT.equals("*") || NT.equals("/")) {
			Y();
			Z();
			Xprime();
		}
		else
			reject();
	}

	private void Y() {
		if (NT.equals("*"))
			accept("*");
		if (NT.equals("/"))
			accept("/");

	}

	private void Z() {
		if (NT.equals("(")) {
			accept("(");
			Q();
			accept(")");
		}
		if (NT.equals("id")) {
			R();
		}
		if (NT.equals("NUM")) {
			accept("NUM");
		}
	}

	private void Beta() {
		if (NT.equals(")")) { return;
		}
		else if (NT.equals("id") || NT.equals("(") || NT.equals("NUM")) {
			Omega();
		}
		else
			reject();
	}

	private void Omega() {
		Q();
		Omegaprime();
	}

	private void Omegaprime() {
		if (NT.equals(",")) {
			accept(",");
			Q();
			Omegaprime();
		}
		else if (NT.equals(")")) { return;
		}
		else
			reject();
	}

}
