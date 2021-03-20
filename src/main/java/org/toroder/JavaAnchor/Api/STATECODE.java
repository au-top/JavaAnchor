package org.toroder.JavaAnchor.Api;

/**

 * @author autopMateBook
 */
public enum STATECODE {
/*
* 2000~3999 Success Type State code
* 4000~5999 Failed Type State Code
* */
    RES_OK(2000),
    RES_ERROR_PATH_FIND_NOT(4001);

    public  int code;
    STATECODE(int code){
        this.code=code;
    }
}