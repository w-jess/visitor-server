package com.cf.visitor.facade.dto;

import com.cf.support.result.PageRequest;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2022/11/28
 */
@Data
@Accessors(chain = true)
public class ReserveRecordsDTO extends PageRequest {
	private Long userId;
}
