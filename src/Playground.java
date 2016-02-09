import java.util.PriorityQueue;

/**
 * Created by Zopo on 09.02.2016.
 */
public class Playground {
    private int _width;
    private int _height;
    private boolean[][] _field;
    private Binary2dField _calculatedField;

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
    
    public void start() {
    	_calculatedField = divide(_field, 0, 0);
    	System.out.println("Count: " + count);
    }
    public int count = 0;
    Binary2dField divide(boolean[][] fieldArray, int topLeftX, int topLeftY) {
    	int height = fieldArray.length;
    	int width = fieldArray[0].length;
    	
    	 if (height == 1 && width == 1) {
    		 Binary2dField newField = new Binary2dField();
    		 PriorityQueue<Move> defaultMove = new PriorityQueue<>();
    		 if (fieldArray[0][0] == true) {
    			 defaultMove.add(new PaintLine(topLeftY, topLeftX, topLeftY, topLeftX));
    			 ++count;
    		 }
    		 newField.setMoves(defaultMove);
    		 newField.setTiles(fieldArray);
    		 return newField;
    	 }
    	
    	Direction direction;
    	Binary2dField field1, field2;
		if(height > width) {
    		direction = Direction.VERTICAL;
    		int midwayPoint = height/2;
    		boolean[][] fieldArrayHalf1 = new boolean[midwayPoint][width];
    		boolean[][] fieldArrayHalf2 = new boolean[height - midwayPoint][width];
    		
    		
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
    		
    		field1 = divide(fieldArrayHalf1, topLeftX, topLeftY);
    		field2 = divide(fieldArrayHalf2, topLeftX, topLeftY + (height - midwayPoint));
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
    		
    		field1 = divide(fieldArrayHalf1, topLeftX, topLeftY);
    		field2 = divide(fieldArrayHalf2, topLeftX + (width - midwayPoint), topLeftY);
    	}
    	
    	return merge(field1, field2, direction);
    }
    
    Binary2dField merge(Binary2dField field1, Binary2dField field2, Direction direction) {
        Binary2dField newField = new Binary2dField();
        boolean[][] mergedTiles = {};

        //Append tiles
        switch (direction){
            case HORIZONTAL:
                //initialize new Tileset with new size
                mergedTiles = new boolean[field1.getTiles().length][field1.getTiles()[0].length + field2.getTiles()[0].length];

                //fill the new Tileset
                for (int i = 0; i < field1.getTiles().length; i++){
                    //Copy field1 tiles into new Tileset
                    for (int j = 0; j < field1.getTiles()[i].length; j++){
                        mergedTiles[i][j] = field1.getTiles()[i][j];
                    }

                    //Copy field2 tiles into new Tileset
                    for (int j = 0; j < field2.getTiles()[i].length; j++){
                        mergedTiles[i][j + field1.getTiles()[i].length] = field2.getTiles()[i][j];
                    }
                }
                break;

            case VERTICAL:
                //initialize new Tileset with new size
                mergedTiles = new boolean[field1.getTiles().length + field2.getTiles().length][field1.getTiles()[0].length];

                //fill the new Tileset
                //Copy field1 tiles into new Tileset
                for (int i = 0; i < field1.getTiles().length; i++){
                    for (int j = 0; j < field1.getTiles()[i].length; j++){
                        mergedTiles[i][j] = field1.getTiles()[i][j];
                    }
                }

                //Copy field2 tiles into new Tileset
//                for (int i = 0; i < field2.getTiles().length; i++){
//                    for (int j = 0; j < field1.getTiles()[i].length; j++){
//                        mergedTiles[i + field1.getTiles().length][j] = field2.getTiles()[i][j];
//                    }
//                }
                for (int i = field1.getTiles().length; i < field1.getTiles().length + field2.getTiles().length; ++i) {
                	for (int j = 0; j < field2.getTiles()[i - field1.getTiles().length].length; ++j) {
                		mergedTiles[i][j] = field2.getTiles()[i - field1.getTiles().length][j];
                	}
                }
                break;
        }
        newField.setTiles(mergedTiles);

        //Append moves
        PriorityQueue<Move> mergedMoves = new PriorityQueue<>();
        mergedMoves.addAll(field1.getMoves());
        mergedMoves.addAll(field2.getMoves());
        newField.setMoves(mergedMoves);

        //optimize moves
        newField.optimizeMoves();

    	return newField;
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
    
    public Binary2dField getCalculatedField() {
    	return _calculatedField;
    }
}
