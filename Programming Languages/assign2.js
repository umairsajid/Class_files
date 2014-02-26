/*Sterling Kohel
  Programming Languages
  Assignment 2*/


var make_rn_func = function(seed){
	var oldSeed = seed;
	var printResult = function (seed, newNum){
		return "The function is seeded with " + seed + " returns " + newNum;
	}	
	var result = function (num){
		return (((num*9)+5)%1024);
	}
	return function() {oldSeed = result(oldSeed); return printResult(seed, oldSeed);};
}

var isPrime = function(num){
	var isPrime_helper = function(counter, num){
		if (isEq((num%counter), 0) && !isEq(num, 2)){
			return false;
		}
		else if (isGreater((counter * counter), num) || isEq(num, 2)){
			return true;
		}
		else{
			return isPrime_helper(add1(counter), num);
		}
	}
	return isPrime_helper(2, num);
}

var xeroxPrime = function(list){
	if (isNull(list)){
		return [];
	}
	else if (isPrime(car(list)) || isEq(car(list), 2)){
		return cons(car(list), cons(car(list), xeroxPrime(cdr(list))));
	}
	else{
		return cons(car(list), xeroxPrime(cdr(list)));
	}
}

var xeroxPrime2 = function(list){
	return underscore.sortBy(underscore.difference(underscore.flatten(underscore.zip(list,
		    underscore.filter(list, function(x){if (isPrime(x)) return x;}))), [undefined]), function (num) {return num;});
}

var evalPoly = function(list, base){
	return underscore.reduce(list, function(sum, num) {return sum * base + num;}, 0);
}

var path = function (n, bst){
	var path_helper = function (n, bst, ret){
		if (isNull(bst)){
			return n + " is not in this tree";
		}
		if (isEq(n, car(bst))){
			return ret([]); 
		}
		else if (isLess(n, car(bst))){
			return path_helper(n, car(cdr(bst)), function (t) { return ret(cons(0, t));});
		}
		else{
			return path_helper(n, car(cdr(cdr(bst))), function (t) { return ret(cons(1, t));});
		}
	}
	return path_helper (n, bst, function(x) { return x;});
}

var analyze_paths = function (treeList, algorithm){
	var analyze_paths_helper = function (n, bst, ret){	
 		if (isEq(n, car(bst))){
			return ret(0);
		}
		else if (isLess(n, car(bst))){
			return analyze_paths_helper(n, car(cdr(bst)), function (t) {return ret(t+1);});
		}
		else{
			return analyze_paths_helper(n, car(cdr(cdr(bst))), function (t) {return ret(t+1);});
		}
	}
	var find_all_lengths = function (list, nums){
	 	if(isNull(list)){
	 		return nums([]);
	 	}
	  	return find_all_lengths(cdr(list), function(t) {return nums(cons(analyze_paths_helper(car(car(list)), car(cdr(car(list))), function (x) {return x;}), t))});
	}
	return underscore.reduce(find_all_lengths(treeList, function (x) {return x;}), algorithm);
}