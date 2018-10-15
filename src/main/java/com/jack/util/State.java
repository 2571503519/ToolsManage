package com.jack.util;

import com.jack.exception.NotFoundCodeException;
import lombok.Getter;

/**
 * Created by Jackaroo Zhang on 2018/10/11.
 */
public class State {

    public enum AdminState {
        NORMAL(2), FORBID(1), DELETE(0);
        @Getter private Integer stateCode;
        AdminState(Integer stateCode) {
            this.stateCode = stateCode;
        }

        public static AdminState codeOf(Integer stateCode) {
            if (stateCode == 2) return NORMAL;
            if (stateCode == 1) return FORBID;
            if (stateCode == 0) return DELETE;
            else throw new NotFoundCodeException();
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
