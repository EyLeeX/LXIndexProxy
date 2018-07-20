package mfw.index.search.service.impl.mapper;

import mfw.index.search.service.dto.SearchDto;
import mfw.index.comm.dto.QueryResult;
import mfw.index.comm.util.Utils;

/**
 * Created by jinweile on 2016/1/22.
 */
public class SearchResponseMapper {


    private SearchResponseMapper(){}


	/**
	 * 搜索结果转换
	 * @param queryResult
	 * @return
	 */
    public static SearchDto.SearchResponse of(QueryResult queryResult){
        SearchDto.SearchResponse searchResponse = new SearchDto.SearchResponse();
        searchResponse.setFacets(Utils.toJson(queryResult.getFacetResult()));
        searchResponse.setItems(Utils.toJson(queryResult.getFqResult()));
        searchResponse.setGroups(Utils.toJson(queryResult.getGroupResult()));
        searchResponse.setTotalCount(queryResult.getCount());

        return searchResponse;
    }

    /**
     * 搜索结果转换
     * @param queryResult
     * @return
     */
    public static SearchDto.ScrollSearchResponse ofscroll(QueryResult queryResult){
        SearchDto.ScrollSearchResponse searchResponse = new SearchDto.ScrollSearchResponse();
        searchResponse.setFacets(Utils.toJson(queryResult.getFacetResult()));
        searchResponse.setItems(Utils.toJson(queryResult.getFqResult()));
        searchResponse.setTotalCount(queryResult.getCount());
        searchResponse.setGroups(Utils.toJson(queryResult.getGroupResult()));
        searchResponse.setScrollId(queryResult.getScrollId());
        return searchResponse;
    }

}
