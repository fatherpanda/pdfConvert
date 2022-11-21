package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.repository.custom.Ba04CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.dipol.pdfconvert.data.entity.qdsl.QBa04.ba04;

@Repository
public class Ba04RepositoryImpl extends QuerydslRepositorySupport implements Ba04CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Ba04RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Ba04.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<String> getReportVersion() {
        return from(ba04).select(ba04.ba04PK().reportVersion).groupBy(ba04.ba04PK().reportVersion).fetch();
    }

    @Override
    public List<Ba04> getListByReportVersiton(String reportVersion) {

        return from(ba04).where(ba04.ba04PK().reportVersion.eq(reportVersion)).orderBy(ba04.sortOrder.asc()).fetch();
    }
}
