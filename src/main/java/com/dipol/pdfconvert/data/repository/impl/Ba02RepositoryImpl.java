package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Ba02;
import com.dipol.pdfconvert.data.repository.custom.Ba02CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class Ba02RepositoryImpl extends QuerydslRepositorySupport implements Ba02CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Ba02RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Ba02.class);
        this.queryFactory = queryFactory;
    }
}
