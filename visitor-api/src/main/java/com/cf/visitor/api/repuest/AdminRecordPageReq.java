package com.cf.visitor.api.repuest;

import com.cf.support.result.PageRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author whx
 * @date 2022/11/23
 */
@Data
@Accessors(chain = true)
public class AdminRecordPageReq extends PageRequest {
	Integer type;

	String name;

	String depName;

	Date reserveDate;

	Integer state;
}
