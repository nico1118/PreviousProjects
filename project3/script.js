


//Calculate the Sum
function calcSUM(array) {
	var sum = 0.00;
	var i;
		for (i = 0; i < (array.length);i++){
		sum+= array[i];
	}
	return sum.toFixed(2);
}

//Calculate the mean
function calcMean(array) {
	var mean = calcSUM(array) / array.length;
	return mean.toFixed(2);
}

//Calculate the median (even or odd)
function calcMedian(array) {
	var median;
	var test = array.length % 2;
	var half = Math.floor(array.length / 2);
	debugger;
	if (test == 0) {
		
		var value1 = array[half];
		var value2 = array[half-1];
		    median = (value1 + value2) / 2;
		return median.toFixed(2);
	}
	else {
		median = array[half+1];
		return median.toFixed(2);
	}
}
//Finding the min
function findMin(array) {
	var min = 1000000;
	min = array[0].toFixed(2);
	debugger;
	return min;
}
//Finding the max
function findMax(array) {
	var max = array[array.length-1].toFixed(2);
	return max;
}

//Finding the variance
function calcVariance(array) {
	var vmean = calcMean(array);
	var vsum = 0.00;
	var vvariance = 0;
	var i;
	for ( i = 0; i < (array.length) ;i++){
		vsum+= Math.pow((array[i] - vmean), 2);
	}
	vvariance = vsum / (array.length);
	return vvariance.toFixed(2);
}

//Finding the STD DEV
function calcStdDev(array) {
	var svariance = calcVariance(array);
	var sstddev = Math.sqrt(svariance);
	return sstddev.toFixed(2);
}

//Finding the Mode
function calcMode(array) {

	var mode = [array[0]];
	var counter = 1;
	var maxCounter = 1;
	var i;
	for (i = 1; i<(array.length)+1;i++) {
		if (array[i] == array[i-1]){
			counter++;
			
		}
		else {
			console.log(mode + " Counter: " + counter + " Max: " + maxCounter)
			if (counter > maxCounter) {
			mode = [];
			mode.push(array[i-1]);
			maxCounter = counter;
			
			}
			else if (counter == maxCounter) {
			mode.push(array[i-1]);
			}
			counter = 1;
			
		}

	}
	return mode;
}

//Combining all functions and getting Input
function performStatistic() {
	//Getting the input and converting to an array with numbers
	var input = document.getElementById("input").value;
	var array = input.split(" ").map(Number);
	array.sort(function(a, b){return a - b});
	//Performing calculations
	//MIN
	document.getElementById("min").value = findMin(array);
	//MAX
	document.getElementById("max").value = findMax(array);
	//SUM
	document.getElementById("sum").value = calcSUM(array);
	//MEAN
	document.getElementById("mean").value = calcMean(array);
	//MEDIAN
	document.getElementById("median").value = calcMedian(array);
	//MODE
	document.getElementById("mode").value = calcMode(array);
	//VARIANCE
	document.getElementById("var").value = calcVariance(array);
	//STD DEV
	document.getElementById("stddev").value = calcStdDev(array);
	
	
	return false;
}









