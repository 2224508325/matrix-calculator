package linalg;

/*** A class that represents a multidimensional real-valued (double) vector
 *   and supports various vector computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, <YOUR_EMAIL>
 *
 */
public class Vector {

	public int _nDim;       // Dimension of the Vector; nomenclature: _ for data member, n for integer
	public double[] _adVal; // Contents of the Vector; nomenclature: _ for data member, a for array, d for double

	/** Constructor: allocates space for a new vector of dimension dim
	 * 
	 * @param dim
	 * @throws LinAlgException if vector dimension is < 1
	 */
	public Vector(int dim) throws LinAlgException {
		if (dim <= 0)
			throw new LinAlgException("Vector dimension " + dim + " cannot be less than 1");
		_nDim = dim;
		_adVal = new double[dim]; // Entries will be automatically initialized to 0.0
	}
	
	/** Copy constructor: makes a new copy of an existing Vector v
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param v
	 */
	public Vector(Vector v) {
		_nDim = v._nDim;
		_adVal = new double[_nDim]; // This allocates an array of size _nDim
		for (int index = 0; index < _nDim; index++)
			_adVal[index] = v._adVal[index];
	}

	/** Constructor: creates a new Vector with dimension and values given by init
	 * 
	 * @param init: a String formatted like "[ -1.2 2.0 3.1 5.8 ]" (must start with [ and end with ])
	 * @throws LinAlgException if init is not properly formatted (missing [ or ], or improperly formatted number)
	 */
	public Vector(String init) throws LinAlgException {
		
		// The following says split init on whitespace (\\s) into an array of Strings
		String[] split = init.split("\\s");  
		// Uncomment the following to see what split produces
		 //for (int i = 0; i < split.length; i++)
		 //	System.out.println(i + ". " + split[i]);

		if (!split[0].equals("[") || !split[split.length-1].equals("]"))
			throw new LinAlgException("Malformed vector initialization: missing [ or ] in " + init);

		// We don't count the [ and ] in the dimensionality
		_nDim = split.length - 2;
		_adVal = new double[_nDim];
		
		// Parse each number from init and add it to the Vector in order (note the +1 offset to account for [)
		for (int index = 0; index < _nDim; index++) {
			try {
				set(index, Double.parseDouble(split[index + 1]));
			} catch (NumberFormatException e) {
				throw new LinAlgException("Malformed vector initialization: could not parse " + split[index + 1] + " in " + init);
			}
		}
	}

