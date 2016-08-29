import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class DataGenerator
{
	/** Program name. */
	protected static final String progName = "DataGenerator";
	
	/** Start of integer range to generate values from. */
	protected Double mSampleDensity;
	
	/**
	 * Constructor.
	 * 
	 * @param sampleDensity Define what percentage of edges are generated.
	 * @throws IllegalArgumentException if Density is unsable (not a percentage).
	 */
	public DataGenerator(int sampleDensity) throws IllegalArgumentException {
		
		if (sampleDensity < 0 || sampleDensity > 100) {
			throw new IllegalArgumentException("Sample density is invalid.");
		}
		double percent = sampleDensity / 100.0;
		/**
		* Divide by two as all friendhsips are mutual (bi-directional)
		* Otherwise SampleDensity is twice requested amount.
		*/
		mSampleDensity = (percent/2);
		
	} // end of DataGenerator()

	
	/**
	 * Generate 'sampleSize' number of samples, using sampling with replacement.
	 * 
	 * @param sampleSize Number of samples to generate.
	 */
	public String[] createFriendSample(int sampleSize) {
		
		String[] samples = new String[sampleSize];
		int rowEdges = (int) (mSampleDensity*sampleSize);
		int currentRow = 0;
		
		//Populate 'picker'
		int[] range = new int[sampleSize];
		for (int i = 0; i < sampleSize; i++) {
			range[i] = i;
		}		
		
		while (currentRow < sampleSize) {
			StringBuilder sb = new StringBuilder();
			shuffleArray(range);
			for (int i = 0; i < rowEdges; i++) {
				sb.append(currentRow + " " + String.valueOf(range[i]) + "\n");
			}
			samples[currentRow] = sb.toString();
			currentRow++;			
		}
		return samples;
	}
	// Implementing Fisher Yates shuffle
	  static void shuffleArray(int[] ar)
	  {
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	/**
	 * Error message.
	 */
	public static void usage() {
		System.err.println(progName + ": <sample density (percentage)> <sample size>");
		System.exit(1);
	} // end of usage()
	
	
	/**
	 * Main method.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// check correct number of command line arguments
		if (args.length != 2) {
			usage();
		}
		
		PrintWriter printWriter = null;
		try
        {				    	
	    	// percentage edge 'density'
			int edgeDensity = Integer.parseInt(args[0]);
			
			// number of values to sample
			int sampleSize = Integer.parseInt(args[1]);
			
			DataGenerator gen = new DataGenerator(edgeDensity);

			File testfile = new File("testing/d"+edgeDensity+"_s"+sampleSize+".in");
			testfile.createNewFile();
			printWriter = new PrintWriter(testfile);
			
			String[] samples = null;			
			samples = gen.createFriendSample(sampleSize);
			
			// print out samples
			if (samples != null) {
				for (int i = 0; i < samples.length; i++) {
					printWriter.print(samples[i]);
				}
			}
	    	
        }
        catch (FileNotFoundException e)
        {
			System.err.println(e.getMessage());
			usage();
        }
        finally
        {
            if ( printWriter != null ) 
            {
                printWriter.close();
            }
        }

		
	} // end of main()
} // end of class DataGenerator
