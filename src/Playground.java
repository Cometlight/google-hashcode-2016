/**
 * Created by Zopo on 09.02.2016.
 */
public class Playground {
    private int _width;
    private int _height;
    private boolean[][] _field;

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
