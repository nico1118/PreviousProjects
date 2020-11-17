/*Variables*/
var creditForm = 
	'<form name="pForm" onSubmit="return validateForm();">' +
		'<label class="list">First Name:</label>' +
			'<input class="inputs"  type="text" name="firstName" required><br>' +
		'<label class="list">Last Name:</label>' +
			'<input class="inputs"  type="text" name="lastName" required><br>' +
		'<label class="list">Address:</label>' +
			'<input class="inputs"  type="text" name="address" required><br>' +
		'<label class="list">City:</label>' +
			'<input class="inputs"  type="text" name="city" required><br>' +
		'<label class="list">Zip:</label>' +
			'<input class="inputs"  type="text" name="zipcode" required><br>' +
		'<label class="list">Email Address:</label>' +
			'<input class="inputs" type="email" name="ccEmail" required><br>' +
		'<label class="list">Name on Card:</label>' +
		'<input type="text" name="nameOnCard" required><br>' +
		'<label class="list">Card Number:</label>' +
		'<input type="text" name="cardNumber" required><br>' +
		'<label class="list"><a href="https://en.wikipedia.org/wiki/Card_security_code" target="_blank">CVV2/CVC</a>:</label>' +
		'<input type="text" name="cvc" required><br>' +
		'<label class="list">State:</label>' +
		'<select id="state" class="inputs">' +
			'<option value="">Select a State</option>' +
			'<option value="Alabama">Alabama</option>' +
			'<option value="Alaska">Alaska</option>' +
			'<option value="Arizona">Arizona</option>' +
			'<option value="Arkansas">Arkansas</option>' +
			'<option value="California">California</option>' +
			'<option value="Colorado">Colorado</option>' +
			'<option value="Connecticut">Connecticut</option>' +
			'<option value="Delaware">Delaware</option>' +
			'<option value="Florida">Florida</option>' +
			'<option value="Georgia">Georgia</option>' +
			'<option value="Hawaii">Hawaii</option>' +
			'<option value="Idaho">Idaho</option>' +
			'<option value="Illinois">Illinois</option>' +
			'<option value="Indiana">Indiana</option>' +
			'<option value="Iowa">Iowa</option>' +
			'<option value="Kansas">Kansas</option>' +
			'<option value="Kentucky">Kentucky</option>' +
			'<option value="Louisiana">Louisiana</option>' +
			'<option value="Maine">Maine</option>' +
			'<option value="Maryland">Maryland</option>' +
			'<option value="Massachusetts">Massachusetts</option>' +
			'<option value="Michigan">Michigan</option>' +
			'<option value="Minnesota">Minnesota</option>' +
			'<option value="Mississippi">Mississippi</option>' +
			'<option value="Missouri">Missouri</option>' +
			'<option value="Montana">Montana</option>' +
			'<option value="Nebraska">Nebraska</option>' +
			'<option value="Nevada">Nevada</option>' +
			'<option value="New Hampshire">New Hampshire</option>' +
			'<option value="New Jersey">New Jersey</option>' +
			'<option value="New Mexico">New Mexico</option>' +
			'<option value="New York">New York</option>' +
			'<option value="North Carolina">North Carolina</option>' +
			'<option value="North Dakota">North Dakota</option>' +
			'<option value="Ohio">Ohio</option>' +
			'<option value="Oklahoma">Oklahoma</option>' +
			'<option value="Oregon">Oregon</option>' +
			'<option value="Pennsylvania">Pennsylvania</option>' +
			'<option value="Rhode Island">Rhode Island</option>' +
			'<option value="South Carolina">South Carolina</option>' +
			'<option value="South Dakota">South Dakota</option>' +
			'<option value="Tennessee">Tennessee</option>' +
			'<option value="Texas">Texas</option>' +
			'<option value="Utah">Utah</option>' +
			'<option value="Vermont">Vermont</option>' +
			'<option value="Virginia">Virginia</option>' +
			'<option value="Washington">Washington</option>' +
			'<option value="West Virginia">West Virginia</option>' +
			'<option value="Wisconsin">Wisconsin</option>' +
			'<option value="Wyoming">Wyoming</option>' +
			
		'</select><br>' +
		'<label class="list">Exiry:</label>' +
		'<input class="inputs"  type="month" name="expDate" min="1970-1" max="2020-12" value="2019-04"><br>' +
		'<input class="inputs"  type="image" src="images/PayNow.png" alt="Submit" width="86px" height="38px">' +
	'</form>'
