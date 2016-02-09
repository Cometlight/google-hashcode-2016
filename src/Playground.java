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
    	int height = fieldArray.length;
    	int width = fieldArray[0].length;
    	
    	 if (height == 1 && width == 1) {
    		 return null;
//    		 return new Binary2dField( value );
    	 }
    	
    	Direction direction;
    	Binary2dField field1, field2;
		if(height > width) {
    		direction = Direction.VERTICAL;
//    		int arrSize = height * width;
    		int midwayPoint = height/2;
    		boolean[][] fieldArrayHalf1 = new boolean[midwayPoint][width];
    		boolean[][] fieldArrayHalf2 = new boolean[height - midwayPoint][width];
    		
    		
//    		System.arraycopy(fieldArray, 0, fieldArrayHalf1, 0, arrSize/2);
//    		System.arraycopy(fieldArray, arrSize/2, fieldArrayHalf2, 0, arrSize - arrSize/2);
    		for(int i = 0; i < midwayPoint; ++i) {
    			for(int j = 0; j < width; ++j) {
    				fieldArrayHalf1[i][j] = fieldArray[i][j];
    			}
    		}
    		for(int i = height - midwayPoint; i < height; ++i) {
    			for(int j = 0; j < width; ++j) {
    				fieldArrayHalf2[i - (height - midwayPoint)][j] = fieldArray[i][j];
    			}
    		}
    		
    		field1 = divide(fieldArrayHalf1);
    		field2 = divide(fieldArrayHalf2);
    	} else {
    		direction = Direction.HORIZONTAL;
    		
    		int midwayPoint = width/2;
    		boolean[][] fieldArrayHalf1 = new boolean[height][midwayPoint];
    		boolean[][] fieldArrayHalf2 = new boolean[height][width - midwayPoint];
    		
    		for(int i = 0; i < height; ++i) {
    			for(int j = 0; j < midwayPoint; ++j) {
    				fieldArrayHalf1[i][j] = fieldArray[i][j];
    			}
    		}
    		for(int i = 0; i < height; ++i) {
    			for(int j = width - midwayPoint; j < width; ++j) {
    				fieldArrayHalf2[i][j - (width - midwayPoint)] = fieldArray[i][j];
    			}
    		}
    		
    		field1 = divide(fieldArrayHalf1);
    		field2 = divide(fieldArrayHalf2);
    	}
    	
    	return merge(field1, field2, direction);
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
