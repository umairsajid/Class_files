//Sterling Kohel
//Assignment 7


function Sequence(generator) {
    this.thunk = generator;
    var memo = {};
    // Call when we want the first n in the sequence
    this.take = function (n) {
        var value;
        var a = [];
               
        for (i = 0; i < n; i++) {
            if (i in memo){
                a.push(memo[i]);
            }
            else{
                value = this.thunk();
                a.push(value);
                memo[i] = value;
            }
        }
        return a;
    };

    this.getMemo = function() {
	return memo;
    }

    this.map = function (n) {
        return new Sequence(function () {return n(generator());});
    };

    this.filter = function(n){
        var value;
        return new Sequence(function () {
            value = generator();
            while (!n(value))
                value = generator();
            return value;});      
    };
}

fib = function() {
    var x = 1;
    var y = 1;
    return function() {
    var prev = x;
    x = y;
    y += prev;
    return prev;
    };
};


ints_from1 = function() {
    var x = 1;
    return function() {
        var temp = x;
        x = x + 1;
        return temp;
    };
};

ints_from = function(start) {
    return function() {
        var s = start;
        return function() {
            var temp = s;
            s = s + 1;
            return temp;
        };
    };
};

iterates_from = function(start,func){
    return function(){
        var value = start;
        return function(){
            var temp = value;
            value = func(value);
            return temp;
        };
    };
};

interleave = function(x, y) {
	ctr = { };
 	ctr.key = true;
	xMemo = x.getMemo();
	yMemo = y.getMemo();
	count = { };
	count.key = 0;
	fun = function(ctr,count,xMemo,yMemo) {
		if(ctr.key)
		{
			ctr.key = false;
			count.key++;
			return xMemo[count.key-1];
		}	
		else
		{	
			ctr.key = true;
			return yMemo[count.key-1];
		}
	}
	return new Sequence(function() {return fun(ctr,count,xMemo,yMemo);});
};



a = new Sequence( function() { return Math.random(); } );
b = new Sequence( fib() );
c = b.map(function(x) {return x+1;});
c.take(10);

b = new Sequence( fib() );
b.take(10);
b.take(20);


y = new Sequence( ints_from1() ).filter(function (x) { return x % 2 === 0; });
y.take(10);

powers_of_two = iterates_from(1, function(x){return 2 * x;});
q = new Sequence(powers_of_two());
q.take(10);

h5 = iterates_from(5, function(n){
    if (n % 2 === 0)
        return  n / 2;
    else return n * 3 + 1});
hailstone5 = new Sequence(h5()); 
hailstone5.take(10);
h11 = iterates_from(11, function(n){
    if (n % 2 === 0)
        return  n / 2;
    else return n * 3 + 1});
hailstone11 = new Sequence(h11());
hailstone11.take(10);

interleave(hailstone11,hailstone5).take(20);
