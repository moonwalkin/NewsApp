package com.example.newsapp.data.mapper

import com.example.newsapp.domain.models.ArticleDomain

interface Mapper<T, E> {

    fun mapDataToDomain(toDomain: T): E

    fun mapDomainToData(toData: E): T
}