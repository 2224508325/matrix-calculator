package linalg;

/*** A class that represents a two dimensional real-valued (double) matrix
 *   and supports various matrix computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, <YOUR_EMAIL>
 *
 */
public class Matrix {

	public int _nRows; // Number of rows in this matrix; nomenclature: _ for data member, n for integer
	public int _nCols; // Number of columns in this matrix; nomenclature: _ for data member, n for integer
	// TODO: add your own data member to represent the matrix content
	//       you could use a 2D array, or an array of Vectors (e.g., for each row)
	public double _Rvalue[][];// initialize a 2D array to store matrix value
	//public double[] _Cvalue[];
	
	
	/** Create a new matrix of the given row and column dimensions
	 * 
	 * @param rows
	 * @param cols
	 * @throws LinAlgException if either rows or cols is <= 0
	 */
	public Matrix(int rows, int cols) throws LinAlgException {
		// TODO: hint: see the corresponding Vector constructor
		if (rows <= 0) {
			throw new LinAlgException("Rows dimension " + rows + " cannot be less than 1");
		}
		if (cols <= 0) {
			throw new LinAlgException("Cols dimension " + rows + " cannot be less than 1");
		}
		_nRows = rows;//input parameter 'rows' equal to _nRows
		_nCols = cols;//input parameter 'cols' equal to _nCols
		_Rvalue = new double[rows][cols];//set the index on how rows and cols correspond to the index of the 2d array
		//_Cvalue[cols] = new double[cols];
	}
	
	/** Copy constructor: makes a new copy of an existing Matrix m
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param m
	 */
	public Matrix(Matrix m) {
		// TODO: hint: see the corresponding Vector "copy constructor" for an example
		_nRows = m._nRows;
		_nCols = m._nCols;
		_Rvalue = new double[_nRows][_nCols];
		//_Cvalue[_nCols] = new double[_nCols];
		for (int i = 0; i < _nRows;i++) {
			for (int k = 0; k < _nCols;k++) {
			_Rvalue[i][k] = m._Rvalue[i][k];
		}
		}
		//for (int i = 0; i < _nCols;i++) {
		//	_Cvalue[i] = m._Cvalue[i];
		//}
		
	}

	/** Constructs a String representation of this Matrix
	 * 
	 */
	public String toString() {
		// Optional: hint: see Vector.toString() for an example
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < _nRows; i++) {
			sb.append("[");
			for (int k = 0; k < _nCols; k++) {
				sb.append(String.format(" %6.3f ", _Rvalue[i][k]));
			}
			sb.append(" ] \n");
		}
		
