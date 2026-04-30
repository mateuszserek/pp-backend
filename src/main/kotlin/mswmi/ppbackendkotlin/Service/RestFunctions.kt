package mswmi.ppbackendkotlin.Service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

object RestFunctions {
    public fun calculateNextQuery(pageNum: Int, pageSize: Int) : String{
        val nextPageSkip = pageNum + 1
        val nextQuery = "?pageNum=${nextPageSkip}&pageSize=$pageSize"
        return nextQuery
    }

    fun getPageableObject(pageNum: Int, pageSize: Int): PageRequest {
        return PageRequest.of(
            pageNum,
            pageSize,
            Sort.by(Sort.Direction.ASC, "id")
        )
    }
}