;

var paypalForm = 
	'<form id="pForm" onSubmit="return validateForm();">' +
		'<label class="list">Email Address:</label>' +
		'<input class="inputs"  type="text" name="ppEmail" required><br>' +
		'<label class="list">Password:</label>' +
		'<input class="inputs"  type="password" name="password" required><br>' +
		'<input type="image" src="images/PayNow.png" alt="Submit" width="86px" height="38px">' +
	'</form>';
/*Functions*/

function testLength(value, length, exactLength) {
	if (exactLength){
		if (value.length == length){
			return true;
		}
		else {
			alert("Not the correct length");
			return false;
		}
	}
	else if (!exactLength) {
		if (value.length >= length) {
			return true;
		}
		else {
			alert("Please use more digits")
			return false;
		}
	}
}
function testNumber(value) {
	if (isNaN(value)){
		return true;
	}
	else {
		alert("Please enter a number");
		return false;
	};
}
function updateForm(control) {
	if (control == 'ccForm'){
		document.getElementById("paymentinfo").innerHTML = creditForm;
	}
	if (control == 'ppForm'){
		document.getElementById("paymentinfo").innerHTML = paypalForm;
	}
}
function validateControl(control, name, length) {
	var exactLength = 0;
	if (name == "Zip"){
		exactLength = 5;
	}
	if (name == "Zip"){
		exactLength = 5;
	}
	if (testLength(control, length) && testNumber(control) {
		return true;
	}
}
function validateCreditCard(value) {
	var size = false;
	var number = false;
	var digit = false;
	value = value.replace(/\s/g, '');
	var firstDigit = value.valueOf(0);
	
	if (testLength(value, 15) || testLength(value, 16)) {
		this.size = true;
	}
	if (testNumber(value)) {
		this.number = true;
	}
	if (firstDigit == 3 || firstDigit == 4 || firstDigit == 5 || firstDigit == 6) {
		digit = true;
	}
	if (size && number && digit) {
		return true;
	}
}
function validateDate(value){
	if (value > (Date()+2.628e+15){
		return true;
	}
	else {
		return false;
	}
}

function validateEmail(value){
	var test = value.search(/@/i);
	if (test >=1){
		return true;
	}
	else{
		return false;
	}
}
function validatePassword(value, minLength){
	if (value.length >= minLength){
		return true;
	}
	else {
		return false;
	}
}
function validateState(control) {
	if (control == ""){
		return false;
	}
	else return true;
}
function validateForm() {
	var fname = document.forms["pForm"]["firstName"].value;
	var lname = document.forms["pForm"]["lastName"].value;
	var addr = document.forms["pForm"]["address"].value;
	var city = document.forms["pForm"]["city"].value;
	var zip = document.forms["pForm"]["zipcode"].value;
	var ccEmail = document.forms["pForm"]["ccEmail"].value;
	var noC = document.forms["pForm"]["nameOnCard"].value;
	var cN = document.forms["pForm"]["cardNumber"].value;
	var cvc = document.forms["pForm"]["cvc"].value;
	var stateDrop = document.getElementById("state");
	var state = stateDrop.options[stateDrop.selectedIndex].value;
	var expiry = document.forms["pForm"]["expDate"].value;
	var ccform = document.getElementById("ccRadio").checked;
	var ppEmail = document.forms["pForm"]["ppEmail"];
	var pass = document.forms["pForm"]["password"];
	
	if (ccform){
		var a = validateControl(zip, Zip, 5);
		var b = validateControl(cvc, CVC, 3);
		var c = validateState(state);
		var d = validateEmail(ccEmail);
		var e = validateCreditCard(cN);
		if (a && b && c && d && e){
			alert("Payment Submitted");
		}
		else if (!a){
			alert("Zip Code is invalid");
		}
		else if (!b){
			alert("CVV2/CVC Code is invalid");
		}
		else if (!c){
			alert("Please enter a State");
		}
		else if (!d){
			alert("Email is invalid");
		}
		else if (!e){
			alert("Credit Card Number is invalid");
		}
	}
	else{
		var x = validateEmail(ppEmail);
		var y = validatePassword(pass);
		
		if (x && y){
			alert("Payment Submitted");
		}
		else if(!x){
			alert("Email is invalid");
		}
		else if(!y){
			alert("Password is Invalid");
		}
	}

}