	return sb.toString();
	}

	/** Tests whether another Object o (most often a matrix) is a equal to *this*
	 *  (i.e., are the dimensions the same and all elements equal each other?)
	 * 
	 * @param o the object to compare to
	 */
	public boolean equals(Object o) {
		// Optional: hint: see Vector.equals(), you can also use Vector.equals() for checking equality 
		//             of row vectors if you store your matrix as an array of Vectors for rows
		
		// Optional: this should not always return false!
		if (o instanceof Matrix) {
			Matrix m = (Matrix)o;
			if (_nRows != m._nRows)
				return false;
			if (_nCols != m._nCols)
				return false;
			for (int i = 0; i < _nRows; i++) {
			for (int index = 0; index < _nCols; index++)
				try {
					if (getRow(i)._adVal[index] != m.getRow(i)._adVal[index])
						return false;
				} catch (LinAlgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
		else return false; // This should not always return false!
	}
	
	/** Return the number of rows in this matrix
	 *   
	 * @return 
	 */
	public int getNumRows() {
		// TODO (this should not return -1!)
		return _nRows;
	}

	/** Return the number of columns in this matrix
	 *   
	 * @return 
	 */
	public int getNumCols() {
		// TODO (this should not return -1!)
		return _nCols;
	}

	/** Return the scalar value at the given row and column of the matrix
	 * 
	 * @param row
	 * @param col
	 * @return
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public double get(int row, int col) throws LinAlgException {
		// TODO (this should not return -1!)
		if ( col > _nCols) throw new LinAlgException("Colume indices are out of bounds");
		if ( row > _nRows) throw new LinAlgException("Row indices are out of bounds");

		double number = 0;
		for (int i = 0; i < _nRows; i++) {
			for (int k = 0; k < _nCols;k++) {
				if (i == row && k == col) {
					number = _Rvalue[i][k];
				}
			}
		}
		return number;
	}
	
	/** Return the Vector of numbers corresponding to the provided row index
	 * 
	 * @param row
	 * @return
	 * @throws LinAlgException if row is out of bounds
	 */
	public Vector getRow(int row) throws LinAlgException {
		// TODO (this should not return null!)
		
		if (row > _nRows) throw new LinAlgException("row is out of bounds");
		Matrix m = new Matrix(this);
		Vector v = new Vector(_nCols);
		//v._nDim = _nCols;
		for (int k = 0; k < _nRows; k ++) {
			if (k == row) {
		for (int i = 0;i < _nCols; i ++) {
			v._adVal[i]=m._Rvalue[k][i];
		}
		}
		}
		return v;
	}

	/** Set the row and col of this matrix to the provided val
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	public void set(int row, int col, double val) throws LinAlgException {
		// TODO
		//Matrix m = new Matrix(this);
		if ( col > _nCols) throw new LinAlgException("Colume indices are out of bounds");
		if ( row > _nRows) throw new LinAlgException("Row indices are out of bounds");
		//for (int i = 0; i < _nRows; i++) {
		//	for (int k = 0; k < _nCols;k++) {
		//		if (i == row && k == col) {
				 _Rvalue[row][col] = val;
		//		}
		//	}
		//}
					
			
				
			}
	
	/** Return a new Matrix that is the transpose of *this*, i.e., if "transpose"
	 *  is the transpose of Matrix m then for all row, col: transpose[row,col] = m[col,row]
	 *  (should not modify *this*)
	 * 
	 * @return
	 * @throws LinAlgException
	 */
	public Matrix transpose() throws LinAlgException {
		Matrix transpose = new Matrix(_nCols, _nRows);
		for (int row = 0; row < _nRows; row++) {
			for (int col = 0; col < _nCols; col++) {
				transpose.set(col, row, get(row,col));
			}
		}
		return transpose;
	}

	/** Return a new Matrix that is the square identity matrix (1's on diagonal, 0's elsewhere) 
	 *  with the number of rows, cols given by dim.  E.g., if dim = 3 then the returned matrix
	 *  would be the following:
	 *  
	 *  [ 1 0 0 ]
	 *  [ 0 1 0 ]
	 *  [ 0 0 1 ]
	 * 
	 * @param dim
	 * @return
	 * @throws LinAlgException if the dim is <= 0
	 */
	public static Matrix GetIdentity(int dim) throws LinAlgException {
		// TODO: this should not return null!
		if ( dim <= 0) throw new LinAlgException("dim cannot be less or equal to 0");
		
		Matrix m = new Matrix(dim, dim);
		m._nRows = dim;
		m._nCols = dim;
		for (int i = 0; i < dim;i++) {
			m._Rvalue[i][i] = 1;
		}
		return m;
		
	}
	
	/** Returns the Matrix result of multiplying Matrix m1 and m2
	 *  (look up the definition of matrix multiply if you don't remember it)
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 * @throws LinAlgException if m1 columns do not match the size of m2 rows
	 */
	public static Matrix Multiply(Matrix m1, Matrix m2) throws LinAlgException{
		// TODO: this should not return null!
		if (m1._nCols != m2._nRows){
			throw new LinAlgException("m1 columns and m2 rows does not match");
		}
		Matrix m = new Matrix(m1._nRows,m2._nCols);
		//m2.transpose();
		for (int i = 0; i < m1._nRows; i++) {
			for(int k = 0; k< m2._nCols; k++) {
				for(int g = 0; g < m1._nCols;g++) {
				m._Rvalue[i][k] += m1.getRow(i)._adVal[g] * m2.transpose().getRow(k)._adVal[g];
			}
			}
		}
		
		return m;
	}
		
	/** Returns the Vector result of multiplying Matrix m by Vector v (assuming v is a column vector)
	 * 
	 * @param m
	 * @param v
	 * @return
	 * @throws LinAlgException if m columns do match the size of v
	 */
	public static Vector Multiply(Matrix m, Vector v) throws LinAlgException {
		// TODO: this should not return null!
		if(m._nCols != v._nDim) {
			throw new LinAlgException("Cannot multiply matrix with " + m._nCols + " columns with a vector of dimensions " + v._nDim);
	}
	Vector V = new Vector(m._nRows);
	for(int i = 0; i < m._nRows; i++) {
		for(int k = 0; k < m._nCols; k++) {
			V._adVal[i] += m.getRow(i)._adVal[k] * v._adVal[k];
		}
	}
	return V;
	}	
}
