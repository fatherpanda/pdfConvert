package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.repository.custom.Da01CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class Da01RepositoryImpl extends QuerydslRepositorySupport implements Da01CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Da01RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Da01.class);
        this.queryFactory = queryFactory;
    }
}
