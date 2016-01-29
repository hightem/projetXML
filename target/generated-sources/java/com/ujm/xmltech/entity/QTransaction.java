package com.ujm.xmltech.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = -392116521;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final StringPath endToEndId = createString("endToEndId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTransaction(String variable) {
        super(Transaction.class, forVariable(variable));
    }

    public QTransaction(BeanPath<? extends Transaction> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QTransaction(PathMetadata<?> metadata) {
        super(Transaction.class, metadata);
    }

}

