package activity;

import Utils.UtilsLog;

public class TestBean {
    String name;
    private TestBean() {

    }
    private void  setBean(String name) {
       this.name=name;
    }
    public String get() {
        UtilsLog.i("zhm","name=="+name);
        return name;
    }

}
