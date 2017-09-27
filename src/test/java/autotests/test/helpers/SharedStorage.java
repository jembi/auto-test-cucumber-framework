package autotests.test.helpers;

import java.util.HashMap;

public class SharedStorage {
    private static SharedStorage myself = new SharedStorage();

    private static HashMap<String,Object> sharedData = new HashMap<String, Object>();

    private SharedStorage(){ }

    /**
     * Shared storage is setup as threadsafe by following the singleton pattern.
     * @return : The only instantiatable instance of SharedStorage.
     */
    public static SharedStorage getInstance(){
        if(myself == null){
            myself = new SharedStorage();
        }

        return myself;
    }

    public void clearSharedStorage(){
        myself = new SharedStorage();
    }

    public Object getData(String key){
        return sharedData.get(key);
    }

    public void addData(String key, Object value){
        sharedData.put(key,value);
    }
}
