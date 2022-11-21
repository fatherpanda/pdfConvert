package com.dipol.pdfconvert.data.repository.impl;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.repository.custom.Aa02CustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import static com.dipol.pdfconvert.data.entity.qdsl.QAa02.aa02;
import java.util.List;

@Repository
public class Aa02RepositoryImpl extends QuerydslRepositorySupport implements Aa02CustomRepository {
    private final JPAQueryFactory queryFactory;

    public Aa02RepositoryImpl(JPAQueryFactory queryFactory) {
        super(Aa02.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Aa02> selectByGubn(String gubn) {
        return from(aa02).where(aa02.aa02PK().gubn.eq(gubn)).fetch();
    }
}
