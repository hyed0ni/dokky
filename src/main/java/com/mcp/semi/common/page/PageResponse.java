package com.mcp.semi.common.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
	private List<T> items;
	private int currentPage;
	private int totalPage;
	private int startPage;
	private int endPage;
}
