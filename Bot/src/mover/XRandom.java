package mover;

import java.util.Random;

/**
 * A small extension of java.util.Random, providing a couple
 * of handy methods Random is missing.
 * 
 * @author ackley
 * @version 1.0
 */
public class XRandom extends Random {
       public static final long serialVersionUID = 1;

    /**
     * Create an XRandom initialized with a randomly-chosen seed, so results
     * will tend to differ from run to run even if nothing else changes.
     */
    public XRandom() { super(); }

    /**
     * Create an XRandom initialized with the specified seed, so results
     * will remain identical from run to run -- so long as the same seed is
     * used, and all other random objects and methods (such as Math.random())
     * are scrupulously avoided.
     * @param seed the seed to initialize the generator with.
     */
    public XRandom(long seed) { super(seed); }
    
    /**
     * Draw a sample from an exponentially distributed random variable
     * with the given mean value.  
     *
     * @param mean the specified average value of the produced numbers
     * @return a sample drawn from exponentially distributed random variable
     *  with average value 'mean'
     *  @throws IllegalArgumentException if the mean is equal to zero
     */
    public double nextExponential(double mean) {
        if (mean == 0.0) 
            throw new IllegalArgumentException("Zero mean illegal");
        double lambda = 1.0/mean;
        double u = 0.0;
        while (u == 0.0) u = nextDouble();
        return (-1.0/lambda)*Math.log(u);
    }

    /**
     * Return true at random the specified fraction of the time.  Setting 
     * probability to 0.5 makes this method act the same as nextBoolean().
     * Setting it to 0.0 makes this method always return false, setting it
     * to 1.0 makes it always return true.  
     * @param probability the chance of returning true
     * @return true with the given probability, false with 1-probability
     * @throws IllegalArgumentException if probability is less than 0.0 or 
     * greater than 1.0
     * 
     */
    public boolean nextProbability(double probability) {
        if (probability < 0 || probability > 1.0)
            throw new IllegalArgumentException("Bad probability");
        return nextDouble() <= probability;
    }

}
