/**
 * Created by Zopo on 09.02.2016.
 */
public class Playground {
    private int _width;
    private int _height;
    private boolean[][] _field;

    static enum Direction {
    	HORIZONTAL, VERTICAL;
    }
    
    /*
     *  CONSTRUCTOR
     */


    /*
     *  METHODS
     */
    public void printField(){
        StringBuilder fieldAsString = new StringBuilder();
        for (int i = 0; i < _field.length; i++){
            for (int j = 0; j < _field[i].length; j++){
                if (_field[i][j] == true){
                    fieldAsString.append('#');
                } else {
                    fieldAsString.append('.');
                }
            }
            fieldAsString.append("\n");
        }
        System.out.println(fieldAsString.toString());
    }
    
    Binary2dField divide(boolean[][] fieldArray) {
    	// if (fieldArray len == 1)
    	// return new Binary2dField( value );
    	
    	int length = fieldArray.length;
//    	int width = fieldArray[0].wid
//    	if(fieldArray.)
    	return null;
    }
    
    Binary2dField merge(Binary2dField field1, Binary2dField field2, Direction direction) {
    	return null;
    }

    /*
     *  GETTER AND SETTER
     */
    public int getWidth() {
        return _width;
    }

    public void setWidth(int width) {
        _width = width;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int height) {
        _height = height;
    }

    public boolean[][] getField() {
        return _field;
    }

    public void setField(boolean[][] field) {
        _field = field;
    }
}
