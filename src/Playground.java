import java.util.PriorityQueue;

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
                for (int i = 0; i < field2.getTiles().length; i++){
                    for (int j = 0; j < field1.getTiles()[i].length; j++){
                        mergedTiles[i + field1.getTiles().length][j] = field2.getTiles()[i][j];
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
}
