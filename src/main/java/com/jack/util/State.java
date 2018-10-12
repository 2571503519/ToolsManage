package com.jack.util;

/**
 * Created by Jackaroo Zhang on 2018/10/11.
 */
public class State {

    public enum AdminState {
        NORMAL(2), FORBID(1), DELETE(0);
        private Integer stateCode;
        AdminState(Integer stateCode) {
            this.stateCode = stateCode;
        }
    }

    public enum CommonState {
        NORMAL(2), FORBID(1), DELETE(0);
        private Integer stateCode;
        CommonState(Integer stateCode) {
            this.stateCode = stateCode;
        }
    }

}
