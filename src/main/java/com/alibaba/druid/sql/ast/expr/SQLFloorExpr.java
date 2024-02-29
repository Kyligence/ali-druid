/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLExprImpl;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.util.Objects;

public class SQLFloorExpr extends SQLExprImpl implements SQLValuableExpr {

    private String value;

    private final String timeUnit;

    private final boolean needQuote;

    private SQLExpr castSqlExpr;

    public SQLFloorExpr(String value, String timeUnit, boolean needQuote, SQLExpr castSqlExpr) {
        this.value = value;
        this.timeUnit = timeUnit;
        this.needQuote = needQuote;
        this.castSqlExpr = castSqlExpr;
    }

    public SQLFloorExpr(String value, String timeUnit, boolean needQuote) {
        this.timeUnit = timeUnit;
        this.value = value;
        this.needQuote = needQuote;
    }

    public SQLFloorExpr(SQLExpr sqlExpr, String timeUnit) {
        this.castSqlExpr = sqlExpr;
        this.timeUnit = timeUnit;
        this.needQuote = false;
    }

    public SQLFloorExpr(String value) {
        this(value, null, true);
    }

    public boolean isNeedQuote() {
        return needQuote;
    }

    public SQLExpr getCastSqlExpr() {
        return castSqlExpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SQLFloorExpr that = (SQLFloorExpr) o;
        return Objects.equals(value, that.value) && Objects.equals(timeUnit, that.timeUnit) && Objects.equals(castSqlExpr, that.castSqlExpr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, timeUnit, castSqlExpr);
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    @Override
    protected void accept0(SQLASTVisitor v) {
        v.visit(this);
        v.endVisit(this);
    }

    @Override
    public SQLExpr clone() {
        return new SQLFloorExpr(value, timeUnit, needQuote, castSqlExpr);
    }

    @Override
    public String getValue() {
        return value;
    }
}
