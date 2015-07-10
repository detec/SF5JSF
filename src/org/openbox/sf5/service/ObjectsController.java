package org.openbox.sf5.service;







public class ObjectsController {

    private ObjectService Service = new ObjectServiceImpl();
  //  private ObservableList<Transponders> List = FXCollections.observableArrayList();

    public void add(Object obj){
    	Service.add(obj);
   }

    public void update(Object obj){
        Service.update(obj);
   } 
    
    public void remove(Class<?> clazz, long id)     {
        Service.remove(clazz, id);
   }
    
   public void saveOrUpdate(Object obj) {
	   Service.saveOrUpdate(obj);
   }
   
   
   public Object select(Class<?> clazz, long id) {
	   return Service.select(clazz, id);
   }
    
}
