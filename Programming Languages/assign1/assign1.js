var duple = function (n, x){
	if (isZero(n)){
		return [];
	}
	else{		
		return cons(x, duple(sub1(n), x));
	}
}


var reverse = function (list){
	return reverse_helper([], list)
}


var reverse_helper = function (newList, oldList){
	if(isNull(oldList)){
		return newList
	}
	else{
		return reverse_helper(cons(car(oldList), newList), cdr(oldList));
	}
}


var flatten = function (list){
		return reverse(flatten_helper([], list));
}


var flatten_helper = function(newList, oldList){
	if(isNull(oldList)){
		return newList;
	}
	else if (isList(car(oldList))){
	 	return flatten_helper(flatten_helper(newList, car(oldList)), cdr(oldList));
	}
	else{
		return flatten_helper(cons(car(oldList), newList),(cdr(oldList)));
	}
}


var down = function (l){
	if (isNull(l)){
		return [];
	}
	else{
		return cons(cons(car(l), []), down(cdr(l)));
	}
}


var up = function (l){
	return reverse(up_helper([], l));
}


var up_helper = function (newList, oldList){
	if (isNull(oldList)){
		return newList
	}
	else if(isNull(car(oldList)) && isList(car(oldList))){
		return up_helper(newList, cdr(oldList));
	}
	else if (isList(car(oldList))){
		return up_helper(cons(car(car(oldList)), newList) ,cons(cdr(car(oldList)), cdr(oldList)));
	}
	else{
		return up_helper(cons(car(oldList), newList), cdr(oldList));
	}
}


var path = function (n, bst){
	if (isEq(n, car(bst))){
		return []; 
	}
	else if (isLess(n, car(bst))){
		return cons(0, path(n, car(cdr(bst))));
	}
	else{
		return cons(1, path(n, car(cdr(cdr(bst)))));
	}
}