package com.example.newsapp.data.mapper


interface Mapper<T, E> {

    fun mapDataToDomain(toDomain: T): E

    fun mapDomainToData(toData: E): T
}