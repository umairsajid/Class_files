/** returns a random value between 0.0 and 1.0 */
public class RandomEvalFunction extends EvalFunction
{
    public double computeValue(Node node)
    {
    	return Math.random();
    }// computeValue method

}// RandomEvalFunction class
