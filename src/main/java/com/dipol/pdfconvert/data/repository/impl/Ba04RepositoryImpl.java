package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.repository.custom.Ba04CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class Ba04RepositoryImpl extends QuerydslRepositorySupport implements Ba04CustomRepository{
    private final JPAQueryFactory queryFactory;

    public Ba04RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Ba04.class);
        this.queryFactory = queryFactory;
    }
}
