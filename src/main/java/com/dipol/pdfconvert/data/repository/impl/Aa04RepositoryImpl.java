package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.entity.Aa04;
import com.dipol.pdfconvert.data.repository.custom.Aa04CustomRepository;
import com.dipol.pdfconvert.data.repository.custom.Ba02CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class Aa04RepositoryImpl extends QuerydslRepositorySupport implements Aa04CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Aa04RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Aa04.class);
        this.queryFactory = queryFactory;
    }
}
