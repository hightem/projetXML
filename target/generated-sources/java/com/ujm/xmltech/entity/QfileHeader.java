package com.ujm.xmltech.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;


/**
 * QfileHeader is a Querydsl query type for fileHeader
 */
public class QfileHeader extends EntityPathBase<fileHeader> {

    private static final long serialVersionUID = -325712912;

    public static final QfileHeader fileHeader = new QfileHeader("fileHeader");

    public final NumberPath<Integer> CtrlSum = createNumber("CtrlSum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath MsgId = createString("MsgId");

    public final StringPath NbOfTxs = createString("NbOfTxs");

    public final StringPath nomCreancier = createString("nomCreancier");

    public QfileHeader(String variable) {
        super(fileHeader.class, forVariable(variable));
    }

    public QfileHeader(BeanPath<? extends fileHeader> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QfileHeader(PathMetadata<?> metadata) {
        super(fileHeader.class, metadata);
    }

}

