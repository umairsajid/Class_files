window.onload = startUp;
var myVar = setInterval(function () {myTimer()}, 1000);

function startUp(){	
	var firstValue = document.getElementById('first');
	var secondValue = document.getElementById('second');
	var sign = document.getElementById('sign');
	var answer = document.getElementById('submit');

	firstValue.innerHTML = newNumber();
	secondValue.innerHTML = newNumber();	
	sign.innerHTML = newSign();
	answer.onclick = subtract;
};

function myTimer() {
    var time = document.getElementById('time');
    
    if (time.innerHTML !== '0')   	
    	time.innerHTML = time.innerHTML - 1;
    else{
    	var correct = document.getElementById('correct');
    	alert('You got ' + correct.innerHTML + ' correct!!')
    	clearInterval(myVar);
    }     	    
};

function newNumber(){
	 return Math.floor((Math.random() * 100) + 1);	
};

function newSign(){
	var symbol = Math.floor((Math.random() * 2) + 1);
	if (symbol === 1)
		return '+';
	else
		return '-';
};

function subtract(){
	var firstValue = document.getElementById('first');
	var secondValue = document.getElementById('second');
	var sign = document.getElementById('sign');
	var correct = document.getElementById('correct');
	var userAnswer = document.getElementById('answer');

	if (sign.innerHTML === '-')
		var actualAnswer = parseInt(firstValue.innerHTML) - parseInt(secondValue.innerHTML);
	else 
		var actualAnswer = parseInt(firstValue.innerHTML) + parseInt(secondValue.innerHTML);
	
	if (parseInt(userAnswer.value) == actualAnswer){
		correct.innerHTML = parseInt(correct.innerHTML) + 1;
		firstValue.innerHTML = newNumber();
		secondValue.innerHTML = newNumber();
		sign.innerHTML = newSign();
		userAnswer.style.backgroundColor = 'white';
		userAnswer.value = '';
	}
	else{
		userAnswer.style.backgroundColor = 'red';	
	}
};