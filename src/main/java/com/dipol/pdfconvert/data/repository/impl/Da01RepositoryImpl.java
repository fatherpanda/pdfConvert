package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Aa01;
import com.dipol.pdfconvert.data.repository.custom.Aa01CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class Aa01RepositoryImpl extends QuerydslRepositorySupport implements Aa01CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Aa01RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Aa01.class);
        this.queryFactory = queryFactory;
    }
}
