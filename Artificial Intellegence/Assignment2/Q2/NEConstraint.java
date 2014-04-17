/** Represents a binary constraint of the form: A != B */
public class NEConstraint{
      
        private Variable source; // one of the variables involved in the constraint
        private Variable destination; // the other variable involved 

        NEConstraint(Variable source, Variable destination){
            this.source = source;
            this.destination = destination;
            
            //Add arcs to each variable:
            Arc srcDest = new Arc(source,destination,this);
            Arc destSrc = new Arc(destination,source,this);
            source.addOutgoing(srcDest);
            destination.addIncoming( srcDest );
            source.addIncoming(destSrc);
            destination.addOutgoing(destSrc);           
            
        }
    
        public Variable getSource(){
            return source;
        }
    
        public Variable getDestination(){
            return destination;
        }
        
        public String toString(){
            return source.toString() + " -> " +destination.toString();
        }// toString method
}// NEConstraint class