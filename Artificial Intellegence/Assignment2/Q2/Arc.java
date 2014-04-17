
/**
 * Class to represent an arc within a binary NE constraint
 * Every constraint gets two arcs, one in each direction.
 */
public class Arc {
    private NEConstraint constraint;
    private Variable source, destination;

    public Arc(Variable source, Variable destination, NEConstraint c){
        this.source = source;
        this.destination = destination;
        constraint = c;
    }

    public Variable getSource(){
    	return source;
    }// getSource method

    public Variable getDestination(){
    	return destination;
    }// getDestination method

    public NEConstraint getConstraint(){
	    	return constraint;
    }// getDestination method

}