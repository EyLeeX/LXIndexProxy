package mfw.index.search.service.dto;

import lombok.Getter;
import lombok.Setter;
import mtime.lark.net.rpc.util.PageInfo;
import mtime.lark.pb.FieldType;
import mtime.lark.pb.annotation.ProtoField;
import mtime.lark.pb.annotation.ProtoMessage;

/**
 * Dto
 * mscgenVersion: 0.4.4
 */
public class SearchDto {

    private static final String OBJECT_TYPE = "objectType: ";
    private static final String KEYWORD = "keyword: ";
    private static final String ITEMS = "items: ";
    private static final String FACETS = "facets: ";
    private static final String GROUPS = "groups: ";
    private static final String TOTAL_COUNT = "totalCount: ";
    private static final String SCROLL_ID = "scrollId: ";

    private SearchDto() {

    }

    /**
     * Search 请求参数
     */
    @Setter
    @Getter
    @ProtoMessage(description = "Search 请求参数")
    public static class SearchRequest {
        /**
         * 搜索对象类型
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = true, description = "搜索对象类型")
        private String objectType;
        /**
         * 搜索关键词
         */
        @ProtoField(order = 2, type = FieldType.STRING, required = false, description = "搜索关键词")
        private String keyword;
        /**
         * 分页信息
         */
        @ProtoField(order = 3, type = FieldType.OBJECT, required = false, description = "分页信息")
        private PageInfo pageInfo;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +=OBJECT_TYPE + this.objectType + "\n";
			s +=KEYWORD + this.keyword + "\n";
			s +="pageInfo: " + this.pageInfo + "\n";
			return s;

		}
    }


    /**
     * Search 响应结果
     */
    @Setter
    @Getter
    @ProtoMessage(description = "Search 响应结果")
    public static class SearchResponse {
        /**
         * 搜索结果，以 JSON 数组表示
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = false, description = "搜索结果，以 JSON 数组表示")
        private String items;
        /**
         * 聚合结果，以 JSON 数组表示
         */
        @ProtoField(order = 2, type = FieldType.STRING, required = false, description = "聚合结果，以 JSON 数组表示")
        private String facets;
        /**
         * 分组结果，以 JSON 数组表示
         */
        @ProtoField(order = 3, type = FieldType.STRING, required = false, description = "分组结果，以 JSON 数组表示")
        private String groups;
        /**
         * 总记录数
         */
        @ProtoField(order = 4, type = FieldType.INT32, required = false, description = "总记录数")
        private int totalCount;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +=ITEMS + this.items + "\n";
			s +=FACETS + this.facets + "\n";
			s +=GROUPS + this.groups + "\n";
			s +=TOTAL_COUNT + this.totalCount + "\n";
			return s;

		}
    }


    /**
     * ScrollSearch 请求参数
     */
    @Setter
    @Getter
    @ProtoMessage(description = "ScrollSearch 请求参数")
    public static class ScrollSearchRequest {
        /**
         * 搜索对象类型
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = true, description = "搜索对象类型")
        private String objectType;
        /**
         * 搜索关键词
         */
        @ProtoField(order = 2, type = FieldType.STRING, required = false, description = "搜索关键词")
        private String keyword;
        /**
         * pageSize
         */
        @ProtoField(order = 3, type = FieldType.INT32, required = false, description = "pageSize")
        private int pageSize;
        /**
         * pageSize
         */
        @ProtoField(order = 4, type = FieldType.STRING, required = false, description = "pageSize")
        private String scrollId;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +=OBJECT_TYPE + this.objectType + "\n";
			s +=KEYWORD + this.keyword + "\n";
			s +="pageSize: " + this.pageSize + "\n";
			s +=SCROLL_ID + this.scrollId + "\n";
			return s;

		}
    }


    /**
     * ScrollSearch 响应结果
     */
    @Setter
    @Getter
    @ProtoMessage(description = "ScrollSearch 响应结果")
    public static class ScrollSearchResponse {
        /**
         * 搜索结果，以 JSON 数组表示
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = false, description = "搜索结果，以 JSON 数组表示")
        private String items;
        /**
         * 聚合结果，以 JSON 数组表示
         */
        @ProtoField(order = 2, type = FieldType.STRING, required = false, description = "聚合结果，以 JSON 数组表示")
        private String facets;
        /**
         * 分组结果，以 JSON 数组表示
         */
        @ProtoField(order = 3, type = FieldType.STRING, required = false, description = "分组结果，以 JSON 数组表示")
        private String groups;
        /**
         * 总记录数
         */
        @ProtoField(order = 4, type = FieldType.INT32, required = false, description = "总记录数")
        private int totalCount;
        /**
         * pageSize
         */
        @ProtoField(order = 5, type = FieldType.STRING, required = false, description = "pageSize")
        private String scrollId;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +=ITEMS + this.items + "\n";
			s +=FACETS + this.facets + "\n";
			s +=GROUPS + this.groups + "\n";
			s +=TOTAL_COUNT + this.totalCount + "\n";
			s +=SCROLL_ID + this.scrollId + "\n";
			return s;

		}
    }


    /**
     * Index 请求参数
     */
    @Setter
    @Getter
    @ProtoMessage(description = "Index 请求参数")
    public static class IndexRequest {
        /**
         * 建立索引对象串
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = true, description = "建立索引对象串")
        private String indexMsgJson;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +="indexMsgJson: " + this.indexMsgJson + "\n";
			return s;

		}
    }


    /**
     * CloseScroll 请求参数
     */
    @Setter
    @Getter
    @ProtoMessage(description = "CloseScroll 请求参数")
    public static class CloseScrollRequest {
        /**
         * 释放指定scroll
         */
        @ProtoField(order = 1, type = FieldType.STRING, required = true, description = "释放指定scroll")
        private String scrollId;
        /**
         * 指定集群
         */
        @ProtoField(order = 2, type = FieldType.STRING, required = true, description = "指定集群")
        private String clusterRouter;

		@Override
		public String toString() {
			String s = getClass().getSimpleName() + ":\n";
			s +=SCROLL_ID + this.scrollId + "\n";
			s +="clusterRouter: " + this.clusterRouter + "\n";
			return s;

		}
    }



}