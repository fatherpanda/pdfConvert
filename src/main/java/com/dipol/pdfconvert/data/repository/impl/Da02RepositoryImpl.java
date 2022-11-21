package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Da02;
import com.dipol.pdfconvert.data.repository.custom.Da02CustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import static com.dipol.pdfconvert.data.entity.qdsl.QDa02.da02;

import java.util.List;

@Repository
public class Da02RepositoryImpl extends QuerydslRepositorySupport implements Da02CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Da02RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Da02.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Da02> selectDa02(Pageable pageable, Da02 da02Param) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!da02Param.getDa02PK().getAccNo().equals("")) {
            builder.and(da02.da02PK().accNo.contains(da02Param.getDa02PK().getAccNo()));
        }
        if (da02Param.getDa02PK().getEventNo() != null) {
            builder.and(da02.da02PK().eventNo.eq(da02Param.getDa02PK().getEventNo()));
        }
        JPQLQuery<Da02> query = from(da02).where(builder).orderBy(da02.da02PK().eventNo.asc(), da02.sortOrder.asc());
        List<Da02> da02List = getQuerydsl().applyPagination(pageable, query).fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(da02List, pageable, totalCount);
    }

    @Override
    public List<Integer> selectEventNo() {
        return from(da02).select(da02.da02PK().eventNo.as("eventNo")).groupBy(da02.da02PK().eventNo)
                .orderBy(da02.da02PK().eventNo.asc()).fetch();
    }
}
