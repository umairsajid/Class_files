// Problem 1
var SUBREC = "^f x y.((("+IF+" ("+ISZERO+" y)) x) ((f ("+PRED+" x)) ("+PRED+" y)))";
var Y_SUB = "("+Y+" "+SUBREC+")";
console.log(eval_and_prettify("(("+Y_SUB+" "+FIVE+") "+THREE+")"));

// Problem 2
var LE = "^f x y.("+ISZERO+" (("+SUB+" x) y))";
var Y_LE = "("+Y+" "+LE+")";
console.log(eval_and_prettify("(("+Y_LE+" "+FIVE+") "+THREE+")"));
console.log(eval_and_prettify("(("+Y_LE+" "+THREE+") "+FIVE+")"));


// Problem 3
var QUOTIENT = "^f x y.((("+IF+" (("+Y_LE+" x) ("+PRED+" y))) "+ZERO+") ("+SUCC+" ((f (("+Y_SUB+" x) y)) y)))";
var Y_QUO = "("+Y+" "+QUOTIENT+")";
console.log(eval_and_prettify("(("+Y_QUO+" "+THREE+") "+TWO+")"));


// Problem 4

var Y_in_JS = function (F) {
    return (function (x) {
        return F(function (y) { return (x(x))(y);});
    })
    (function (x) {
        return F(function (y) { return (x(x))(y);});
    }) ;
} ;

var FactGen = function (fact) {
    return (function(n) {
        return ((n == 0) ? 1 : (n*fact(n-1))) ;
    });
} ;

console.log((Y_in_JS(FactGen))(6));


var GCD_gen = function (GCD){
        return (function (a) { 
            return (function(b){
                return((b==0) ? a:GCD(b)(a%b))
            });
        });
};

var FibGen = function (fib){
		return (function (a) { 
			if (a == 0 || a == 1){
				return 1;
			}
		    else{ 
				return (fib(a-1) + fib(a-2));
            }
		});
}

console.log((Y_in_JS(FibGen))(14));



console.log(((Y_in_JS(GCD_gen))(21))(7)) ;
console.log(((Y_in_JS(GCD_gen))(23))(7)) ;

// Problem 5

// lex_depth_expr receives an abstract syntax tree from our lambda
// calculus interpreter and return the corresponding expression in
// lexical address form

var lex_depth_expr = function (ast) {
  // Complete the function here
};

var test5 = lex_depth_expr(parser.parse("(((^x y1 z1.((x y1) z1) ^a.a) ^b.b) ^c.c)"));
require('util').puts(require('util').inspect(test5, false, 20, true));

