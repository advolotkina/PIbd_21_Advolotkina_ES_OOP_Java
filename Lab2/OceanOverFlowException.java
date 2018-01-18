/**
 * Created by Ekaterina Advolotkina on 17.01.2018.
 */
public class OceanOverFlowException extends Exception {
    public OceanOverFlowException(){
        super("В океане нет свободных мест");
    }
}