	/** Overrides method toString() on Object: converts the class to a human readable String
	 * 
	 *  Note 1: this is invoked *automatically* when the object is listed where a String is expected,
	 *          e.g., "System.out.println(v);" is actually equivalent to "System.out.println(v.toString());"       
	 *          
	 *  Note 2: for debugging purposes, you should always define a toString() method on a class you define
	 */
	@Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
	public String toString() {
		// We could just repeatedly append to an existing String, but that copies the String each
		// time, whereas a StringBuilder simply appends new characters to the end of the String
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < _nDim; i++)
			sb.append(String.format(" %6.3f ", _adVal[i])); // Append each vector value in order
		sb.append(" ]");
		return sb.toString();
	}

	/** Overrides address equality check on Object: allows semantic equality testing of vectors,
	 *  i.e., here we say two objects are equal iff they have the same dimensions and values
	 *        match at all indices
	 * 
	 * Note: you should almost always define equals() since the default equals() on Object simply
	 *       tests that two objects occupy the same space in memory (are actually the same instance), 
	 *       but does not test that two objects may be different instances but have the same content
	 *       
	 * @param o the object to compare to
	 */
	@Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector)o; // This is called a cast (or downcast)... we can do it since we
			                      // know from the if statement that o is actually of subtype Vector
			if (_nDim != v._nDim)
				return false; // Two Vectors cannot be equal if they don't have the same dimension
			for (int index = 0; index < _nDim; index++)
				if (_adVal[index] != v._adVal[index])
					return false; // If two Vectors mismatch at any index, they are not equal
			return true; // Everything matched... objects are equal!
		} else // if we get here "(o instanceof Vector)" was false
			return false; // Two objects cannot be equal if they don't have the same class type
	}
	
	/** Get the dimension of this vector
	 * 
	 * @return: the dimensionality of this Vector
	 */
	public int getDim() {
		// TODO (this should not return -1!)
		return _nDim; // this return the dimension of the vector stored in the constructor 
	}

	/** Returns the value of this vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @return
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
	public double get(int index) throws LinAlgException {
		// TODO (this should not return -1.0!)
		//int[][] Vector = new int[0][1];
		//for (int i = 0; i <= index;i++) {
		if (index > _nDim) throw new LinAlgException("array index is out of bounds"); // exception is made when dimension in v is smaller than the index
		return _adVal[index]; // return the variable in the certain index
		//}
	}

	/** Set the value val of the vector at the given index (remember: array indices start at 0)
	 * 
	 * @param index
	 * @param val
	 * @throws LinAlgException if array index is out of bounds (see throw examples above)
	 */
	public void set(int index, double val) throws LinAlgException {
		// TODO
		//for (int i = 0; i < index; i++) {
		//	if (i == index) {
		if (index > _nDim) throw new LinAlgException("array index is out of bounds");
				_adVal[index] = val; // change the value in _adval array to val with index
			}
		//}
	//}

	/** This adds a scalar d to all elements of *this* Vector
	 *  ... (should modify *this*). The following is merely a representation of 
	 *  ... how this method should behave. 
	 *	a => [[1,2,3],[4,5,6]]
	 *  a.scalarAddInPlace(1)
	 * 	a => [[2,3,4],[5,6,7]] 
	 * 
	 * @param d
	 */
	public void scalarAddInPlace(double d) {
		
		for (int index = 0; index < _nDim; index++)
			_adVal[index] += d; //a for loop is made to add up each value in the array with d
	}
	
	/** This creates a new Vector with the same dimention of *this*, 
	 *  ... and copy all element of *this* to new vector object. Add scalar to every element in new vector
	 *  ... and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return new Vector after scalar addition
	 */
	public Vector scalarAdd(double d) {
		// TODO (this should not return null!)
		
		Vector v = new Vector(this); // make a copy of the original vector
		v.scalarAddInPlace(d); // modify the function with scalarAddInPlace
		return v; //output vector
		
		
		//return null;
	}
	
	/** This multiplies a scalar d by all elements of *this* Vector
	 *  (should modify *this*)
	 * 
	 * @param d
	 */
	public void scalarMultInPlace(double d) {
		// TODO
		for (int index = 0; index < _nDim; index++) 
			_adVal[index] *= d; //make a for loop where each value in the index times value d
	}
	
	/** This creates a new Vector, multiplies it by a scalar d, and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return new Vector after scalar addition
	 */
	public Vector scalarMult(double d) {
		// TODO (this should not return null!)
		Vector v = new Vector(this); //make a copy with the vector
		v.scalarMultInPlace(d);
		return v; // return the modification of the copy of the vector
	}

	/** Performs an elementwise addition of v to *this*, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public void elementwiseAddInPlace(Vector v) throws LinAlgException {
		// TODO
		if ( v._nDim != _nDim) throw new LinAlgException("dimensions of the two operand vectors do not match");
		for (int i = 0; i < _nDim; i++) {
			_adVal[i] += v._adVal[i];
		}//if the dimension of vector v is not equal to the dimension stored in the constructor, the exception happens
		//make a for loop in range 0 to _ndim, every index of the _adVal array is added by the value in v in the same index
		
		
	}

	/** Performs an elementwise addition of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public Vector elementwiseAdd(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
		if ( v._nDim != _nDim) throw new LinAlgException("dimensions of the two operand vectors do not match");
		this.elementwiseAddInPlace(v);
		return this; // return the modify version of the vector
		
		
		//return null;
	}
	
	/** Performs an elementwise multiplication of *this* and v, modifies *this*
	 * 
	 * @param v
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public void elementwiseMultInPlace(Vector v) throws LinAlgException {
		// TODO
		if ( v._nDim != _nDim) throw new LinAlgException("dimensions of the two operand vectors do not match");
		for (int i = 0;i<_nDim;i++) {
			this._adVal[i] *= v._adVal[i];
		}
	}// make a for loop in range 0 to _nDim
	//each value of index of _adVal is multiply by value of vector v 

	/** Performs an elementwise multiplication of *this* and v and returns a new Vector with result
	 * 
	 * @param v
	 * @return
	 * @throws LinAlgException if dimensions of the two operand vectors do not match
	 */
	public Vector elementwiseMult(Vector v) throws LinAlgException {
		// TODO (this should not return null!)
		if ( v._nDim != _nDim) throw new LinAlgException("dimensions of the two operand vectors do not match");
		this.elementwiseMultInPlace(v);
		return this;
	}//use the multiply method to return the new version of the vector in the consructor
	/** Performs an inner product of Vectors v1 and v2 and returns the scalar result
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @throws LinAlgException
	 */
	public static double InnerProd(Vector v1, Vector v2) throws LinAlgException {
		// TODO (this should not return -1.0!)
		if ( v1._nDim!= v2._nDim) throw new LinAlgException("dimensions of the two operand vectors do not match");
		double total = 0;
		v1.elementwiseMult(v2);
		for (int i = 0; i < v1._nDim; i++) {
			total += v1._adVal[i];
		}
		return total;
	}
}// multiply the two function with the multiply method, then add each index with a for loop in range from 0 to _nDim, and output the sumed value
