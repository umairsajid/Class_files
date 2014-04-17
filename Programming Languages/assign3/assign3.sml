(*	Sterling Kohel  *)
(*	assign3.sml		*)
(*	March 10, 2014	*)

(*----------Problem 1--------------------------------------*)
datatype 'ingredient pizza =
    Bottom
    | Topping of ('ingredient * ('ingredient pizza));

datatype fish =
    Anchovy
    | Shark
    | Tuna;

val my_pizza = Topping(Tuna, Topping(Shark, Topping(Anchovy, Bottom)));

fun rem_anchovy Bottom = Bottom
    | rem_anchovy (Topping(Anchovy,p)) = rem_anchovy(p)
    | rem_anchovy (Topping(t,p)) = Topping(t, rem_anchovy(p));

rem_anchovy my_pizza;

fun rem_ingredient ing = let
	fun rem_ing Bottom = Bottom
   		 | rem_ing (Topping(t,p)) = 
			if ing = t
			then rem_ing(p)
			else Topping(t, rem_ing(p))
	in 
		rem_ing
	end;

(*----------Problem 2--------------------------------------*)
datatype tagged = 
	Int of int
	| Real of real
	| Bool of bool
	| String of string;

exception RunTimeTypeError;

fun dynamic_checked_add(Int m, Int n):tagged = 
		let 
			val Int num1 =  Int m
			val Int num2 =  Int n
		in
			Int (num1 + num2)
		end
	| dynamic_checked_add(Real m, Real n):tagged = 
		let
			val Real num1 = Real m
			val Real num2 = Real n
		in
			Real (num1 + num2)
		end
	| dynamic_checked_add(m:tagged, n:tagged):tagged = raise RunTimeTypeError;

fun dynamic_checked_and(Bool m, Bool n):tagged =
        let
                val Bool bool1 = Bool m
                val Bool bool2 = Bool n
        in
                Bool (bool1 andalso bool2)
        end
	| dynamic_checked_and(m:tagged, n:tagged):tagged = raise RunTimeTypeError;

fun dynamic_checked_concatenate(String m, String n):tagged = 
		let
			val String s1 = String m
			val String s2 = String n
		in
			String (s1 ^ s2)
		end
	| dynamic_checked_concatenate(m:tagged, n:tagged):tagged = raise RunTimeTypeError;
			
		 
(*----------Problem 3--------------------------------------*)
fun power_set_helper(a, nil) = nil
		| power_set_helper (a, lst) = (a :: hd lst) :: power_set_helper(a, tl lst);

(*----------Problem 4--------------------------------------*)
fun power_set(nil) = [nil]
     |   power_set(x::xs) =
             power_set(xs)@power_set_helper(x,power_set(xs));


(*----------Problem 5--------------------------------------*)
datatype FORMULA = 	
ATOMIC of string
| AND of FORMULA * FORMULA
| OR of FORMULA * FORMULA
| NOT of FORMULA;

fun inList(x, []) = false
	| inList(x,L) =
		if (hd L) = x
			then true
		else inList(x,tl L); 

fun eval (ATOMIC(x), L) = (inList(x, L)) 
	| eval (AND (x,y), L) = (eval (x, L)) andalso (eval(y,L)) 
	| eval (OR (x,y), L) = (eval (x, L)) orelse (eval (y,L)) 
	| eval (NOT (x), L) = not (eval(x, L));

(*----------Problem 6--------------------------------------*) 
fun check_formula(ATOMIC(x)) = "neither"
	| check_formula(OR(x,y)) = if check_formula(x) = check_formula(y)  
		then "tautology" 
		else "neither"
	| check_formula(AND(x,y)) = if check_formula(x) = check_formula(y) 
		then "contradiction" 
		else "neither"
	| check_formula(NOT(x)) = "neither";